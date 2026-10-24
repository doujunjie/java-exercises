package solutions.ch06_methods;

/**
 * 练习 3 参考答案：斐波那契数列（递归与迭代对比）
 *
 * 递归思路：
 * fib(n) = fib(n - 1) + fib(n - 2)，出口是 fib(0) = 0、fib(1) = 1。
 * 但这样会形成一棵巨大的递归树，fib(5) 会多次计算 fib(3)、fib(2)……
 * 时间复杂度约为 O(2^n)，n = 35 时调用次数已经达到千万级。
 *
 * 迭代思路：
 * 用 prev = 0、curr = 1 保存相邻两项，循环 n 次向后推：
 * next = prev + curr; prev = curr; curr = next;
 * 时间复杂度 O(n)，空间复杂度 O(1)。
 *
 * 易错点：
 * - n == 0 时迭代不能直接返回 curr，否则会错返回 1。
 * - 递归不加记忆化时，n 超过 40 就会明显卡顿。
 * - System.nanoTime 适合测量耗时，返回值单位是纳秒；不要用 currentTimeMillis 测很短的操作。
 * - 单次计时受 JIT 预热影响，这里只看数量级差异，不做精确性能结论。
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

    /** 递归求第 n 个斐波那契数，存在大量重复计算 */
    public static long fibRecursive(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fibRecursive(n - 1) + fibRecursive(n - 2);
    }

    /** 迭代求第 n 个斐波那契数，从前往后递推 */
    public static long fibIterative(int n) {
        if (n == 0) {
            return 0;
        }
        long prev = 0;
        long curr = 1;
        for (int i = 2; i <= n; i++) {
            long next = prev + curr;
            prev = curr;
            curr = next;
        }
        return curr;
    }
}
