package solutions.ch06_methods;

/**
 * 练习 2 参考答案：阶乘（递归）
 *
 * 思路：
 * 1. 先校验非法参数：n < 0 时抛出 IllegalArgumentException。
 * 2. 递归出口：0! 和 1! 都等于 1。
 * 3. 递归关系：n! = n * (n - 1)!，方法自己调用自己，直到走到出口。
 *
 * 以 5! 为例，调用链为：
 * factorial(5) = 5 * factorial(4)
 *              = 5 * 4 * factorial(3)
 *              = 5 * 4 * 3 * 2 * factorial(1)
 *              = 120
 *
 * 易错点：
 * - 忘记写递归出口会导致无限递归，最终抛 StackOverflowError。
 * - 0! 按数学定义等于 1，不能返回 0。
 * - 阶乘增长极快，13! 已经超出 int 范围，所以返回值用 long；21! 会超出 long。
 * - 负数没有阶乘，必须显式抛异常，而不是返回 0 或 -1。
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

    /** 递归计算 n 的阶乘，n 不能为负数 */
    public static long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n 不能为负数: " + n);
        }
        if (n == 0 || n == 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }
}
