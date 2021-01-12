public class AVL extends BST {

    private AVL root;
    
    @Override 
    public Node add(Node current, String word) { 
        // 1
        super.add(current, word);
 
        /* 2. Update height of this ancestor current */
        int currentHeight = measureDeepestBranch(current);
   
        /* 3. Get the balance factor of this ancestor node to check whether this node became unbalanced Różnica ma być ok 1*/
        int balance = getBalance(current); 
  
        // If this current becomes unbalanced, then there are 4 cases Left Left Case 
        int compareCurrentToLeft = word.compareTo(current.getLeft().getWord());
        int compareCurrentToRight = word.compareTo(current.getRight().getWord());
        if (balance > 1 && compareCurrentToLeft < 0 ) 
            return rightRotate(current); 
  
        // Right Right Case 
        if (balance < -1 && compareCurrentToRight > 0) 
            return leftRotate(current); 
  
        // Left Right Case 
        if (balance > 1 && compareCurrentToLeft > 0) { 
            current.setLeft(leftRotate(current.getLeft())); 
            return rightRotate(current); 
        } 
  
        // Right Left Case 
        if (balance < -1 && compareCurrentToRight < 0) { 
            current.setRight(leftRotate(current.getRight())); 
            return leftRotate(current); 
        } 
  
        /* return the (unchanged) current pointer */
        return current; 
    } 
  

    // A utility function to right rotate subtree rooted with y 
    Node rightRotate(Node y) { 
        Node x = y.getLeft(); 
        Node T2 = x.getRight(); 
  
        // Perform rotation 
        x.setRight(y); 
        y.setLeft(T2); 
  
        // Update heights 
        int currentHeightY = measureDeepestBranch(y);
        int currentHeightX = measureDeepestBranch(x);
        /*
        y.height = max(height(y.left), height(y.right)) + 1; 
        x.height = max(height(x.left), height(x.right)) + 1; 
  */
        // Return new root 
        return x; 
    } 
  
    // A utility function to left rotate subtree rooted with x 
    Node leftRotate(Node x) { 
        Node y = x.getRight(); 
        Node T2 = y.getLeft(); 
  
        // Perform rotation 
        y.setLeft(x); 
        x.setRight(T2); 
  
         // Update heights 
         int currentHeightY = measureDeepestBranch(y);
         int currentHeightX = measureDeepestBranch(x);
         /*
         y.height = max(height(y.left), height(y.right)) + 1; 
         x.height = max(height(x.left), height(x.right)) + 1; 
   */
  
        // Return new root 
        return y; 
    } 
  
    // Get Balance factor of node N 
    int getBalance(Node Node) { 
        if (Node == null) 
            return 0; 
  
        return measureDeepestBranch(Node.getLeft()) - measureDeepestBranch(Node.getRight()); 
    } 
 
}

