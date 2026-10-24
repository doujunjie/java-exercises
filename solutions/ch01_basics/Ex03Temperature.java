package solutions.ch01_basics;

/**
 * 参考答案：练习 3 摄氏温度转华氏温度
 *
 * 思路：公式是 F = C * 9 / 5 + 32。因为 celsius 是 double，
 * 先算 celsius * 9 会得到一个 double，再除以 5.0，小数部分不会丢失。
 *
 * 易错点（本题的核心）：
 * 如果写成 celsius * 9 / 5 + 32，先算 9 / 5 时两个操作数都是 int，
 * 会执行整数除法得到 1，整个公式就变成 celsius * 1 + 32，结果完全错误。
 * 正确写法有三种：
 * 1. celsius * 9.0 / 5 + 32
 * 2. celsius * 9 / 5.0 + 32
 * 3. celsius * 9 / (double) 5 + 32
 * 只要除法两边有一个是 double，就会执行浮点除法。
 *
 * 另外浮点数运算有微小误差，比较结果时不要用 ==，而要用 Math.abs(差) < 1e-6。
 */
public class Ex03Temperature {

    public static void main(String[] args) {
        double f = celsiusToFahrenheit(37.0);
        System.out.println("37.0 摄氏度 = " + f + " 华氏度");
        System.out.println(Math.abs(f - 98.6) < 1e-6 ? "通过" : "未通过（期望 98.6，实际 " + f + "）");

        double f2 = celsiusToFahrenheit(100.0);
        System.out.println("100.0 摄氏度 = " + f2 + " 华氏度");
        System.out.println(Math.abs(f2 - 212.0) < 1e-6 ? "通过" : "未通过（期望 212.0，实际 " + f2 + "）");

        double f3 = celsiusToFahrenheit(-40.0);
        System.out.println("-40.0 摄氏度 = " + f3 + " 华氏度");
        System.out.println(Math.abs(f3 + 40.0) < 1e-6 ? "通过" : "未通过（期望 -40.0，实际 " + f3 + "）");
    }

    /** 摄氏温度转华氏温度：F = C * 9 / 5 + 32 */
    public static double celsiusToFahrenheit(double celsius) {
        // 用 5.0 保证执行浮点除法；若写成 9 / 5，会先做整数除法得到 1
        return celsius * 9 / 5.0 + 32;
    }
}
