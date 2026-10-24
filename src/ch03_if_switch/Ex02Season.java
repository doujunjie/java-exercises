package ch03_if_switch;

/**
 * 练习 2：按月份返回季节
 *
 * 【要求】
 * 1. 实现 season(int month)，用 switch 按月份返回季节：
 *    3、4、5 月 -> 春
 *    6、7、8 月 -> 夏
 *    9、10、11 月 -> 秋
 *    12、1、2 月 -> 冬
 * 2. 月份不在 1 ~ 12 范围时返回「月份不合法」
 * 3. 阅读注释和参考答案，对比传统 switch 与 Java 14 箭头 switch 两种写法
 *
 * 【示例结果】
 * 4 -> 春
 * 10 -> 秋
 * 13 -> 月份不合法
 *
 * 【知识点】switch 语句、多个 case 共用分支、default、Java 14 箭头 switch、switch 表达式
 */
public class Ex02Season {

    public static void main(String[] args) {
        check(3, "春");
        check(6, "夏");
        check(9, "秋");
        check(12, "冬");
        check(13, "月份不合法");
    }

    /** 打印月份对应的季节，并与期望值比较 */
    private static void check(int month, String expected) {
        String actual = season(month);
        System.out.println(month + " -> " + actual);
        System.out.println(expected.equals(actual)
                ? "通过"
                : "未通过（期望 " + expected + "，实际 " + actual + "）");
    }

    /** 把月份转换成季节 */
    public static String season(int month) {
        // TODO 用 switch 判断月份，别忘记 default 分支
        // 提示：传统 switch 中多个 case 可以连续写，共用同一段逻辑
        return "";
    }
}
