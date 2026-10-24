package solutions.ch03_if_switch;

/**
 * 参考答案：练习 3 三个整数中的最大值
 *
 * 思路（if 嵌套）：
 * 先比较 a 和 b，把较大的那个再和 c 比：
 * 1. 如果 a >= b，则最大值只可能是 a 或 c；
 * 2. 否则最大值只可能是 b 或 c。
 * 注意用 >= 而不是 >，这样三个数相等时也能返回正确结果。
 *
 * 三种写法对比：
 * 1. if 嵌套：步骤最清楚，适合教学和复杂条件，但代码最长。
 * 2. Math.max：Math.max(a, Math.max(b, c)) 一行搞定，语义最直观，推荐。
 * 3. 三元运算符：嵌套 ? : 可以一行写完，但嵌套层数多了可读性差，不建议超过两层。
 *
 * 易错点：
 * 1. 把 Math.max 写成 Math.Max（Java 区分大小写）。
 * 2. 三元运算符嵌套时要注意括号和结合方向，必要时加括号。
 * 3. 比较时用 >= 可以正确处理相等的情况。
 */
public class Ex03MaxOfThree {

    public static void main(String[] args) {
        check(3, 9, 5, 9);
        check(-1, -7, -3, -1);
        check(4, 4, 4, 4);

        // 写法二：Math.max 嵌套调用
        int mathMax = Math.max(3, Math.max(9, 5));
        System.out.println("Math.max(3, Math.max(9, 5)) = " + mathMax);
        System.out.println(mathMax == 9 ? "通过" : "未通过（期望 9，实际 " + mathMax + "）");

        // 写法三：三元运算符嵌套
        int ternaryMax = 3 >= 9 ? (3 >= 5 ? 3 : 5) : (9 >= 5 ? 9 : 5);
        System.out.println("三元运算符结果 = " + ternaryMax);
        System.out.println(ternaryMax == 9 ? "通过" : "未通过（期望 9，实际 " + ternaryMax + "）");
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
        int max;
        if (a >= b) {
            // a 至少不小于 b，最大值只在 a 和 c 之间
            if (a >= c) {
                max = a;
            } else {
                max = c;
            }
        } else {
            // b 比 a 大，最大值只在 b 和 c 之间
            if (b >= c) {
                max = b;
            } else {
                max = c;
            }
        }
        return max;
    }
}
