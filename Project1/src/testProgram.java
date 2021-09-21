import java.util.*;
import java.lang.*;

public class testProgram {

   public static void main(String[] args) {
      // TODO Auto-generated method stub

      // create a reversed order array of size denoted by user input
      Scanner sc = new Scanner(System.in);
      System.out.println("Enter your array size: ");
      int size = sc.nextInt(); // expected user input(1000,5000,10000,50000,75000,100000,500000

      int arr[] = new int[size];
      int pointer = size - 1;
      for (int i = 0; i < size; i++) {
         arr[i] = pointer--;
      }
      // print out unsorted reversed order array for worst case scenario
      for (int j = 0; j < size; j++) {
         System.out.print(arr[j] + " ");
      }
      System.out.println();

      // test code runtime
      int first = arr[0];
      int last = arr[size - 1];
      long startTime = System.nanoTime();
      hybridsort(arr, first, last, 10);
      long endTime = System.nanoTime();
      long elapsedTime = endTime - startTime;

      System.out.println("The time elapsed to run this function is: " + elapsedTime);

      // print out sorted array to check whether array is actually sorted
      for (int k = 0; k < size; k++) {
         System.out.print(arr[k] + " ");
      }

   }

   // mergeSort algorithm
   public static void mergesort(int[] arr, int first, int last) {
      int mid = (first + last) / 2;
      if (last - first <= 0) {
         return;
      } else {
         mergesort(arr, first, mid);
         mergesort(arr, mid + 1, last);
      }
      merge(arr, first, mid, last);
   }

   // merge algorithm
   public static void merge(int arr[], int first, int mid, int last) {

      int size = arr.length;
      int[] tempArray = new int[size];
      int first1 = first;
      int last1 = mid;

      int first2 = mid + 1;
      int last2 = last;

      int i = first1;

      while ((first1 <= last1) && (first2 <= last2)) {
         if (arr[first1] < arr[first2]) {
            tempArray[i] = arr[first1];
            first1++;
         } else {
            tempArray[i] = arr[first2];
            first2++;
         }
         i++;
      }

      while (first1 <= last1) {
         tempArray[i] = arr[first1];
         first1++;
         i++;
      }
      while (first2 <= last2) {
         tempArray[i] = arr[first2];
         first2++;
         i++;
      }

      for (int j = first; j <= last; j++) {
         arr[j] = tempArray[j];
      }
   }

   // insertionSort algorithm
   public static void insertionSort(int[] arr, int first, int last) {
      int size = arr.length;
      if (last - first <= 0)
         return;
      else {
         for (int i = 1; i < size; i++) {
            for (int j = i; j > 0; j++) {
               if (arr[j] < arr[j - 1]) {
                  int temp = arr[j - 1];
                  arr[j - 1] = arr[j];
                  arr[j] = temp;
               } else {
                  break;
               }

            }
         }
      }
   }

   // hybridsort algorithm
   public static void hybridsort(int[] arr, int first, int last, int S) {
      if (last - first > S) {
         int mid = (first + last) / 2;
         hybridsort(arr, first, mid, S);
         hybridsort(arr, mid + 1, last, S);
         merge(arr, first, mid, last);
      } else {
         insertionSort(arr, first, last);
      }

   }

}