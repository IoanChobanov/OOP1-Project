package ImageHandling;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 * Клас за работа с PBM изображения.
 */
public class PBMImage extends Image {
    private boolean[][] pixels;

    public PBMImage(File file) {
        super(file);
        this.format = "pbm";
    }

    /**
     * Зарежда изображението от файл и парсира данните.
     * @throws IOException при грешка при четене или невалиден формат.
     */
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

    /**
     * Прилага grayscale трансформация (не се поддържа за PBM).
     */
    @Override
    public void applyGrayscale() {
        System.out.println("Grayscale transformation not suitable for PBM.");
    }

    /**
     * Прилага monochrome трансформация (не се поддържа за PBM).
     */
    @Override
    public void applyMonochrome() {
        System.out.println("Monochrome transformation not suitable for PBM.");
    }

    /**
     * Обръща цветовете на изображението (негатив).
     */
    @Override
    public void applyNegative() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                pixels[i][j] = !pixels[i][j];
            }
        }
        System.out.println("Applied negative to PBM image.");
    }

    /**
     * Завърта изображението на 90 градуса.
     * @param direction Посока на завъртане ("left"/"right").
     */
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

    /**
     * Създава колаж от текущото и друго изображение.
     * @param direction Ориентация ("horizontal"/"vertical").
     * @param img1 Първо изображение за колажа.
     * @param img2 Второ изображение за колажа.
     * @param outImageName Име на изходното изображение.
     */
    @Override
    public void applyCollage(String direction, Image img1, Image img2, String outImageName) {
        PBMImage image1 = (PBMImage) img1;
        PBMImage image2 = (PBMImage) img2;

        if (direction.equals("horizontal")) {
            createHorizontalCollage(image1, image2, outImageName);
        } else {
            createVerticalCollage(image1, image2, outImageName);
        }
    }

    /**
     * Създава хоризонтален колаж.
     * @param image1 Първо изображение.
     * @param image2 Второ изображение.
     * @param outImageName Име на изходния файл.
     */
    private void createHorizontalCollage(PBMImage image1, PBMImage image2, String outImageName) {
        this.width = image1.width + image2.width;
        this.height = image1.height;
        this.pixels = new boolean[height][width];

        for (int y = 0; y < height; y++) {
            System.arraycopy(image1.pixels[y], 0, this.pixels[y], 0, image1.width);
            System.arraycopy(image2.pixels[y], 0, this.pixels[y], image1.width, image2.width);
        }

        this.file = new File(outImageName);
    }

    /**
     * Създава вертикален колаж.
     * @param image1 Първо изображение.
     * @param image2 Второ изображение.
     * @param outImageName Име на изходния файл.
     */
    private void createVerticalCollage(PBMImage image1, PBMImage image2, String outImageName) {
        this.width = image1.width;
        this.height = image1.height + image2.height;
        this.pixels = new boolean[height][width];

        for (int y = 0; y < image1.height; y++) {
            System.arraycopy(image1.pixels[y], 0, this.pixels[y], 0, width);
        }

        for (int y = 0; y < image2.height; y++) {
            System.arraycopy(image2.pixels[y], 0, this.pixels[y + image1.height], 0, width);
        }

        this.file = new File(outImageName);
    }

    /**
     * Запазва изображението във файл.
     * @param outputFile Файлът, в който да се запише.
     * @throws IOException при грешка при запис.
     */
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

    /**
     * Създава копие на текущото изображение.
     * @return Нова инстанция с идентични данни.
     */
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

