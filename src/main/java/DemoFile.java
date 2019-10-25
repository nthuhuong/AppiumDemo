import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DemoFile {
    public static void main(String[] args) throws IOException {

        File file = new File(DemoFile.class.getClassLoader().getResource("Polling.txt").getFile());
        System.out.println(file);
        printFile(file);
    }

    private static void printFile(File file) {
        if (file == null) return;

        try {
            FileReader reader = new FileReader(file);
            BufferedReader br = new BufferedReader(reader);
            String line = br.readLine();
            System.out.println(line);
        } catch (Exception e){

        }
    }
}
