public class BinarySearchTree {
    private TreeNode root;
    private int numItems;

    public BinarySearchTree() {
    }

    public TreeNode search(TreeNode root, Object searchKey) {
        if (root is empty) return "Not found!";
        else if (searchKey == root's key) return root;
        else if (searchKey < root's key)
            return search(root's left child, searchKey);
        else
            return search(root's right child, searchKey);
    }

    public void insert_prev(TreeNode root, Object newKey) {
        if (root is null)
            newItem을 key로 갖는 새 node를 매단다;
        else if (newItem < root's key)
            insert(root's left child, newItem);
        else
            insert(root's right child, newItem);
    }// search와 구조가 거의 유사하다.

    public void delete_prev(TreeNode rootNode, Object searchKey) {
        dNode = search(root, searchKey);
        deleteNode(dNode);
    }

    public void delete(TreeNode rootNode, Object searchKey) {
        root = deleteItem(root, searchKey);
    }

    private TreeNode deleteItem(TreeNode tNode, Object searchKey) {
        if(tNode == null) {exception} // item not found!
        else {
            if (searchKey == tNode's key) { // item found!
                tNode = deleteNode(tNode);
            } else if (searchKey < tNode's key) {
                tNode.setLeft(deleteItem(tNode.getLeft(), searchKey));
            } else {
                tNode.setRight(deleteItem(tNode.getRight(), searchKey));
            }
        }

        return tNode; // tNode; parent에매달리는 Node
    }

    private TreeNode deleteNode_prev(TreeNode tNode) {
        if (dNode is a leaf) // case 1
            dNode 삭제;     
        else if (dNode has only one child c) // case 2
            c replace dNode;
        else { // case 3
            minNode = dNode's right subtree의 leftmost node;
            // minNode has at most one right child
            minNode replaces dNode;
            deleteNode(minNode); // case 1 or 2
    }

    private TreeNode deleteNode(TreeNode tNode) {
        // 1. tNode is a leaf
        // 2. tNode has only one child
        // 3. tNode has two children

        // case 1
        if ((tNode.getLeft() == null) && (tNode.getRight() == null)) {
            return null;
        } else if (tNode.getLeft() == null) { // case 2
            return tNode.getRight();
        } else if (tNode.getRight() == null) { // case 2
            return tNode.getLeft();
        } else { // case 3 - two children
            tNode.setItem(minimum item of tNode's right subtree);
            tNode.setRight(deleteMin(tNode.getRight()));
            return tNode; // tNode survived
        }
    }

    private TreeNode deleteMin(TreeNode tNode) {
        if (tNode.getLeft() == null) { // found min
            return tNode.getRight(); // right child moves to min's place
        } else { // branch left then backtrack
            tNode.setLeft(deleteMin(tNode.getLeft()));
            return tNode;
        }
    }


    public void insert(TreeNode root, Object newKey) {
        root = insertItem(root, newKey);
    }

    private TreeNode insertItem(TreeNode tNode, Object newItem) {
        if (tNode == null) // insert after leaf or into an empty tree
            tNode = new TreeNode(newItem, null, null);
        else if (newItem < tNode's item) // branch left
            tNode.setLeft(insertItem(tNode.getLeft(), newItem));
        else // branch right
            tNode.setRight(insertItem(tNode.getRight(), newItem));
        return tNode;
    }// tNode는 null일 때만, 값이 바뀐다.


}
