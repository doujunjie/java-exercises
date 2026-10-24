package ch02_operators;

/**
 * 练习 1：两个整数的加、减、乘、除、取余
 *
 * 【要求】
 * 1. 实现 calc(int a, int b)，按顺序返回 5 个结果：a+b、a-b、a*b、a/b、a%b
 * 2. main 中用固定数据 17 和 5 自检 5 个结果
 * 3. 再用 -7 和 3 自检一次，观察整数除法和取余在负数下的结果
 *
 * 【示例结果】
 * 17 + 5 = 22
 * 17 - 5 = 12
 * 17 * 5 = 85
 * 17 / 5 = 3    （整数除法，小数部分舍弃）
 * 17 % 5 = 2
 * -7 / 3 = -2   （向零取整）
 * -7 % 3 = -1   （余数的符号与被除数相同）
 *
 * 【知识点】算术运算符、整数除法、取余、数组作为返回值、Scanner 键盘输入
 *
 * 【Scanner 写法】（只放在注释里示范；main 中不要真的读键盘，否则会一直等待输入）
 * Scanner scanner = new Scanner(System.in);
 * System.out.print("请输入两个整数：");
 * int a = scanner.nextInt();
 * int b = scanner.nextInt();
 * int[] result = calc(a, b);
 * scanner.close();
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
    }

    /**
     * 依次返回 a+b、a-b、a*b、a/b、a%b 五个结果。
     * 注意：调用时 b 不能为 0，否则整数除法会抛出 ArithmeticException。
     */
    public static int[] calc(int a, int b) {
        // TODO 计算 5 个结果并放进数组返回
        return new int[5];
    }
}
