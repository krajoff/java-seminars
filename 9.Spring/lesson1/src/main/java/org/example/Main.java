package org.example;

import com.google.gson.Gson;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException  {
        Person person = new Person("Nikolay", "Ivanov", 15);
        try(FileOutputStream fileOutputStream = new FileOutputStream(person.getName()+".bin");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){
            Gson gson = new Gson();
            String json = gson.toJson(person);
            objectOutputStream.writeObject(json);
            System.out.println("Экземпляр класса Person сериализован.");
        }
    }
}