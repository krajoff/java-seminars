package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
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

    void writeTwoValues(int numberRequestsToSatisfy, Set<Integer> set) throws IOException {
        FileWriter fw = new FileWriter("output.txt");
        if (numberRequestsToSatisfy == 0) {
            fw.write(String.valueOf(0));
        } else if (set != null && numberRequestsToSatisfy != -1) {
            fw.write(String.valueOf(numberRequestsToSatisfy));
            fw.write("\n");
            String string = set
                    .stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(" "));
            fw.write(string);
        } else {
            fw.write(String.valueOf(-1));
        }
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

    public boolean isValid() {
        return startNode != -1 && endNode != -1 &&
                price != -1 && time != -1;
    }


    public Couple() {
    }

    @Override
    public String toString() {
        return "Couple{" +
                "startNode=" + startNode +
                ", endNode=" + endNode +
                ", price=" + price +
                ", time=" + time +
                ", offer=" + offer +
                '}';
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

class ConnectionCouple {
    List<Couple> coupleList;
    int totalPrice = 0;
    int totalTime = 0;

    public ConnectionCouple() {
        coupleList = new ArrayList<>();
    }

    public ConnectionCouple(Couple couple) {
        this();
        coupleList.add(couple);
        totalTime += couple.time;
        totalPrice += couple.price;
    }

    public ConnectionCouple(ConnectionCouple connectionCouple) {
        coupleList = connectionCouple.coupleList;
        totalPrice = connectionCouple.totalPrice;
        totalTime = connectionCouple.totalTime;
    }

    public ConnectionCouple(ConnectionCouple connectionCouple, Couple couple) {
        this(connectionCouple);
        coupleList.add(couple);
        totalTime += couple.time;
        totalPrice += couple.price;
    }


    public boolean contain(Couple couple) {
        for (Couple value : coupleList) {
            if ((value.startNode == couple.startNode
                    && value.endNode == couple.endNode) ||
                    (value.endNode == couple.startNode
                            && value.startNode == couple.endNode)) {
                return true;
            }
        }
        return false;
    }

    public Couple getLastCouple() {
        return coupleList.get(coupleList.size() - 1);
    }

    @Override
    public String toString() {
        return coupleList.stream().map(String::valueOf)
                .collect(Collectors.joining("->", "{", "}"));
    }
}

class Solution_task3 {
    int numberNodes, numberLines;
    int[][] descriptionLines;
    int numberOffers;
    int[][] offers;
    int numberRequests;
    int[][] requests;
    int numberOffersToSatisfy = 0;
    int wholePrice;
    int numberSatisfyRequests;
    Set<Integer> offersToSatisfy = new HashSet<>();
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
        l++;
        requests = new int[numberRequests][3];
        for (int i = l; i < numberRequests + l; i++) {
            line = input[i].split(" ");
            requests[i - l] = Stream.of(line)
                    .mapToInt(Integer::parseInt).toArray();
        }
        System.out.println("numberNodes = " + numberNodes +
                " numberLines = " + numberLines);
        System.out.print("descriptionLines = ");
        Arrays.stream(descriptionLines).forEach(el ->
                System.out.println("[" + el[0] + ", " + el[1] + ", " + el[2] + "]"));
        System.out.println("numberOffers = " + numberOffers);
        System.out.print("offers = ");
        Arrays.stream(offers).forEach(el ->
                System.out.println("[" + el[0] + ", " + el[1] + ", " + el[2] + ", " + el[3] + "]"));
        System.out.println("numberRequests = " + numberRequests);
        System.out.print("requests = ");
        Arrays.stream(requests).forEach(el ->
                System.out.println("[" + el[0] + ", " + el[1] + ", " + el[2] + "]"));
    }


    void calculation(int[] request) {
        int starNode = request[0];
        int endNode = request[1];
        int time = request[2];
        Couple couple;
        List<ConnectionCouple> tempConnection = new ArrayList<>();
        for (int i = 1; i < numberNodes + 1; i++) {
            couple = couples.getCouple(starNode, i);
            if (couple.isValid() && couple.time <= time) {
                System.out.println(couple);
                var connectionCouple =
                        new ConnectionCouple(couples.getCouple(starNode, i));
                tempConnection.add(connectionCouple);
            }
        }
        System.out.println("1.Connection couples size = " + tempConnection.size());
        System.out.println("1.List of connections = " + tempConnection);

        int j = 0;
        int count;
        int lastCoupleLastEndNode;
        Couple additionCouple;
        while (j < tempConnection.size()) {
            var con = tempConnection.get(j);
            lastCoupleLastEndNode = con.getLastCouple().endNode;
            count = 0;
            for (int i = 1; i < numberNodes + 1; i++) {
                additionCouple = couples.getCouple(lastCoupleLastEndNode, i);
                if (lastCoupleLastEndNode != endNode && additionCouple.isValid()) {
                    if (!con.contain(additionCouple) && (con.totalTime + additionCouple.time) <= time) {
                        var newConnection = new ConnectionCouple(con, additionCouple);
                        tempConnection.add(newConnection);
                        count++;
                    }
                }
            }
            if (count > 0) {
                tempConnection.remove(j);
            } else {
                j++;
            }
        }
        System.out.println("2.Connection couples size = " + tempConnection.size());
        System.out.println(tempConnection);

        j = 0;
        while (j < tempConnection.size()) {
            var con = tempConnection.get(j);
            lastCoupleLastEndNode = con.getLastCouple().endNode;
            if (lastCoupleLastEndNode != endNode) {
                tempConnection.remove(j);
                j--;
            }
            j++;
        }
        System.out.println("3.Connection couples size = " + tempConnection.size());
        System.out.println(tempConnection);

        int price = Integer.MAX_VALUE;
        ConnectionCouple bestCouples = null;
        for (ConnectionCouple couples : tempConnection) {
            if (couples.totalPrice < price) {
                price = couples.totalPrice;
                bestCouples = couples;
            }
        }
        System.out.println("4.Best couple = " + bestCouples);

        int offer;
        if (bestCouples != null) {
            for (int i = 0; i < bestCouples.coupleList.size(); i++) {
                var bc = bestCouples.coupleList.get(i);
                offer = bc.offer;
                wholePrice += bc.price;
                if (offer != -1)
                    offersToSatisfy.add(offer);
            }
            numberSatisfyRequests++;
        }
        System.out.println("5.Offers = " + offersToSatisfy);

    }

    void solve(String[] input) throws IOException {
        coverData(input);
        for (int[] request : requests) {
            System.out.println("Calculation request: " + Arrays.toString(request));
            calculation(request);
        }

        numberOffersToSatisfy = offersToSatisfy.size();
        if (numberSatisfyRequests != numberRequests) {
            offersToSatisfy = null;
            numberOffersToSatisfy = -1;
        }
        else if (wholePrice == 0) {
            numberOffersToSatisfy = 0;
        }

        System.out.println("offersToSatisfy = " + offersToSatisfy);
        System.out.println("numberOffersToSatisfy = " + numberOffersToSatisfy);

        FileDealing_3 fileDealing3 = new FileDealing_3();
        fileDealing3.writeTwoValues(numberOffersToSatisfy, offersToSatisfy);
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
        String relativePath = "input_task3_4.txt";
        FileDealing_3 fileDealing = new FileDealing_3();
        String[] inputs = fileDealing.read(relativePath);
        Solution_task3 solution = new Solution_task3();
        solution.solve(inputs);
        solution.printCouples();
    }
}
