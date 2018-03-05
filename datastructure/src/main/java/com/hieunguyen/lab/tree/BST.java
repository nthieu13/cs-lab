package com.hieunguyen.lab.tree;

/**
 * Created by hieunguyen on 4/2/17.
 */
public class BST {
    class Node {
        int value;
        Node left;
        Node right;
        Node parent;

        public Node(int value, Node parent) {
            this.value = value;
            this.parent = parent;
            this.left = null;
            this.right = null;
        }

        public void replaceChild(Node child, Node replacement) {
            if (left == child) {
                left = replacement;
            }
            if (right == child) {
                right = replacement;
            }
            if (replacement != null) {
                replacement.parent = this;
            }
        }
    }

    Node root;
    int size;

    public BST() {
        root = null;
        size = 0;
    }

    public boolean insert(int value) {
        if (root == null) {
            root = new Node(value, null);
            size = 1;
            return true;
        }
        Node node = root;
        while(node != null) {
            if (node.value == value) {
                return false;
            } else if (value > node.value) {
                if (node.right == null) {
                    node.right = new Node(value, node);
                    size++;
                    return true;
                }
                node = node.right;
            } else {
                if (node.left == null) {
                    node.left = new Node(value, node);
                    size++;
                    return true;
                }
                node = node.left;
            }
        }
        return false;
    }

    public boolean contains(int value) {
        if (root == null) {
            return false;
        }
        Node node = root;
        while(node != null) {
            if (node.value == value) {
                return true;
            } else if (value > node.value) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return false;
    }

    public Node find(int value) {
        if (root == null) {
            return null;
        }
        Node node = root;
        while(node != null) {
            if (node.value == value) {
                return node;
            } else if (value > node.value) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return null;
    }

    public boolean remove(int value) {
        Node node = find(value);
        if (node == null) {
            return false;
        }

        Node parent = node.parent;
        if (node.left == null && node.right == null) {
            if (parent == null) {
                this.root = null;
            } else {
                parent.replaceChild(node, null);
            }
        } else if (node.left != null && node.right == null) {
            if (parent == null) {
                this.root = node.left;
                root.parent = null;
            } else {
                parent.replaceChild(node, node.left);
            }
        } else if (node.left == null && node.right != null) {
            if (parent == null) {
                this.root = node.right;
                root.parent = null;
            } else {
                parent.replaceChild(node, node.right);
            }
        } else {
            Node rightmost = node.left;
            while (rightmost.right != null) {
                rightmost = rightmost.right;
            }
            node.value = rightmost.value;
            rightmost.parent.replaceChild(rightmost, rightmost.left);
        }
        size--;
        return true;
    }

    private String dfs(Node node) {
        if (node == null) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        builder.append(dfs(node.left));
        builder.append(node.value);
        builder.append(",");
        builder.append(dfs(node.right));
        return builder.toString();
    }

    public String toString() {
        String ret = "";
        if (root != null) {
            ret += dfs(root);
        }
        return ret.substring(0, ret.length() - 1);
    }

    public static void main(String[] args) {
        BST bst = new BST();
        bst.insert(5);
        bst.insert(6);
        bst.insert(4);
        bst.insert(2);
        bst.insert(7);

        System.out.println(bst.toString());

        bst.insert(3);
        System.out.println(bst.toString());

        bst.insert(1);
        System.out.println(bst.toString());

        bst.remove(4);
        System.out.println(bst.toString());

        bst.remove(5);
        System.out.println(bst.toString());
    }
}
