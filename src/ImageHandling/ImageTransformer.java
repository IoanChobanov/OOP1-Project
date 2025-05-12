package ImageHandling;

import java.util.List;

public class ImageTransformer {
    public static void applyTransformations(Image image, List<String> transformations) {
        for (String transformation : transformations) {
            applyTransformation(image, transformation);
        }
    }

    private static void applyTransformation(Image image, String transformation) {
        String[] parts = transformation.split("_", 2);
        String action = parts[0];
        String arguments = (parts.length > 1) ? parts[1] : "";

        switch (action) {
            case "grayscale":
                image.applyGrayscale();
                break;
            case "monochrome":
                image.applyMonochrome();
                break;
            case "negative":
                image.applyNegative();
                break;
            case "rotate":
                image.applyRotation(arguments);
                break;
            default:
                System.out.println("Unknown transformation: " + action);
        }
    }
}