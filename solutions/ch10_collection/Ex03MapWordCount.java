package solutions.ch10_collection;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 练习 3 参考答案：HashMap 词频统计
 *
 * 思路：
 * 1. 词频统计的惯用写法是 map.put(word, map.getOrDefault(word, 0) + 1)，
 *    也可以用 map.merge(word, 1, Integer::sum)。
 * 2. 打印 TopN 不能直接对 Map 排序（Map 本身无序），要先把 entrySet 转成 List 再 sort。
 * 3. 排序规则是「次数降序，次数相同按单词字母序升序」，所以 Comparator 里要先比 value 再比 key。
 *
 * 易错点：
 * 1. 拼接比较器时写成 b.getValue() - a.getValue() 理论上有溢出风险，用 Integer.compare 更稳。
 * 2. 直接对 map.keySet() 排序不会影响 Map 的遍历顺序，keySet 只是视图。
 * 3. 文本为空时要返回空 Map，不能对空串 split 后拿到 [""] 这种假单词。
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
     */
    public Map<String, Integer> wordCount(String text) {
        Map<String, Integer> map = new HashMap<>();
        if (text == null || text.isBlank()) {
            return map;
        }
        // trim 去掉首尾空白，\\s+ 把连续空白当作一个分隔符
        String[] words = text.trim().split("\\s+");
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
            // 等价写法：map.merge(word, 1, Integer::sum);
        }
        return map;
    }

    /**
     * 按次数降序打印前 n 个单词，次数相同时按单词字母序升序。
     */
    public void printTopN(Map<String, Integer> map, int n) {
        if (map == null || map.isEmpty() || n <= 0) {
            System.out.println("    （没有可以统计的数据）");
            return;
        }

        // Map 本身无序，先转成 List<Map.Entry> 才能排序
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(map.entrySet());
        entries.sort((first, second) -> {
            int byCount = Integer.compare(second.getValue(), first.getValue());
            if (byCount != 0) {
                return byCount;
            }
            return first.getKey().compareTo(second.getKey());
        });

        // n 可能大于实际单词数，取较小值，避免下标越界
        int limit = Math.min(n, entries.size());
        for (int i = 0; i < limit; i++) {
            Map.Entry<String, Integer> entry = entries.get(i);
            System.out.println("    第 " + (i + 1) + " 名：" + entry.getKey() + " -> " + entry.getValue());
        }
    }
}
