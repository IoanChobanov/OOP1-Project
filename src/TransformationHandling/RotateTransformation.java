package TransformationHandling;

import ImageHandling.Image;
/**
 * Клас който имплементира Rotate трансформация
 */
public class RotateTransformation implements Transformation {
    private final String direction;

    public RotateTransformation(String direction) {
        this.direction = direction;
    }

    @Override
    public void execute(Image image) {
        image.applyRotation(direction);
    }
}
