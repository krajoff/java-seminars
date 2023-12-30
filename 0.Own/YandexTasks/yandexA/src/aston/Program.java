package aston;

public class Program {
    public static void main(String[] args) {
        Integer ii = 1;
        change(ii);
        System.out.println(ii);
    }
    public void change(Integer i){
        ++i;
    }
}
