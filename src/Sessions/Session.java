package Sessions;

import java.util.ArrayList;
import java.util.List;

public class Session {
    private static int sessionCounter = 0;
    private final int sessionId;
    private final List<String> images;
    private final List<String> transformations;

    public Session(String initialImage) {
        this.sessionId = ++sessionCounter;
        this.images = new ArrayList<>();
        this.transformations = new ArrayList<>();
        this.images.add(initialImage);
    }

    public int getSessionId() {
        return sessionId;
    }

    public List<String> getImages() {
        return images;
    }

    public void addImage(String image) {
        images.add(image);
    }

    public void addTransformation(String transformation) {
        transformations.add(transformation);
    }

    public List<String> getTransformations() {
        return transformations;
    }
}

