package TransformationHandling;

import ImageHandling.Image;

/**
 * Клас който имплементира Grayscale трансформация
 */
public class GrayscaleTransformation implements Transformation {
    @Override
    public void execute(Image image) {
        image.applyGrayscale();
    }
}
