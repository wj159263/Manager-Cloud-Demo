package com.wj.desiner._08tree;

public class LinkedBinaryTree implements BinaryTree {
    private Node root;

    public LinkedBinaryTree() {
    }

    public LinkedBinaryTree(Node root) {

        this.root = root;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public int size() {
        return size(this.root);
    }
    public int size(Node node){
        if(node == null){
            return 0;
        }
        if(node.leftChild == null && node.rightChild ==null){
            return 1;
        }else {
            int leftsize = 0;
            int rightsize = 0;
            if(node.leftChild != null){
                BinaryTree left = new LinkedBinaryTree(node.leftChild);
                leftsize = left.size();
            }
            if(node.rightChild != null){
                BinaryTree right = new LinkedBinaryTree(node.rightChild);
                rightsize = right.size();
            }
            return (leftsize + rightsize + 1);
        }
    }

    @Override
    public int getHeight() {
        return getHeight(this.root);
    }
    public int getHeight(Node node){
        if(node == null){
            return 0;
        }
        if(node.leftChild == null && node.rightChild ==null){
            return 1;
        }else {
            int leftHeight = 0;
            int rightHeight = 0;
            if(node.leftChild != null){
                BinaryTree left = new LinkedBinaryTree(node.leftChild);
                leftHeight = left.getHeight();
            }
            if(node.rightChild != null){
                BinaryTree right = new LinkedBinaryTree(node.rightChild);
                rightHeight = right.getHeight();
            }
            return leftHeight>rightHeight?(leftHeight+1):(rightHeight+1);
        }
    }

    @Override
    public Node findKey(int value) {
      return findKey(value,this.root);
    }
    public Node findKey(int value, Node root) {
        if(root == null) {
            return null;
        }else if(root!=null && root.value ==value){
            return root;
        }else {
           Node node1 = this.findKey(value,root.leftChild);
           Node node2 = this.findKey(value,root.rightChild);
           if(node1 != null && node1.value == value){
               return node1;
           }
           if(node2 != null && node2.value == value){
                return node2;
           }
           return null;
        }
    }
    @Override
    public void preOrderTraverse() {
        if(!isEmpty()){
            System.out.print(root.value);
        }
        if(root.leftChild != null){
            BinaryTree left = new LinkedBinaryTree(root.leftChild);
            left.preOrderTraverse();
        }
        if(root.rightChild != null){
            BinaryTree right = new LinkedBinaryTree(root.rightChild);
            right.preOrderTraverse();
        }
    }

    @Override
    public void inOrderTraverse() {
        if(root.leftChild != null){
            BinaryTree left = new LinkedBinaryTree(root.leftChild);
            left.inOrderTraverse();
        }
        System.out.print(root.value);
        if(root.rightChild != null){
            BinaryTree right = new LinkedBinaryTree(root.rightChild);
            right.inOrderTraverse();
        }
    }

    @Override
    public void postOrderTraverse() {
        if(root.leftChild != null){
            BinaryTree left = new LinkedBinaryTree(root.leftChild);
            left.postOrderTraverse();
        }

        if(root.rightChild != null){
            BinaryTree right = new LinkedBinaryTree(root.rightChild);
            right.postOrderTraverse();
        }
        System.out.print(root.value);
    }

    @Override
    public void postOrderTraverse(Node node) {
        if(node.leftChild != null){
            BinaryTree left = new LinkedBinaryTree(node.leftChild);
            left.postOrderTraverse();
        }

        if(node.rightChild != null){
            BinaryTree right = new LinkedBinaryTree(node.rightChild);
            right.postOrderTraverse();
        }
        System.out.print(node.value);
    }

    @Override
    public void preOrderByStack() {

    }

    @Override
    public void inOrderByStack() {

    }

    @Override
    public void postOrderByStack() {

    }

    @Override
    public void levelOrderByStack() {

    }
}
