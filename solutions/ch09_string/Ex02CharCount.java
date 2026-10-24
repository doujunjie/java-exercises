package solutions.ch09_string;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 练习 2：字符统计（参考答案）
 *
 * 思路：
 * 1. HashMap 的 key 是 Character，value 是次数；遍历字符串用 charAt(i) 拿到每个 char，
 *    自动装箱成 Character 作为 key。
 * 2. 累加用 map.getOrDefault(c, 0) + 1：第一次遇到该字符时取到 0，避免空指针。
 * 3. 排序时 HashMap 本身无序，必须先把 entrySet 复制到 List 再排序。
 * 4. 比较器先比次数（降序：b 减 a 的写法用 Integer.compare(b, a)），
 *    次数相同再比字符（升序：Character.compare(a, b)）。
 * 5. 用 List.sort 而不是 Collections.sort，写法更直接；比较器里不要用减法，避免溢出。
 *
 * 易错点：
 * - 直接 map.get(c) + 1，字符第一次出现时 get 返回 null，自动拆箱抛 NullPointerException。
 * - 对 HashMap 直接排序是无效的，它不保证顺序，必须先转成 List。
 * - 降序写成 Integer.compare(a, b) 结果会变成升序。
 * - 用 (a.getValue() - b.getValue()) 做减法比较，数值大时可能溢出。
 * - 打印时用 println(entry)，格式会是 "l=2" 没问题，但若打印 key 和 value 用 + 拼接要注意中间不要多空格。
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
     * @return key 为字符、value 为出现次数的 Map；s 为 null 时返回空 Map
     */
    public static Map<Character, Integer> countChars(String s) {
        Map<Character, Integer> map = new HashMap<>();
        if (s == null) {
            return map;
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 第一次出现时 getOrDefault 返回 0，因此不会出现 null + 1 的空指针
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        return map;
    }

    /**
     * 按出现次数从高到低打印，次数相同的按字符升序。
     *
     * @param map 统计结果，可能为 null 或空
     */
    public static void printSorted(Map<Character, Integer> map) {
        if (map == null || map.isEmpty()) {
            return;
        }
        // HashMap 无序，先转成 List 才能排序
        List<Map.Entry<Character, Integer>> entries = new ArrayList<>(map.entrySet());
        entries.sort((left, right) -> {
            int byCount = Integer.compare(right.getValue(), left.getValue());
            if (byCount != 0) {
                // 次数不同：降序
                return byCount;
            }
            // 次数相同：字符升序
            return Character.compare(left.getKey(), right.getKey());
        });
        for (Map.Entry<Character, Integer> entry : entries) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
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
