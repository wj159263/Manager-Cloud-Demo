package com.wj.desiner._08tree;

public class BinaryTreeTest {
    public static void main(String[] args) {
        //创建二叉树
        Node node5 = new Node(5, null, null);
        Node node4 = new Node(4, null, node5);
        Node node7 = new Node(7, null, null);
        Node node6 = new Node(6, null, node7);
        Node node3 = new Node(3, null, null);
        Node node2 = new Node(2, node3, node6);
        Node node1 = new Node(1, node4, node2);
        //
        LinkedBinaryTree linkedBinaryTree = new LinkedBinaryTree(node1);
        linkedBinaryTree.preOrderTraverse();
        System.out.println();
        linkedBinaryTree.inOrderTraverse();
        System.out.println();
        linkedBinaryTree.postOrderTraverse();
        System.out.println();
        linkedBinaryTree.postOrderTraverse(node2);
        System.out.println();
        System.out.println(linkedBinaryTree.getHeight(node2));
        System.out.println(linkedBinaryTree.size());
        System.out.println(linkedBinaryTree.size(node5));
        System.out.println(linkedBinaryTree.findKey(2));
    }
}
