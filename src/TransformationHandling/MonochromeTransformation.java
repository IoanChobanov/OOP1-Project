package TransformationHandling;

import ImageHandling.Image;

/**
 * Клас който имплементира Monochrome трансформация
 */
public class MonochromeTransformation implements Transformation {
    @Override
    public void execute(Image image) {
        image.applyMonochrome();
    }
}
