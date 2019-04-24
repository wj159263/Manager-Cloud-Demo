package com.wj.desiner._08tree;

public interface BinaryTree {
    public boolean isEmpty();
    public int size();//树节点
    public int getHeight();//树高度
    public Node findKey(int value);//查找指定节点
    public void preOrderTraverse();//前序递归遍历
    public void inOrderTraverse();//中序递归遍历
    public void postOrderTraverse();//后序递归遍历
    public void postOrderTraverse(Node node);//后序递归遍历
    public void preOrderByStack();//前序非递归遍历
    public void inOrderByStack();//中序非递归遍历
    public void postOrderByStack();//后序非递归遍历
    public void levelOrderByStack();//按层次遍历二叉树
}
