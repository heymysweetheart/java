import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by leo on 15/8/12.
 */
public class FileInputStreamFileOutputStream {
    public static void main(String[] args) {
        FileInputStream fileInputStream = null;
        FileOutputStream fileoutputStream = null;
        int tempInt;

        try {
            fileInputStream = new FileInputStream("/Users/leo/sia.log");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fileoutputStream = new FileOutputStream("/Users/leo/sia.log.bak");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            while ((tempInt = fileInputStream.read()) != -1) {
                fileoutputStream.write(tempInt);
                System.out.print((char) tempInt);
            }

            fileInputStream.close();
            fileoutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}