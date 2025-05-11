package Commands;

import Exceptions.CommandException;
import ImageHandling.Image;
import Sessions.Session;
import Sessions.SessionManager;

public class CollageCommand implements CreateCommand{
    private final SessionManager sessionManager;

    public CollageCommand(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

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

    private void validateArguments(String[] args) throws CommandException {
        if (args.length != 4) {
            throw new CommandException("Invalid arguments: collage <horizontal|vertical> <image1> <image2> <outimage>");
        }

        if (!args[0].equals("horizontal") && !args[0].equals("vertical")) {
            throw new CommandException("Direction must be either 'horizontal' or 'vertical'");
        }
    }

    private Image findImageInSession(Session session, String imageName) throws CommandException {
        for (Image img : session.getImages()) {
            if (img.getFile().getName().equals(imageName)) {
                return img;
            }
        }
        throw new CommandException("Image not found in session: " + imageName);
    }

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

    private void createAndAddCollage(Session session, String direction, Image image1, Image image2, String outImageName)
            throws CommandException {
        Image collage = image1.cloneImage();
        collage.applyCollage(direction, image1, image2, outImageName);
        session.getImages().add(collage);
        System.out.println("Created collage '" + outImageName + "' (" + direction + ")");
    }
}


