package solutions.ch10_collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * 练习 2 参考答案：Set 去重与顺序
 *
 * 思路：
 * 1. 三个 Set 的构造器都可以接收 Collection，Arrays.asList(words) 正好是 List。
 * 2. 去重是 Set 的天然能力：重复 add 不会报错，只是 add 返回 false。
 * 3. 三者的差别在「顺序」和「能不能存 null」上。
 *
 * 对比：
 * - HashSet：无序（哈希桶顺序），不重复，可以存一个 null。
 * - LinkedHashSet：按插入顺序遍历，不重复，可以存一个 null。
 * - TreeSet：按自然顺序（或 Comparator）排序，不重复，不能存 null，存 null 会抛 NullPointerException。
 *
 * 易错点：
 * 1. 自定义对象放进 HashSet 去重，必须同时重写 hashCode() 和 equals()，否则地址不同就算两个元素。
 * 2. 自定义对象放进 TreeSet，要么实现 Comparable，要么在构造 TreeSet 时传入 Comparator。
 * 3. 这里的 "apple" 和 "Apple" 是两个不同元素，Set 去重区分大小写。
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
     * 分别用三种 Set 去重并打印结果，同时说明三者的区别。
     */
    public static void demo(String[] words) {
        Set<String> hashSet = new HashSet<>(Arrays.asList(words));
        Set<String> linkedHashSet = new LinkedHashSet<>(Arrays.asList(words));
        Set<String> treeSet = new TreeSet<>(Arrays.asList(words));

        System.out.println("HashSet       去重结果：" + hashSet);
        System.out.println("LinkedHashSet 去重结果：" + linkedHashSet);
        System.out.println("TreeSet       去重结果：" + treeSet);
        System.out.println();

        // HashSet：底层是哈希表，遍历顺序由哈希值决定，看起来是「无序」的；
        // 不允许重复；允许存一个 null。
        System.out.println("HashSet：无序、不可重复、可以存一个 null");
        // LinkedHashSet：在哈希表的基础上多了一条双向链表记录插入顺序；
        // 不允许重复；允许存一个 null。
        System.out.println("LinkedHashSet：按插入顺序、不可重复、可以存一个 null");
        // TreeSet：底层是红黑树，按元素自然顺序排序（String 按字典序，大写字母在小写字母之前）；
        // 不允许重复；不能存 null，因为要调用 compareTo 排序，会抛 NullPointerException。
        System.out.println("TreeSet：按自然顺序升序、不可重复、不能存 null（抛 NullPointerException）");
        System.out.println("补充：\"apple\" 和 \"Apple\" 是两个不同元素，去重区分大小写");
    }

    /**
     * 用 HashSet 去重。
     */
    public static Set<String> dedupWithHashSet(String[] words) {
        return new HashSet<>(Arrays.asList(words));
    }

    /**
     * 用 LinkedHashSet 去重。
     */
    public static Set<String> dedupWithLinkedHashSet(String[] words) {
        return new LinkedHashSet<>(Arrays.asList(words));
    }

    /**
     * 用 TreeSet 去重。
     */
    public static Set<String> dedupWithTreeSet(String[] words) {
        return new TreeSet<>(Arrays.asList(words));
    }
}
