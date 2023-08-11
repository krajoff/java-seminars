package ModelElements;

import java.lang.reflect.Type;
import java.util.List;

public class Scene {
    public int id;
    public List<PoligonalModel> models;
    public List<Flash> flashes;
    public List<Camera> cameras;


    public Scene(int id, List<PoligonalModel> models, List<Flash> flashes, List<Camera> cameras) throws Exception {
        this.id = id;
        this.flashes = flashes;
        if (models.size() > 0) {
            this.models = models;
        } else {
            throw new Exception("Wrong model number");
        }
        if (cameras.size() > 0) {
            this.cameras = cameras;
        } else {
            throw new Exception("Wrong camera number");
        }
    }

    public <T> T method1(T flash) {
        return flash;
    }

    public <T, E> T method2(T flash, E camera) {
        return flash;
    }
}
