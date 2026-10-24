package ch09_string;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * 练习 2：字符统计
 *
 * 【要求】
 * 1. 实现 Map&lt;Character, Integer&gt; countChars(String s)，
 *    统计每个字符出现的次数，要求用 HashMap
 * 2. 实现 void printSorted(Map&lt;Character, Integer&gt; map)，
 *    按次数从高到低打印，次数相同时按字符升序，每行格式「字符=次数」
 * 3. main 自检："hello" 的统计结果是 1 个 h、1 个 e、2 个 l、1 个 o；
 *    printSorted 的输出为 l=2、e=1、h=1、o=1（各占一行）
 *
 * 【示例输出】
 * 文本：hello
 * 统计结果：{a=..., ...}
 * 按次数排序后：
 * l=2
 * e=1
 * h=1
 * o=1
 * 通过：countChars("hello")
 * 通过：printSorted 的输出
 *
 * 【知识点】HashMap、getOrDefault、entrySet、List 排序、Comparator / 比较器链、
 *           Character.compare、Integer.compare
 *
 * 说明：为了 main 能直接调用，两个方法都声明为 static。
 */
public class Ex02CharCount {

    public static void main(String[] args) {
        String text = "hello";
        Map<Character, Integer> result = countChars(text);

        System.out.println("文本：" + text);
        System.out.println("统计结果：" + result);
        System.out.println("按次数排序后：");
        printSorted(result);

        Map<Character, Integer> second = countChars("aabbc");
        System.out.println("---- 另一种输入 ----");
        System.out.println("文本：aabbc，统计结果：" + second);
        printSorted(second);

        System.out.println("---- 自检 ----");
        Map<Character, Integer> expected = new HashMap<>();
        expected.put('h', 1);
        expected.put('e', 1);
        expected.put('l', 2);
        expected.put('o', 1);
        check("countChars(\"hello\")", expected, result);

        Map<Character, Integer> expectedSecond = new HashMap<>();
        expectedSecond.put('a', 2);
        expectedSecond.put('b', 2);
        expectedSecond.put('c', 1);
        check("countChars(\"aabbc\")", expectedSecond, second);

        String line = System.lineSeparator();
        check("printSorted 的输出",
                "l=2" + line + "e=1" + line + "h=1" + line + "o=1",
                capture(() -> printSorted(result)));
        check("printSorted 并列时按字符升序",
                "a=2" + line + "b=2" + line + "c=1",
                capture(() -> printSorted(second)));
    }

    /**
     * 统计字符串中每个字符出现的次数。
     *
     * @param s 待统计的字符串
     * @return key 为字符、value 为出现次数的 Map
     */
    public static Map<Character, Integer> countChars(String s) {
        // TODO 实现：遍历 s 的每个字符，用 HashMap 累加次数
        // 提示：map.getOrDefault(c, 0) + 1，不要直接 map.get(c) + 1（第一次会空指针）
        return new HashMap<>();
    }

    /**
     * 按出现次数从高到低打印，次数相同的按字符升序。
     *
     * @param map 统计结果，可能为 null 或空
     */
    public static void printSorted(Map<Character, Integer> map) {
        // TODO 实现：把 entrySet 放进 List，写比较器排序，再逐行打印 "字符=次数"
        // 提示：Comparator.comparing 或 List.sort + Integer.compare / Character.compare
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
