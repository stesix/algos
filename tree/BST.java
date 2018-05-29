
class BSTNode {
    public BSTNode left;
    public BSTNode right;
    public BSTNode parent;
    public int key;

    public BSTNode(int k, BSTNode l, BSTNode r, BSTNode p) {
        left = l;
        right = r;
        key = k;
    }
}

public class BST {

    static BSTNode treeRoot;

    public static BSTNode searchRec(BSTNode node, int key) {
        if (node == null || node.key == key)
            return node;

        if (node.key > key)
            return searchRec(node.left, key);
        return searchRec(node.right, key);
    }

    public static BSTNode search(int key) {
        BSTNode node = treeRoot;

        while (key != node.key || node == null) {
            if (key > node.key) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return node;
    }

    // unbalanced
    public static void insert(int key) {
        BSTNode x = treeRoot;
        BSTNode y = null;

        while (x != null) {
            y = x;
            //System.out.println("Searching ("+ key +") for Y = " + x.key);
            if (x.key > key) {
                x = x.left;
            } else {
                x = x.right;
            }
        }

        if (y == null) {
            treeRoot = new BSTNode(key, null, null, null);
        } else {
            if (y.key > key) {
                y.left = new BSTNode(key, null, null, y);
            } else {
                y.right = new BSTNode(key, null, null, y);
            }
        }
    }

    private static void transplant(BSTNode killed, BSTNode replacement) {
        if (killed.parent == null) {
            treeRoot = replacement;
        } else if (killed == killed.parent.left) {
            killed.parent.left = replacement;
        } else if (killed == killed.parent.right) {
            killed.parent.right = replacement;
        }
    }

    public static BSTNode getMinimum(BSTNode root) {
        BSTNode node = root;
        while(node.left != null)
            node = node.left;

        return node;
    }

    public static BSTNode getMaximum(BSTNode root) {
        BSTNode node = root;
        while(node.right != null)
            node = node.right;

        return node;
    }

    public static void delete(BSTNode node) {
        if (node.left == null) {
            transplant(node, node.right);
        } else if (node.right == null) {
            transplant(node, node.left);
        } else {
            BSTNode y = getMinimum(node.right);
            if (node != y.parent) {
                transplant(y, y.right);
                y.right = node.right;
                y.right.parent = y;
            }

            transplant(node, y);
            y.left = node.left;
            y.left.parent = y;
        }
    }

    private static void print(BSTNode node) {
        print(node, 0);
    }

    private static void print(BSTNode node, int h) {
        if (node == null)
            return;

        print(node.left, h + 1);
        for (int i = 0; i < h; i++)
            System.out.print("\t");
        System.out.println(node.key);
        print(node.right, h + 1);
    }


    public static void main(String[] args) {
        int[] input = { 4, 2, 9, 6, 23, 12, 34, 0, 1 };

        for (int i = 0; i < input.length; i++) {
            insert(input[i]);
        }

        print(treeRoot);
    }
}
