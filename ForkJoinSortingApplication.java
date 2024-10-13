import java.util.Arrays;
import java.util.concurrent.*;;

class ParentTask <T extends Comparable<T>> extends RecursiveAction{
    private final static int THRESHOLD = 100;
    protected T[] appData;
    protected int left;
    protected int right;

    public ParentTask(T[] arr, int left, int right){
        this.appData = arr;
        this.left = left;
        this.right = right;
    }

    public static <T extends Comparable<T>> void sortUsingMergeSortApp(T[] array){
        ForkJoinPool pool = new ForkJoinPool();
        MergeSortApp<T> task = new MergeSortApp<>(array, 0, array.length - 1);
        pool.invoke(task);
    }

    public static <T extends Comparable<T>> void sortUsingQuickSortApp(T[] array){
        ForkJoinPool pool = new ForkJoinPool();
        QuickSortApp<T> task = new QuickSortApp<>(array, 0, array.length - 1);
        pool.invoke(task);
    }

    @Override
    protected void compute() {
        
    }

    /* Static Insertion Sort */
    private static <T extends Comparable<T>> void insertionSort(T[] arr, int left, int right){

        for (int i = 1; i < right; ++i) {
            T key = arr[i];

            int j = i - 1;
            while (j >= left && arr[j].compareTo(key) > 0) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    
    public static class MergeSortApp <T extends Comparable <T>> extends ParentTask<T>{


        public MergeSortApp(T[] array, int left, int right){
            super(array, left, right);
        }

        @Override
        protected void compute(){
            if(right - left < THRESHOLD){
                insertionSort(appData, left, right);
            }
            else {
                int mid = ((right - left) / 2) + left;
                MergeSortApp<T> leftTask = new MergeSortApp<>(appData, left, mid);
                MergeSortApp<T> rightTask = new MergeSortApp<>(appData, mid+1, right);

                invokeAll(leftTask, rightTask);

                merge(appData, left, mid, right);
            }
        }

        private void merge(T[] data, int start, int mid, int end){
            int n1 = mid - start + 1;
            int n2 = end - mid;
    
            T[] arr1 = Arrays.copyOfRange(data, start, mid + 1);
            T[] arr2 = Arrays.copyOfRange(data, mid + 1, end + 1);

            // for(int i=0; i<n1; i++){
            //     arr1[i] = data[i];
            // }
    
            // for(int i=0; i<n2; i++){
            //     arr2[i] = data[i];
            // }
    
            int i = 0;
            int j = 0;
            int k = start;
    
            while(i < n1 && j < n2){
                if(arr1[i].compareTo(arr2[j]) <= 0){
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
    }

    public static class QuickSortApp <T extends Comparable <T>> extends ParentTask<T>{

        public QuickSortApp(T[] arr, int left, int right) {
            super(arr, left, right);
        }
        
        @Override
        protected void compute(){
            if(right - left < THRESHOLD){
                insertionSort(appData, left, right);
            }
            else {
                int pivot = partition(appData, left, right);
                QuickSortApp leftTask = new QuickSortApp<>(appData, left, pivot - 1);
                QuickSortApp rightTask = new QuickSortApp<>(appData, pivot + 1, right);

                invokeAll(leftTask, rightTask);
            }
        }

        private int partition(T[] arr, int low, int high){
            T pivot = arr[high];

            int i = low - 1;

            for(int j = low; j<high; j++){
                T compareElement = arr[j];
                if(compareElement.compareTo(pivot) < 0){
                    i++;
                    swap(arr, i, j);
                }
            }

            swap(arr, i+1, high);
            return i+1;
        }

        private void swap(T[] arr, int i, int j) {
            T temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }    
    }
}

class ForkJoinSortingApplication {
    public static void main(String args[]){

        System.out.println("\n\nFork-Join эрэмбэлэлтийн программд тавтай морилно уу");
        System.out.println();

        Integer[] array1 = {28, 56, 46, 56, 88, 89, 19, 29, 67, 66, 20, 87, 86, 8, 88, 67, 89, 28, 45, 56, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67};

        long startTime;
        long endTime;

        startTime = System.currentTimeMillis();
        ParentTask.sortUsingMergeSortApp(array1);
        endTime = System.currentTimeMillis();
        System.out.println("------> Mergesort алгоритм ашиглавал: " + (endTime - startTime));
        System.out.println();

        Integer[] array2 = {28, 56, 46, 56, 88, 89, 19, 29, 67, 66, 20, 87, 86, 8, 88, 67, 89, 28, 45, 56, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67};

        startTime = System.currentTimeMillis();
        ParentTask.sortUsingQuickSortApp(array2);
        endTime = System.currentTimeMillis();
        System.out.println("------> Quicksort алгоритм ашиглавал: " + (endTime - startTime));
        System.out.println();

        Integer[] array3 = {28, 56, 46, 56, 88, 89, 19, 29, 67, 66, 20, 87, 86, 8, 88, 67, 89, 28, 45, 56, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67, 78, 98, 90, 99, 34, 67};

        startTime = System.currentTimeMillis();
        Arrays.sort(array3);
        endTime = System.currentTimeMillis();
        System.out.println("------> Java-ийн default sort() алгоритм ашиглавал: " + (endTime - startTime));
        System.out.println();
    }
}