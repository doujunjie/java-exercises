package ch05_arrays;

import java.util.Arrays;

/**
 * 练习 2：数组反转
 *
 * 【要求】
 * 1. 实现 reverse(int[] arr)，使用双指针原地反转数组
 * 2. 实现 reversed(int[] arr)，返回反转后的新数组，不改变原数组
 * 3. 在 main 中打印反转前后的数组并自检
 * 4. 通过对比说明哪个方法会改变原数组、哪个不会
 *
 * 【示例结果】{1, 2, 3, 4} → 反转后 {4, 3, 2, 1}
 *
 * 【知识点】数组下标、双指针、引用传递、Arrays.toString 与 Arrays.equals
 */
public class Ex02Reverse {

    public static void main(String[] args) {
        int[] original = {1, 2, 3, 4, 5};
        int[] before = Arrays.copyOf(original, original.length);

        System.out.println("原数组: " + Arrays.toString(original));

        int[] copied = reversed(original);
        System.out.println("reversed 返回的新数组: " + Arrays.toString(copied));
        System.out.println(Arrays.equals(original, before)
                ? "通过（reversed 没有改变原数组）"
                : "未通过（reversed 不应改变原数组，期望 " + Arrays.toString(before)
                + "，实际 " + Arrays.toString(original) + "）");
        System.out.println(Arrays.equals(copied, new int[]{5, 4, 3, 2, 1})
                ? "通过"
                : "未通过（期望 [5, 4, 3, 2, 1]，实际 " + Arrays.toString(copied) + "）");

        reverse(original);
        System.out.println("reverse 之后的原数组: " + Arrays.toString(original));
        System.out.println(Arrays.equals(original, new int[]{5, 4, 3, 2, 1})
                ? "通过"
                : "未通过（期望 [5, 4, 3, 2, 1]，实际 " + Arrays.toString(original) + "）");
    }

    /**
     * 原地反转数组：交换首尾元素，两个指针向中间靠拢。
     *
     * 直接修改传入的数组对象，所以调用方看到的数组内容会改变。
     */
    public static void reverse(int[] arr) {
        // TODO 使用双指针从两端向中间交换
    }

    /**
     * 返回反转后的新数组，不修改传入的数组。
     *
     * 新数组第 i 个元素取原数组第 arr.length - 1 - i 个元素。
     */
    public static int[] reversed(int[] arr) {
        // TODO 创建新数组，按下标倒序复制
        return new int[0];
    }
}
