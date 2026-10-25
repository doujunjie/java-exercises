package ch01_basics;

/**
 * 练习 3：摄氏温度转华氏温度
 *
 * 【要求】
 * 1. 实现 celsiusToFahrenheit 方法，把摄氏温度换算成华氏温度
 * 2. 公式：F = C * 9 / 5 + 32
 * 3. 在注释里说明：如果写成 9 / 5，会先做整数除法得到 1，导致结果错误
 *
 * 【示例】
 * 输入 37.0 -> 输出 98.6
 * 输入 100.0 -> 输出 212.0
 *
 * 【知识点】double 运算、整数除法陷阱、方法定义与调用
 */
public class Ex03Temperature {

    public static void main(String[] args) {
        double f = celsiusToFahrenheit(37.0);
        System.out.println("37.0 摄氏度 = " + f + " 华氏度");
        System.out.println(Math.abs(f - 98.6) < 1e-6 ? "通过" : "未通过（期望 98.6，实际 " + f + "）");

        double f2 = celsiusToFahrenheit(100.0);
        System.out.println("100.0 摄氏度 = " + f2 + " 华氏度");
        System.out.println(Math.abs(f2 - 212.0) < 1e-6 ? "通过" : "未通过（期望 212.0，实际 " + f2 + "）");
    }

    /** 摄氏温度转华氏温度：F = C * 9 / 5 + 32 */
    public static double celsiusToFahrenheit(double celsius) {
        // TODO 实现转换（注意 9 / 5 是整数除法）
            return celsius * 9 / 5.0 + 32;
    }
}
