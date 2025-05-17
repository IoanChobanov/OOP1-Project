package TransformationHandling;

import ImageHandling.Image;

public class GrayscaleTransformation implements Transformation {
    @Override
    public void execute(Image image) {
        image.applyGrayscale();
    }
}
