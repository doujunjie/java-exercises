package solutions.ch04_loops;

/**
 * 练习 3 参考答案：素数判断与打印
 *
 * 思路：
 * 1. 小于等于 1 的整数不是素数，先单独处理。
 * 2. 从 2 开始试除，只要 i * i <= n 就继续；找到因数立即返回 false。
 * 3. 循环正常结束说明没有因数，返回 true。
 *
 * 为什么可以只查到 sqrt(n)：
 * 如果 n = a * b 且 a <= b，那么 a 一定不大于 sqrt(n)，
 * 所以只要检查到 sqrt(n) 就能发现所有可能的因数。
 *
 * 易错点：
 * - 忘记处理 n <= 1，会把 1 误判成素数。
 * - 循环条件写成 i < n 也能得到正确结果，但多了大量无用计算。
 * - i * i 在 n 接近 int 最大值时可能溢出，工程代码可改成 i <= n / i。
 *
 * break 与 continue 的区别：
 * - break 立即结束当前这一层循环，跳到循环之后。
 * - continue 结束本次循环，跳过剩余语句，直接进入下一次循环的条件判断。
 * 本题找到因数时用 return 直接结束方法，比 break 更直接。
 */
public class Ex03Prime {

    public static void main(String[] args) {
        int[] numbers = {-3, 0, 1, 2, 3, 4, 17, 25, 91, 97};
        boolean[] expected = {false, false, false, true, true,
                false, true, false, false, true};

        for (int i = 0; i < numbers.length; i++) {
            boolean actual = isPrime(numbers[i]);
            System.out.println("isPrime(" + numbers[i] + ") = " + actual);
            System.out.println(actual == expected[i]
                    ? "通过"
                    : "未通过（期望 " + expected[i] + "，实际 " + actual + "）");
        }

        System.out.println("1~50 之间的素数：");
        printPrimes(50);
    }

    /** 判断 n 是否为素数 */
    public static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /** 打印 2 到 limit（含 limit）之间的所有素数 */
    public static void printPrimes(int limit) {
        for (int i = 2; i <= limit; i++) {
            if (isPrime(i)) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }
}
