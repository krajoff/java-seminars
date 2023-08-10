package InMemoryModel;

import ModelElements.*;
import Stuff.*;

import java.util.List;

public class ModelStore {
    public List<PoligonalModel> models;
    public List<Scene> scenes;
    public List<Flash> flashes;
    public List<Camera> cameras;
    private List<changeObserver> modelChangeObserver;

    public ModelStore(List<PoligonalModel> models, List<Scene> scenes, List<Flash> flashes, List<Camera> cameras, List<changeObserver> modelChangeObserver) {
        this.models = models;
        this.scenes = scenes;
        this.flashes = flashes;
        this.cameras = cameras;
        this.modelChangeObserver = modelChangeObserver;
    }

    public Scene getScena(int number){
        Scene scene = null;
        return scene;
    }
    public void NotifyChange(IModelChanger modelChanger){}
}
