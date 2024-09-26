package org.example;

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Добавление клиента
    public void addClient(String phoneNumber, String clientData) {
        TrieNode currentNode = root;
        for (char digit : phoneNumber.toCharArray()) {
            int index = digit - '0'; // Конвертируем символ цифры в соответствующий индекс 0-9
            if (currentNode.children[index] == null) {
                currentNode.children[index] = new TrieNode();
            }
            currentNode = currentNode.children[index];
        }
        currentNode.isEndOfNumber = true;
        currentNode.clientData = clientData;
    }

    // Поиск клиента по номеру телефона
    public String findClient(String phoneNumber) {
        TrieNode currentNode = root;
        for (char digit : phoneNumber.toCharArray()) {
            int index = digit - '0';
            if (currentNode.children[index] == null) {
                return null; // Клиент с таким номером не найден
            }
            currentNode = currentNode.children[index];
        }
        return currentNode.isEndOfNumber ? currentNode.clientData : null;
    }

    // Удаление клиента
    public boolean deleteClient(String phoneNumber) {
        return deleteHelper(root, phoneNumber, 0);
    }

    private boolean deleteHelper(TrieNode currentNode, String phoneNumber, int depth) {
        if (depth == phoneNumber.length()) {
            if (!currentNode.isEndOfNumber) {
                return false; // Номер телефона не найден
            }
            currentNode.isEndOfNumber = false;
            currentNode.clientData = null;
            return isNodeEmpty(currentNode); // Проверяем, пустой ли узел, чтобы можно было удалить его
        }

        int index = phoneNumber.charAt(depth) - '0';
        TrieNode childNode = currentNode.children[index];
        if (childNode == null) {
            return false; // Номер телефона не найден
        }

        boolean shouldDeleteChild = deleteHelper(childNode, phoneNumber, depth + 1);

        if (shouldDeleteChild) {
            currentNode.children[index] = null;
            return isNodeEmpty(currentNode) && !currentNode.isEndOfNumber;
        }

        return false;
    }

    // Вспомогательная функция для проверки, пустой ли узел (не содержит ли он детей)
    private boolean isNodeEmpty(TrieNode node) {
        for (int i = 0; i < 10; i++) {
            if (node.children[i] != null) {
                return false;
            }
        }
        return true;
    }
}
