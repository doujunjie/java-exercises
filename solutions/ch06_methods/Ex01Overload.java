package solutions.ch06_methods;

/**
 * 练习 1 参考答案：方法重载
 *
 * 重载的判定依据：
 * 同一个类中，方法名相同、参数列表不同（参数个数、类型或顺序不同），就构成重载。
 * 返回值类型、参数名、访问修饰符都不参与重载判定。
 * 编译器在调用时根据实参的个数和类型选择最匹配的方法。
 *
 * 易错点：
 * - 只改返回值类型不构成重载，会直接编译报错。
 * - max(3, 5) 会匹配 int 版本；max(3.0, 5) 会匹配 double 版本，注意字面量类型。
 * - 三参数版本可以复用两参数版本，减少重复代码。
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
        return a > b ? a : b;
    }

    /** 返回三个 int 中的最大值，复用两参数版本 */
    public static int max(int a, int b, int c) {
        return max(max(a, b), c);
    }

    /** 返回两个 double 中的较大值 */
    public static double max(double a, double b) {
        return a > b ? a : b;
    }
}
