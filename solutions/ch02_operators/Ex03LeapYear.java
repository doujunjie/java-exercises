package solutions.ch02_operators;

/**
 * 参考答案：练习 3 判断闰年
 *
 * 思路：闰年满足下面两个条件之一：
 * 1. 能被 4 整除，但不能被 100 整除（普通闰年，如 2024）；
 * 2. 能被 400 整除（世纪闰年，如 2000）。
 * 合并成一个表达式：(year % 4 == 0 && year % 100 != 0) || year % 400 == 0
 *
 * 易错点：
 * 1. 不能只写 year % 4 == 0，否则 1900 会被误判为闰年。
 * 2. && 的优先级高于 ||，但为了可读性建议加括号。
 * 3. 判断相等用 ==，不要写成 =（赋值）。
 * 4. 表达式本身已经是 boolean，不需要再写 if (...) return true; else return false;，
 *    直接 return 表达式即可。
 */
public class Ex03LeapYear {

    public static void main(String[] args) {
        check(2000, true);
        check(1900, false);
        check(2024, true);
        check(2023, false);
    }

    /** 打印年份的判断结果，并与期望值比较 */
    private static void check(int year, boolean expected) {
        boolean actual = isLeapYear(year);
        System.out.println(year + " 是闰年：" + actual);
        System.out.println(actual == expected
                ? "通过"
                : "未通过（期望 " + expected + "，实际 " + actual + "）");
    }

    /** 判断 year 是否为闰年 */
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }
}
