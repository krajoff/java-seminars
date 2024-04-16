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

class Node {
    int number;

    public Node(int number) {
        this.number = number;
    }
}

class Nodes {
    List<Node> nodes;
    int totalPrice = 0;
    int totalTime = 0;

    public Nodes() {
        nodes = new ArrayList<>();
    }

    public Nodes(Node node) {
        new Nodes();
        nodes.add(node);
    }

    public Nodes(List<Node> nodes) {
        new Nodes();
        this.nodes = nodes;
    }

    public List<Node> addNode(Node node, int price, int time) {
        nodes.add(node);
        totalPrice += price;
        totalTime += time;
        return nodes;
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
    int[][][] nodes; // [][][0] - price; [][][1] - time

    void coverData(String[] input) {
        int l = 0;
        String[] line = input[l].split(" ");
        numberNodes = Integer.parseInt(line[0]);
        numberLines = Integer.parseInt(line[1]);
        nodes = new int[numberNodes + 1][numberNodes + 1][2];
        for (int[][] matrix : nodes) {
            for (int[] row : matrix) {
                Arrays.fill(row, -1);
            }
        }
        descriptionLines = new int[numberLines][3];
        l++;
        for (int i = l; i < numberLines + l; i++) {
            line = input[i].split(" ");
            descriptionLines[i - l] = Stream.of(line)
                    .mapToInt(Integer::parseInt).toArray();
            nodes[descriptionLines[i - l][0]]
                    [descriptionLines[i - l][1]][0] = 0;
            nodes[descriptionLines[i - l][1]]
                    [descriptionLines[i - l][0]][0] = 0;
            nodes[descriptionLines[i - l][0]]
                    [descriptionLines[i - l][1]][1]
                    = descriptionLines[i - l][2];
            nodes[descriptionLines[i - l][1]]
                    [descriptionLines[i - l][0]][1]
                    = descriptionLines[i - l][2];
        }
        l = l + numberLines;
        numberOffers = Integer.parseInt(input[l]);
        l++;
        offers = new int[numberOffers][4];
        for (int i = l; i < numberOffers + l; i++) {
            line = input[i].split(" ");
            offers[i - l] = Stream.of(line)
                    .mapToInt(Integer::parseInt).toArray();
            nodes[offers[i - l][0]]
                    [offers[i - l][1]][0] = offers[i - l][3];
            nodes[offers[i - l][0]]
                    [offers[i - l][1]][1] = offers[i - l][2];
            nodes[offers[i - l][1]]
                    [offers[i - l][0]][0] = offers[i - l][3];
            nodes[offers[i - l][1]]
                    [offers[i - l][0]][1] = offers[i - l][2];
        }
        l = l + numberOffers;
        numberRequests = Integer.parseInt(input[l]);
        requests = new int[numberRequests][3];

        System.out.println("numberNodes = " + numberNodes +
                " numberLines = " + numberLines);
        System.out.println("descriptionLines = "
                + Arrays.toString(descriptionLines));
        System.out.println("numberOffers = " + numberOffers);
        System.out.println("offers = " + Arrays.toString(offers));
        System.out.println("numberRequests = " + numberRequests);
        System.out.println("requests = " + Arrays.toString(requests));
        printArray(nodes);
    }


    void calculation(Node startNode, int[] endNode) {
        while (true){
            Nodes tempNodesList = new Nodes();
            tempNodesList.addNode(startNode);
        }

    }

    void solve(String[] input) throws IOException {
        coverData(input);


        List<Integer> temp = new ArrayList<>();
        List<Integer> minPriceByDay = new ArrayList<>();


        FileDealing_3 fileDealing3 = new FileDealing_3();
        fileDealing3.writeTwoValues(numberRequestsToSatisfy, requestsToSatisfy);
    }


    void printArray(int[][][] nodes) {
        System.out.println("Nodes:");
        for (int depth = 0; depth < 2; depth++) {
            for (int row = 0; row < numberNodes + 1; row++) {
                for (int col = 0; col < numberNodes + 1; col++) {
                    System.out.print(nodes[col][row][depth] + " | ");
                }
                System.out.println();
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
    }
}
