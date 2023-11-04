public class LinkedList {
    int size;
    Node head;

    public void add(int value) {
        Node newNode = new Node();
        newNode.value = value;
        newNode.next = null;
        newNode.previous = null;
        if (head == null)
            head = newNode;
        else {
            Node currentNode = head;
            while (currentNode.next != null)
                currentNode = currentNode.next;
            currentNode.next = newNode;
            newNode.previous = currentNode;
        }
        size++;
    }

    public void add(int... arg) {
        for (int j : arg) {
            this.add(j);
        }
    }

    public void revert() {
        if (head != null && head.next != null) {
            Node temp = head;
            revert(head.next, head);
            temp.next = null;
        }
    }

    private void revert(Node currentNode, Node previousNode) {
        if (currentNode.next == null) {
            head = currentNode;
        } else {
            revert(currentNode.next, currentNode);
        }
        currentNode.next = previousNode;
    }

    public void remove(int value) {
        Node currentNode = head;
        while (true) {
            if (currentNode == null)
                return;
            Node pNode = currentNode.previous;
            Node nNode = currentNode.next;
            if (currentNode.value == value) {
                if (currentNode.previous == null) {
                    nNode.previous = null;
                    head = nNode;
                    currentNode = null;
                } else if (currentNode.next == null) {
                    pNode.next = null;
                    currentNode = null;
                } else {
                    pNode.next = nNode;
                    nNode.previous = pNode;
                    currentNode = null;

                }
                size--;
                return;
            }
            currentNode = currentNode.next;
        }
    }

    public void print() {
        if (head != null) {
            System.out.print("[");
            Node currentNode = head;
            while (currentNode.next != null) {
                System.out.print(currentNode.value + " ");
                currentNode = currentNode.next;
            }
            System.out.println(currentNode.value + "] : " + size);
        }
    }
}