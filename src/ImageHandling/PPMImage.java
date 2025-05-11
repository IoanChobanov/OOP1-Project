package ImageHandling;

import Exceptions.CommandException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class PPMImage extends Image {
    private int[][][] pixels;
    private int maxColorValue;


    public PPMImage(File file) {
        super(file);
        this.format = "ppm";
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
        boolean isGrayscale = true;
        for (int i = 0; i < height && isGrayscale; i++) {
            for (int j = 0; j < width; j++) {
                if (pixels[i][j][0] != pixels[i][j][1] ||
                        pixels[i][j][1] != pixels[i][j][2]) {
                    isGrayscale = false;
                    break;
                }
            }
        }

        if (isGrayscale) {
            System.out.println("Image is already grayscale. No transformation applied.");
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
        boolean isMonochrome = true;
        for (int i = 0; i < height && isMonochrome; i++) {
            for (int j = 0; j < width; j++) {
                boolean isBlack = (pixels[i][j][0] == 0 &&
                        pixels[i][j][1] == 0 &&
                        pixels[i][j][2] == 0);
                boolean isWhite = (pixels[i][j][0] == maxColorValue &&
                        pixels[i][j][1] == maxColorValue &&
                        pixels[i][j][2] == maxColorValue);
                if (!isBlack && !isWhite) {
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
    public void applyCollage(String direction, Image img1, Image img2, String outImageName) {
        PPMImage image1 = (PPMImage) img1;
        PPMImage image2 = (PPMImage) img2;

        if (direction.equals("horizontal")) {
            createHorizontalCollage(image1, image2, outImageName);
        } else {
            createVerticalCollage(image1, image2, outImageName);
        }
    }

    private void createHorizontalCollage(PPMImage image1, PPMImage image2, String outImageName) {
        this.width = image1.width + image2.width;
        this.height = image1.height;
        this.maxColorValue = Math.max(image1.maxColorValue, image2.maxColorValue);
        this.pixels = new int[height][width][3];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < image1.width; x++) {
                System.arraycopy(image1.pixels[y][x], 0, this.pixels[y][x], 0, 3);
            }
        }

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < image2.width; x++) {
                System.arraycopy(
                        image2.pixels[y][x], 0,
                        this.pixels[y][x + image1.width], 0, 3
                );
            }
        }

        this.file = new File(outImageName);
    }

    private void createVerticalCollage(PPMImage image1, PPMImage image2, String outImageName) {
        this.width = image1.width;
        this.height = image1.height + image2.height;
        this.maxColorValue = Math.max(image1.maxColorValue, image2.maxColorValue);
        this.pixels = new int[height][width][3];

        for (int y = 0; y < image1.height; y++) {
            for (int x = 0; x < width; x++) {
                System.arraycopy(image1.pixels[y][x], 0, this.pixels[y][x], 0, 3);
            }
        }

        for (int y = 0; y < image2.height; y++) {
            for (int x = 0; x < width; x++) {
                System.arraycopy(
                        image2.pixels[y][x], 0,
                        this.pixels[y + image1.height][x], 0, 3
                );
            }
        }

        this.file = new File(outImageName);
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
}

