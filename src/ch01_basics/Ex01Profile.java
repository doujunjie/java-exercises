package ch01_basics;

/**
 * 练习 1：个人信息卡
 *
 * 【要求】
 * 1. 声明 4 个变量：String 姓名、int 年龄、double 身高（单位米）、boolean 是否在校
 * 2. 给 4 个变量赋初值，用 System.out.println 逐行输出
 * 3. 至少有一行使用字符串拼接（+），把变量和说明文字连成一句话
 * 4. 输出 4 行，顺序和格式参考下面的示例输出
 *
 * 【示例输出】
 * 姓名：张三
 * 年龄：20
 * 身高：1.75 米
 * 是否在校：true
 *
 * 【知识点】变量的声明与赋值、基本数据类型、String 类型、字符串拼接、println 输出
 */
public class Ex01Profile {

    public static void main(String[] args) {
        // TODO 声明并赋值 4 个变量，然后逐行打印个人信息
        // 提示：变量名建议用 name、age、height、inSchool
        // 提示：字符串和其它类型用 + 连接时，会自动转换成文字再拼接
        String name = "张三";
        int age = 20;
        double height = 1.75;
        boolean inSchool = true;
        System.out.println("姓名：" + name);
        System.out.println("年龄：" + age);
        System.out.println("身高：" + height + " 米");
        System.out.println("是否在校：" + inSchool);
    }
}
