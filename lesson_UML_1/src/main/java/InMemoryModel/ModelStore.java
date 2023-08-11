package InMemoryModel;

import ModelElements.*;
import Stuff.*;

import java.util.ArrayList;
import java.util.List;

public class ModelStore implements IModelChanger{
    public List<PoligonalModel> models;
    public List<Scene> scenes;
    public List<Flash> flashes;
    public List<Camera> cameras;
    private List<IModelChangedObserver> changedObserver;

    public ModelStore(List<PoligonalModel> models,
                      List<Scene> scenes,
                      List<Flash> flashes,
                      List<Camera> cameras,
                      List<IModelChangedObserver> changeObserver) throws Exception {
        this.models = new ArrayList<>();
        this.scenes = new ArrayList<>();
        this.flashes = new ArrayList<>();
        this.cameras = new ArrayList<>();
        models.add(new PoligonalModel(null));
        flashes.add(new Flash());
        cameras.add(new Camera());
        scenes.add(new Scene(0, models, flashes, cameras));
        this.changedObserver = changeObserver;
    }

    public Scene getScena(int id) {
        for (int i = 0; i < scenes.size(); i++) {
            if (scenes.get(i).id == id) {
                return scenes.get(i);
            }
        }
        return null;
    }

    @Override
    public void NotifyChange(IModelChanger modelChanger) {
    }
}
