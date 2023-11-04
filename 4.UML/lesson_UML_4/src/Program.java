import java.util.Scanner;
import repositories.*;
public class Program {
    public static void main(String[] args) {
        CustomsRepository customsRepository;
        customsRepository = new CustomsRepositoryImp();
        System.out.print("Введите имя пользователя, на которого будем покупать билет: ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        if (customsRepository.isExist(name)) {
            System.out.print("Какие операции выполнить: " +
                    "1 - вывести билеты пользователя. " +
                    "2 - найти новые билеты: ");
            String str = scanner.nextLine();
            switch (str){
                case "1":
                    System.out.println(customsRepository.tickets(name));
                    break;
                case "2":
                    break;
            }

        }

    }
}
