package solutions.ch05_arrays;

import java.util.Arrays;

/**
 * 练习 3 参考答案：冒泡排序
 *
 * 思路：
 * 外层循环控制比较轮数，最多 arr.length - 1 轮；
 * 内层循环比较相邻的 arr[j] 与 arr[j + 1]，前者大就交换。
 * 每完成一轮，当前无序区最大的元素就“冒”到了末尾，所以内层次数可以减去 i。
 *
 * 提前结束优化：
 * 用 swapped 记录本轮是否发生交换。如果一整轮都没有交换，
 * 说明数组已经有序，后面的轮次没有意义，直接 break。
 *
 * 易错点：
 * - 内层循环边界写成 j < arr.length - 1 会越界访问 arr[j + 1]。
 * - 忘记每轮缩小内层范围虽然结果正确，但会多做很多无效比较。
 * - 交换时直接把一个变量赋给另一个会丢数据，必须借助临时变量。
 * - 冒泡排序是稳定排序；平均和最坏时间复杂度 O(n^2)，最好情况（已有序）O(n)。
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

    /** 冒泡排序（升序），带提前结束优化 */
    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }
}
