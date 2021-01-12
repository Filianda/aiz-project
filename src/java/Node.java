public class Node {
    private Node left;
    private Node right;

    private String word;
    private int counter;

    public Node(String word) {
        this.left = null;
        this.right = null;
        this.word = word;
        this.counter = 1;
    }

    public void incrementCounter() {
        this.counter++;
    }

    public boolean isLeaf() {
        return (left == null && right == null);
    }

    public boolean hasChild(Node node) {
        if (node == null) {
            return false;
        }
        return this.left == node || this.right == node;
    }

    public String getWord() {
        return word;
    }

    public int getCounter() {
        return counter;
    }
    
    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return String.format("Node[%s, %d]", word, counter);
    }
}

