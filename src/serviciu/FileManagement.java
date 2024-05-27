package serviciu;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Instant;
import java.util.List;

public class FileManagement {
    File file;

    private final String FILE_PATH;
    private final StringBuilder STRING_BUILDER = new StringBuilder();

    public FileManagement(String filePath) {
        FILE_PATH = filePath;
        file = new File(FILE_PATH);
        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File " + file.getName() + " exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void writeFile(String content) {
        try(FileWriter fileWriter = new FileWriter(FILE_PATH, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(content + "\n");
        }   catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void replaceLine(String new_line, int nr_line) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            StringBuffer outputBuffer = new StringBuffer();
            String line;
            int index = 0;
            while ((line = reader.readLine()) != null) {
                if (index == nr_line)
                    outputBuffer.append(new_line);
                else
                    outputBuffer.append(line);
                outputBuffer.append("\n");

                index++;
            }
            try (FileWriter fileWriter = new FileWriter(FILE_PATH, false)) {
                fileWriter.write(outputBuffer.toString());
            }
//            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FILE_PATH));
//            bufferedWriter.write(outputBuffer.toString());
            //Files.write(Path.of(FILE_PATH), outputBuffer, StandardOpenOption.TRUNCATE_EXISTING);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public int getLineNumber_byName(String nume) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            int index = 0;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                if (words.length > 0 && words[0].equals(nume)) {
                    return index;
                }
                index++;
            }



        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }

}
