package studia;

import sun.nio.cs.ISO_8859_2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class SimpleTextEditorBase {
    OutputStreamWriter writer;
    FileOutputStream stream;
    SimpleTextEditorBase(){

    }
    void putSomeTextAndSave() {
        Scanner input = new Scanner(System.in);
        System.out.println("Podaj tekst do zapisu:\n");
        String textToSave = input.next();
        try {
            File file = new File("D:\\Tomek\\Studia\\Semestr 6\\Java zaawansowana\\text.txt");
            if (file.exists())
                file.delete();
            file.createNewFile();
            stream = new FileOutputStream(file);
            System.out.println("Wybierz format w jakim chcesz dokonać zapisu: \n1.UTF8\n2. ISO8859 2\n3. Cp1250");
            int formatInfo = (int)input.nextInt();
            switch (formatInfo) {
                case 1:
                    writer = new OutputStreamWriter(stream, "UTF8");
                    break;
                case 2:
                    writer = new OutputStreamWriter(stream, "ISO-8859-2");
                    break;
                case 3:
                    writer = new OutputStreamWriter(stream, "CP1250");
                    break;
                default:
                    System.out.println("Niewłaściwy wybór");
                    break;
            }
            writer.write(textToSave);
            writer.flush();
            stream.close();
        }
        catch (Exception ex){
            System.out.print("Błąd!");
        }
    }
}
