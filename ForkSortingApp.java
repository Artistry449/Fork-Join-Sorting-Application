import java.util.Random;
import java.util.Scanner;

class ForkSortingApp {
    Scanner scanner;
    int[] testData;
    final int THRESHOLD;

    public ForkSortingApp(){
        this.THRESHOLD = 100;
        scanner = new Scanner(System.in);
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
    void insertionSort(int arr[])
    {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    /* Дата-г хэвлэх */
    static void printArray(int arr[])
    {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");

        System.out.println();
    }
    
    public static void main(String[] args) throws Exception{
        ForkSortingApp app = new ForkSortingApp();

        System.out.println("\n\nFork-Join эрэмбэлэлтийн программд тавтай морилно уу");

        Thread.sleep(3000);

        System.out.print("\n-Хэдэн тоо эрэмбэлэхийг оруулна уу: ");
        int userThresholdInput = app.scanner.nextInt();
        System.out.print("\nЭрэмбэлэх тооны хэмжээ(1000 дотор гэх мэт): ");
        int userMaxSizeInput = app.scanner.nextInt();
        System.out.println();

        // Энгийн эрэмбэлэлтийн алгоритм(Insertion Sort)
        if(userThresholdInput < app.THRESHOLD){
            app.generateRandomNumbers(userThresholdInput, userMaxSizeInput);

            System.out.print("Таны тест дата: \n");
            for(int item: app.testData){
                System.out.print(item + " ");
            }
            System.out.println();

        }
        // Merge Sort || Quicksort
        else {
            System.out.println("Хэрэглэгчийн оруулсан хэмжээ " + app.THRESHOLD + "-аас их байна.");
        }
    }
}