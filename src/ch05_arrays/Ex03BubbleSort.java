package ch05_arrays;

import java.util.Arrays;

/**
 * 练习 3：冒泡排序
 *
 * 【要求】
 * 1. 实现 bubbleSort(int[] arr)，从小到大排序
 * 2. 每轮比较相邻两个元素，逆序就交换
 * 3. 增加优化标志：本轮没有发生交换就提前结束
 * 4. 在 main 中打印排序前后的数组并自检
 *
 * 【示例结果】{5, 2, 9, 1, 5, 6} → 排序后 {1, 2, 5, 5, 6, 9}
 *
 * 【知识点】相邻元素比较与交换、嵌套循环、有序区逐渐扩大、最好与最坏时间复杂度
 */
public class Ex03BubbleSort {

    public static void main(String[] args) {
        int[] arr = {5, 2, 9, 1, 5, 6};
        System.out.println("排序前: " + Arrays.toString(arr));
        bubbleSort(arr);
        System.out.println("排序后: " + Arrays.toString(arr));
        System.out.println(Arrays.equals(arr, new int[]{1, 2, 5, 5, 6, 9})
                ? "通过"
                : "未通过（期望 [1, 2, 5, 5, 6, 9]，实际 " + Arrays.toString(arr) + "）");

        int[] sorted = {1, 2, 3, 4};
        System.out.println("已有序数组排序前: " + Arrays.toString(sorted));
        bubbleSort(sorted);
        System.out.println("已有序数组排序后: " + Arrays.toString(sorted));
        System.out.println(Arrays.equals(sorted, new int[]{1, 2, 3, 4})
                ? "通过"
                : "未通过（期望 [1, 2, 3, 4]，实际 " + Arrays.toString(sorted) + "）");

        int[] empty = {};
        bubbleSort(empty);
        System.out.println("空数组排序后: " + Arrays.toString(empty));
        System.out.println(empty.length == 0 ? "通过" : "未通过（期望空数组）");
    }

    /**
     * 冒泡排序（升序）。
     *
     * 外层循环控制轮数，内层循环比较相邻元素并交换；每轮结束最大的元素会冒到末尾。
     * 如果某一轮一次交换都没有发生，说明数组已经有序，可以提前结束。
     */
    public static void bubbleSort(int[] arr) {
        // TODO 双重循环 + swapped 优化标志
    }
}
