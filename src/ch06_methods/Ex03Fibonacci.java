package ch06_methods;

/**
 * 练习 3：斐波那契数列（递归与迭代对比）
 *
 * 【要求】
 * 1. 实现 fibRecursive(int n)，用递归求第 n 个斐波那契数
 * 2. 实现 fibIterative(int n)，用循环求第 n 个斐波那契数
 * 3. 规定 fib(0) = 0，fib(1) = 1，fib(n) = fib(n - 1) + fib(n - 2)
 * 4. 在 main 中用 System.nanoTime 对比 n = 35 时两种写法的耗时
 * 5. 在注释中说明递归重复计算的代价
 *
 * 【示例结果】
 * fib(0) = 0，fib(1) = 1，fib(10) = 55
 * n = 35 时两种写法结果都是 9227465，但递归明显更慢
 *
 * 【知识点】递归树、重复子问题、迭代与状态转移、System.nanoTime 计时
 */
public class Ex03Fibonacci {

    public static void main(String[] args) {
        long[] expected = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55};

        for (int n = 0; n <= 10; n++) {
            long recursive = fibRecursive(n);
            long iterative = fibIterative(n);
            System.out.println("fib(" + n + ") 递归 = " + recursive + "，迭代 = " + iterative);
            System.out.println(recursive == expected[n] && iterative == expected[n]
                    ? "通过"
                    : "未通过（期望 " + expected[n] + "，实际 递归 " + recursive + "、迭代 " + iterative + "）");
        }

        long start = System.nanoTime();
        long recursive35 = fibRecursive(35);
        long recursiveCost = System.nanoTime() - start;

        start = System.nanoTime();
        long iterative35 = fibIterative(35);
        long iterativeCost = System.nanoTime() - start;

        System.out.println("n = 35 递归结果: " + recursive35 + "，耗时约 " + recursiveCost / 1_000_000.0 + " ms");
        System.out.println("n = 35 迭代结果: " + iterative35 + "，耗时约 " + iterativeCost / 1_000_000.0 + " ms");
        System.out.println(recursive35 == 9227465 && iterative35 == 9227465
                ? "通过"
                : "未通过（期望 9227465，实际 递归 " + recursive35 + "、迭代 " + iterative35 + "）");
    }

    /**
     * 递归求第 n 个斐波那契数。
     *
     * 直接按定义递归会反复计算同一个子问题，时间复杂度约为 O(2^n)，
     * n 稍微变大耗时就急剧上升，这也是需要用迭代或记忆化优化的原因。
     */
    public static long fibRecursive(int n) {
        // TODO 递归出口 n == 0、n == 1，其余返回前两项之和
        return 0;
    }

    /**
     * 迭代求第 n 个斐波那契数。
     *
     * 用两个变量保存前两项，从前往后递推，时间复杂度 O(n)，空间复杂度 O(1)。
     */
    public static long fibIterative(int n) {
        // TODO 用循环递推，注意 n == 0 的边界
        return 0;
    }
}
