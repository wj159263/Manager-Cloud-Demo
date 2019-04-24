package com.wj.desiner._07array;

public class OrderArray {
    public static void main(String[] args) {
        int[] arr = {1,2,4,3,1,3,2,9,0,6,6,8,5,1,2,1,3,0};
        maoPao(arr);
        for (int x = 0 ;x<arr.length;x++){
            System.out.println(arr[x]);
        }
    }

    public static void maoPao(int[] arr){
        for (int i = 0;i<arr.length-1; i++){
            for (int x = 0;x < arr.length-1-i; x++){
                if(arr[x] > arr[x+1]){
                    int temp = arr[x];
                    arr[x] = arr[x+1];
                    arr[x+1] = temp;
                }
            }
        }
    }
}
