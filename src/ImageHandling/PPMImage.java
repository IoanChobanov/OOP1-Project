package ImageHandling;

import Exceptions.CommandException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class PPMImage extends Image {
    private int[][][] pixels;
    private static final double DEFAULT_THRESHOLD = 0.5;

    public PPMImage(File file) {
        super(file);
        this.format = "PPM";
    }

    @Override
    public void load() throws IOException {
        List<String> lines = Files.readAllLines(file.toPath());
        if (!lines.get(0).equals("P3")) throw new IOException("Invalid PPM format");

        String[] size = lines.get(1).split(" ");
        width = Integer.parseInt(size[0]);
        height = Integer.parseInt(size[1]);
        maxColorValue = Integer.parseInt(lines.get(2));

        pixels = new int[height][width][3];

        int row = 0;
        for (int i = 3; i < lines.size(); i++) {
            String[] values = lines.get(i).split("\\s+");
            for (int col = 0, j = 0; col < width; col++, j += 3) {
                pixels[row][col][0] = Integer.parseInt(values[j]);
                pixels[row][col][1] = Integer.parseInt(values[j + 1]);
                pixels[row][col][2] = Integer.parseInt(values[j + 2]);
            }
            row++;
        }

        System.out.println("PPM Image: " + width + "x" + height);
    }

    @Override
    public void applyGrayscale() {
        double complianceRatio = getGrayscaleComplianceRatio();
        if (complianceRatio >= DEFAULT_THRESHOLD) {
            System.out.printf("Skipped grayscale: %.1f%% of pixels already grayscale.%n",
                    complianceRatio * 100);
            return;
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int gray = (int) (0.3 * pixels[i][j][0] + 0.59 * pixels[i][j][1] + 0.11 * pixels[i][j][2]);
                pixels[i][j][0] = pixels[i][j][1] = pixels[i][j][2] = gray;
            }
        }
        System.out.println("Applied grayscale to PPM image.");
    }

    @Override
    public void applyMonochrome() {
        double complianceRatio = getMonochromeComplianceRatio();
        if (complianceRatio >= DEFAULT_THRESHOLD) {
            System.out.printf("Skipped monochrome: %.1f%% of pixels already monochrome.%n",
                    complianceRatio * 100);
            return;
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int avg = (pixels[i][j][0] + pixels[i][j][1] + pixels[i][j][2]) / 3;
                if (avg > maxColorValue / 2) {
                    pixels[i][j][0] = pixels[i][j][1] = pixels[i][j][2] = maxColorValue;
                } else {
                    pixels[i][j][0] = pixels[i][j][1] = pixels[i][j][2] = 0;
                }
            }
        }
        System.out.println("Applied monochrome to PPM image.");
    }

    @Override
    public void applyNegative() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                pixels[i][j][0] = maxColorValue - pixels[i][j][0];
                pixels[i][j][1] = maxColorValue - pixels[i][j][1];
                pixels[i][j][2] = maxColorValue - pixels[i][j][2];
            }
        }
        System.out.println("Applied negative to PPM image.");
    }

    @Override
    public void applyRotation(String direction) {
        int[][][] newPixels = new int[width][height][3];
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
        System.out.println("Applied " + direction + " rotation to PPM image.");
    }

    @Override
    public void applyCollage(String direction, Image image1, Image image2) throws CommandException {
        //to do
    }

    @Override
    public void save(File outputFile) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("P3\n");
        sb.append(width).append(" ").append(height).append("\n");
        sb.append(maxColorValue).append("\n");

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                sb.append(pixels[i][j][0]).append(" ")
                        .append(pixels[i][j][1]).append(" ")
                        .append(pixels[i][j][2]).append(" ");
            }
            sb.append("\n");
        }

        Files.write(outputFile.toPath(), sb.toString().getBytes());
        System.out.println("Saved PPM image to " + outputFile.getAbsolutePath());
    }

    @Override
    public Image cloneImage() {
        PPMImage copy = new PPMImage(new File(file.getPath()));
        copy.width = this.width;
        copy.height = this.height;
        copy.format = this.format;
        copy.maxColorValue = this.maxColorValue;

        copy.pixels = new int[height][width][3];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.arraycopy(this.pixels[i][j], 0, copy.pixels[i][j], 0, 3);
            }
        }

        return copy;
    }

    public double getGrayscaleComplianceRatio() {
        int grayscalePixels = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (pixels[i][j][0] == pixels[i][j][1] &&
                        pixels[i][j][1] == pixels[i][j][2]) {
                    grayscalePixels++;
                }
            }
        }
        return (double) grayscalePixels / (width * height);
    }

    public double getMonochromeComplianceRatio() {
        int monoPixels = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                boolean isBlack = (pixels[i][j][0] == 0 &&
                        pixels[i][j][1] == 0 &&
                        pixels[i][j][2] == 0);
                boolean isWhite = (pixels[i][j][0] == maxColorValue &&
                        pixels[i][j][1] == maxColorValue &&
                        pixels[i][j][2] == maxColorValue);
                if (isBlack || isWhite) {
                    monoPixels++;
                }
            }
        }
        return (double) monoPixels / (width * height);
    }
}

