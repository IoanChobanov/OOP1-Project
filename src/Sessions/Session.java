package Sessions;

import ImageHandling.Image;

import java.util.ArrayList;
import java.util.List;

public class Session {
    private static int sessionCounter = 0;
    private final int sessionId;
    private final List<Image> images;
    private final List<String> transformations;

    public Session(List<Image> images) {
        this.sessionId = ++sessionCounter;
        this.images = new ArrayList<>(images);
        this.transformations = new ArrayList<>();
    }

    public int getSessionId() {
        return sessionId;
    }

    public List<Image> getImages() {
        return images;
    }

    public void addTransformation(String transformation) {
        transformations.add(transformation);
    }

    public List<String> getTransformations() {
        return transformations;
    }

    public void removeLastTransformation() {
        if (!transformations.isEmpty()) {
            transformations.remove(transformations.size() - 1);
        }
    }
}

