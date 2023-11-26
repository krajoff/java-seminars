import java.util.HashMap;
import java.util.Random;

/**
 * В качестве задачи предлагаю вам реализовать код для демонстрации парадокса
 * Монти Холла (Парадокс Монти Холла — Википедия ) и наглядно убедиться в верности
 * парадокса (запустить игру в цикле на 1000 и вывести итоговый счет).
 * Необходимо:
 * Создать свой Java Maven или Gradle проект;
 * Самостоятельно реализовать прикладную задачу;
 * Сохранить результат в HashMap<шаг теста, результат>
 * Вывести на экран статистику по победам и поражениям
 **/
public class Main {
    public static void main(String[] args) {
        Random prizeBox = new Random();
        Random chooseBox = new Random();
        int round = 10000;
        HashMap<Integer, Integer> result = new HashMap<>();

        for (int i = 0; i < round; i++) {
            if (prizeBox.nextInt(3) == chooseBox.nextInt(3)) {
                result.put(i, 0);
            } else {
                result.put(i, 1);
            }
        }
        Integer sumWin = 0;
        for (Integer value : result.values()) {
            sumWin += value;
        }

        System.out.println("Побед: " + sumWin + " (" + sumWin*100/round + "%), " +
                "поражений: " + (round - sumWin) + " (" + (round - sumWin)*100/round + "%)");
    }
}
