package solutions.ch05_arrays;

import java.util.Arrays;

/**
 * 练习 2 参考答案：数组反转
 *
 * 思路：
 * 1. reverse 使用双指针：left 指向头部、right 指向尾部，
 *    交换后 left++、right--，直到 left >= right。
 * 2. reversed 新建一个等长数组，第 i 个位置放原数组第 length - 1 - i 个元素。
 *
 * 两者对比：
 * - reverse 原地修改传入的数组对象，方法返回后原数组内容已经改变；
 * - reversed 不修改原数组，原数组保持不变，只是返回一个新数组。
 * Java 中数组是引用类型，方法收到的是引用的副本，但两个引用指向同一个数组对象，
 * 所以通过引用修改元素会影响调用方。
 *
 * 易错点：
 * - 双指针循环条件写成 left <= right 也不会错，但会多交换一次中间元素。
 * - reversed 中直接写 result = arr 只是复制引用，两个变量仍指向同一个数组。
 * - 奇数长度数组中间元素不需要交换。
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

    /** 原地反转数组，会改变传入的数组 */
    public static void reverse(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }

    /** 返回反转后的新数组，不改变传入的数组 */
    public static int[] reversed(int[] arr) {
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = arr[arr.length - 1 - i];
        }
        return result;
    }
}
