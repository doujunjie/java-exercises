package ch10_collection;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 练习 3：HashMap 词频统计
 *
 * 【要求】
 * 1. 实现 Map&lt;String, Integer&gt; wordCount(String text)：按空格拆分文本，统计每个单词出现的次数
 * 2. 实现 void printTopN(Map&lt;String, Integer&gt; map, int n)：按次数降序打印前 n 个，
 *    次数相同时按单词字母序升序
 * 3. main 用一段英文文本自检
 *
 * 【示例输出】
 * the -> 3
 * fox -> 2
 * brown -> 1
 * 第 1 名：the -> 3
 * 第 2 名：fox -> 2
 * 第 3 名：brown -> 1
 *
 * 【知识点】
 * HashMap 增删查、getOrDefault、entrySet、List 排序 Comparator、Map 不保证顺序
 */
public class Ex03MapWordCount {

    public static void main(String[] args) {
        Ex03MapWordCount exercise = new Ex03MapWordCount();
        String text = "the quick brown fox jumps over the lazy dog the fox";

        System.out.println("原文：" + text);
        System.out.println();

        Map<String, Integer> map = exercise.wordCount(text);
        System.out.println("词频统计（按单词字母序打印）：");

        List<String> keys = new ArrayList<>(map.keySet());
        keys.sort(Comparator.naturalOrder());
        if (keys.isEmpty()) {
            System.out.println("    （空 map，请先实现 wordCount）");
        } else {
            for (String key : keys) {
                System.out.println("    " + key + " -> " + map.get(key));
            }
        }

        System.out.println();
        System.out.println("出现次数最多的前 3 个单词：");
        exercise.printTopN(map, 3);
        System.out.println();

        System.out.println("===== 自检 =====");
        boolean pass = map.size() == 8
                && Integer.valueOf(3).equals(map.get("the"))
                && Integer.valueOf(2).equals(map.get("fox"))
                && Integer.valueOf(1).equals(map.get("dog"))
                && !map.containsKey("cat");
        if (pass) {
            System.out.println("通过");
        } else {
            System.out.println("未通过（期望 8 个单词、the=3、fox=2、dog=1、不含 cat，"
                    + "实际 size=" + map.size()
                    + "、the=" + map.get("the")
                    + "、fox=" + map.get("fox")
                    + "、dog=" + map.get("dog") + "）");
        }
    }

    /**
     * 统计 text 中每个单词出现的次数。
     * 提示：先 trim 再按 \\s+ 拆分；用 map.getOrDefault(word, 0) + 1 累加。
     * 边界：text 为 null 或空白串时返回空 Map，不要抛异常。
     *
     * @param text 英文文本，单词之间用空格分隔
     * @return 单词到出现次数的映射
     */
    public Map<String, Integer> wordCount(String text) {
        // TODO 实现...
        return new HashMap<>();
    }

    /**
     * 按次数降序打印前 n 个单词，次数相同时按单词字母序升序。
     * 提示：先把 entrySet 转成 List，再写 Comparator 比较 value 和 key；n 超过 map 大小时按实际大小处理。
     *
     * @param map 词频统计结果
     * @param n   要打印的个数
     */
    public void printTopN(Map<String, Integer> map, int n) {
        // TODO 实现...
    }
}
