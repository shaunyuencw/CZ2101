import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.util.Random;
import java.util.Scanner;

//   TODO 1. Plot Graph to show Asymptotic Run Time Complexity of n
//   TODO 2. Create the function seperately to generate Sims for "S" and "n"
//   TODO 3. inplaceMergeSort()

public class App {
    private static long hybridComparisons;
    private static long mergeComparisons;
    private static int S;
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        boolean cont = true;

        do{
            System.out.printf("Enter n for simulation: ");
            int arraySize = sc.nextInt();

            System.out.printf("Enter S for simulation: ");
            S = sc.nextInt();

            System.out.printf("Randomly generated arrays? (Y/N): ");
            if (sc.next().charAt(0) == 'N'){


                int[] customArray = new int[arraySize];
                int counter;
                boolean valid = true;

                do{
                    valid = true;
                    System.out.println("Which custom array would you like to test?");
                    System.out.println("1) Fully Ascending");
                    System.out.println("2) Fully Descending");
                    System.out.println("3) All same elements");
                    
                    int choice = sc.nextInt();
                    switch(choice){
                        case 1:
                            counter = 1;
                            for(int i = 0; i < arraySize; i++){
                                customArray[i] = counter;
                                counter++;
                            }
                            break;
                        case 2:
                            counter = arraySize;
                            for(int i = 0; i < arraySize; i++){
                                customArray[i] = counter;
                                counter--;
                            }
                            break;
                        case 3:
                            for(int i = 0; i < arraySize; i++){
                                customArray[i] = 10;
                            }
                            break;
                        default:
                            valid = false;
                            System.out.println("Invalid option.");
                        }
                    } while(!valid);
                    int[] mergeCustom = customArray.clone();
                    int[] hybridCustom = customArray.clone();

                    long startTime, endTime;
                    hybridComparisons = 0;
                    mergeComparisons = 0;

                    startTime = System.currentTimeMillis();
                    hybridSort(hybridCustom, 0, hybridCustom.length - 1);
                    endTime = System.currentTimeMillis();

                    System.out.println("====== Hybrid Sort ====== ");
                    //printArray(hybridArr);
                    System.out.println("No. of key comparisons: " + hybridComparisons);
                    System.out.println("Time taken: " + (endTime - startTime) + " milliseconds.");
                    System.out.println();

                    startTime = System.currentTimeMillis();
                    mergeSort(mergeCustom, 0, mergeCustom.length - 1);
                    endTime = System.currentTimeMillis();

                    System.out.println("====== Merge Sort ====== ");
                    //printArray(mergeArr);
                    System.out.println("No. of key comparisons: " + mergeComparisons);
                    System.out.println("Time taken: " + (endTime - startTime) + " milliseconds.");
            }

            else{
                System.out.printf("How many times to run simulation? ");
                int numTrials = sc.nextInt();

                runSimulation(arraySize, numTrials);

            }

            System.out.printf("Continue? (Y/N): ");
            if(sc.next().charAt(0) == 'N'){
                System.out.println("Terminating Program! :)");
                cont = false;
            }
        } while(cont);

        sc.close();
    }

    static void runSimulation(int n, int numTrials) throws IOException{
        for (int i = 0; i < numTrials; i++){
            int[] arr = genRanArray(n, 0, 1000000);

            hybridComparisons = 0;
            mergeComparisons = 0;
            long sTime, eTime;
            int[] hybridArr = arr.clone();
            int[] mergeArr = arr.clone();

            String fileString = "projectAnalysis.csv";

            File f = new File(fileString);
            boolean exist = f.exists() && !f.isDirectory();

            FileWriter fw = new FileWriter(fileString, true);
            PrintWriter pw = new PrintWriter(fw);
            
            if (!exist){
                // File does not exist yet, generate the file with headers
                pw.print("arraySize,");
                pw.print("threshhold,");
                pw.print("hybridKeyComp,");
                pw.print("hybridTiming,");
                pw.print("mergeKeyComp,");
                pw.print("mergeTiming");

                
                System.out.println("File Created!");
            }

            // File already exist, just append to it
            else{
                // arraySize & threshhold
                pw.printf("%d, ", n);
                pw.printf("%d, ", S);

                System.out.printf("For n = %d, S = %d \n", n, S);

                sTime = System.currentTimeMillis();
                hybridSort(hybridArr, 0, hybridArr.length - 1);
                eTime = System.currentTimeMillis();

                System.out.println("====== Hybrid Sort ====== ");
                //printArray(hybridArr);
                System.out.println("No. of key comparisons: " + hybridComparisons);
                System.out.println("Time taken: " + (eTime - sTime) + " milliseconds.");
                System.out.println();

                pw.printf("%d, ", hybridComparisons);
                pw.printf("%d, ", (eTime - sTime));
                
                sTime = System.currentTimeMillis();
                mergeSort(mergeArr, 0, mergeArr.length - 1);
                eTime = System.currentTimeMillis();

                System.out.println("====== Merge Sort ====== ");
                //printArray(mergeArr);
                System.out.println("No. of key comparisons: " + mergeComparisons);
                System.out.println("Time taken: " + (eTime - sTime) + " milliseconds.");

                pw.printf("%d, ", mergeComparisons);
                pw.printf("%d", (eTime - sTime));


                System.out.println("Added row! \n");
            }

            pw.println();
            pw.close();
        }
    }

    static void printArray(int[] arr){
        for (int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.print("\n");
    }

    static int[] genRanArray(int size, int min, int max){
        Random random = new Random();
        int[] rArray = new int[size];
        
        for (int i = 0; i < size; i++){
            rArray[i] = random.nextInt(max-min) + min;
        }

        return rArray;
    }

    static void mergeSort(int[] arr, int leftIndex, int rightIndex){
        if (rightIndex > leftIndex){
            int midIndex = (leftIndex + rightIndex) / 2;

            mergeSort(arr, leftIndex, midIndex);
            mergeSort(arr, midIndex + 1, rightIndex);

            merge(arr, leftIndex, midIndex, rightIndex, "merge");
        }
    }

    static void hybridSort(int[] arr, int leftIndex, int rightIndex){
        if (rightIndex - leftIndex <= S){
            // Standard Insertion Sort
            insertionSort(arr, leftIndex, rightIndex);
        }

        else{
            // Standard Merge Sort 
            int midIndex = (leftIndex + rightIndex) /  2;

            hybridSort(arr, leftIndex, midIndex);
            hybridSort(arr, midIndex + 1, rightIndex);

            merge(arr, leftIndex, midIndex, rightIndex, "hybrid");
        }
    }

    static void merge(int[] arr, int leftIndex, int midIndex, int rightIndex, String typeString){
        // Get size of subArrays
        int lSize = midIndex - leftIndex + 1;
        int rSize = rightIndex - midIndex;

        // Create temp subArrays
        int lArr[] = new int[lSize];
        int rArr[] = new int[rSize];

        // Duplicate date into temp subArray
        for (int i = 0; i < lSize; i++){
            lArr[i] = arr[leftIndex + i];
        }

        for (int j = 0; j < rSize; j++){
            rArr[j] = arr[midIndex + 1 + j];
        } 

        // Merge in the temp arrays
        int lArrPointer = 0, rArrPointer = 0;
        int mergedArrPointer = leftIndex; // Moving index of the merged array pointer

        // Merge whilst sorting
        while (lArrPointer < lSize && rArrPointer < rSize){
            if (lArr[lArrPointer] <= rArr[rArrPointer]){
                arr[mergedArrPointer] = lArr[lArrPointer];
                lArrPointer++;
            }
            else{
                arr[mergedArrPointer] = rArr[rArrPointer];
                rArrPointer++;
            }
            if (typeString == "hybrid"){
                hybridComparisons++;
            }
            else if (typeString == "merge"){
                mergeComparisons++;
            }

            mergedArrPointer++;
        }

        // Insert in remaining values in subArray
        while (lArrPointer < lSize){
            arr[mergedArrPointer] = lArr[lArrPointer];
            lArrPointer++;
            mergedArrPointer++;
        }

        while (rArrPointer < rSize){
            arr[mergedArrPointer] = rArr[rArrPointer];
            rArrPointer++;
            mergedArrPointer++;
        }
    }

    static void insertionSort(int[] arr, int leftIndex, int rightIndex) {
        for (int i = leftIndex; i < rightIndex  ; i++){
            int temp = arr[i + 1];
            int j = i + 1;
            while (j > leftIndex && arr[j-1] > temp){
                hybridComparisons++;
                arr[j] = arr[j - 1];
                j--;
            }
        }
    }
}
