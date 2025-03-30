package ImageHandling;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class PPMImage extends Image {
    private int[][][] pixels;

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
                pixels[row][col][0] = Integer.parseInt(values[j]);     // Red
                pixels[row][col][1] = Integer.parseInt(values[j + 1]); // Green
                pixels[row][col][2] = Integer.parseInt(values[j + 2]); // Blue
            }
            row++;
        }

        System.out.println("Loaded PPM Image: " + width + "x" + height);
    }

    @Override
    public void save(File outputFile) throws IOException {
        // to be implemented3
    }
}

