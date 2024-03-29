public class Splay extends BST {

    @Override
    public void add(String word) {
        SplayNode newNode = addNode(word);
        splay(newNode);
    }

    SplayNode addNode(String word) {
        SplayNode newNode = addNode((SplayNode)root, (SplayNode)root, word);
        if (root == null) {
            root = newNode;
        }
        return newNode;
    }

    private SplayNode addNode(SplayNode current, SplayNode parent, String word) {
        if (current == null) {
            return new SplayNode(word, parent);
        }

        int compare = word.compareTo(current.getWord());
        if (compare < 0) { // word is smaller than current.word
            if (current.getLeft() != null) {
                return addNode(current.getLeft(), current, word);
            } else {
                current.setLeft(new SplayNode(word, current));
                return current.getLeft();
            }
        } else if (compare > 0) { // word is greater than current.word
            if (current.getRight() != null) {
                return addNode(current.getRight(), current, word);
            } else {
                current.setRight(new SplayNode(word, current));
                return current.getRight();
            }
        } else {
            current.incrementCounter();
            return current;
        }
    }

    /**
     * Moves node to the top of the tree (to become a new root).
     */
    private void splay(SplayNode node) {
        if (node == null) {
            return; // do nothing
        }
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
    void rotate(SplayNode parent, SplayNode child) throws Exception {
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
        } else {
            parent.setRight(child.getLeft());
            child.setLeft(parent);
        }
        child.setParent(grandparent);
    }
}

