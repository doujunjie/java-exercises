package ch01_basics;

/**
 * 练习 2：基本类型与类型转换
 *
 * 【要求】
 * 1. 演示自动类型转换：int -> long -> double，打印转换前后的值
 * 2. 实现 toInt(double d)，把 double 强制转换成 int，观察小数部分被直接截断
 * 3. 实现 codeToChar(int code)，把整数编码转换成对应的字符，例如 65 -> 'A'
 * 4. 在 main 中演示 char 与 int 的互转（'A' 与 65）
 * 5. 在 main 中演示 Integer.parseInt 把字符串 "123" 转成数字 123
 *
 * 【示例结果】
 * 自动转换：65 -> 65 -> 65.0
 * (int) 3.99 = 3
 * 65 -> A
 * A -> 65
 * "123" 转成数字后 + 1 = 124
 *
 * 【知识点】自动类型转换、强制类型转换、精度丢失、char 与 int、Integer.parseInt
 */
public class Ex02TypeConvert {

    public static void main(String[] args) {
        // 自动类型转换：小范围类型可以直接赋给大范围类型
        int i = 65;
        long l = i;
        double d = l;
        System.out.println("自动转换：" + i + " -> " + l + " -> " + d);

        // TODO 调用 toInt(3.99)，打印结果，并自检是否等于 3
        // 提示：输出格式可以是 (int) 3.99 = 3
            System.out.println("(int) 3.99 = " + toInt(3.99));
        // TODO 调用 codeToChar(65)，打印结果，并自检是否等于 'A'
        System.out.println("65 -> " + codeToChar(65));
        // char 与 int 互转：char 本质上是 16 位无符号整数，可以直接赋给 int
        char letter = 'A';
        int code = letter;
        System.out.println(letter + " -> " + code);
        System.out.println(code == 65 ? "通过" : "未通过（期望 65，实际 " + code + "）");

        // 字符串转数字：parseInt 只接受合法的十进制整数字符串
        int number = Integer.parseInt("123");
        System.out.println("\"123\" 转成数字后 + 1 = " + (number + 1));
        System.out.println(number + 1 == 124 ? "通过" : "未通过（期望 124，实际 " + (number + 1) + "）");
    }

    /** 把 double 强制转换成 int：小数部分直接截断，不做四舍五入 */
    public static int toInt(double d) {
        // TODO 实现强制转换
        int truncated = (int) d;
        return truncated;
    }

    /** 把整数编码转换成对应的 char 字符：65 -> 'A' */
    public static char codeToChar(int code) {
        // TODO 实现 int 到 char 的转换
        char character = (char) code;
        return character;
    }
}
