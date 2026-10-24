package ch10_collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * 练习 2：Set 去重与顺序
 *
 * 【要求】
 * 1. 给定 String[] words（含重复元素），分别用 HashSet、LinkedHashSet、TreeSet 去重并打印
 * 2. 在代码注释里对比三者：是否有序、是否可重复、能否存 null
 * 3. 自检三种集合的大小，以及 LinkedHashSet 与 TreeSet 的元素顺序
 *
 * 【示例输出】
 * HashSet       去重结果：[banana, Apple, orange, apple]
 * LinkedHashSet 去重结果：[apple, banana, orange, Apple]
 * TreeSet       去重结果：[Apple, apple, banana, orange]
 *
 * 【知识点】
 * Set 不重复、HashSet 哈希表、LinkedHashSet 链表维护插入顺序、TreeSet 红黑树排序
 */
public class Ex02SetDedup {

    public static void main(String[] args) {
        String[] words = {"apple", "banana", "apple", "orange", "banana", "Apple"};

        System.out.println("原始数组：" + Arrays.toString(words) + "，长度 = " + words.length);
        System.out.println();
        demo(words);

        System.out.println();
        System.out.println("===== 自检 =====");
        Set<String> hashSet = dedupWithHashSet(words);
        Set<String> linkedHashSet = dedupWithLinkedHashSet(words);
        Set<String> treeSet = dedupWithTreeSet(words);

        System.out.println("HashSet 大小 = " + hashSet.size());
        System.out.println("LinkedHashSet 大小 = " + linkedHashSet.size());
        System.out.println("TreeSet 大小 = " + treeSet.size());
        System.out.println("LinkedHashSet 顺序 = " + new ArrayList<>(linkedHashSet));
        System.out.println("TreeSet 顺序 = " + new ArrayList<>(treeSet));

        boolean pass = hashSet.size() == 4
                && linkedHashSet.size() == 4
                && treeSet.size() == 4
                && new ArrayList<>(linkedHashSet).equals(List.of("apple", "banana", "orange", "Apple"))
                && new ArrayList<>(treeSet).equals(List.of("Apple", "apple", "banana", "orange"));
        if (pass) {
            System.out.println("通过");
        } else {
            System.out.println("未通过（期望三者大小都是 4、LinkedHashSet 保持插入顺序、TreeSet 按字母升序，"
                    + "实际大小 " + hashSet.size() + " / " + linkedHashSet.size() + " / " + treeSet.size()
                    + "，LinkedHashSet 顺序 " + new ArrayList<>(linkedHashSet)
                    + "，TreeSet 顺序 " + new ArrayList<>(treeSet) + "）");
        }
    }

    /**
     * 分别用三种 Set 去重并打印结果，同时在注释里说明三者的区别。
     *
     * @param words 含重复元素的单词数组
     */
    public static void demo(String[] words) {
        // TODO 实现：三个 Set 都用 Arrays.asList(words) 构造，然后打印
    }

    /**
     * 用 HashSet 去重。
     * 提示：HashSet 不保证顺序，底层是哈希表，允许存一个 null。
     *
     * @param words 含重复元素的单词数组
     * @return 去重后的集合
     */
    public static Set<String> dedupWithHashSet(String[] words) {
        // TODO 实现...
        return new HashSet<>();
    }

    /**
     * 用 LinkedHashSet 去重。
     * 提示：LinkedHashSet 用链表记录插入顺序，遍历顺序与第一次出现的顺序一致。
     *
     * @param words 含重复元素的单词数组
     * @return 去重后的集合
     */
    public static Set<String> dedupWithLinkedHashSet(String[] words) {
        // TODO 实现...
        return new LinkedHashSet<>();
    }

    /**
     * 用 TreeSet 去重。
     * 提示：TreeSet 按元素的自然顺序排序，元素必须实现 Comparable（String 已实现）。
     *
     * @param words 含重复元素的单词数组
     * @return 去重并排好序的集合
     */
    public static Set<String> dedupWithTreeSet(String[] words) {
        // TODO 实现...
        return new TreeSet<>();
    }
}
