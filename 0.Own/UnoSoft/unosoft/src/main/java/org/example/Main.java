package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
//        if (args.length != 1) {
//            System.err.println("Usage: java -jar unosoft.jar <input-file.txt>");
//            System.exit(1);
//        }
//        String inputFilePath = args[0];
        long startTime = System.currentTimeMillis();
        String inputFilePath = "D:\\JAVA\\java-seminars\\0.Own\\UnoSoft\\unosoft\\build\\libs\\lng-big.csv";
        Set<List<Double>> set = readFile(inputFilePath, Double.class);
        List<Set<List<Double>>> groups = findAndMergeGroups(set);
        sortGroups(groups);
        long groupCount = groups.stream().filter(group -> group.size() > 1).count();
        System.out.println("Groups number with more than one element: " + groupCount);
        writeOutput(groups, "output.txt");
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println("Time execution: " + executionTime + " ms");
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

    public static <T extends Number> Set<List<T>> readFile(String filename, Class<T> clazz) throws IOException {
        Set<List<Double>> doubleSet = readFileAsDoubles(filename);
        if (clazz == Double.class) {
            return doubleSet.stream()
                    .map(list -> (List<T>) list)
                    .collect(Collectors.toSet());
        } else if (clazz == Long.class) {
            return doubleSet.stream()
                    .map(list -> list.stream()
                            .map(Double::longValue)
                            .map(clazz::cast)
                            .collect(Collectors.toList()))
                    .collect(Collectors.toSet());
        } else {
            throw new IllegalArgumentException("Unsupported class type: " + clazz);
        }
    }

    private static boolean isValidNumberString(String str) {
        return !str.contains("\"") || (str.startsWith("\"") && str.endsWith("\""));
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


    public static <T> void sortGroups(List<Set<List<T>>> groups) {
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
