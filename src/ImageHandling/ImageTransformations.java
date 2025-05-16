package ImageHandling;

import java.util.List;

/**
 * Клас, който прилага трансформациите от колекцията с трансформации върху дадено изображение.
 */
public class ImageTransformations {
    /**
     * Метод който минава през всички трансформации в колекцията от трансформации и ги прилага върху подаденото изображение.
     * @param image Изображението, върху което ще се прилагат трансформациите.
     * @param transformations Колекция, която съдържа трансформациите който ще се прилагат.
     */
    public static void applyTransformations(Image image, List<String> transformations) {
        for (String transformation : transformations) {
            applyTransformation(image, transformation);
        }
    }

    /**
     * Метод, който обработвата подадената трансформация и избира кой метод да извика според аргументите.
     * @param image Изображението върху, което ще се прилага трансформацията.
     * @param transformation Низ името на трансформацията.
     */
    private static void applyTransformation(Image image, String transformation) {
        String[] parts = transformation.split(" ", 2);
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