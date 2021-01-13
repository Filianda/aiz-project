public class BST {
    
    private Node root;

    public BST() {
        this.root = null;
    }

    public void add(String word) {
        Node newNode = add(root, word);
        if (root == null) {
            root = newNode;
        }
    }

    Node add(Node current, String word) {
        if (current == null) {
            return new Node(word);
        }
        
        int compare = word.compareTo(current.getWord());
        if (compare < 0) { // word is smaller than current.word
            if (current.getLeft() != null) {
                return add(current.getLeft(), word);
            } else {
                current.setLeft(new Node(word));
                return current.getLeft();
            }
        } else if (compare > 0) { // word is greater than current.word
            if (current.getRight() != null) {
                return add(current.getRight(), word);
            } else {
                current.setRight(new Node(word));
                return current.getRight();
            }
        } else { // word is equal to current.word
            current.incrementCounter();
            return current;
        }
    }

    private int contains(Node current, String word) {
        if (current == null) {
            return 0;
        } 
        if (word.equals(current.getWord())) {
            return 1;
        } 
        int compare = word.compareTo(current.getWord());
        return compare < 0 ? contains(current.getLeft(), word) + 1 : contains(current.getRight(), word) + 1;
    }

    public boolean contains(String word) {
        return (contains(root, word) > 0);
    }

    /**
     * This method returns number of operations needed
     * to search a given word. If the word is not present
     * in the tree returned value is 0.
     *
     * @param word word to be calculated
     */
    public int getLevel(String word) {
        return contains(root, word);
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

