import java.util.List;

public class App {
    public static void main(String[] args) {
        System.out.println(CRUDUtils.findAllUser());

        System.out.println(CRUDUtils.addUser(new User(1,"pp","asd@tt.ru","ff")));
        System.out.println(CRUDUtils.findAllUser());
    }
}
