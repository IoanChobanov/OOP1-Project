package ImageHandling;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Клас за зареждане и валидиране на трите типа изображения, поддържани от тази програма.
 */
public class ImageLoader {

    /**
     * Зарежда изображение от файл и създава съответстващ обект според формата.
     * @param file Файлов обект, сочещ към изображението.
     * @return Инстанция на Image от съответния тип (PBM/PGM/PPM)
     * @throws IOException При несъответстващ файлов формат.
     */
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

    /**
     * Извлича magicNumber от файла (намира се на първия ред).
     * @param file Файлът, от който ще се чете.
     * @return magicNumber низ.
     * @throws IOException Ако файлът не съдържа валиден magicNumber.
     */
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

