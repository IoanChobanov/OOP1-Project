package ImageHandling;

import java.io.File;
import java.io.IOException;

public class ImageLoader {
    public static Image loadImage(File file) throws IOException {
        String magicNumber = getMagicNumber(file);

        switch (magicNumber) {
            case "P1":
                return new PBMImage(file);
            case "P2":
                return new PGMImage(file);
            case "P3":
                return new PPMImage(file);
            default:
                throw new IOException("Unsupported file format: " + magicNumber);
        }
    }

    private static String getMagicNumber(File file) throws IOException {
        return java.nio.file.Files.readAllLines(file.toPath()).get(0).trim();
    }
}

