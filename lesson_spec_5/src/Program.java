import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Program {
    // Написать функцию, создающую резервную копию всех файлов в
    // директории(без поддиректорий) во вновь созданную папку ./backup
    public static void main(String[] args) throws IOException {
        String pathSource = ".\\src\\source\\";
        String pathBackup = ".\\src\\backup\\";
        cp(pathSource, pathBackup);
        Tree.print(new File("."), "", true);

    }

    private static void cp(String copy, String paste) throws IOException {
        File in = new File(copy);
        File out = new File(paste);
        if(!out.exists()){
            new File(paste).mkdir();
        }
        File[] files = in.listFiles();
        for (File value : files) {
            if (value.isFile()) {
                try (FileOutputStream fileOutputStream =
                             new FileOutputStream(paste + value.getName())) {
                    try (FileInputStream fileInputStream =
                                 new FileInputStream(value)) {
                        int c;
                        while ((c = fileInputStream.read()) != -1) {
                            fileOutputStream.write(c);
                        }
                    }
                }
            }
        }
    }
}

