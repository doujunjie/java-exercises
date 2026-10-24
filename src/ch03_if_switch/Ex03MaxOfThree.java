package ch03_if_switch;

/**
 * 练习 3：三个整数中的最大值
 *
 * 【要求】
 * 1. 实现 max(int a, int b, int c)，用 if 嵌套比较出三个数中的最大值
 * 2. main 中用多组数据自检，包括三个数相等和全是负数的情况
 * 3. 在 main 中再用 Math.max 和三元运算符（? :）各写一遍，比较三种写法的可读性
 *
 * 【示例结果】
 * max(3, 9, 5) = 9
 * max(-1, -7, -3) = -1
 * max(4, 4, 4) = 4
 *
 * 【知识点】if 嵌套、Math.max、三元运算符、方法调用
 */
public class Ex03MaxOfThree {

    public static void main(String[] args) {
        check(3, 9, 5, 9);
        check(-1, -7, -3, -1);
        check(4, 4, 4, 4);

        // TODO 用 Math.max 再比较一遍（提示：Math.max 一次比较两个数，可以嵌套调用）
        // 打印结果并自检是否等于 9

        // TODO 用三元运算符 ? : 再比较一遍
        // 打印结果并自检是否等于 9
    }

    /** 打印三个数的最大值，并与期望值比较 */
    private static void check(int a, int b, int c, int expected) {
        int actual = max(a, b, c);
        System.out.println("max(" + a + ", " + b + ", " + c + ") = " + actual);
        System.out.println(actual == expected
                ? "通过"
                : "未通过（期望 " + expected + "，实际 " + actual + "）");
    }

    /** 返回三个整数中的最大值 */
    public static int max(int a, int b, int c) {
        // TODO 用 if 嵌套实现
        return 0;
    }
}
