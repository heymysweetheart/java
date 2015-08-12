import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by leo on 15/8/12.
 */
public class FileInputStreamFileOutputStream {
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fileInputStream = null;
        FileOutputStream fileoutputStream = null;
        int tempInt;

        fileInputStream = new FileInputStream("/Users/leo/sia.log");
        fileoutputStream = new FileOutputStream("/Users/leo/sia.log.bak");

        try {
            while ((tempInt = fileInputStream.read()) != -1) {
                fileoutputStream.write(tempInt);
                System.out.print((char) tempInt);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}