
public class RedBlackTree {

    private RBNode root;
    static RBNode nil;

    static {
        nil = new RBNode(0);
        nil.left = nil;
        nil.right = nil;
    }

    public void insert(RBNode node) {
        RBNode nodeCheck = root;
        RBNode nodeParent = RedBlackTree.nil;

        while (nodeCheck != RedBlackTree.nil) {
            nodeParent = nodeCheck;

            //System.out.println("(insert) considering node (key: " + nodeCheck.key + ") against new one (key: " + node.key + ")");

            if (node.key < nodeCheck.key)
                nodeCheck = nodeCheck.left;
            else
                nodeCheck = nodeCheck.right;
        }

        node.parent = nodeParent;
        if (nodeParent == RedBlackTree.nil) {
            System.out.println("(insert) node (key = " + node.key + ") is now ROOT");
            root = node;
        } else if (node.key < nodeParent.key) {
            System.out.println("(insert) node (key = " + node.key + ") is now LEFT SON (small)");
            nodeParent.left = node;
        } else {
            System.out.println("(insert) node (key = " + node.key + ") is now RIGHT SON (big)");
            nodeParent.right = node;
        }


        node.left = RedBlackTree.nil;
        node.right = RedBlackTree.nil;
        node.isRed = true;
        insertFixUp(node);
    }

    public RBNode createNode(int key) {
        return new RBNode(key, RedBlackTree.nil, RedBlackTree.nil, RedBlackTree.nil, false);
    }

    private void insertFixUp(RBNode node) {
        RBNode uncle = null;

        System.out.println("(insert fixup) considering RED node with key = " + node.key );

        while (node.parent.isRed) {
            System.out.println("(insert fixup) considering RED parent node with key = " + node.parent.key );
            if (node.parent == node.getGrandparent().left) {
                // am I the son of a smaller parent?
                // if yes, let's take the uncle.
                uncle = node.getUncle();

                if (uncle.isRed) {
                    System.out.println("(insert fixup) CASE 1: node's uncle (key: " + uncle.key + ") is red.");
                    node.parent.isRed = false;
                    uncle.isRed = false;
                    node.getGrandparent().isRed = true;
                    node = node.getGrandparent();
                } else {
                    if (node == node.parent.right) {
                        // am I the great bro?
                        // if yes, let's become parent.
                        System.out.println("(insert fixup) CASE 2: node's uncle (key: " + uncle.key + ") is black and node is a right child.");
                        node = node.parent;
                        rotateLeft(node);
                    }

                    System.out.println("(insert fixup) CASE 3: node's uncle (key: " + uncle.key + ") is black and node is a left child.");
                    node.parent.isRed = false;
                    node.getGrandparent().isRed = true;
                    rotateRight(node.getGrandparent());
                }
            } else {
                // am I the son of a greater parent?
                // if yes, let's take the uncle.
                uncle = node.getUncle();

                if (uncle.isRed) {
                    System.out.println("(insert fixup) CASE 1A: node's uncle (key: " + uncle.key + ") is red.");
                    node.parent.isRed = false;
                    uncle.isRed = false;
                    node.getGrandparent().isRed = true;
                    node = node.getGrandparent();
                } else {

                    if (node == node.parent.left) {
                        // am I the small bro?
                        // if yes, let's become parent.
                        System.out.println("(insert fixup) CASE 2A: node's uncle (key: " + uncle.key + ") is black and node is a left child.");
                        node = node.parent;
                        rotateLeft(node);
                    }

                    System.out.println("(insert fixup) CASE 3A: node's uncle (key: " + uncle.key + ") is black and node is a right child.");
                    node.parent.isRed = false;
                    node.getGrandparent().isRed = true;
                }
            }
        }

        root.isRed = false;
    }

    public void delete(RBNode node) {
        RBNode tmp = node;
        RBNode son;
        boolean tmpOrigCol = tmp.isRed;

        if (node.left == RedBlackTree.nil) {
            son = node.right;
            transplant(node, node.right);
        } else if (node.right == RedBlackTree.nil) {
            son = node.left;
            transplant(node, node.left);
        } else {
            tmp = getMinimum(node.right);
            tmpOrigCol = tmp.isRed;
            son = tmp.right;

            if (tmp.parent == node) {
                son.parent = tmp;
            } else {
                transplant(tmp, tmp.right);
                tmp.right = node.right;
                node.right.parent = tmp;
            }

            transplant(node, tmp);
            tmp.left = node.left;
            tmp.left.parent = node;
            tmp.isRed = node.isRed;
        }

        if (!tmpOrigCol)
            deleteFixUp(node);
    }

    public RBNode getMinimum(RBNode node) {
        while(node.left != RedBlackTree.nil)
            node = node.left;
        return node;
    }

    public RBNode getMaximum(RBNode node) {
        while(node.right != RedBlackTree.nil)
            node = node.right;
        return node;
    }

    public void print() {
        print(root, 0);
    }

    public void print(RBNode node) {
        print(node, 0);
    }

    private void print(RBNode node, int h) {
        if (node == RedBlackTree.nil)
            return;

        print(node.left, h + 1);
        for (int i = 0; i < h; i++)
            System.out.print("\t");
        System.out.println(node.key + " (" + (node.isRed?"RED":"BLACK") + ")" );
        print(node.right, h + 1);
    }

    private void deleteFixUp(RBNode node) {
        RBNode brother;
        while (node != root && !node.isRed) {
            if (node == node.parent.left) {
                brother = node.parent.right;
                if (brother.isRed) {
                    System.out.println("(delete fixup) CASE 1: bother (key: " + brother.key + ") is red.");
                    brother.isRed = false;
                    node.parent.isRed = true;
                    rotateLeft(node.parent);
                    brother = node.parent.right;
                }

                if (!brother.left.isRed && !brother.right.isRed) {
                    System.out.println("(delete fixup) CASE 2: bother (key: " + brother.key + ") and sons are black.");
                    brother.isRed = true;
                    node = node.parent;
                } else if (!brother.right.isRed) {
                    System.out.println("(delete fixup) CASE 3: bother (key: " + brother.key + ") is black and left son is red.");
                    brother.left.isRed = false;
                    brother.isRed = true;
                    rotateRight(brother);
                    brother = node.parent.right;
                }

                System.out.println("(delete fixup) CASE 4: bother (key: " + brother.key + ") is black and right son is red.");
                brother.isRed = node.parent.isRed;
                node.parent.isRed = false;
                brother.right.isRed = false;
                rotateLeft(node.parent);
                node = root;
            } else {
                brother = node.parent.left;
                if (brother.isRed) {
                    brother.isRed = false;
                    node.parent.isRed = true;
                    rotateRight(node.parent);
                }

                if (!brother.left.isRed && !brother.right.isRed) {
                    brother.isRed = true;
                    node = node.parent;
                } else if (!brother.left.isRed) {
                    brother.right.isRed = false;
                    brother.isRed = true;
                    rotateLeft(brother);
                    brother = node.parent.left;
                }

                brother.isRed = node.parent.isRed;
                node.parent.isRed = false;
                brother.left.isRed = false;
                rotateLeft(node.parent);
                node = root;
            }
        }

        node.isRed = false;
    }

    private void rotateLeft(RBNode node) {
        RBNode rightChild = node.right;
        node.right = rightChild.left;

        if (rightChild.left != RedBlackTree.nil)
            rightChild.left.parent = node;

        rightChild.parent = node.parent;

        if (node.parent == RedBlackTree.nil)
            root = rightChild;
        else if (node == node.parent.left)
            node.parent.left = rightChild;
        else
            node.parent.right = rightChild;

        rightChild.left = node;
        node.parent = rightChild;
    }

    private void rotateRight(RBNode node) {
        RBNode leftChild = node.left;
        node.left = leftChild.right;

        if (leftChild.right != RedBlackTree.nil)
            leftChild.left.parent = node;

        leftChild.parent = node.parent;

        if (node.parent == RedBlackTree.nil)
            root = leftChild;
        else if (node == node.parent.right)
            node.parent.right = leftChild;
        else
            node.parent.left = leftChild;

        leftChild.right = node;
        node.parent = leftChild;
    }

    private void transplant(RBNode rem, RBNode replace) {
        if (rem.parent == RedBlackTree.nil)
            root = replace;
        else if (rem == rem.parent.left)
            rem.parent.left = replace;
        else {
            rem.parent.right = replace;
            replace.parent = rem.parent;
        }
    }

    public RedBlackTree() {
        root = RedBlackTree.nil;
    }


    public static void main(String[] args) {
        RedBlackTree rbt = new RedBlackTree();

        //int[] input = { 4, 2, 9, 6, 23, 12, 34, 0, 1 };

        for (int i = 0; i < 15; i++) {
            RBNode node = rbt.createNode(50 - i);
            rbt.insert(node);
        }

        rbt.print();
    }
}

class RBNode {
    public RBNode left;
    public RBNode right;
    public RBNode parent;
    public int key;
    public boolean isRed;

    public RBNode(int key, RBNode left, RBNode right, RBNode parent, boolean isRed) {
        this.key = key;
        this.left = left;
        this.right = right;
        this.parent = parent;
        this.isRed = isRed;
    }

    public RBNode(int key) {
        this.key = key;
        isRed = false;
        left = RedBlackTree.nil;
        right = RedBlackTree.nil;
        parent = RedBlackTree.nil;
    }

    public RBNode getParent() {
        return parent;
    }

    public RBNode getGrandparent() {
        if (parent == RedBlackTree.nil)
            return RedBlackTree.nil;
        return parent.parent;
    }

    public RBNode getUncle() {
        if (parent == RedBlackTree.nil || parent.parent == RedBlackTree.nil)
            return RedBlackTree.nil;

        if (parent == parent.parent.left)
            return parent.parent.left;
        else
            return parent.parent.right;
    }

}
