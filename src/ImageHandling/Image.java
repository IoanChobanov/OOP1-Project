package ImageHandling;

import Exceptions.CommandException;

import java.io.File;
import java.io.IOException;

public abstract class Image {
    protected int width;
    protected int height;
    protected String format;
    protected File file;

    public Image(File file) {
        this.file = file;
    }

    public abstract void load() throws IOException;
    public abstract void save(File outputFile) throws IOException;
    public abstract Image cloneImage();

    public abstract void applyGrayscale();
    public abstract void applyMonochrome();
    public abstract void applyNegative();
    public abstract void applyRotation(String direction);
    public abstract void applyCollage(String direction, Image image1, Image image2) throws CommandException;

    public String getFormat() {
        return format;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public File getFile() {
        return file;
    }
}
