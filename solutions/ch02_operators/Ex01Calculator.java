package solutions.ch02_operators;

/**
 * 参考答案：练习 1 两个整数的加、减、乘、除、取余
 *
 * 思路：先分别算出 5 个结果存到局部变量，再放进 int 数组返回。
 * 返回数组是为了让一个方法一次带回多个结果（Java 方法只能有一个返回值）。
 *
 * 整数除法与取余规则（重点）：
 * 1. 两个 int 相除，结果仍是 int，小数部分直接舍弃，不做四舍五入。
 *    17 / 5 = 3，-7 / 3 = -2（向零取整，不是向下取整的 -3）。
 * 2. 取余 a % b 满足 a = (a / b) * b + (a % b)，
 *    余数的符号永远和被除数 a 相同：
 *    -7 % 3 = -1，7 % -3 = 1，-7 % -3 = -1。
 * 3. 除数为 0 时，整数除法会抛 ArithmeticException: / by zero。
 *
 * Scanner 版写法见类末尾注释。
 */
public class Ex01Calculator {

    public static void main(String[] args) {
        int[] r = calc(17, 5);
        System.out.println("17 + 5 = " + r[0]);
        System.out.println(r[0] == 22 ? "通过" : "未通过（期望 22，实际 " + r[0] + "）");
        System.out.println("17 - 5 = " + r[1]);
        System.out.println(r[1] == 12 ? "通过" : "未通过（期望 12，实际 " + r[1] + "）");
        System.out.println("17 * 5 = " + r[2]);
        System.out.println(r[2] == 85 ? "通过" : "未通过（期望 85，实际 " + r[2] + "）");
        System.out.println("17 / 5 = " + r[3]);
        System.out.println(r[3] == 3 ? "通过" : "未通过（期望 3，实际 " + r[3] + "）");
        System.out.println("17 % 5 = " + r[4]);
        System.out.println(r[4] == 2 ? "通过" : "未通过（期望 2，实际 " + r[4] + "）");

        // 负数参与运算：除法和取余的规则和数学课本不完全一样
        int[] negative = calc(-7, 3);
        System.out.println("-7 / 3 = " + negative[3]);
        System.out.println(negative[3] == -2 ? "通过" : "未通过（期望 -2，实际 " + negative[3] + "）");
        System.out.println("-7 % 3 = " + negative[4]);
        System.out.println(negative[4] == -1 ? "通过" : "未通过（期望 -1，实际 " + negative[4] + "）");

        // 除数为负：余数符号跟着被除数走
        int[] third = calc(7, -3);
        System.out.println("7 % -3 = " + third[4]);
        System.out.println(third[4] == 1 ? "通过" : "未通过（期望 1，实际 " + third[4] + "）");
    }

    /**
     * 依次返回 a+b、a-b、a*b、a/b、a%b 五个结果。
     * 注意：调用时 b 不能为 0，否则整数除法会抛出 ArithmeticException。
     */
    public static int[] calc(int a, int b) {
        int sum = a + b;
        int difference = a - b;
        int product = a * b;
        int quotient = a / b;
        int remainder = a % b;
        return new int[]{sum, difference, product, quotient, remainder};
    }

    /*
     * 键盘输入版（需要 import java.util.Scanner;）：
     *
     * Scanner scanner = new Scanner(System.in);
     * System.out.print("请输入两个整数：");
     * int a = scanner.nextInt();
     * int b = scanner.nextInt();
     * if (b == 0) {
     *     System.out.println("除数不能为 0");
     * } else {
     *     int[] result = calc(a, b);
     *     System.out.println(a + " + " + b + " = " + result[0]);
     *     // 其余结果同理
     * }
     * scanner.close();
     *
     * 注意：nextInt() 不会消费换行符，后面如果再用 nextLine() 会读到空字符串。
     */
}
