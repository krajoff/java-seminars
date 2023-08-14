import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
        Random rnd = ThreadLocalRandom.current();
        List<ItemFabric> fabricList = new ArrayList<>();
        fabricList.add(new GoldGenerator());
        fabricList.add(new GemGenerator());
        for(int i =0; i<20; i++)
        {
            int index = Math.abs(rnd.nextInt()%2)==0?0:1;
            ItemFabric fabric = fabricList.get(index);
            fabric.openReward();
        }



    }
}
/*

*/
/*
 * abstract class ItemFabric
 *   openReward
 *   createItem
 * interface iGameItem
 *   open
 *gold
 *gem
 *
 * */