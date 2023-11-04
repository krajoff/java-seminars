package ru.balancing.sample;

import ru.balancing.regular.*;

public class Main {
    public static void main(String[] args) {
        int result = NewClass.sum(1, 2);
        System.out.println(Decorator.decorator(result)); ;
    }
}
