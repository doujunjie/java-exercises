package ch08_oop_advanced;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * 练习 3：接口与 default 方法
 *
 * 【要求】
 * 1. 定义接口 Playable：void play()、void stop()，以及 default 方法
 *    String description()，默认返回 "可演奏乐器"
 * 2. 实现类 Piano、Guitar 实现 Playable：
 *    Piano.play() 打印 "钢琴：叮咚叮咚"，Piano.stop() 打印 "钢琴：停止演奏"；
 *    Guitar.play() 打印 "吉他：扫弦"，Guitar.stop() 打印 "吉他：停止扫弦"
 * 3. main 用 Playable[] 遍历调用 play()/stop()，并演示直接调用 description()
 *
 * 【示例输出】
 * ---- 乐器一览 ----
 * Piano：可演奏乐器
 * 钢琴：叮咚叮咚
 * 钢琴：停止演奏
 * Guitar：可演奏乐器
 * 吉他：扫弦
 * 吉他：停止扫弦
 * 通过：Piano.play() 输出
 *
 * 【知识点】interface、implements、接口方法默认 public abstract、default 方法、
 *           面向接口编程（Playable[] 统一处理不同实现）
 *
 * 说明：Playable、Piano、Guitar 与测试类写在同一个文件里，只有 Ex03Interface 是 public。
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
        // TODO 实现：打印 "钢琴：叮咚叮咚"
    }

    @Override
    public void stop() {
        // TODO 实现：打印 "钢琴：停止演奏"
    }
}

/**
 * 吉他。
 */
class Guitar implements Playable {

    @Override
    public void play() {
        // TODO 实现：打印 "吉他：扫弦"
    }

    @Override
    public void stop() {
        // TODO 实现：打印 "吉他：停止扫弦"
    }
}
