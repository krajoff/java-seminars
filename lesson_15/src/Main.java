import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();
        tree.add(5, 7, 10, 3, 6, 20, 25, 35, 45, 1, 2, 4, 8);
        tree.printTree();
    }
}
