package studia;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GZIPCompesser {
    GZIPOutputStream outputStream;
    GZIPInputStream inputStream;

    GZIPCompesser(){}
    void zipFiles(String filePath, String zipedFilePath){
        byte[] buffer = new byte[1024];
        try{
            outputStream = new GZIPOutputStream(new FileOutputStream(zipedFilePath));
            FileInputStream in = new FileInputStream(filePath);
            int len;
            while ((len = in.read(buffer)) > 0) {
                outputStream.write(buffer, 0, len);
            }
            in.close();
            outputStream.finish();
            outputStream.close();
            System.out.println("Skompresowano");

        }catch(Exception ex){
            System.out.println("Błąd");
        }
    }
    void unzipFiles(String filePath, String zipedFilePath){
        byte[] buffer = new byte[1024];
        try{
            inputStream = new GZIPInputStream(new FileInputStream(zipedFilePath));
            FileOutputStream outputFileStream = new FileOutputStream(filePath);
            int len;
            while ((len = inputStream.read(buffer)) > 0) {
                outputFileStream.write(buffer, 0, len);
            }
            inputStream.close();
            outputFileStream.close();
            System.out.println("Rozpakowano");

        }catch(Exception ex){
            System.out.println("Błąd");
        }
    }
}
