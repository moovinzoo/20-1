public class AVL<T extends Comparable> {
    private AVLNode<T> root;
    private int numItems;

    public AVL() {
        root = null;
        numItems = 0;
    }

    public int getNumItems() {
        return numItems;
    }

    public AVLNode search(String key) {
        return find(root, key);
    }

    private AVLNode find(AVLNode<T> rt, String key) {
        if (rt == null) return null;

        int compareKey = rt.getItem().compareTo(key);
        if (compareKey == 0) return rt;
        else if (compareKey > 0) {
            return find(rt.getLeftChild(), key);
        } else {
            return find(rt.getRightChild(), key);
        }
    }

    public CDLL getLocationList(String key) {
        return search(key).getLocationList();
    }

    public void add(String key, T location) {
        if (root == null) { // new Node
            root = new AVLNode<T>(key, location);
        } else {
            insert(root, key, location);
        }
    }

    // 새로운 노드가 삽입되면 true를 리턴하도록 해서, recursively balance가 수정될 수 있도록 한다.
    // ** Parent ptr가 추가되는 것은 불필요하다.
    private boolean insert(AVLNode<T> rt, String key, T location) {

    }
//        int compareKey = rt.getItem().compareTo(key);
//        if (compareKey == 0) {
//            rt.getLocationList().add(location);
//            return false;
//        }
//        else if (compareKey > 0) {
//            if (rt.getLeftChild() == null) {
//                rt.setLeftChild(new AVLNode<T>(key, location));
//                updateBalance(rt);
//                return;
//            } else {
//                insert(rt.getLeftChild(), key, location);
//            }
//        } else {
//            if (rt.getRightChild() == null) {
//                rt.setRightChild(new AVLNode<T>(key, location));
//                updateBalance(rt);
//                return;
//            } else {
//                insert(rt.getLeftChild(), key, location);
//            }
//        }
//    }

//    public void add(String key, T location) {
//       insert(root, key, location);
//    }
////
////    public AVLNode<T> findParent(AVLNode<T> child) {
////
////    }
//
//    private void insert(AVLNode<T> rt, String key, T location) {
//        if (rt == null) { // new Node
//            rt = new AVLNode<T>(key, location);
//        }
//        int compareKey = rt.getItem().compareTo(key);
//        if (compareKey == 0) rt.getLocationList().add(location);
//        else if (compareKey > 0) {
//            insert(rt.getLeftChild(), key, location);
//        } else {
//            insert(rt.getRightChild(), key, location);
//        }
//    }

    private void updateBalance(AVLNode<T> rt) {
        rt.updateBalance();
        if (rt.getBalance() > 1 || rt.getBalance() < -1) {
            rotate(rt);
        }
    }

    private void rotate(AVLNode<T> rt) {
    }


    private void leftRotate() {
    }

    private void rightRotate() {
    }

    public void delete(AVLNode<T> rt, T searchKey) {
    }

    private AVLNode<T> deleteItem(AVLNode<T> rt, T searchKey) {
        return new AVLNode();
    }

    private AVLNode<T> deleteNode(AVLNode<T> node) {
        return new AVLNode();
    }

    public AVLNode<T> search(AVLNode<T> rt, T sear) {

    }
}
