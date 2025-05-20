package TransformationHandling;

public class TransformationHandler {
    public static Transformation createTransformation(String transformationString) {
        String[] parts = transformationString.split(" ", 2);
        String action = parts[0];
        String argument = (parts.length > 1) ? parts[1] : "";

        switch (action) {
            case "grayscale":
                return new GrayscaleTransformation();
            case "monochrome":
                return new MonochromeTransformation();
            case "negative":
                return new NegativeTransformation();
            case "rotate":
                return new RotateTransformation(argument);
            default:
                throw new IllegalArgumentException("Unknown transformation: " + action);
        }
    }
}
