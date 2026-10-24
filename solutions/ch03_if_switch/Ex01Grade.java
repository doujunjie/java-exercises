package solutions.ch03_if_switch;

/**
 * 参考答案：练习 1 分数转等级
 *
 * 思路：
 * 1. 先做输入校验：小于 0 或大于 100 直接返回「分数不合法」。
 * 2. 再用 if-else if 链从高到低判断，最后一个 else 兜住 0 ~ 59。
 *
 * 为什么从高到低判断？
 * 如果先写 score >= 60，那么 95 也会满足，就永远进不到 90 以上的分支。
 * 区间判断的条件必须互斥且顺序正确，或者写成 score >= 90 && score <= 100 的完整区间。
 *
 * 易错点：
 * 1. 判断相等用 ==，不要写成 score = 90（赋值，编译报错）。
 * 2. 比较字符串内容要用 equals，例如 "优秀(A)".equals(actual)，不能用 ==。
 * 3. 边界值 90、80、70、60 要归入较高等级，所以用 >=。
 * 4. 非法分数要先判断，否则 -1 会落进「不及格」，不符合题意。
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
        if (score < 0 || score > 100) {
            return "分数不合法";
        } else if (score >= 90) {
            return "优秀(A)";
        } else if (score >= 80) {
            return "良好(B)";
        } else if (score >= 70) {
            return "中等(C)";
        } else if (score >= 60) {
            return "及格(D)";
        } else {
            // 走到这里说明分数在 0 ~ 59 之间
            return "不及格(E)";
        }
    }
}
