package solutions.ch08_oop_advanced;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * 练习 3：接口与 default 方法（参考答案）
 *
 * 思路：
 * 1. 接口里的抽象方法默认就是 public abstract，写不写这两个修饰符都一样；
 *    实现类重写时必须用 public，否则会把访问权限降低，编译报错。
 * 2. description() 用 default 修饰并给出方法体，实现类一行都不用写就拥有这个能力；
 *    如果某个乐器想要不同的描述，可以重写它。
 * 3. main 用 Playable[] 统一处理，调用时只认接口，不关心具体是 Piano 还是 Guitar，
 *    以后新增 Violin 只要实现 Playable，这个循环一行都不用改。
 *
 * 易错点：
 * - 实现类重写接口方法时漏写 public，编译报错（接口方法默认 public）。
 * - 接口里写 default 方法时忘了 default 关键字，直接写方法体，编译报错。
 * - 接口不能 new，要用实现类创建对象再赋给接口引用。
 * - 一个类实现多个接口时，若两个接口有同签名的 default 方法，必须重写消除冲突。
 * - 接口没有构造方法，字段默认是 public static final 常量。
 *
 * 说明：只有与文件名同名的 Ex03Interface 是 public 类。
 */
public class Ex03Interface {

    public static void main(String[] args) {
        Playable[] playables = {new Piano(), new Guitar()};

        System.out.println("---- 乐器一览 ----");
        for (Playable playable : playables) {
            // description() 是接口里的 default 方法，实现类没有写也能调用
            System.out.println(playable.getClass().getSimpleName() + "：" + playable.description());
            playable.play();
            playable.stop();
        }

        System.out.println("---- 自检 ----");
        check("Piano.description()", "可演奏乐器", playables[0].description());
        check("Guitar.description()", "可演奏乐器", playables[1].description());
        check("Piano.play() 输出", "钢琴：叮咚叮咚", capture(playables[0]::play));
        check("Piano.stop() 输出", "钢琴：停止演奏", capture(playables[0]::stop));
        check("Guitar.play() 输出", "吉他：扫弦", capture(playables[1]::play));
        check("Guitar.stop() 输出", "吉他：停止扫弦", capture(playables[1]::stop));
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

    /** 把 action 打印到控制台的内容抓成字符串，方便自检 void 方法。 */
    private static String capture(Runnable action) {
        PrintStream original = System.out;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try {
            System.setOut(new PrintStream(buffer, true, StandardCharsets.UTF_8));
            action.run();
        } finally {
            System.setOut(original);
        }
        return buffer.toString(StandardCharsets.UTF_8).trim();
    }
}

/**
 * 可演奏的乐器。
 */
interface Playable {

    void play();

    void stop();

    /** default 方法：接口给出默认实现，实现类不写也能直接调用。 */
    default String description() {
        return "可演奏乐器";
    }
}

/**
 * 钢琴。
 */
class Piano implements Playable {

    @Override
    public void play() {
        System.out.println("钢琴：叮咚叮咚");
    }

    @Override
    public void stop() {
        System.out.println("钢琴：停止演奏");
    }
}

/**
 * 吉他。
 */
class Guitar implements Playable {

    @Override
    public void play() {
        System.out.println("吉他：扫弦");
    }

    @Override
    public void stop() {
        System.out.println("吉他：停止扫弦");
    }
}
