package ch03_if_switch;

/**
 * 练习 1：分数转等级
 *
 * 【要求】
 * 1. 实现 grade(int score)，按下表返回等级文字：
 *    90 ~ 100 -> 优秀(A)
 *    80 ~ 89  -> 良好(B)
 *    70 ~ 79  -> 中等(C)
 *    60 ~ 69  -> 及格(D)
 *    0 ~ 59   -> 不及格(E)
 *    小于 0 或大于 100 -> 分数不合法
 * 2. 必须使用 if-else if 链实现，不要用 switch
 * 3. main 中至少自检 5 个正常分数和 2 个非法分数
 *
 * 【示例结果】
 * 95 -> 优秀(A)
 * 59 -> 不及格(E)
 * 101 -> 分数不合法
 *
 * 【知识点】if-else if 链、区间判断、条件顺序、String 内容比较
 */
public class Ex01Grade {

    public static void main(String[] args) {
        check(95, "优秀(A)");
        check(85, "良好(B)");
        check(75, "中等(C)");
        check(65, "及格(D)");
        check(59, "不及格(E)");
        check(101, "分数不合法");
        check(-1, "分数不合法");
    }

    /** 打印分数对应的等级，并与期望值比较 */
    private static void check(int score, String expected) {
        String actual = grade(score);
        System.out.println(score + " -> " + actual);
        System.out.println(expected.equals(actual)
                ? "通过"
                : "未通过（期望 " + expected + "，实际 " + actual + "）");
    }

    /** 把分数转换成等级文字 */
    public static String grade(int score) {
        // TODO 用 if-else if 链判断，注意先处理非法分数
        return "";
    }
}
