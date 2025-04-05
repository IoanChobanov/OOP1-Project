package ImageHandling;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty() && !line.startsWith("#")) {
                    return line;
                }
            }
        }
        throw new IOException("No magic number found in file.");
    }
}

