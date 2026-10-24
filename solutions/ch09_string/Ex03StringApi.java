package solutions.ch09_string;

import java.util.Arrays;

/**
 * 练习 3：字符串常用 API（参考答案）
 *
 * 思路：
 * 1. reverseByStringBuilder：StringBuilder 是可变字符序列，reverse() 原地翻转后 toString 即可。
 * 2. reverseByCharArray：String 不可变，先 toCharArray() 拿到可改的数组，
 *    再用 left/right 双指针交换，最后 new String(chars) 造出新字符串。
 * 3. reverseByRecursion：把问题缩小成「反转剩下的部分 + 第一个字符」，出口是长度 0 或 1。
 * 4. countKinds：四个计数器分别对应 Character.isUpperCase / isLowerCase / isDigit / 空格。
 *
 * 易错点：
 * - 以为 String 能直接改：s.charAt(0) = 'x' 编译不过，String 是不可变对象。
 * - char[] 交换时忘了临时变量，或者循环条件写成 left <= right 导致中间字符被换回去。
 * - 递归忘了写出口，或者出口写成 length == 0 却漏了 length == 1，导致无限递归 StackOverflowError。
 * - 递归用 s.substring(1) + s.charAt(0) 时顺序写反，得到的是原串而不是反转。
 * - 统计数字用 Character.isDigit，不要用 isLetter，也不要用 ASCII 比较硬编码。
 * - 空格判断用 c == ' '，char 用单引号；写成 " "（String）会编译报错。
 * - 打印 int[] 要 Arrays.toString，直接拼字符串只会得到 [I@1b6d3586。
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
        if (s == null) {
            return null;
        }
        return new StringBuilder(s).reverse().toString();
    }

    /**
     * 方式二：转成 char[]，用双指针首尾交换。
     *
     * @param s 原字符串
     * @return 反转后的字符串
     */
    public static String reverseByCharArray(String s) {
        if (s == null) {
            return null;
        }
        char[] chars = s.toCharArray();
        int left = 0;
        int right = chars.length - 1;
        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
        return new String(chars);
    }

    /**
     * 方式三：递归。思路：reverse(s) = reverse(s.substring(1)) + s.charAt(0)。
     *
     * @param s 原字符串
     * @return 反转后的字符串
     */
    public static String reverseByRecursion(String s) {
        // 出口：空串和单字符已经是「反转好的」
        if (s == null || s.length() <= 1) {
            return s;
        }
        // 第一个字符挪到最后，剩下的继续递归
        return reverseByRecursion(s.substring(1)) + s.charAt(0);
    }

    /**
     * 统计四类字符的个数。
     *
     * @param s 原字符串
     * @return 长度为 4 的数组：[大写字母数, 小写字母数, 数字数, 空格数]
     */
    public static int[] countKinds(String s) {
        int[] result = new int[4];
        if (s == null) {
            return result;
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isUpperCase(c)) {
                result[0]++;
            } else if (Character.isLowerCase(c)) {
                result[1]++;
            } else if (Character.isDigit(c)) {
                result[2]++;
            } else if (c == ' ') {
                result[3]++;
            }
        }
        return result;
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
