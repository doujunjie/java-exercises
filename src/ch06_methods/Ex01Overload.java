package ch06_methods;

/**
 * 练习 1：方法重载
 *
 * 【要求】
 * 1. 实现 max(int a, int b)，返回两个整数中的较大值
 * 2. 实现 max(int a, int b, int c)，返回三个整数中的最大值
 * 3. 实现 max(double a, double b)，返回两个小数中的较大值
 * 4. 在注释中说明重载的判定依据
 *
 * 【示例结果】
 * max(3, 7) = 7
 * max(3, 7, 5) = 7
 * max(2.5, 7.25) = 7.25
 *
 * 【知识点】方法重载、参数列表、返回值类型、调用时的静态分派
 */
public class Ex01Overload {

    public static void main(String[] args) {
        int two = max(3, 7);
        System.out.println("max(3, 7) = " + two);
        System.out.println(two == 7 ? "通过" : "未通过（期望 7，实际 " + two + "）");

        int three = max(3, 7, 5);
        System.out.println("max(3, 7, 5) = " + three);
        System.out.println(three == 7 ? "通过" : "未通过（期望 7，实际 " + three + "）");

        double decimal = max(2.5, 7.25);
        System.out.println("max(2.5, 7.25) = " + decimal);
        System.out.println(Math.abs(decimal - 7.25) < 1e-9
                ? "通过"
                : "未通过（期望 7.25，实际 " + decimal + "）");

        int negative = max(-8, -3);
        System.out.println("max(-8, -3) = " + negative);
        System.out.println(negative == -3 ? "通过" : "未通过（期望 -3，实际 " + negative + "）");
    }

    /** 返回两个 int 中的较大值 */
    public static int max(int a, int b) {
        // TODO 比较 a 和 b
        return 0;
    }

    /** 返回三个 int 中的最大值 */
    public static int max(int a, int b, int c) {
        // TODO 可以复用 max(int, int)，也可以直接比较
        return 0;
    }

    /** 返回两个 double 中的较大值 */
    public static double max(double a, double b) {
        // TODO 比较 a 和 b
        return 0.0;
    }
}
