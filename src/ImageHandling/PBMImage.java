package ImageHandling;

import Exceptions.CommandException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class PBMImage extends Image {
    private boolean[][] pixels;

    public PBMImage(File file) {
        super(file);
        this.format = "pbm";
    }

    @Override
    public void load() throws IOException {
        List<String> lines = Files.readAllLines(file.toPath());
        if (!lines.get(0).equals("P1")) throw new IOException("Invalid PBM format");

        String[] size = lines.get(1).split(" ");
        width = Integer.parseInt(size[0]);
        height = Integer.parseInt(size[1]);

        pixels = new boolean[height][width];

        int row = 0;
        for (int i = 2; i < lines.size(); i++) {
            String[] values = lines.get(i).split("\\s+");
            for (int col = 0; col < values.length; col++) {
                pixels[row][col] = values[col].equals("1");
            }
            row++;
        }

        System.out.println("PBM Image: " + width + "x" + height);
    }

    @Override
    public void applyGrayscale() {
        System.out.println("Grayscale transformation not suitable for PBM.");
    }

    @Override
    public void applyMonochrome() {
        System.out.println("Monochrome transformation not suitable for PBM.");
    }

    @Override
    public void applyNegative() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                pixels[i][j] = !pixels[i][j];
            }
        }
        System.out.println("Applied negative to PBM image.");
    }

    @Override
    public void applyRotation(String direction) {
        boolean[][] newPixels = new boolean[width][height];
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
        System.out.println("Applied " + direction + " rotation to PBM image.");
    }

    @Override
    public void applyCollage(String direction, Image image1, Image image2) throws CommandException {
        //to do
    }

    @Override
    public void save(File outputFile) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("P1\n");
        sb.append(width).append(" ").append(height).append("\n");

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                sb.append(pixels[i][j] ? "1" : "0").append(" ");
            }
            sb.append("\n");
        }

        Files.write(outputFile.toPath(), sb.toString().getBytes());
        System.out.println("Saved PBM image to " + outputFile.getAbsolutePath());
    }

    @Override
    public Image cloneImage() {
        PBMImage clone = new PBMImage(this.file);
        clone.width = this.width;
        clone.height = this.height;
        clone.format = this.format;
        clone.pixels = new boolean[this.height][this.width];
        for (int i = 0; i < height; i++) {
            System.arraycopy(this.pixels[i], 0, clone.pixels[i], 0, width);
        }
        return clone;
    }

}

