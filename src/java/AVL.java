public class AVL extends Splay {

    @Override
    public void add(String word) {
        SplayNode newNode = super.addNode(word);
        try {
            doBalanceFromNodeToRoot(newNode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doBalanceFromNodeToRoot(SplayNode node) throws Exception {
        if (node == null || node.getGrandparent() == null) {
            return;
        }

        while (node.getGrandparent() != null) {
            if (isUnabalnced(node.getGrandparent())) {
                int balance = getBalance(node.getGrandparent());
                if (balance < -1) { // sub-tree is tilted to the right
                    if (node.isRightChild()) {
                        zigZig(node);
                    } else {
                        zigZag(node);
                    }
                } else if (balance > 1) { // sub-tree is tilted to the left
                    if (node.isLeftChild()) {
                        zigZig(node);
                    } else {
                        zigZag(node);
                    }
                }
                break;
            }
            node = node.getParent();
        }
    }

    private void zigZig(SplayNode lowerNode) throws Exception {
        rotate(lowerNode.getGrandparent(), lowerNode.getParent());
    }

    private void zigZag(SplayNode lowerNode) throws Exception {
        if (lowerNode == null || lowerNode.getGrandparent() == null) {
            return;
        }
        SplayNode parent = lowerNode.getParent();
        SplayNode grandparent = lowerNode.getGrandparent();
        rotate(parent, lowerNode);
        rotate(grandparent, lowerNode);
    }

    private boolean isUnabalnced(Node node) {
        return Math.abs(getBalance(node)) > 1;
    }
  
    private int getBalance(Node node) { 
        if (node == null) {
            return 0;
        }
        return measureDeepestBranch(node.getLeft()) - measureDeepestBranch(node.getRight()); 
    }
}

