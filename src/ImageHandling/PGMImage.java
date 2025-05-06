package ImageHandling;

import Exceptions.CommandException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class PGMImage extends Image {
    private int[][] pixels;

    public PGMImage(File file) {
        super(file);
        this.format = "PGM";
    }

    @Override
    public void load() throws IOException {
        List<String> lines = Files.readAllLines(file.toPath());
        if (!lines.get(0).equals("P2")) throw new IOException("Invalid PGM format");

        String[] size = lines.get(1).split(" ");
        width = Integer.parseInt(size[0]);
        height = Integer.parseInt(size[1]);
        maxColorValue = Integer.parseInt(lines.get(2));

        pixels = new int[height][width];

        int row = 0;
        for (int i = 3; i < lines.size(); i++) {
            String[] values = lines.get(i).split("\\s+");
            for (int col = 0; col < values.length; col++) {
                pixels[row][col] = Integer.parseInt(values[col]);
            }
            row++;
        }

        System.out.println("PGM Image: " + width + "x" + height);
    }

    @Override
    public void applyGrayscale() {
        System.out.println("Grayscale transformation not suitable for PGM.");
    }

    @Override
    public void applyMonochrome() {
        boolean isMonochrome = true;
        for (int i = 0; i < height && isMonochrome; i++) {
            for (int j = 0; j < width; j++) {
                int val = pixels[i][j];
                if (val != 0 && val != maxColorValue) {
                    isMonochrome = false;
                    break;
                }
            }
        }

        if (isMonochrome) {
            System.out.println("Image is already monochrome. No transformation applied.");
            return;
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                pixels[i][j] = (pixels[i][j] > maxColorValue/2)
                        ? maxColorValue : 0;
            }
        }
        System.out.println("Applied monochrome to PGM image.");
    }

    @Override
    public void applyNegative() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                pixels[i][j] = maxColorValue - pixels[i][j];
            }
        }
        System.out.println("Applied negative to PGM image.");
    }

    @Override
    public void applyRotation(String direction) {
        int[][] newPixels = new int[width][height];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (direction.equals("left")) {
                    newPixels[width - 1 - j][i] = pixels[i][j];
                } else {
                    newPixels[j][height - 1 - i] = pixels[i][j];
                }
            }
        }
        pixels = newPixels;

        int temp = width;
        width = height;
        height = temp;
        System.out.println("Applied " + direction + " rotation to PGM image.");
    }

    @Override
    public void applyCollage(String direction, Image image1, Image image2) throws CommandException {
        //to do
    }

    @Override
    public void save(File outputFile) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("P2\n");
        sb.append(width).append(" ").append(height).append("\n");
        sb.append(maxColorValue).append("\n");

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                sb.append(pixels[i][j]).append(" ");
            }
            sb.append("\n");
        }

        Files.write(outputFile.toPath(), sb.toString().getBytes());
        System.out.println("Saved PGM image to " + outputFile.getAbsolutePath());
    }

    @Override
    public Image cloneImage() {
        PGMImage copy = new PGMImage(new File(file.getPath()));
        copy.width = this.width;
        copy.height = this.height;
        copy.format = this.format;
        copy.maxColorValue = this.maxColorValue;

        copy.pixels = new int[height][width];
        for (int i = 0; i < height; i++) {
            System.arraycopy(this.pixels[i], 0, copy.pixels[i], 0, width);
        }

        return copy;
    }
}

