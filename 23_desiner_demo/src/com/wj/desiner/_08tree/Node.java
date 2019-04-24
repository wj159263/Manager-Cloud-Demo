package com.wj.desiner._08tree;

public class Node {
    public int value;
    public Node leftChild;
    public Node rightChild;

    public Node(int value) {
        this.value = value;
    }

    public Node(int value, Node leftChild, Node rightChild) {

        this.value = value;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", leftChild=" + leftChild +
                ", rightChild=" + rightChild +
                '}';
    }
}
