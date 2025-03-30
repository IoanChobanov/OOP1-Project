package ImageHandling;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class PBMImage extends Image {
    private boolean[][] pixels;

    public PBMImage(File file) {
        super(file);
        this.format = "PBM";
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

        System.out.println("Loaded PBM Image: " + width + "x" + height);
    }

    @Override
    public void save(File outputFile) throws IOException {
        // to be implemented1
    }
}

