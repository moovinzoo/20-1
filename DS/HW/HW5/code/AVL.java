import java.awt.*;

public class AVL<T extends Comparable<T>> {

    class AVLNode<T extends Comparable<T>> {
        // Node Items
        private CDLL<T> locationList;
        private String strChunk;

        // Child nodes
        private AVLNode leftChild;
        private AVLNode rightChild;

        // Members for the unique properties of AVL
        private int leftHeight;
        private int rightHeight;
        private int height;
        private int balance;

        public AVLNode(String newChunk, T location) {
            strChunk = newChunk;
            locationList = new CDLL<T>();
            locationList.add(location);
            leftChild = null;
            rightChild = null;
            this.updateNode();
        }

        public AVLNode(String newChunk, T location, AVLNode<T> left, AVLNode<T> right) {
            strChunk = newChunk;
            locationList = new CDLL<T>();
            locationList.add(location);
            leftChild = left;
            rightChild = right;
        }

        public void updateNode() {
            this.setHeight(updateHeight(this));
            int bal = this.getBalance();
            if (bal > 1 || bal < -1) rotate(this);
        }

        private int updateHeight(AVLNode<T> rt) {
            if (rt == null) return -1;

            int lHeight = updateHeight(rt.getLeftChild());
            int rHeight = updateHeight(rt.getRightChild());
            int rtHeight = (lHeight >= rHeight)? lHeight + 1 : rHeight + 1;

            rt.setLeftHeight(lHeight);
            rt.setRightHeight(rHeight);
            rt.setBalance(rHeight - lHeight);

            return rtHeight;
        }

        private void rotate(AVLNode<T> rt) {
            // Left rotation을 이용해 balancing해야 하는 경우
            if (rt.getBalance() > 1) {
                // 필요한 경우, 부분적으로 rotateRight하고
                if (rt.getRightChild().getBalance() < 0) {
                    rt.setRightChild(rotateRight(rt.getRightChild()));
                }
                // rt를 기준으로 전체적으로 rotateLeft한다.
                rotateLeft(rt);


            // Right rotation을 이용해 balancing해야 하는 경우
            } else if(rt.getBalance() < -1) {
                // 필요한 경우, 부분적으로 rotateLeft하고
                if (rt.getLeftChild().getBalance() > 0) {
                    rt.setLeftChild(rotateLeft(rt.getLeftChild()));
                }
                // rt를 기준으로 전체적으로 rotateRight한다.
                rotateRight(rt);
            }
        }

        private AVLNode<T> rotateLeft(AVLNode<T> rt) {
            AVLNode<T> newNode = rt.getRightChild();
            rt.setRightChild(newNode.getLeftChild());
            newNode.setLeftChild(rt);
            rt.updateNode();
            newNode.updateNode();

            return newNode;
        }

        private AVLNode<T> rotateRight(AVLNode<T> rt) {
            AVLNode<T> newNode = rt.getLeftChild();
            rt.setLeftChild(newNode.getRightChild());
            newNode.setRightChild(rt);
            rt.updateNode();
            newNode.updateNode();

            return newNode;
        }

        public String preorder() {
            StringBuffer sb = new StringBuffer();
            sb.append(this.getStrChunk());
            if (this.getLeftChild() != null) sb.append(" " + this.getLeftChild().preorder());
            if (this.getRightChild() != null) sb.append(" " + this.getRightChild().preorder());
            return sb.toString();
        }
//            if (currLocationList == null || currLocationList.size() == 0) {
//                sb.append("EMPTY");
//            } else {
//                for (int i = 0; i < currLocationList.size(); i++) {
//                    sb.append(currLocationList.get(i).toString());
//                }
//            }

        public void setBalance(int b) {
            balance = b;
        }

        public void setHeight(int h) {
            height = h;
        }

        public void setLeftHeight(int h) {
            leftHeight = h;
        }

        public void setRightHeight(int h) {
            rightHeight = h;
        }

        public void setLocationList(CDLL<T> _locationList) {
           locationList = _locationList;
        }

        public void setStrChunk(String _strChunk) {
            strChunk = _strChunk;
        }

        public int getBalance() {
            return balance;
        }

        public int getLeftHeight() {
            return leftHeight;
        }

        public int getRightHeight() {
            return rightHeight;
        }

        public String getStrChunk() {
            return strChunk;
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

        int compareKey = rt.getStrChunk().compareTo(key);
        if (compareKey == 0) return rt;
        else if (compareKey > 0) {
            return find(rt.getLeftChild(), key);
        } else {
            return find(rt.getRightChild(), key);
        }
    }

    public void preorder() {
        if (this.numItems == 0 || this.root == null) {
            System.out.println("EMPTY");
            return;
        } else {
            System.out.println(root.preorder());
        }
    }

    public void add(String key, T location) {
        root = insert(root, key, location);
    }

    // 새로운 노드가 삽입되면 true를 리턴하도록 해서, recursively balance가 수정될 수 있도록 한다.
    // ** Parent ptr가 추가되는 것은 불필요하다.
    private AVLNode insert(AVLNode rt, String key, T location) {

        if (rt == null) {
            numItems++;
            return (new AVLNode<T>(key, location));

        } else {
            int compareKey = rt.getStrChunk().compareTo(key);
            if (compareKey == 0) {
                // rt에 이미 삽입된 것과 동일한 key가 삽입되는 경우, 노드의 추가는 없고 List에 Location을 add 해준다.
                rt.getLocationList().add(location);
                return rt;

            } else if (compareKey > 0) {
                // rt의 left에 키가 삽입되야 하는 경우
                rt.leftChild = insert(rt.getLeftChild(), key, location);
                rt.updateNode();
                return rt;
            } else {
                // rt의 right에 키가 삽입되야 하는 경우
                rt.rightChild = insert(rt.getRightChild(), key, location);
                rt.updateNode();
                return rt;
            }
        }
    }
//    private AVLNode insert(AVLNode rt, String key, T location) {
//        if (rt == null) {
//            return (new AVLNode<T>(key, location));
////            rt.updateNode();
//            return true;
//
//        } else {
//            int compareKey = rt.getStrChunk().compareTo(key);
//            if (compareKey == 0) {
//                // rt에 이미 삽입된 것과 동일한 key가 삽입되는 경우, 노드의 추가는 없고 List에 Location을 add 해준다.
//                rt.getLocationList().add(location);
//                return false;
//
//            } else if (compareKey > 0) {
//                // rt의 left에 키가 삽입되야 하는 경우
//                if (insert(rt.getLeftChild(), key, location)) {
//                    rt.updateNode();
//                    return true;
//                }
//                return false;
//            } else {
//                // rt의 right에 키가 삽입되야 하는 경우
//                if (insert(rt.getRightChild(), key, location)) {
//                    rt.getRightChild().updateNode();
//                    return true;
//                }
//                return false;
//            }
//        }
//    }

    public void delete(String searchKey) {
        root = deleteItem(root, searchKey);
    }

    private AVLNode<T> deleteItem(AVLNode<T> rt, String searchKey) {
        if (rt == null) return null;
        else {
            int compare = searchKey.compareTo(rt.getStrChunk());
            if (compare == 0) {
                rt = deleteNode(rt);
                rt.updateNode();
            } else if (compare < 0) {
                rt.setLeftChild(deleteItem(rt.getLeftChild(), searchKey));
            } else {
                rt.setRightChild(deleteItem(rt.getRightChild(), searchKey));
            }
            return rt;
        }
    }

    private AVLNode<T> deleteNode(AVLNode<T> rt) {
        if ((rt.getLeftChild() == null) && (rt.getRightChild() == null)) {
            return null;
        } else if (rt.getLeftChild() == null) {
            return rt.getRightChild();
        } else if (rt.getRightChild() == null) {
            return rt.getLeftChild();
        } else {
            AVLNode<T> minNode = findMinNode(rt);
            rt.setLocationList(minNode.getLocationList());
            rt.setStrChunk(minNode.getStrChunk());
            rt.setRightChild(deleteItem(rt.getRightChild(), minNode.getStrChunk()));
            return rt;
        }

    }

    private AVLNode<T> findMinNode(AVLNode<T> rt) {
        if (rt.getLeftChild() == null) return rt;
        else return findMinNode(rt.getLeftChild());
    }
}
