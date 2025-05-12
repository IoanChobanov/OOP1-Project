package Commands;

import Exceptions.CommandException;
import ImageHandling.Image;
import Sessions.Session;
import Sessions.SessionManager;

/**
 * Клас, който имплементира колаж между две изображения.
 */
public class CollageCommand implements CreateCommand{
    private final SessionManager sessionManager;

    public CollageCommand(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    /**
     * Метод, който валидира всичко свързано с изискванията за създаване на колаж.
     * @param args Аргументи подадени от главното меню.
     * @throws CommandException Обработва поредица по време на изпълнение.
     */
    @Override
    public void execute(String... args) throws CommandException {
        validateArguments(args);

        Session session = sessionManager.getValidatedActiveSession();
        String direction = args[0];
        String image1Name = args[1];
        String image2Name = args[2];
        String outImage = args[3];

        Image image1 = findImageInSession(session, image1Name);
        Image image2 = findImageInSession(session, image2Name);

        validateImageCompatibility(image1, image2, outImage);

        createAndAddCollage(session, direction, image1, image2, outImage);
    }

    /**
     * Метод, който валидира аргументите подадени от менюто.
     * @param args Аргументи подадени от менюто.
     * @throws CommandException Обработва грешки, в случай на неправилни аргументи.
     */
    private void validateArguments(String[] args) throws CommandException {
        if (args.length != 4) {
            throw new CommandException("Invalid arguments: collage <horizontal|vertical> <image1> <image2> <outimage>");
        }

        if (!args[0].equals("horizontal") && !args[0].equals("vertical")) {
            throw new CommandException("Direction must be either 'horizontal' or 'vertical'");
        }
    }

    /**
     * Метод, който валидира дали подадените изображения съществуват в текущата сесия.
     * @param session Сесията, в която се търси изображението.
     * @param imageName Името, на търсеното изображение.
     * @return Намереното изображение.
     * @throws CommandException Ако изображението не бъде намерено в текущата сесия.
     */
    private Image findImageInSession(Session session, String imageName) throws CommandException {
        for (Image img : session.getImages()) {
            if (img.getFile().getName().equals(imageName)) {
                return img;
            }
        }
        throw new CommandException("Image not found in session: " + imageName);
    }

    /**
     * Метод, който проверява дали двете подадени изображения са от еднакъв тип и размер.
     * Проверява и дали подаденото име за колаж е с валиден формат.
     * @param image1 Първото изображение, което ще се използва за колажа.
     * @param image2 Второто изображение, което ще се използва за колажа.
     * @param outImage Името на изображението, което ще се добави като краен разултат в сесията.
     * @throws CommandException Ако изображенията не са от еднакъв тип, размерност и outImage е с невалиден формат.
     */
    private void validateImageCompatibility(Image image1, Image image2, String outImage) throws CommandException {
        if (!image1.getFormat().equals(image2.getFormat())) {
            throw new CommandException("Images must be of the same format");
        }

        if (image1.getWidth() != image2.getWidth() || image1.getHeight() != image2.getHeight()) {
            throw new CommandException("Images must have identical dimensions");
        }

        String expectedExtension = "." + image1.getFormat();
        if (!outImage.endsWith(expectedExtension)) {
            throw new CommandException("Output filename must end with " + expectedExtension);
        }
    }

    /**
     * Метод, който създава колажа от двете изображения и го добавя в текущата сесия.
     * @param session Сесията, която съдържа изображенията.
     * @param direction Посоката на колажа.
     * @param image1 Първото изображение.
     * @param image2 Второто изображение.
     * @param outImageName Името на изображението, което ще се образува от колажа.
     * @throws CommandException Обработва грешките, по време на изпълнение.
     */
    private void createAndAddCollage(Session session, String direction, Image image1, Image image2, String outImageName)
            throws CommandException {
        Image collage = image1.cloneImage();
        collage.applyCollage(direction, image1, image2, outImageName);
        session.getImages().add(collage);
        System.out.println("Created collage '" + outImageName + "' (" + direction + ")");
    }
}


