package solutions.ch05_arrays;

/**
 * 练习 1 参考答案：数组最大值、最小值与平均值
 *
 * 思路：
 * 1. 先判断数组是否为空，空数组直接返回约定值 0，避免访问 arr[0] 时越界。
 * 2. 把第一个元素当作临时最大值/最小值，从下标 1 开始遍历比较。
 * 3. 求和用 int 或 long 累加，最后除以 (double) arr.length 保留小数。
 *
 * 易错点：
 * - 空数组直接访问 arr[0] 会抛 ArrayIndexOutOfBoundsException。
 * - avg 写成 sum / arr.length 会做整数除法，3.875 会变成 3.0。
 * - 空数组求平均不能直接除，0 / 0 会得到 NaN，因此要单独返回 0.0。
 */
public class Ex01MaxMinAvg {

    public static void main(String[] args) {
        int[] data = {3, 1, 4, 1, 5, 9, 2, 6};

        int actualMax = max(data);
        System.out.println("最大值: " + actualMax);
        System.out.println(actualMax == 9 ? "通过" : "未通过（期望 9，实际 " + actualMax + "）");

        int actualMin = min(data);
        System.out.println("最小值: " + actualMin);
        System.out.println(actualMin == 1 ? "通过" : "未通过（期望 1，实际 " + actualMin + "）");

        double actualAvg = avg(data);
        System.out.println("平均值: " + actualAvg);
        System.out.println(Math.abs(actualAvg - 3.875) < 1e-9
                ? "通过"
                : "未通过（期望 3.875，实际 " + actualAvg + "）");

        int[] empty = {};
        System.out.println("空数组最大值: " + max(empty));
        System.out.println(max(empty) == 0 ? "通过" : "未通过（期望 0，实际 " + max(empty) + "）");
        System.out.println("空数组最小值: " + min(empty));
        System.out.println(min(empty) == 0 ? "通过" : "未通过（期望 0，实际 " + min(empty) + "）");
        System.out.println("空数组平均值: " + avg(empty));
        System.out.println(avg(empty) == 0.0 ? "通过" : "未通过（期望 0.0，实际 " + avg(empty) + "）");

        int[] single = {7};
        System.out.println("单元素数组最大值: " + max(single));
        System.out.println(max(single) == 7 ? "通过" : "未通过（期望 7，实际 " + max(single) + "）");
    }

    /** 返回数组最大值，空数组返回 0 */
    public static int max(int[] arr) {
        if (arr.length == 0) {
            return 0;
        }
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    /** 返回数组最小值，空数组返回 0 */
    public static int min(int[] arr) {
        if (arr.length == 0) {
            return 0;
        }
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        return min;
    }

    /** 返回数组平均值，空数组返回 0.0 */
    public static double avg(int[] arr) {
        if (arr.length == 0) {
            return 0.0;
        }
        int sum = 0;
        for (int value : arr) {
            sum += value;
        }
        return (double) sum / arr.length;
    }
}
