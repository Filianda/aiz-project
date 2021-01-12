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
        ((SplayNode) left).setParent(this);
    }

    @Override
    public SplayNode getRight() {
        return (SplayNode) super.getRight();
    }

    @Override
    public void setRight(Node right) {
        super.setRight(right);
        ((SplayNode) right).setParent(this);
    }
    
    public SplayNode getParent() {
        return (SplayNode) parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
}

