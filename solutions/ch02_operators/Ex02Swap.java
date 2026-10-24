package solutions.ch02_operators;

/**
 * 参考答案：练习 2 交换两个整数
 *
 * 关键理解：Java 的方法参数是「值传递」。
 * 如果在方法里直接写 a = b; b = temp;，交换的只是形参（副本），
 * 调用方的变量不会改变。所以本题把交换后的两个值放进数组返回。
 *
 * 三种交换写法对比：
 *
 * 写法一：临时变量（最推荐）
 *     int temp = a;
 *     a = b;
 *     b = temp;
 *     优点：语义最清楚，适用于任意类型（int、String、对象都行）。
 *     缺点：多占一个临时变量的空间。
 *
 * 写法二：加减法（不用临时变量）
 *     a = a + b;
 *     b = a - b;
 *     a = a - b;
 *     优点：不需要额外变量，思路直观。
 *     缺点：a + b 可能超出 int 范围导致溢出，结果错误；
 *           只适用于数值类型，可读性一般。
 *
 * 写法三：异或（位运算）
 *     a = a ^ b;
 *     b = a ^ b;
 *     a = a ^ b;
 *     优点：不产生进位，不会溢出；不需要额外变量。
 *     缺点：只适用于整数；可读性最差，调试时不容易看懂；
 *           如果 a 和 b 是同一个变量的引用（本题是基本类型，不会出现），会被清零。
 *
 * 结论：日常开发优先用临时变量，性能差异可以忽略，可读性最重要。
 */
public class Ex02Swap {

    public static void main(String[] args) {
        int[] first = swap(3, 8);
        System.out.println("交换前：a = 3, b = 8");
        System.out.println("交换后：a = " + first[0] + ", b = " + first[1]);
        System.out.println(first[0] == 8 && first[1] == 3
                ? "通过"
                : "未通过（期望 a = 8, b = 3，实际 a = " + first[0] + ", b = " + first[1] + "）");

        int[] second = swap(-5, 5);
        System.out.println("交换前：a = -5, b = 5");
        System.out.println("交换后：a = " + second[0] + ", b = " + second[1]);
        System.out.println(second[0] == 5 && second[1] == -5
                ? "通过"
                : "未通过（期望 a = 5, b = -5，实际 a = " + second[0] + ", b = " + second[1] + "）");
    }

    /** 交换两个整数，按 {b, a} 的顺序返回 */
    public static int[] swap(int a, int b) {
        // 写法一：临时变量，最清晰
        int temp = a;
        a = b;
        b = temp;
        return new int[]{a, b};
    }
}
