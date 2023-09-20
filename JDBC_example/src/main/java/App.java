import java.util.List;

public class App {
    public static void main(String[] args) {
        System.out.println(CRUDUtils.findAllUser());

        CRUDUtils.addUser(new User(1,"pp","asd@tt.ru","ff"));
        System.out.println(CRUDUtils.findAllUser());
        CRUDUtils.updatePasswordById(2,"sdfddd");
        System.out.println(CRUDUtils.findUserById(5));
        CRUDUtils.deleteUserById(2);

    }
}
