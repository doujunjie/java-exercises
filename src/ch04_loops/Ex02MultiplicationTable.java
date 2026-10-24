package ch04_loops;

/**
 * 练习 2：九九乘法表
 *
 * 【要求】
 * 1. 实现 printTable()，使用嵌套 for 循环打印九九乘法表
 * 2. 内层循环使用 System.out.print 让同一行的算式连续输出
 * 3. 每行结束后使用 System.out.println 换行
 * 4. 输出呈三角形：第 i 行有 i 个算式，形如 1*2=2 2*2=4
 *
 * 【示例输出】
 * 1*1=1
 * 1*2=2 2*2=4
 * 1*3=3 2*3=6 3*3=9
 * ...
 * 1*9=9 2*9=18 3*9=27 ... 9*9=81
 *
 * 【知识点】嵌套循环、外层控制行与内层控制列、print 与 println 的区别
 */
public class Ex02MultiplicationTable {

    public static void main(String[] args) {
        System.out.println("九九乘法表：");
        printTable();
    }

    /** 打印九九乘法表，第 i 行输出 i 个算式 */
    public static void printTable() {
        // TODO 用两层 for 循环打印，内层用 print，每行结束用 println
    }
}
