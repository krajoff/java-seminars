package org.example;

class TrieNode {
    TrieNode[] children;
    boolean isEndOfNumber;
    String clientData; // Можно заменить на более сложную структуру, если требуется

    public TrieNode() {
        children = new TrieNode[10]; // Массив для цифр от 0 до 9
        isEndOfNumber = false;
        clientData = null;
    }
}
