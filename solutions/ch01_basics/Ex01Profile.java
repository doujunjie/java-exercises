package solutions.ch01_basics;

/**
 * 参考答案：练习 1 个人信息卡
 *
 * 思路：先声明 4 个不同类型的变量并赋初值，再用 println 逐行输出。
 * 每一行都用字符串拼接（+）把说明文字和变量值连起来。
 *
 * 易错点：
 * 1. println 没有 println(String, int) 这样的多参数重载，必须先用 + 拼成一个字符串。
 * 2. 局部变量必须先赋值再使用，否则编译报错「可能尚未初始化变量」。
 * 3. 单位、空格要自己加，例如 "身高：" + height + " 米"，否则数字和文字会粘在一起。
 */
public class Ex01Profile {

    public static void main(String[] args) {
        String name = "张三";
        int age = 20;
        double height = 1.75;
        boolean inSchool = true;

        // 逐行输出个人信息
        System.out.println("姓名：" + name);
        System.out.println("年龄：" + age);
        System.out.println("身高：" + height + " 米");
        System.out.println("是否在校：" + inSchool);

        // 扩展：拼接的顺序会影响结果
        // "" + 1 + 2 先拼字符串，得到 "12"；而 1 + 2 + "" 先算加法，得到 "3"
        System.out.println("\"\" + 1 + 2 = " + ("" + 1 + 2));
        System.out.println("1 + 2 + \"\" = " + (1 + 2 + ""));
    }
}
