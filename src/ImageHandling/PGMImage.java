package ImageHandling;

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

        System.out.println("Loaded PGM Image: " + width + "x" + height);
    }

    @Override
    public void save(File outputFile) throws IOException {
        // to be implemented2
    }
}

