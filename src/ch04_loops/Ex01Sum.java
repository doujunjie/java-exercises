package ch04_loops;

/**
 * 练习 1：1~100 求和
 *
 * 【要求】
 * 1. 实现 sum1To100()，用 for 循环求 1 到 100 的和
 * 2. 实现 sumEven1To100()，用 while 循环求 1 到 100 中所有偶数的和
 * 3. 实现 sumOdd1To100()，用 do-while 循环求 1 到 100 中所有奇数的和
 * 4. 在方法注释中说明三种循环的差别
 *
 * 【示例结果】
 * 1~100 求和：5050
 * 1~100 偶数和：2550
 * 1~100 奇数和：2500
 *
 * 【知识点】for / while / do-while 的语法与适用场景、循环变量作用域、累加器
 */
public class Ex01Sum {

    public static void main(String[] args) {
        int actual = sum1To100();
        System.out.println("1~100 求和: " + actual);
        System.out.println(actual == 5050 ? "通过" : "未通过（期望 5050，实际 " + actual + "）");

        actual = sumEven1To100();
        System.out.println("1~100 偶数和: " + actual);
        System.out.println(actual == 2550 ? "通过" : "未通过（期望 2550，实际 " + actual + "）");

        actual = sumOdd1To100();
        System.out.println("1~100 奇数和: " + actual);
        System.out.println(actual == 2500 ? "通过" : "未通过（期望 2500，实际 " + actual + "）");

        boolean totalOk = sum1To100() == sumEven1To100() + sumOdd1To100();
        System.out.println(totalOk ? "通过（总和 = 偶数和 + 奇数和）" : "未通过（总和应等于偶数和 + 奇数和）");
    }

    /**
     * 用 for 循环求 1~100 的和。
     *
     * for 适合循环次数已知的场景：初始化、循环条件、变量更新集中在一行，
     * 循环变量只在 for 语句及其循环体内有效。
     */
    public static int sum1To100() {
        // TODO 使用 for 循环从 1 累加到 100
        return 0;
    }

    /**
     * 用 while 循环求 1~100 中所有偶数的和。
     *
     * while 适合只知道循环条件、次数不固定的场景；循环变量要在循环外声明，
     * 循环体内必须记得更新变量，否则会死循环。
     */
    public static int sumEven1To100() {
        // TODO 使用 while 循环累加 1~100 中的偶数
        return 0;
    }

    /**
     * 用 do-while 循环求 1~100 中所有奇数的和。
     *
     * do-while 先执行一次循环体再判断条件，因此循环体至少执行一次；
     * 语法末尾的 while(...) 后面必须写分号。
     */
    public static int sumOdd1To100() {
        // TODO 使用 do-while 循环累加 1~100 中的奇数
        return 0;
    }
}
