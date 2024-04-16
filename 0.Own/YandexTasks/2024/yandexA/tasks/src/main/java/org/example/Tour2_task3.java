package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class FileDealing_3 {
    String[] read(String file) throws IOException {
        StringBuilder input = new StringBuilder();
        try (var fr = new FileReader(file);
             var br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null) {
                input.append(line).append("\n");
            }
        }
        String string = input.toString();
        return string.split("\n");
    }

    void writeTwoValues(int totalPrice, int[] buyByDays) throws IOException {
        FileWriter fw = new FileWriter("output.txt");
        fw.write(String.valueOf(totalPrice));
        fw.write("\n");
        String string = Arrays
                .stream(buyByDays)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" "));
        fw.write(string);
        fw.close();
    }
}


class Couple {
    int startNode = -1;
    int endNode = -1;
    int price = -1;
    int time = -1;
    int offer = -1;

    public Couple(int startNode, int endNode, int price, int time, int offer) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.price = price;
        this.time = time;
        this.offer = offer;
    }

    public Couple(int startNode, int endNode) {
        this.startNode = startNode;
        this.endNode = endNode;
    }


    public Couple() {
    }

}


class ArrayCouple {
    int size;
    Couple[][] couples;

    public ArrayCouple(int size) {
        couples = new Couple[size + 1][size + 1];
        this.size = size;
    }

    public void setCouple(Couple couple) {
        couples[couple.startNode][couple.endNode] = couple;
        couples[couple.endNode][couple.startNode] = couple;
    }

    public Couple getCouple(int startNode, int endNode) {
        return couples[startNode][endNode];
    }

}

class ListCouples {
    List<Couple> coupleList = new ArrayList<>();

    public ListCouples(Couple couple) {
        if (coupleList.isEmpty()) {
            coupleList = new ArrayList<>();
        } else {
            coupleList.add(couple);
        }
    }

    void add(Couple couple) {
        coupleList.add(couple);
    }
}

class Solution_task3 {
    int numberNodes, numberLines;
    int[][] descriptionLines;
    int numberOffers;
    int[][] offers;
    int numberRequests;
    int[][] requests;
    int numberRequestsToSatisfy;
    int[] requestsToSatisfy;
    ArrayCouple couples;

    void coverData(String[] input) {
        int l = 0;
        String[] line = input[l].split(" ");
        numberNodes = Integer.parseInt(line[0]);
        numberLines = Integer.parseInt(line[1]);
        couples = new ArrayCouple(numberNodes + 1);
        for (int i = 0; i < numberNodes + 1; i++) {
            for (int j = 0; j < numberNodes + 1; j++) {
                couples.setCouple(new Couple(i, j));
            }
        }
        descriptionLines = new int[numberLines][3];
        int[] temp;
        l++;
        for (int i = l; i < numberLines + l; i++) {
            line = input[i].split(" ");
            temp = Stream.of(line)
                    .mapToInt(Integer::parseInt).toArray();
            descriptionLines[i - l] = temp;
            couples.setCouple(new Couple(temp[0], temp[1],
                    0, temp[2], -1));
        }
        l = l + numberLines;
        numberOffers = Integer.parseInt(input[l]);
        l++;
        offers = new int[numberOffers][4];
        for (int i = l; i < numberOffers + l; i++) {
            line = input[i].split(" ");
            temp = Stream.of(line)
                    .mapToInt(Integer::parseInt).toArray();
            offers[i - l] = temp;
            couples.setCouple(new Couple(temp[0], temp[1],
                    temp[3], temp[2], i - l + 1));
        }
        l = l + numberOffers;
        numberRequests = Integer.parseInt(input[l]);
        requests = new int[numberRequests][3];
        for (int i = l; i < numberRequests + l; i++) {
            line = input[i].split(" ");
            requests[i - l] = Stream.of(line)
                    .mapToInt(Integer::parseInt).toArray();
        }
        System.out.println("numberNodes = " + numberNodes +
                " numberLines = " + numberLines);
        System.out.println("descriptionLines = "
                + Arrays.toString(descriptionLines));
        System.out.println("numberOffers = " + numberOffers);
        System.out.println("offers = " + Arrays.toString(offers));
        System.out.println("numberRequests = " + numberRequests);
        System.out.println("requests = " + Arrays.toString(requests));
    }


    void calculation(int[] request) {
        int starNode = request[0];
        int endNode = request[1];
        int time = request[2];
        while (true) {
            ListCouples listCouples =
                    new ListCouples(couples.getCouple(starNode, endNode));
        }

    }

    void solve(String[] input) throws IOException {
        coverData(input);
        for (int i = 0; i < requests.length; i++) {
            calculation(requests[i]);
        }

        List<Integer> temp = new ArrayList<>();
        List<Integer> minPriceByDay = new ArrayList<>();


        FileDealing_3 fileDealing3 = new FileDealing_3();
        fileDealing3.writeTwoValues(numberRequestsToSatisfy, requestsToSatisfy);
    }


    void printCouples() {
        System.out.println("Couples/Price:");
        for (int i = 0; i < numberNodes + 1; i++) {
            for (int j = 0; j < numberNodes + 1; j++) {
                System.out.print(couples.getCouple(i, j).price + " | ");
            }
            System.out.println();
        }
        System.out.println("Couples/Time:");
        for (int i = 0; i < numberNodes + 1; i++) {
            for (int j = 0; j < numberNodes + 1; j++) {
                System.out.print(couples.getCouple(i, j).time + " | ");
            }
            System.out.println();
        }
    }

}

public class Tour2_task3 {
    public static void main(String[] args) throws IOException {
        String relativePath = "input_task3_2.txt";
        FileDealing_3 fileDealing = new FileDealing_3();
        String[] inputs = fileDealing.read(relativePath);
        Solution_task3 solution = new Solution_task3();
        solution.coverData(inputs);
        solution.printCouples();
    }
}
