package ch07_oop_basic;

/**
 * 练习 3：static 成员
 *
 * 【要求】
 * 1. 定义类 IdGenerator，其中 static 字段 count 用来统计「一共创建了多少个对象」
 * 2. 在构造方法里让 count 自增
 * 3. 提供 static 方法 getCount() 返回 count
 * 4. 再写一个 static 工具方法 static int add(int a, int b)
 * 5. main 里创建若干对象，打印计数并说明「静态成员属于类、被所有对象共享」
 *
 * 【示例结果】
 * 创建对象前，count = 0
 * 创建 3 个对象后，count = 3
 * add(2, 3) = 5
 * 自检全部「通过」
 *
 * 【知识点】static 字段、static 方法、类名调用、静态成员与实例成员的区别
 *
 * 说明：IdGenerator 与 Ex03StaticCounter 在同一个文件里，只有后者是 public。
 */
public class Ex03StaticCounter {

    public static void main(String[] args) {
        System.out.println("创建对象前，count = " + IdGenerator.getCount());

        new IdGenerator();
        new IdGenerator();
        new IdGenerator();

        System.out.println("创建 3 个对象后，count = " + IdGenerator.getCount());
        System.out.println("add(2, 3) = " + IdGenerator.add(2, 3));
        System.out.println("add(-4, 10) = " + IdGenerator.add(-4, 10));

        System.out.println("---- 自检 ----");
        check("创建 3 个对象后的计数", 3, IdGenerator.getCount());
        check("add(2, 3)", 5, IdGenerator.add(2, 3));
        check("add(-4, 10)", 6, IdGenerator.add(-4, 10));

        System.out.println();
        System.out.println("说明：static 成员属于类，被所有对象共享。");
        System.out.println("count 只有一份，存在方法区（类）里，不属于某一个对象；");
        System.out.println("所以无论用类名 IdGenerator.getCount() 还是用任何一个对象去访问，");
        System.out.println("看到的都是同一个数字。");
    }

    /** 比较期望值与实际值，打印「通过」或「未通过（期望 X，实际 Y）」。 */
    private static void check(String label, Object expected, Object actual) {
        boolean ok = expected == null ? actual == null : expected.equals(actual);
        if (ok) {
            System.out.println("通过：" + label);
        } else {
            System.out.println("未通过：" + label + "（期望 " + expected + "，实际 " + actual + "）");
        }
    }
}

/**
 * 对象编号生成器：每 new 一个对象，静态计数 count 加 1。
 */
class IdGenerator {

    /** 静态字段：属于类，所有对象共享同一份。 */
    private static int count = 0;

    public IdGenerator() {
        // TODO 实现：让静态计数 count 加 1
    }

    /** 静态方法：直接用类名调用，例如 IdGenerator.getCount()。 */
    public static int getCount() {
        // TODO 实现：返回 count
        return 0;
    }

    /** 静态工具方法：两个整数相加。 */
    public static int add(int a, int b) {
        // TODO 实现：返回 a + b
        return 0;
    }
}
