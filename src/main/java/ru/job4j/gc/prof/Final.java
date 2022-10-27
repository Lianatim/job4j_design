package ru.job4j.gc.prof;

public class Final {
    public static void main(String[] args) {
        System.out.println("Parallel - MergeSort: " + String.format("%.4f", (05.396023 - 05.388374)));
        System.out.println("Parallel - BubbleSort: " + String.format("%.4f", (12.338324 - 11.122059)));
        System.out.println("Parallel - InsertSort: " + String.format("%.4f", (04.865667 - 04.676622)));
        System.out.println(" ");
        System.out.println("G1 - MergeSort: " + String.format("%.4f", (26.970922 - 26.962222)));
        System.out.println("G1 - BubbleSort: " + String.format("%.4f", (59.192754 - 57.972996)));
        System.out.println("G1 - InsertSort: " + String.format("%.4f", (57.421326 - 57.229137)));
        System.out.println(" ");
        System.out.println("ZGC - MergeSort: " + String.format("%.4f", (05.609973 - 05.603733)));
        System.out.println("ZGC - BubbleSort: " + String.format("%.4f", (08.011482 - 06.797059)));
        System.out.println("ZGC - InsertSort: " + String.format("%.4f", (07.177600 - 06.983412)));
    }
}
