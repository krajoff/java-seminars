package org.example;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Main {
    public static void main(String[] args) throws IOException {
        Person person = new Person("Nikolay", "Ivanov", 22);
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(person.getName()+".bin"));
        person.serialize(out);
    }
}