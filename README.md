# ForkJoinSortingApplication

Үйлдлийн систем 2024/2025A хичээлийн бие даалтын ажил

Энэхүү программ нь тодорхой дата-г(энэ жишээнд тоон утгатай массив) хуваагаад эзэмших(Divide and Conquer) алгоритмаар олон урсгалт(multithread) программ бичихэд оршино. Бодлогын тайлбарыг доор хавсаргав.

# Бодлогын тайлбар(эх сурвалж: Operating System Concepts 10th edition)
Implement the preceding project (Multithreaded Sorting Application) using Java’s fork-join parallelism API. This project will be developed in two different versions. Each version will implement a different divide-and-conquer sorting algorithm:

1. Quicksort
2. Mergesort

The Quicksort implementation will use the Quicksort algorithm for dividing the list of elements to be sorted into a left half and a right half based on the position of the pivot value. The Mergesort algorithm will divide the list into two evenly sized halves. For both the Quicksort and Mergesort algorithms, when the list to be sorted falls within some threshold value (for example, the list is size 100 or fewer), directly apply a simple algorithm such as the Selection or Insertion sort. Most data structures texts describe these two well-known, divide-and-conquer sorting algorithms.
The class SumTask shown in Section 4.5.2.1 extends RecursiveTask, which is a result-bearing ForkJoinTask. As this assignment will involve sorting the array that is passed to the task, but not returning any values, you will instead create a class that extends RecursiveAction, a non result-bearing ForkJoinTask (see Figure 4.19).
The objects passed to each sorting algorithm are required to implement Java’s Comparable interface, and this will need to be reflected in the class definition for each sorting algorithm. The source code download for this text includes Java code that provides the foundations for beginning this project.

# Ашигласан сан

Java RecursiveAction
