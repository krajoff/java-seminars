package Philosophes.seminar;

public class Aston
{
    public static void main(String[] args) {
        String msg = "Hello";
        change(msg);
        System.out.println(msg);
        msg = change(msg);
        System.out.println(msg);

        Integer value = 1;
        value = cc(value);
        System.out.println(value);
        System.out.println(value);
        System.out.println(value);
        System.out.println(value+1);

    }
    public static String change(String msg){
        msg = msg + "world!";
        return " java!";
    }

    public static Integer cc(Integer value){return value++;
    }

}
