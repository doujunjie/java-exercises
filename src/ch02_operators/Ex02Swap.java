package ch02_operators;

/**
 * 练习 2：交换两个整数
 *
 * 【要求】
 * 1. 实现 swap(int a, int b)，返回交换后的两个数 {b, a}
 * 2. main 中用两组数据自检，例如 swap(3, 8) 应得到 {8, 3}
 * 3. 阅读参考答案里的注释，对比三种交换写法：临时变量、加减法、异或
 *
 * 【示例结果】
 * 交换前：a = 3, b = 8
 * 交换后：a = 8, b = 3
 *
 * 【知识点】方法参数的值传递、数组作为返回值、异或运算、复合赋值
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
        // TODO 交换 a 和 b，再把结果放进数组返回
        return new int[]{a, b};
    }
}
