package ch04_loops;

/**
 * 练习 3：素数判断与打印
 *
 * 【要求】
 * 1. 实现 isPrime(int n)：判断 n 是否为素数
 * 2. 循环条件使用 i * i <= n 进行优化，并正确处理 n <= 1 的情况
 * 3. 实现 printPrimes(int limit)：打印 2 到 limit 之间的所有素数
 * 4. 在注释中说明 break 与 continue 的区别
 *
 * 【示例结果】
 * isPrime(2) = true，isPrime(17) = true
 * isPrime(1) = false，isPrime(91) = false
 * printPrimes(30) 输出：2 3 5 7 11 13 17 19 23 29
 *
 * 【知识点】循环与取余、循环终止条件优化、break 与 continue、方法返回提前结束
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

    /**
     * 判断 n 是否为素数。
     *
     * 只需要检查 2 到 sqrt(n) 之间的整数：如果 n 有大于 sqrt(n) 的因数，
     * 那么它一定还有一个小于 sqrt(n) 的因数。用 i * i <= n 可以避免调用 Math.sqrt。
     */
    public static boolean isPrime(int n) {
        // TODO n <= 1 直接返回 false；再循环到 i * i <= n 判断能否被整除
        return false;
    }

    /**
     * 打印 2 到 limit（含 limit）之间的所有素数，数字之间用一个空格分隔。
     *
     * break 用于立即跳出当前这一层循环；continue 用于跳过本次循环剩下的语句，
     * 直接进入下一次循环。本题只需在循环中用 if 判断并打印，不需要 break 或 continue。
     */
    public static void printPrimes(int limit) {
        // TODO 从 2 遍历到 limit，调用 isPrime 判断后打印
    }
}
