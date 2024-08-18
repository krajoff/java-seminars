package org.example;


public class Week {

    private int toDayOfWeek(String num)
    {
        try
        {
            return Integer.parseInt(num) % 7;
        }
        catch(NumberFormatException ex)
        {
            ex.printStackTrace();
            return 0;
        }
    }


    public void getDayofWeek(String num){
        if(toDayOfWeek(num) >= 5)
            System.out.println("Мы не работаем в выходные");
        else
            System.out.println("Ждём вас в этот день");

    }

}
