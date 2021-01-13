public class SplayNode extends Node {
    Node parent;

    public SplayNode(String word, SplayNode parent) {
        super(word);
        this.parent = parent;
    }

    @Override
    public SplayNode getLeft() {
        return (SplayNode) super.getLeft();
    }

    @Override
    public void setLeft(Node left) {
        super.setLeft(left);
        if (left != null) {
            ((SplayNode) left).setParent(this);
        }
    }

    @Override
    public SplayNode getRight() {
        return (SplayNode) super.getRight();
    }

    @Override
    public void setRight(Node right) {
        super.setRight(right);
        if (right != null) {
            ((SplayNode) right).setParent(this);
        }
    }
    
    public SplayNode getParent() {
        return (SplayNode) parent;
    }

    public SplayNode getGrandparent() {
        if (parent == null) {
            return null;
        } else {
            return ((SplayNode)parent).getParent();
        }
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public boolean isLeftChild() {
        if (parent == null) {
            return false;
        }
        return parent.getLeft() == this;
    }

    public boolean isRightChild() {
        if (parent == null) {
            return false;
        }
        return parent.getRight() == this;
    }
}

