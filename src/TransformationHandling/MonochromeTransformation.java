package TransformationHandling;

import ImageHandling.Image;

public class MonochromeTransformation implements Transformation {
    @Override
    public void execute(Image image) {
        image.applyMonochrome();
    }
}
