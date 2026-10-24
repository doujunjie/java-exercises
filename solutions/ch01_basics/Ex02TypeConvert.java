package solutions.ch01_basics;

/**
 * 参考答案：练习 2 基本类型与类型转换
 *
 * 思路：
 * 1. int -> long -> double 是范围由小到大，编译器自动完成，不需要写任何转换符号。
 * 2. double -> int 是范围由大到小，必须写 (int) 强制转换，小数部分直接截断。
 * 3. int 和 char 都是整数类型，char 的范围是 0 ~ 65535，两者可以互相强制转换。
 * 4. Integer.parseInt 把字符串解析成 int，字符串必须全是数字（可带正负号）。
 *
 * 易错点：
 * 1. (int) 3.99 的结果是 3，不是 4；强制转换不四舍五入，需要四舍五入用 Math.round。
 * 2. 负数截断方向是向零：(int) -3.99 得到 -3。
 * 3. Integer.parseInt("12.3") 会抛 NumberFormatException，小数要用 Double.parseDouble。
 */
public class Ex02TypeConvert {

    public static void main(String[] args) {
        // 自动类型转换：小范围 -> 大范围，直接赋值即可
        int i = 65;
        long l = i;
        double d = l;
        System.out.println("自动转换：" + i + " -> " + l + " -> " + d);

        // 强制转换：小数部分被直接截断
        int truncated = toInt(3.99);
        System.out.println("(int) 3.99 = " + truncated);
        System.out.println(truncated == 3 ? "通过" : "未通过（期望 3，实际 " + truncated + "）");

        // 负数同样向零截断，注意不是 -4
        int negative = toInt(-3.99);
        System.out.println("(int) -3.99 = " + negative + "（向零截断，不是 -4）");
        System.out.println(negative == -3 ? "通过" : "未通过（期望 -3，实际 " + negative + "）");

        // int -> char：65 对应大写字母 A
        char upper = codeToChar(65);
        System.out.println("65 -> " + upper);
        System.out.println(upper == 'A' ? "通过" : "未通过（期望 A，实际 " + upper + "）");

        // 97 对应小写字母 a
        char lower = codeToChar(97);
        System.out.println("97 -> " + lower);
        System.out.println(lower == 'a' ? "通过" : "未通过（期望 a，实际 " + lower + "）");

        // char -> int：char 本质上是 16 位无符号整数，可以直接赋给 int
        char letter = 'A';
        int code = letter;
        System.out.println(letter + " -> " + code);
        System.out.println(code == 65 ? "通过" : "未通过（期望 65，实际 " + code + "）");

        // 字符串转数字：parseInt 只接受合法的十进制整数字符串
        int number = Integer.parseInt("123");
        System.out.println("\"123\" 转成数字后 + 1 = " + (number + 1));
        System.out.println(number + 1 == 124 ? "通过" : "未通过（期望 124，实际 " + (number + 1) + "）");

        // 易错：parseInt 的入参不能带小数点，下面这行会抛 NumberFormatException
        // int wrong = Integer.parseInt("12.3");
        double decimal = Double.parseDouble("12.3");
        System.out.println("Double.parseDouble(\"12.3\") = " + decimal);
    }

    /** 把 double 强制转换成 int：小数部分直接截断，不做四舍五入 */
    public static int toInt(double d) {
        return (int) d;
    }

    /** 把整数编码转换成对应的 char 字符：65 -> 'A' */
    public static char codeToChar(int code) {
        return (char) code;
    }
}
