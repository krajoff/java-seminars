import java.io.File;

/**
 * TODO: Доработать метод print, необходимо распечатать директории и файлы на экран
 */
public class Tree {

    public static void print(File file, String indent, boolean isLast) {
        System.out.print(indent);
        if (isLast) {
            System.out.print("└─");
            indent += "  ";
        } else {
            System.out.print("├─");
            indent += "│ ";
        }
        System.out.println(file.getName());

        File[] files = file.listFiles();

        int subDirCounter = 0;
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                subDirCounter++;
                print(files[i], indent, subDirCounter == files.length);
            } else {
                printfile(files[i], indent, i == files.length - 1);
            }
        }

    }

    public static void printfile(File file, String indent, boolean isLast) {
        System.out.print(indent);
        if (isLast) {
            System.out.print("└─");
        } else {
            System.out.print("├─");
        }
        System.out.println(file.getName());
    }

}
