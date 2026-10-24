package ch09_string;

import java.util.Arrays;

/**
 * 练习 3：字符串常用 API
 *
 * 【要求】
 * 1. String reverseByStringBuilder(String s)：用 StringBuilder 的 reverse() 实现反转
 * 2. String reverseByCharArray(String s)：先 toCharArray()，再首尾交换
 * 3. String reverseByRecursion(String s)：用递归实现反转（每次取首字符放到末尾）
 * 4. int[] countKinds(String s)：返回 [大写字母数, 小写字母数, 数字数, 空格数]
 * 5. main 自检：三种方式反转 "hello" 都应得到 "olleh"，
 *    countKinds("Hello World 2024") 应得到 [2, 8, 4, 2]
 *
 * 【示例输出】
 * 反转结果：olleh / olleh / olleh
 * 大小写数字空格统计：[2, 8, 4, 2]
 * 通过：StringBuilder 反转
 * 通过：char[] 反转
 * 通过：递归反转
 * 通过：countKinds("Hello World 2024")
 *
 * 【知识点】String 不可变、StringBuilder、toCharArray、for-each、递归、
 *           Character.isUpperCase / isLowerCase / isDigit、Arrays.toString
 *
 * 说明：为了 main 能直接调用，方法都声明为 static。
 */
public class Ex03StringApi {

    public static void main(String[] args) {
        String text = "hello";
        System.out.println("原文：" + text);
        System.out.println("StringBuilder 反转：" + reverseByStringBuilder(text));
        System.out.println("char[] 反转：" + reverseByCharArray(text));
        System.out.println("递归反转：" + reverseByRecursion(text));

        String mixed = "Hello World 2024";
        System.out.println("统计：" + Arrays.toString(countKinds(mixed)));

        System.out.println("---- 自检 ----");
        check("StringBuilder 反转 hello", "olleh", reverseByStringBuilder(text));
        check("char[] 反转 hello", "olleh", reverseByCharArray(text));
        check("递归反转 hello", "olleh", reverseByRecursion(text));
        check("递归反转空串", "", reverseByRecursion(""));
        check("countKinds(\"Hello World 2024\")", "[2, 8, 4, 2]",
                Arrays.toString(countKinds(mixed)));
        check("countKinds(\"abc\")", "[0, 3, 0, 0]", Arrays.toString(countKinds("abc")));
    }

    /**
     * 方式一：用 StringBuilder 的 reverse()。
     *
     * @param s 原字符串
     * @return 反转后的字符串
     */
    public static String reverseByStringBuilder(String s) {
        // TODO 实现：new StringBuilder(s).reverse().toString()
        return "";
    }

    /**
     * 方式二：转成 char[]，用双指针首尾交换。
     *
     * @param s 原字符串
     * @return 反转后的字符串
     */
    public static String reverseByCharArray(String s) {
        // TODO 实现：toCharArray，left/right 两个下标交换字符，最后 new String(chars)
        return "";
    }

    /**
     * 方式三：递归。思路：reverse(s) = reverse(s.substring(1)) + s.charAt(0)。
     *
     * @param s 原字符串
     * @return 反转后的字符串
     */
    public static String reverseByRecursion(String s) {
        // TODO 实现：递归出口是 s 长度为 0 或 1，直接返回 s
        return "";
    }

    /**
     * 统计四类字符的个数。
     *
     * @param s 原字符串
     * @return 长度为 4 的数组：[大写字母数, 小写字母数, 数字数, 空格数]
     */
    public static int[] countKinds(String s) {
        // TODO 实现：遍历字符，按 Character.isUpperCase / isLowerCase / isDigit / == ' ' 分别计数
        return new int[4];
    }

    /** 比较期望值与实际值，打印「通过」或「未通过（期望 X，实际 Y）」。 */
    private static void check(String label, Object expected, Object actual) {
        boolean ok = expected == null ? actual == null : expected.equals(actual);
        if (ok) {
            System.out.println("通过：" + label);
        } else {
            System.out.println("未通过：" + label + "（期望 " + expected + "，实际 " + actual + "）");
        }
    }
}
