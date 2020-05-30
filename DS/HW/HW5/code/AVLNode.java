public class AVLNode<T extends Comparable> {
    private CDLL<T> locationList;
    private String item;
    private AVLNode leftChild;
    private AVLNode rightChild;
    private int leftHeight;
    private int rightHeight;
    private int balance;

    public AVLNode(String newItem, T location) {
        item = newItem;
        locationList = new CDLL<T>();
        locationList.add(location);
        leftChild = null;
        rightChild = null;
        leftHeight = 0;
        rightHeight = 0;
        balance = 0;
    }

    public AVLNode(String newItem, T location, AVLNode left, AVLNode right) {
        item = newItem;
        locationList = new CDLL<T>();
        locationList.add(location);
        leftChild = left;
        rightChild = right;
        leftHeight = height(left);
        rightHeight = height(right);
        balance = leftHeight + rightHeight;
    }

    public void updateBalance() {
       leftHeight = height(leftChild);
       rightHeight = height(rightChild);
       balance = rightHeight - leftHeight;
    }

    public int height(AVLNode root) {
        if (root == null) return 0;
        return (height(leftChild) + height(rightChild) + 1);
    }

    public int getLeftHeight() {
        return leftHeight;
    }

    public int getRightHeight() {
        return rightHeight;
    }

    public int getBalance() {
        return balance;
    }

    public String getItem() {
        return item;
    }

    public CDLL<T> getLocationList() {
        return locationList;
    }

    public AVLNode getLeftChild() {
        return leftChild;
    }

    public AVLNode getRightChild() {
        return rightChild;
    }

    public void setLeftChild(AVLNode leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightChild(AVLNode rightChild) {
        this.rightChild = rightChild;
    }
}
