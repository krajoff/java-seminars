package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Usage: java -jar unosoft.jar <input-file.txt>");
            System.exit(1);
        }
        String inputFilePath = args[0];
        long startTime = System.currentTimeMillis();
        //String inputFilePath = "D:\\JAVA\\java-seminars\\0.Own\\UnoSoft\\unosoft\\build\\libs\\lng-big.csv";

        try {
            List<String> lines = Files.readAllLines(Paths.get(inputFilePath));
            if (lines.isEmpty()) {
                throw new IllegalArgumentException("Input file is empty.");
            }
            boolean isDouble = determineDataType(lines.get(0));
            if (isDouble) {
                System.out.println("Data type is double.");
                Set<List<Double>> data = readFileAsDoubles(inputFilePath);
                List<Set<List<Double>>> groups = findAndMergeGroups2(data);
                sortGroups(groups);
                printResults(groups);
            } else {
                System.out.println("Data type is long.");
                Set<List<Long>> data = readFileAsLong(inputFilePath);
                List<Set<List<Long>>> groups = findAndMergeGroups1(data);
                sortGroups(groups);
                printResults(groups);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println("Time execution: " + executionTime + " ms");
    }

    public static <T> List<Set<List<T>>> findAndMergeGroups1(Set<List<T>> data) {
        List<List<T>> rows = new ArrayList<>(data);
        UnionFind uf = new UnionFind(rows.size());
        Map<Integer, Map<T, List<Integer>>> columnValueToIndices = new HashMap<>();
        for (int rowIndex = 0; rowIndex < rows.size(); rowIndex++) {
            List<T> row = rows.get(rowIndex);
            for (int colIndex = 0; colIndex < row.size(); colIndex++) {
                T value = row.get(colIndex);
                if (value == null) continue;

                columnValueToIndices.putIfAbsent(colIndex, new HashMap<>());
                Map<T, List<Integer>> valueToIndices = columnValueToIndices.get(colIndex);

                if (!valueToIndices.containsKey(value)) {
                    valueToIndices.put(value, new ArrayList<>());
                }

                for (Integer existingRowIndex : valueToIndices.get(value)) {
                    uf.union(rowIndex, existingRowIndex);
                }

                valueToIndices.get(value).add(rowIndex);
            }
        }

        Map<Integer, Set<List<T>>> rootToGroup = new HashMap<>();
        for (int rowIndex = 0; rowIndex < rows.size(); rowIndex++) {
            int root = uf.find(rowIndex);
            rootToGroup.putIfAbsent(root, new HashSet<>());
            rootToGroup.get(root).add(rows.get(rowIndex));
        }

        return new ArrayList<>(rootToGroup.values());
    }

    private static boolean determineDataType(String line) {
        String[] parts = line.split(";");
        for (String part : parts) {
            if (!part.isEmpty())
                return part.contains(".");
        }
        return false; // by default to long if no double is detected
    }

    private static <T extends Number> List<Set<List<T>>> findAndMergeGroups2(Set<List<T>> data) {
        List<List<T>> rows = new ArrayList<>(data);
        UnionFind uf = new UnionFind(rows.size());

        Map<Integer, Map<T, List<Integer>>> columnValueToIndices = new HashMap<>();

        for (int rowIndex = 0; rowIndex < rows.size(); rowIndex++) {
            List<T> row = rows.get(rowIndex);
            for (int colIndex = 0; colIndex < row.size(); colIndex++) {
                T value = row.get(colIndex);
                if (value == null) continue;

                columnValueToIndices.putIfAbsent(colIndex, new HashMap<>());
                Map<T, List<Integer>> valueToIndices = columnValueToIndices.get(colIndex);

                if (!valueToIndices.containsKey(value)) {
                    valueToIndices.put(value, new ArrayList<>());
                }

                for (Integer existingRowIndex : valueToIndices.get(value)) {
                    uf.union(rowIndex, existingRowIndex);
                }

                valueToIndices.get(value).add(rowIndex);
            }
        }

        Map<Integer, Set<List<T>>> rootToGroup = new HashMap<>();
        for (int rowIndex = 0; rowIndex < rows.size(); rowIndex++) {
            int root = uf.find(rowIndex);
            rootToGroup.putIfAbsent(root, new HashSet<>());
            rootToGroup.get(root).add(rows.get(rowIndex));
        }

        return new ArrayList<>(rootToGroup.values());
    }

    public static Set<List<Double>> readFileAsDoubles(String filename) throws IOException {
        Set<List<Double>> set = new HashSet<>();
        String string;
        List<String> stringList;
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(filename)))) {
            while ((string = reader.readLine()) != null) {
                stringList = Arrays.stream(string.split(";"))
                        .filter(Main::isValidNumberString)
                        .map(str -> str.replace("\"", ""))
                        .toList();
                if (stringList.stream().noneMatch(s -> s.contains("\""))) {
                    List<Double> longList = new ArrayList<>();
                    for (String column : stringList) {
                        longList.add(column.isEmpty() ? null : Double.valueOf(column));
                    }
                    set.add(longList);
                }
            }
        }
        return set;
    }

    public static Set<List<Long>> readFileAsLong(String filename) throws IOException {
        Set<List<Long>> set = new HashSet<>();
        String string;
        List<String> stringList;
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(filename)))) {
            while ((string = reader.readLine()) != null) {
                stringList = Arrays.stream(string.split(";"))
                        .filter(Main::isValidNumberString)
                        .map(str -> str.replace("\"", ""))
                        .toList();
                if (stringList.stream().noneMatch(s -> s.contains("\""))) {
                    List<Long> longList = new ArrayList<>();
                    for (String column : stringList) {
                        longList.add(column.isEmpty() ? null : Long.valueOf(column));
                    }
                    set.add(longList);
                }
            }
        }
        return set;
    }


    private static boolean isValidNumberString(String str) {
        return !str.contains("\"") || (str.startsWith("\"") && str.endsWith("\""));
    }

    private static <T extends Number> void printResults(List<Set<List<T>>> groups) throws IOException {
        long groupCount = groups.stream().filter(group -> group.size() > 1).count();
        System.out.println("Number of groups with more than one element: " + groupCount);
        writeOutput(groups, "output.txt");
    }

    public static <T> List<Set<List<T>>> findAndMergeGroups(Set<List<T>> data) {
        List<List<T>> rows = new ArrayList<>(data);
        UnionFind uf = new UnionFind(rows.size());
        Map<Integer, Map<T, List<Integer>>> columnValueToIndices = new HashMap<>();

        for (int rowIndex = 0; rowIndex < rows.size(); rowIndex++) {
            List<T> row = rows.get(rowIndex);
            for (int colIndex = 0; colIndex < row.size(); colIndex++) {
                T value = row.get(colIndex);
                if (value == null) continue;

                columnValueToIndices.putIfAbsent(colIndex, new HashMap<>());
                Map<T, List<Integer>> valueToIndices = columnValueToIndices.get(colIndex);

                if (!valueToIndices.containsKey(value)) {
                    valueToIndices.put(value, new ArrayList<>());
                }

                for (Integer existingRowIndex : valueToIndices.get(value)) {
                    uf.union(rowIndex, existingRowIndex);
                }

                valueToIndices.get(value).add(rowIndex);
            }
        }
        // Group rows by their root in Union-Find
        Map<Integer, Set<List<T>>> rootToGroup = new HashMap<>();
        for (int rowIndex = 0; rowIndex < rows.size(); rowIndex++) {
            int root = uf.find(rowIndex);
            rootToGroup.putIfAbsent(root, new HashSet<>());
            rootToGroup.get(root).add(rows.get(rowIndex));
        }
        return new ArrayList<>(rootToGroup.values());
    }


    public static <T extends Number> void sortGroups(List<Set<List<T>>> groups) {
        groups.sort((group1, group2) -> Integer.compare(group2.size(), group1.size()));
    }


    private static <T> void writeOutput(List<Set<List<T>>> groups, String outputFilePath) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFilePath))) {
            for (int i = 0; i < groups.size(); i++) {
                writer.write("Группа " + (i + 1) + "\n");
                for (List<T> row : groups.get(i)) {
                    writer.write(row.toString()
                            .substring(1, row.toString().length() - 1) + "\n");
                }
                writer.write("\n");
            }
        }
    }
}
