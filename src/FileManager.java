import java.io.*;

public class FileManager {
    public static void saveToFile(String filename, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(data);
        } catch (IOException e) {
            System.err.println("\nGagal menyimpan data ke file: " + e.getMessage());
        }
    }

    public static String loadFromFile(String filename) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            System.err.println("\nGagal membaca data dari file: " + e.getMessage());
        }
        return content.toString();
    }
}
