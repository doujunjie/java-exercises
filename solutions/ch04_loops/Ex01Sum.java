package solutions.ch04_loops;

/**
 * 练习 1 参考答案：1~100 求和
 *
 * 思路：
 * 1. for：循环次数已知，三部分写在括号里，最不容易漏写变量更新。
 * 2. while：先判断后执行，循环变量要在循环外初始化。
 * 3. do-while：先执行后判断，循环体至少执行一次，结尾的 while(...) 必须加分号。
 *
 * 易错点：
 * - 三个方法的累加起点都是 1，不是 0。
 * - 偶数从 2 开始、奇数从 1 开始，每次加 2 可以少判断一般情况。
 * - do-while 即使条件是 false 也会先执行一次，不能用来代替“可能一次都不执行”的逻辑。
 */
public class Ex01Sum {

    public static void main(String[] args) {
        int actual = sum1To100();
        System.out.println("1~100 求和: " + actual);
        System.out.println(actual == 5050 ? "通过" : "未通过（期望 5050，实际 " + actual + "）");

        actual = sumEven1To100();
        System.out.println("1~100 偶数和: " + actual);
        System.out.println(actual == 2550 ? "通过" : "未通过（期望 2550，实际 " + actual + "）");

        actual = sumOdd1To100();
        System.out.println("1~100 奇数和: " + actual);
        System.out.println(actual == 2500 ? "通过" : "未通过（期望 2500，实际 " + actual + "）");

        boolean totalOk = sum1To100() == sumEven1To100() + sumOdd1To100();
        System.out.println(totalOk ? "通过（总和 = 偶数和 + 奇数和）" : "未通过（总和应等于偶数和 + 奇数和）");
    }

    /** 用 for 循环求 1~100 的和 */
    public static int sum1To100() {
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            sum += i;
        }
        return sum;
    }

    /** 用 while 循环求 1~100 中所有偶数的和 */
    public static int sumEven1To100() {
        int sum = 0;
        int i = 2;
        while (i <= 100) {
            sum += i;
            i += 2;
        }
        return sum;
    }

    /** 用 do-while 循环求 1~100 中所有奇数的和 */
    public static int sumOdd1To100() {
        int sum = 0;
        int i = 1;
        do {
            sum += i;
            i += 2;
        } while (i <= 100);
        return sum;
    }
}
