package ch05_arrays;

/**
 * 练习 1：数组最大值、最小值与平均值
 *
 * 【要求】
 * 1. 实现 max(int[] arr)，返回数组中的最大值
 * 2. 实现 min(int[] arr)，返回数组中的最小值
 * 3. 实现 avg(int[] arr)，返回数组元素的平均值
 * 4. 处理空数组：三个方法都返回 0，并在注释中说明原因
 *
 * 【示例结果】
 * 数组 {3, 1, 4, 1, 5, 9, 2, 6}
 * 最大值 = 9，最小值 = 1，平均值 = 3.875
 * 空数组：最大值 = 0，最小值 = 0，平均值 = 0.0
 *
 * 【知识点】数组遍历、length 属性、空数组边界、整数除法与浮点除法
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

    /**
     * 返回数组最大值。
     *
     * 空数组没有最大值，约定返回 0，调用方可以通过 arr.length == 0 先行判断。
     */
    public static int max(int[] arr) {
        // TODO 空数组返回 0，否则遍历比较
        return 0;
    }

    /**
     * 返回数组最小值。
     *
     * 空数组没有最小值，约定返回 0。
     */
    public static int min(int[] arr) {
        // TODO 空数组返回 0，否则遍历比较
        return 0;
    }

    /**
     * 返回数组平均值。
     *
     * 空数组不能做除法（会得到 NaN），约定返回 0.0；
     * 求和后要转换成 double 再除，否则会丢失小数部分。
     */
    public static double avg(int[] arr) {
        // TODO 空数组返回 0.0，否则先求和再除以长度
        return 0.0;
    }
}
