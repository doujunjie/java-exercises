package ch05_arrays;

/**
 * 练习 4：杨辉三角
 *
 * 【要求】
 * 1. 实现 yangHui(int rows)，返回杨辉三角的前 rows 行
 * 2. 实现 print(int[][] a)，按行打印二维数组
 * 3. 第 i 行第 j 列 = 上一行第 j-1 列 + 上一行第 j 列，边界位置为 1
 * 4. 在 main 中打印前 10 行
 *
 * 【示例输出】前 5 行
 * 1
 * 1 1
 * 1 2 1
 * 1 3 3 1
 * 1 4 6 4 1
 *
 * 【知识点】二维数组、不规则数组（每行长度不同）、下标边界、递推关系
 */
public class Ex04YangHui {

    public static void main(String[] args) {
        int[][] triangle = yangHui(10);
        print(triangle);
    }

    /**
     * 生成杨辉三角的前 rows 行。
     *
     * 返回值是“不规则二维数组”：第 i 行有 i + 1 个元素。
     * 每行第一个和最后一个元素固定为 1，中间元素由上一行两个相邻元素相加得到。
     */
    public static int[][] yangHui(int rows) {
        // TODO rows <= 0 返回空二维数组；否则逐行创建并填充
        return new int[0][0];
    }

    /** 按行打印二维数组，同一行的数字之间用一个空格分隔 */
    public static void print(int[][] a) {
        // TODO 双重循环打印，每行结束换行
    }
}
