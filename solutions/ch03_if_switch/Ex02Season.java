package solutions.ch03_if_switch;

/**
 * 参考答案：练习 2 按月份返回季节
 *
 * 思路：switch 根据 month 的值跳到对应的 case。
 * 多个 case 可以连续写、共用一段逻辑，这叫「case 穿透」：
 * 3、4、5 都执行到 return "春"，函数直接返回，不会继续往下走。
 *
 * 传统 switch（本文件使用的写法）：
 *     switch (month) {
 *         case 3:
 *         case 4:
 *         case 5:
 *             return "春";
 *         ...
 *         default:
 *             return "月份不合法";
 *     }
 *
 * Java 14 箭头 switch（JDK 21 可用，更简洁，不会意外穿透）：
 *     return switch (month) {
 *         case 3, 4, 5 -> "春";
 *         case 6, 7, 8 -> "夏";
 *         case 9, 10, 11 -> "秋";
 *         case 12, 1, 2 -> "冬";
 *         default -> "月份不合法";
 *     };
 *
 * 两种写法的区别：
 * 1. 箭头形式一个 case 可以写多个值（case 3, 4, 5），传统形式要写多个 case 标签。
 * 2. 箭头形式自动 break，不会穿透；传统形式忘记 break 会继续执行下一个分支。
 * 3. 箭头 switch 可以直接作为表达式返回值（switch 表达式），传统形式只能作为语句。
 * 4. 箭头分支如果有多条语句，用 { } 包起来，并用 yield 返回结果。
 *
 * 易错点：
 * 1. 传统 switch 忘记 break 或 return，会导致 case 穿透，结果错误。
 * 2. 一定要写 default，处理 13、0 这类非法月份。
 * 3. switch 的表达式类型可以是 byte、short、char、int、String、枚举，
 *    不能直接用 long、double。
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
        switch (month) {
            case 3:
            case 4:
            case 5:
                return "春";
            case 6:
            case 7:
            case 8:
                return "夏";
            case 9:
            case 10:
            case 11:
                return "秋";
            case 12:
            case 1:
            case 2:
                return "冬";
            default:
                return "月份不合法";
        }
    }
}
