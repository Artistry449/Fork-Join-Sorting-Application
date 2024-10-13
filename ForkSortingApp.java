import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.*;;

class ForkSortingApp extends RecursiveAction{
    private int[] testData;
    private int userThresholdInput;
    private int userMaxSizeInput;
    final int THRESHOLD = 100;

    public ForkSortingApp(int userThresholdInput, int userMaxSizeInput){
        this.userThresholdInput = userThresholdInput;
        this.userMaxSizeInput = userMaxSizeInput;
    }

    @Override
    protected void compute() {
            // Энгийн эрэмбэлэлтийн алгоритм(Insertion Sort)
            if(userThresholdInput < this.THRESHOLD){
                this.generateRandomNumbers(userThresholdInput, userMaxSizeInput);
    
                System.out.print("Таны тест дата: \n");
                this.printArray(this.testData);
                System.out.println();
    
                this.insertionSort();
    
                System.out.println("Эрэмбэлэгдсэн дата: ");
                this.printArray(this.testData);
            }
            // Merge Sort || Quicksort
            else {
                // System.out.println("Хэрэглэгчийн оруулсан хэмжээ " + app.THRESHOLD + "-аас их байна.");
    
                // Merge sort ашигласан хувилбар
                
    
            }
    }

    private void mergeSort(int[] data, int start, int end){
        if(start > end){
            return; 
        }
        int mid = (start + end)/2;
        mergeSort(data, start, mid);
        mergeSort(data, mid+1, end);
        
        fork ->  merge(data, start, mid, end);
    }

    private void merge(int[] data, int start, int mid, int end){
        int n1 = mid - start + 1;
        int n2 = end - mid;

        int[] arr1 = new int[n1];
        int[] arr2 = new int[n2];

        for(int i=0; i<n1; i++){
            arr1[i] = data[i];
        }

        for(int i=0; i<n2; i++){
            arr2[i] = data[i];
        }

        int i = 0;
        int j = 0;
        int k = start;

        while(i < n1 && j < n2){
            if(arr1[i] <= arr2[j]){
                data[k] = arr1[i];
                i++;
            }
            else {
                data[k] = arr2[j]; 
                j++;
            }
            k++;
        }

        while(i < n1){
            data[k] = arr1[i];
            i++;
            k++;
        }
        
        while(j < n2){
            data[k] = arr2[j];
            j++;
            k++;
        }
    }

    // Тест дата-г үүсгэх
    public void generateRandomNumbers(int userThresholdInput, int maxSize){
        Random random = new Random();
        testData = new int[userThresholdInput];

        for(int i=0; i<userThresholdInput; i++){
            testData[i] = random.nextInt(maxSize);
        }
    }

    /* Insertion Sort */
    void insertionSort()
    {
        int n = testData.length;
        for (int i = 1; i < n; ++i) {
            int key = testData[i];
            int j = i - 1;

            while (j >= 0 && testData[j] > key) {
                testData[j + 1] = testData[j];
                j = j - 1;
            }
            testData[j + 1] = key;
        }
    }

    /* Дата-г хэвлэх */
    public void printArray(int[] arr)
    {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");

        System.out.println();
    }
    
    public static void main(String[] args) throws Exception{
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n\nFork-Join эрэмбэлэлтийн программд тавтай морилно уу");

        Thread.sleep(3000);

        System.out.print("\n-Хэдэн тоо эрэмбэлэхийг оруулна уу: ");
        int userThresholdInput = scanner.nextInt();
        System.out.print("\nЭрэмбэлэх тооны хэмжээ(1000 дотор гэх мэт): ");
        int userMaxSizeInput = scanner.nextInt();
        System.out.println();

        ForkSortingApp app = new ForkSortingApp(userThresholdInput, userMaxSizeInput);

    }
}