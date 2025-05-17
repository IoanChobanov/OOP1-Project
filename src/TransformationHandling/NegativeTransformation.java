package TransformationHandling;

import ImageHandling.Image;

public class NegativeTransformation implements Transformation {
    @Override
    public void execute(Image image) {
        image.applyNegative();
    }
}