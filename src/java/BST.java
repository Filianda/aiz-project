public class BST {
    
    private Node root;

    public BST() {
        this.root = null;
    }

    public void add(String word) {
        root = add(root, word);
    }

    Node add(Node current, String word) {
        if (current == null) {
            return new Node(word);
        }
        
        int compare = word.compareTo(current.getWord());
        if (compare < 0) { // word is smaller than current.word
            current.setLeft(add(current.getLeft(), word));
        } else if (compare > 0) { // word is greater than current.word
            current.setRight(add(current.getRight(), word));
        } else {
            current.incrementCounter();
            return current;
        }
        
        return current;
    }

    private int contains(Node current, String word) {
        if (current == null) {
            return 0;
        } 
        if (word.equals(current.getWord())) {
            return 1;
        } 
        int compare = word.compareTo(current.getWord());
        return compare < 0 ? contains(current.getLeft(), word) : contains(current.getRight(), word);
    }

    public boolean contains(String word) {
        return (contains(root, word) == 1);
    }

    public Node getRoot() {
        return root;
    }

    public int getNumberOfNodes() {
        return getNumberOfNodes(root);
    }

    private int getNumberOfNodes(Node node) {
        if (node == null) {
            return 0;
        }
        if (node.isLeaf()) {
            return 1;
        }
        return getNumberOfNodes(node.getLeft()) + getNumberOfNodes(node.getRight()) + 1;
    }

    // liczenie wysokości drzewa
    public int measureDeepestBranch() {
        return measureDeepestBranch(root);
    }

    protected int measureDeepestBranch(Node node) {
        if (node == null) {
            return 0;
        }
        if (node.isLeaf()) {
            return 1;
        }
        int leftChildDepth = measureDeepestBranch(node.getLeft());
        int rightChildDepth = measureDeepestBranch(node.getRight());
        return leftChildDepth < rightChildDepth ? rightChildDepth + 1 : leftChildDepth + 1;
    }
}

