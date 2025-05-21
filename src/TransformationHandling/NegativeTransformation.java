package TransformationHandling;

import ImageHandling.Image;
/**
 * Клас който имплементира Negative трансформация
 */
public class NegativeTransformation implements Transformation {
    @Override
    public void execute(Image image) {
        image.applyNegative();
    }
}