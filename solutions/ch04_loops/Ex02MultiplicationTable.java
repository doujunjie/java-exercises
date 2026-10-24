package solutions.ch04_loops;

/**
 * 练习 2 参考答案：九九乘法表
 *
 * 思路：
 * 外层循环 i 表示行号，同时也表示这一行最大的被乘数；
 * 内层循环 j 从 1 到 i，负责打印本行的每一个算式。
 * print 不换行，所以同一行的算式连在一起；每行结束调用一次 println 换行。
 *
 * 易错点：
 * - 内层条件写成 j <= 9 会打印成矩形，而不是三角形。
 * - 公式顺序写成 i * j 时，输出会变成 2*1=2 2*2=4，与题目要求的 1*2=2 2*2=4 不一致。
 * - 最后一个算式后面多一个空格不影响结果，但不要漏掉行末换行。
 */
public class Ex02MultiplicationTable {

    public static void main(String[] args) {
        System.out.println("九九乘法表：");
        printTable();
    }

    /** 打印九九乘法表，第 i 行输出 i 个算式 */
    public static void printTable() {
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j + "*" + i + "=" + (j * i));
                if (j < i) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
