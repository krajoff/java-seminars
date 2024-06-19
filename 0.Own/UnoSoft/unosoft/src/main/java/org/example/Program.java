package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Program {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java -jar unosoft.jar <input-file.txt>");
            System.exit(1);
        }
        String inputFilePath = args[0];
        long startTime = System.currentTimeMillis();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String firstLine = reader.readLine();
            if (firstLine == null || firstLine.isEmpty()) {
                throw new IllegalArgumentException("Input file is empty.");
            }
            Set<List<Double>> data = readFileAsDouble(reader, firstLine);
            List<Set<List<Double>>> groups = findAndMergeGroups(data);
            sortGroups(groups);
            printResults(groups);
        } catch (IOException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println("Time execution: " + executionTime + " ms");
    }

    private static <T extends Number> List<Set<List<T>>> findAndMergeGroups(Set<List<T>> data) {
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

    public static Set<List<Double>> readFileAsDouble(BufferedReader reader, String firstLine) throws IOException {
        Set<List<Double>> set = new HashSet<>();
        String line = firstLine;
        List<String> stringList;
        while (line != null) {
            stringList = Arrays.stream(line.split(";"))
                    .filter(Program::isValidNumberString)
                    .map(str -> str.replace("\"", ""))
                    .toList();
            List<Double> row = new ArrayList<>(stringList.size());
            if (stringList.stream().noneMatch(s -> s.contains("\""))) {
                for (String part : stringList) {
                    if (part.isEmpty()) {
                        row.add(null);
                    } else {
                        row.add(Double.parseDouble(part));
                    }
                }
                set.add(row);
            }
            line = reader.readLine();
        }
        return set;
    }

    public static Set<List<Long>> readFileAsLong(BufferedReader reader, String firstLine) throws IOException {
        Set<List<Long>> set = new HashSet<>();
        String line = firstLine;
        List<String> stringList;
        while (line != null) {
            stringList = Arrays.stream(line.split(";"))
                    .filter(Program::isValidNumberString)
                    .map(str -> str.replace("\"", ""))
                    .toList();
            List<Long> row = new ArrayList<>(stringList.size());
            if (stringList.stream().noneMatch(s -> s.contains("\""))) {
                for (String part : stringList) {
                    if (part.isEmpty()) {
                        row.add(null);
                    } else {
                        row.add(Long.parseLong(part));
                    }
                }
                set.add(row);
            }
            line = reader.readLine();
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

