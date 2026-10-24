package ch02_operators;

/**
 * 练习 3：判断闰年
 *
 * 【要求】
 * 1. 实现 isLeapYear(int year)，是闰年返回 true，否则返回 false
 * 2. 规则：能被 4 整除且不能被 100 整除，或者能被 400 整除
 *    即 (year % 4 == 0 && year % 100 != 0) || year % 400 == 0
 * 3. main 中自检 2000、1900、2024、2023 四个年份
 *
 * 【示例结果】
 * 2000 是闰年：true
 * 1900 是闰年：false
 * 2024 是闰年：true
 * 2023 是闰年：false
 *
 * 【知识点】取余、关系运算符、逻辑运算符 && 与 ||、方法返回 boolean
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
        // TODO 按规则返回 true 或 false
        return false;
    }
}
