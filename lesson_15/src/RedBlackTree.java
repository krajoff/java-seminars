import java.io.PrintWriter;

public class RedBlackTree {
    private Node root;

    private class Node {
        private int value;
        private Color color;
        private Node leftchild;
        private Node rightchild;
    }

    public boolean add(int value) {
        if (root != null) {
            boolean result = addNode(root, value);
            root = rebalance(root);
            root.color = Color.BLACK;
            return result;
        } else {
            root = new Node();
            root.color = Color.BLACK;
            root.value = value;
            return true;
        }
    }

    public boolean add(int... arg) {
        for (int j : arg) {
            this.add(j);
        }
        return true;
    }

    private boolean addNode(Node node, int value) {
        if (node.value == value) {
            return false;
        } else {
            if (node.value > value) {
                if (node.leftchild != null) {
                    boolean result = addNode(node.leftchild, value);
                    node.leftchild = rebalance(node.leftchild);
                    return result;
                } else {
                    node.leftchild = new Node();
                    node.leftchild.color = Color.RED;
                    node.leftchild.value = value;
                    return true;
                }
            } else {
                if (node.rightchild != null) {
                    boolean result = addNode(node.rightchild, value);
                    node.rightchild = rebalance(node.rightchild);
                    return result;
                } else {
                    node.rightchild = new Node();
                    node.rightchild.color = Color.RED;
                    node.rightchild.value = value;
                    return true;
                }
            }
        }
    }

    private Node rebalance(Node node) {
        Node result = node;
        boolean needrebalance;
        do {
            needrebalance = false;
            if (result.rightchild != null &&
                    result.rightchild.color == Color.RED &&
                    (result.leftchild == null ||
                            result.leftchild.color == Color.BLACK)) {
                needrebalance = true;
                result = swapRight(result);
            }
            if (result.leftchild != null &&
                    result.leftchild.color == Color.RED &&
                    result.leftchild.leftchild != null &&
                    result.leftchild.leftchild.color == Color.RED) {
                needrebalance = true;
                result = swapLeft(result);
            }
            if (result.leftchild != null &&
                    result.leftchild.color == Color.RED &&
                    result.rightchild != null &&
                    result.rightchild.color == Color.RED) {
                needrebalance = true;
                swapColor(result);
            }
        } while (needrebalance);
        return result;
    }

    private void swapColor(Node node) {
        node.rightchild.color = Color.BLACK;
        node.leftchild.color = Color.BLACK;
        node.color = Color.RED;
    }

    private Node swapLeft(Node node) {
        Node leftchild = node.leftchild;
        Node betweenchild = leftchild.rightchild;
        leftchild.rightchild = node;
        node.leftchild = betweenchild;
        leftchild.color = node.color;
        node.color = Color.RED;
        return leftchild;
    }

    private Node swapRight(Node node) {
        Node rightchild = node.rightchild;
        Node betweenchild = rightchild.leftchild;
        rightchild.leftchild = node;
        node.rightchild = betweenchild;
        rightchild.color = node.color;
        node.color = Color.RED;
        return rightchild;
    }


    private enum Color {
        BLACK, RED
    }

    public void printTree() {
        printHelper(root, "", true);
    }

    private void printHelper(Node root, String indent, boolean last) {
        if (root != null) {
            System.out.print(indent);
            if (last) {
                System.out.print("П----");
                indent += "     ";
            } else {
                System.out.print("Л----");
                indent += "|    ";
            }

            String sColor = root.color == Color.RED ? "красный" : "черный";
            System.out.println(root.value + "(" + sColor + ")");
            printHelper(root.leftchild, indent, false);
            printHelper(root.rightchild, indent, true);
        }
    }

}
