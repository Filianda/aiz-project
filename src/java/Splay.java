public class Splay extends BST {

    private SplayNode root;

    @Override
    public void add(String word) {
        SplayNode node = add(root, root, word);
        if (node != null) {
            splay(node);
        }
    }

    private SplayNode add(SplayNode current, SplayNode parent, String word) {
        if (current == null) {
            return new SplayNode(word, parent);
        }

        SplayNode newNode;
        int compare = word.compareTo(current.getWord());
        if (compare < 0) { // word is smaller than current.word
            newNode = add(current.getLeft(), current, word);
            current.setLeft(newNode);
        } else if (compare > 0) { // word is greater than current.word
            newNode = add(current.getRight(), current, word);
            current.setRight(newNode);
        } else {
            newNode = null;
            current.incrementCounter();
        }
        return newNode;
    }

    /**
     * Moves node to the top of the tree (to become a new root).
     */
    private void splay(SplayNode node) {
        try {
            while (root != node) {
                rotate(node.getParent(), node);
            }
        } catch ( Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Rotates pair of "parent-child" nodes.
     * If we want to rotate parent B and child D nodes:
     * 
     *           F                        F
     *          / \                      / \
     *         B   G                    D   G
     *        / \         --->         / \
     *       A   D                    B   E
     *          / \                  / \
     *         C   E                A   C
     * 
     */
    private void rotate(SplayNode parent, SplayNode child) throws Exception {
        // STEP 1. VALIDATE ARGUMENTS
        if (parent == null || child == null) {
            return;
        }
        if (!parent.hasChild(child)) {
            throw new Exception("Wrong arguments. " + child + " is not a child of " + parent);
        }

        // STEP 2. UPDATE CHILD IN GRANDPARENT
        SplayNode grandparent = parent.getParent();
        if (grandparent == null) {
            root = child;
        } else {
            if (parent == grandparent.getLeft()) {
                grandparent.setLeft(child);
            } else {
                grandparent.setRight(child);
            }
        }

        // STEP 3. ROTATE
        if (parent.getLeft() == child) {
            parent.setLeft(child.getRight());
            child.setRight(parent);
        } else{
            parent.setRight(child.getLeft());
            child.setLeft(parent);
        }
    }
}

