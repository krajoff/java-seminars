package seminars.fifth.number;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Первый модуль генерирует список случайных чисел
public class RandomNumberModule {
    List<Integer> list = new ArrayList<>();
    public List<Integer> create(int count){
        Random number = new Random();
        while (count>0){
            list.add(number.nextInt());
            count--;
        }
        return list;
    }
}
