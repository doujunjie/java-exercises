package ch09_string;

/**
 * 练习 1：回文判断
 *
 * 【要求】
 * 1. 实现方法 boolean isPalindrome(String s)
 * 2. 忽略大小写，并忽略空格和标点符号，只比较字母和数字
 * 3. 必须用双指针实现（left 从左往右，right 从右往左，相遇即结束）
 * 4. main 自检："level" 与 "A man, a plan, a canal: Panama" 为 true，"hello" 为 false
 *
 * 【示例结果】
 * 输入：level，结果：true
 * 输入：A man, a plan, a canal: Panama，结果：true
 * 输入：hello，结果：false
 * 输入：No 'x' in Nixon，结果：true
 * 输入：（空字符串），结果：true
 * 通过：isPalindrome("level")
 *
 * 【知识点】String.charAt、Character.isLetterOrDigit、Character.toLowerCase、
 *           双指针、循环里跳过无效字符
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
     * @return 是回文返回 true
     */
    public static boolean isPalindrome(String s) {
        // TODO 实现：双指针从两端向中间走，跳过非字母数字字符，比较时统一转小写
        return false;
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
