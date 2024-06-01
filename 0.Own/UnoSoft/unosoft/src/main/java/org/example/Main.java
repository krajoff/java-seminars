package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Usage: java -jar unosoft.jar <input-file.txt>");
            System.exit(1);
        }
        String inputFilePath = args[0];
        long startTime = System.currentTimeMillis();
        //String inputFilePath = "D:\\JAVA\\java-seminars\\0.Own\\UnoSoft\\unosoft\\src\\main\\resources\\lng-4.txt";
        Set<List<Long>> set = getLinkedHashSet(inputFilePath);
        List<Set<List<Long>>> groups = getGroups(set);
        sortGroups(groups);
        long groupCount = groups.stream().filter(group -> group.size() > 1).count();
        System.out.println("Groups number with more than one element: " + groupCount);
        writeOutput(groups, "output.txt");
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println("Time execution: " + executionTime + " ms");
    }

    private static Set<List<Long>> getLinkedHashSet(String inputFilePath) throws IOException {
        Set<List<Long>> set = new LinkedHashSet<>();
        String string;
        List<String> stringList;
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(inputFilePath)))) {
            while ((string = reader.readLine()) != null) {
                stringList = Arrays.stream(string.split(";"))
                        .map(s -> s.substring(1, s.length() - 1))
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

    private static List<Set<List<Long>>> getGroups(Set<List<Long>> data) {
        // column, long, set
        Map<Integer, Map<Long, Set<List<Long>>>> columnValueToGroup = new HashMap<>();
        List<Set<List<Long>>> groups = new ArrayList<>();

        for (List<Long> row : data) {
            Set<List<Long>> matchedGroup = null;
            for (int i = 0; i < row.size(); i++) {
                Long value = row.get(i);
                if (value == null) continue; // Пропускаем отсутствующие значения

                columnValueToGroup.putIfAbsent(i, new HashMap<>());
                Map<Long, Set<List<Long>>> valueToGroup = columnValueToGroup.get(i);

                if (valueToGroup.containsKey(value)) {
                    matchedGroup = valueToGroup.get(value);
                    break;
                }
            }

            if (matchedGroup == null) {
                matchedGroup = new HashSet<>();
                groups.add(matchedGroup);
            }

            matchedGroup.add(row);
            for (int i = 0; i < row.size(); i++) {
                Long value = row.get(i);
                if (value == null) continue;
                columnValueToGroup.get(i).put(value, matchedGroup);
            }
        }

        return groups;
    }

    public static void sortGroups(List<Set<List<Long>>> groups) {
        groups.sort((group1, group2) -> Integer.compare(group2.size(), group1.size()));
    }

    private static void writeOutput(List<Set<List<Long>>> groups, String outputFilePath) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFilePath))) {
            for (int i = 0; i < groups.size(); i++) {
                writer.write("Группа " + (i + 1) + "\n");
                for (List<Long> row : groups.get(i)) {
                    writer.write(row.toString()
                            .substring(1, row.toString().length() - 1) + "\n");
                }
                writer.write("\n");
            }
        }
    }


}
