package solutions.ch09_string;

/**
 * 练习 1：回文判断（参考答案）
 *
 * 思路：
 * 1. two pointers：left 指向第一个字符，right 指向最后一个字符。
 * 2. 如果 left 指到的不是字母或数字，直接 left++ 跳过；right 同理。
 * 3. 两边都是有效字符时，统一转小写再比较，不同就立刻返回 false。
 * 4. 相同则 left++、right--，直到 left >= right，说明全部配对成功，返回 true。
 *
 * 易错点：
 * - 只判断了是不是字母，漏了数字：Character.isLetterOrDigit 才能同时覆盖数字。
 * - 跳过字符时写了 left++ 却忘了 continue，导致后面的比较用了旧字符。
 * - 用 s.toLowerCase() 整个转小写虽然也对，但会额外创建一个字符串；双指针边比边转更省。
 * - 空串是回文，循环一次都不进，直接返回 true，别写成 return false。
 * - 用 == 比较 char 是没问题的（char 是基本类型），但比较 String 内容必须用 equals。
 *
 * 说明：为了 main 能直接调用，isPalindrome 声明为 static。
 */
public class Ex01Palindrome {

    public static void main(String[] args) {
        String[] cases = {
                "level",
                "A man, a plan, a canal: Panama",
                "hello",
                "No 'x' in Nixon",
                ""
        };
        boolean[] expected = {true, true, false, true, true};

        for (int i = 0; i < cases.length; i++) {
            boolean actual = isPalindrome(cases[i]);
            System.out.println("输入：" + display(cases[i]) + "，结果：" + actual);
        }

        System.out.println("---- 自检 ----");
        for (int i = 0; i < cases.length; i++) {
            check("isPalindrome(" + display(cases[i]) + ")", expected[i], isPalindrome(cases[i]));
        }
    }

    /**
     * 判断是否为回文串：忽略大小写、空格和标点，只比较字母与数字。
     *
     * @param s 待判断的字符串
     * @return 是回文返回 true；s 为 null 时返回 false
     */
    public static boolean isPalindrome(String s) {
        if (s == null) {
            return false;
        }
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            char leftChar = s.charAt(left);
            char rightChar = s.charAt(right);
            if (!Character.isLetterOrDigit(leftChar)) {
                left++;
                continue;
            }
            if (!Character.isLetterOrDigit(rightChar)) {
                right--;
                continue;
            }
            if (Character.toLowerCase(leftChar) != Character.toLowerCase(rightChar)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /** 空串打印成「（空字符串）」，方便看输出。 */
    private static String display(String s) {
        return s.isEmpty() ? "（空字符串）" : s;
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
