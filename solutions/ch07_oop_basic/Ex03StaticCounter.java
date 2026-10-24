package solutions.ch07_oop_basic;

/**
 * 练习 3：static 成员（参考答案）
 *
 * 思路：
 * 1. count 加 static 后就从「每个对象一份」变成「整个类一份」，new 出来的对象再多也只有这一个变量。
 * 2. 构造方法里 count++，于是每创建一个对象，共享的计数就加 1。
 * 3. getCount 和 add 都加 static，属于类本身，用 IdGenerator.getCount() 调用，不需要先 new。
 *
 * 易错点：
 * - 漏写 static：count 变成实例字段，每个对象各有一份，getCount 永远拿到 0 或 1。
 * - 静态方法里直接使用实例字段或 this（编译报错）：静态方法先于对象存在，没有 this。
 * - 用对象去调用静态方法（id.getCount()）虽然能编译，但会让人误以为是实例方法，应统一用类名调用。
 * - 多个类共享计数时不要重复声明 count。
 *
 * 说明：只有与文件名同名的 Ex03StaticCounter 是 public 类。
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
        // 不要写成 this.count++：虽然能编译，但 static 字段不属于某个对象，
        // 用类名语义更清楚：IdGenerator.count++
        count++;
    }

    /** 静态方法：直接用类名调用，例如 IdGenerator.getCount()。 */
    public static int getCount() {
        return count;
    }

    /** 静态工具方法：两个整数相加。 */
    public static int add(int a, int b) {
        return a + b;
    }
}
