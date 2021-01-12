public class BST {
    
    private Node root;

    public BST() {
        this.root = null;
    }

    public void add(String word) {
        add(root, word);
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

    private boolean contains(Node current, String word) {
        if (current == null) {
            return false;
        } 
        if (word.equals(current.getWord())) {
            return true;
        } 
        int compare = word.compareTo(current.getWord());
        return compare < 0 ? contains(current.getLeft(), word) : contains(current.getRight(), word);
    }

    public boolean contains(String word) {
        return contains(root, word);
    }

    public Node getRoot() {
        return root;
    }

    // liczenie wysokości drzewa
    public int measureDeepestBranch() {
        return measureDeepestBranch(root);
    }

    protected int measureDeepestBranch(Node node) {
        if (node == null || node.ifLeaf()) {
            return 0;
        }
        int leftChildDepth = measureDeepestBranch(node.getLeft());
        int rightChildDepth = measureDeepestBranch(node.getRight());
        return leftChildDepth < rightChildDepth ? rightChildDepth + 1 : leftChildDepth + 1;
    }
}

