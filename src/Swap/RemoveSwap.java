package Swap;

public class RemoveSwap {

    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
        }
    }

    static Node first = null;
    static Node second = null;
    static Node previous = null;

    static void recoverBST(Node root) {

        if (root == null) {
            return;
        }

        // Traverse Left
        recoverBST(root.left);

        // Detect violation
        if (previous != null && previous.data > root.data) {

            // First incorrect node
            if (first == null) {
                first = previous;
            }

            // Second incorrect node
            second = root;
        }

        // Update previous node
        previous = root;

        // Traverse Right
        recoverBST(root.right);
    }

    static void inorder(Node root) {

        if (root == null) {
            return;
        }

        inorder(root.left);

        System.out.print(root.data + " ");

        inorder(root.right);
    }

    public static void main(String[] args) {

        // Creating the BST with two swapped nodes
        Node root = new Node(40);

        root.left = new Node(60);
        root.right = new Node(20);

        root.left.left = new Node(10);
        root.left.right = new Node(30);

        root.right.left = new Node(50);
        root.right.right = new Node(70);

        System.out.println("Before Recovery:");
        inorder(root);
        System.out.println();

        // Find the swapped nodes
        recoverBST(root);

        // Swap the incorrect nodes
        int temp = first.data;
        first.data = second.data;
        second.data = temp;

        System.out.println("After Recovery:");
        inorder(root);
        System.out.println();
    }
}