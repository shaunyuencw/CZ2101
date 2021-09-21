import java.io.*;
import java.util.*;

public class MergeSort {
   
        public static void main(String[] args){
        int[] myArray = new int[]{ 2,1,4,5 }; 

        Mergesort(myArray, 0, myArray.length - 1);
        for (int i = 0; i < myArray.length; i++){
            System.out.print(myArray[i] + " ");
        }
        System.out.println();
        
        
    }
    public static void Mergesort(int[] arr, int left, int right) {
        if(left<right){
    
        int mid = (left+right)/2;
        Mergesort(arr,left,mid);
        Mergesort(arr,mid+1,right);
        merge(arr,left,mid,right);
    
        
        }  
    }  

    public static void merge(int arr[], int l, int m, int r){
        int leftsize = m - l + 1;
        int rightsize = r - m;
        //int i , j = 0;

        /* Create temp arrays */
        int L[] = new int[leftsize];
        int R[] = new int[rightsize];

        /*Copy data to temp arrays*/
        for (int i = 0; i < leftsize; i++)
            L[i] = arr[l + i];
        for (int j = 0; j < rightsize; j++)
            R[j] = arr[m + 1 + j];

        int i = 0;
        int j = 0;

        int pointer = l;
        while(i<leftsize && j<rightsize){
            if(L[i]<= R[j]){
                arr[pointer]=L[i];
                i++;
            }
            else{
                arr[pointer]=R[j];
                j++;
            }
            pointer++;
        }
        while(i<leftsize){
            arr[pointer]=L[i];
            pointer++;
            i++;
        }
        while(j<rightsize){
            arr[pointer]=R[j];
            pointer++;
            j++;
        }

    }  
    }
    