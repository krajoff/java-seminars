public class Main {
    public static void main(String[] args) {
        Integer ii = 1;
        change(ii);
        System.out.println(ii);
    }
   static public void change(Integer i){
        ++i;
    }
}