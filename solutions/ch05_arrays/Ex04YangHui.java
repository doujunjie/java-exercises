package solutions.ch05_arrays;

/**
 * 练习 4 参考答案：杨辉三角
 *
 * 思路：
 * 1. 只创建行数组 a = new int[rows][]，每一行再单独 new int[i + 1]，
 *    这样得到的是每行长度不同的不规则二维数组。
 * 2. 每行首尾元素固定为 1。
 * 3. 中间元素满足 a[i][j] = a[i - 1][j - 1] + a[i - 1][j]。
 *
 * 易错点：
 * - 写成 new int[rows][rows] 会浪费空间，但逻辑上也能做；本题要求不规则数组。
 * - 内层 j 从 1 开始、到 i - 1 结束，首尾不要重复赋值。
 * - 从第 2 行（下标 1）开始才有中间元素，i = 0 时直接跳过内层循环。
 * - rows <= 0 时返回空数组，避免创建长度为负的数组。
 */
public class Ex04YangHui {

    public static void main(String[] args) {
        int[][] triangle = yangHui(10);
        print(triangle);
    }

    /** 生成杨辉三角的前 rows 行 */
    public static int[][] yangHui(int rows) {
        if (rows <= 0) {
            return new int[0][0];
        }
        int[][] a = new int[rows][];
        for (int i = 0; i < rows; i++) {
            a[i] = new int[i + 1];
            a[i][0] = 1;
            a[i][i] = 1;
            for (int j = 1; j < i; j++) {
                a[i][j] = a[i - 1][j - 1] + a[i - 1][j];
            }
        }
        return a;
    }

    /** 按行打印二维数组 */
    public static void print(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                System.out.print(a[i][j]);
                if (j < a[i].length - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
