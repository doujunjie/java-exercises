package ch06_methods;

/**
 * 练习 2：阶乘（递归）
 *
 * 【要求】
 * 1. 实现 long factorial(int n)，使用递归计算 n 的阶乘
 * 2. 规定 0! = 1，1! = 1
 * 3. 当 n < 0 时抛出 IllegalArgumentException，并给出提示信息
 * 4. 在 main 中打印 1! 到 10! 并逐个自检
 *
 * 【示例结果】
 * 1! = 1
 * 2! = 2
 * 3! = 6
 * ...
 * 10! = 3628800
 *
 * 【知识点】递归、递归出口、递归调用链、long 的取值范围、异常参数校验
 */
public class Ex02Factorial {

    public static void main(String[] args) {
        long[] expected = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800};

        for (int n = 0; n <= 10; n++) {
            long actual = factorial(n);
            System.out.println(n + "! = " + actual);
            System.out.println(actual == expected[n]
                    ? "通过"
                    : "未通过（期望 " + expected[n] + "，实际 " + actual + "）");
        }

        try {
            factorial(-1);
            System.out.println("未通过（期望抛出 IllegalArgumentException，实际未抛出）");
        } catch (IllegalArgumentException e) {
            System.out.println("通过（负数参数抛出 IllegalArgumentException：" + e.getMessage() + "）");
        }
    }

    /**
     * 递归计算 n 的阶乘。
     *
     * 递归出口：n == 0 或 n == 1 时返回 1；
     * 递归关系：n! = n * (n - 1)!。
     * n < 0 属于非法参数，直接抛出 IllegalArgumentException。
     */
    public static long factorial(int n) {
        // TODO 先校验负数，再写递归出口和递归调用
        return 0;
    }
}
