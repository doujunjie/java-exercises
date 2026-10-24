package solutions.ch10_collection;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * 练习 1 参考答案：ArrayList 增删改查
 *
 * 思路：
 * 1. ArrayList 的 add 有两个重载：add(E) 追加到末尾，add(int, E) 插入到指定下标。
 * 2. remove 也有两个重载：remove(int) 按下标删，remove(Object) 按元素删，本练习两种都演示一遍。
 * 3. 三种遍历方式的结果相同，但 delete 时只有 Iterator.remove() 是安全的。
 *
 * 易错点：
 * 1. 增强 for 里直接调用 list.remove(...) 会抛 ConcurrentModificationException，
 *    因为增强 for 编译后就是 Iterator，集合的 modCount 变了但迭代器的 expectedModCount 没变（fail-fast）。
 * 2. List.of(...) 返回不可变集合，add / remove 会抛 UnsupportedOperationException。
 * 3. List<Integer> 上写 remove(1) 删的是下标 1，不是元素 1。
 */
public class Ex01ListCrud {

    public static void main(String[] args) {
        Ex01ListCrud exercise = new Ex01ListCrud();

        System.out.println("===== ArrayList 增删改查演示 =====");
        exercise.demo();

        System.out.println();
        System.out.println("===== 自检：用迭代器删除元素 =====");
        List<String> students = new ArrayList<>(List.of("张三", "李四", "王五", "李四"));
        System.out.println("删除前：" + students);

        int removed = exercise.removeByIterator(students, "李四");
        System.out.println("删除的 \"李四\" 个数：" + removed);
        System.out.println("删除后：" + students);

        boolean pass = removed == 2 && students.equals(List.of("张三", "王五"));
        if (pass) {
            System.out.println("通过");
        } else {
            System.out.println("未通过（期望删除 2 个、剩余 [张三, 王五]，实际删除 " + removed + " 个、剩余 " + students + "）");
        }
    }

    /**
     * 演示 ArrayList 的增删改查与三种遍历方式，每一步都打印结果。
     */
    public void demo() {
        List<String> students = new ArrayList<>();
        students.add("张三");
        students.add("李四");
        students.add("王五");
        System.out.println("1. add 添加后：" + students + "，size = " + students.size());

        System.out.println("2. get(1) 查询下标 1：" + students.get(1));

        students.set(1, "李四四");
        System.out.println("3. set(1, \"李四四\") 修改后：" + students);

        students.remove("李四四");
        System.out.println("4. remove(\"李四四\") 按元素删除后：" + students);

        students.add("赵六");
        students.remove(0);
        System.out.println("5. remove(0) 按下标删除后：" + students + "，size = " + students.size());

        System.out.println("6. 普通 for 遍历（适合需要下标的场景）：");
        for (int i = 0; i < students.size(); i++) {
            System.out.println("   下标 " + i + " -> " + students.get(i));
        }

        System.out.println("7. 增强 for 遍历（最简洁，只读场景首选）：");
        for (String name : students) {
            System.out.println("   " + name);
        }

        System.out.println("8. 迭代器遍历（需要在遍历中删除元素时使用）：");
        Iterator<String> iterator = students.iterator();
        while (iterator.hasNext()) {
            System.out.println("   " + iterator.next());
        }

        System.out.println("9. 遍历时删除的坑：增强 for 里直接 remove 会抛异常：");
        List<String> wrongWay = new ArrayList<>(List.of("张三", "李四", "王五", "李四"));
        try {
            for (String name : wrongWay) {
                if ("李四".equals(name)) {
                    wrongWay.remove(name);
                }
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("   增强 for 中 remove 失败：" + e.getClass().getSimpleName());
        }

        List<String> rightWay = new ArrayList<>(List.of("张三", "李四", "王五", "李四"));
        Iterator<String> removeIterator = rightWay.iterator();
        while (removeIterator.hasNext()) {
            if ("李四".equals(removeIterator.next())) {
                // 只能用迭代器自己的 remove，它会同步 expectedModCount
                removeIterator.remove();
            }
        }
        System.out.println("   改用 Iterator.remove() 后：" + rightWay);

        // 补充：JDK 8+ 也可以直接 removeIf，内部同样是用迭代器安全删除
        List<String> byRemoveIf = new ArrayList<>(List.of("张三", "李四", "王五", "李四"));
        byRemoveIf.removeIf("李四"::equals);
        System.out.println("   或者用 removeIf 后：" + byRemoveIf);
    }

    /**
     * 用迭代器删除所有等于 target 的元素，返回删除的个数。
     */
    public int removeByIterator(List<String> list, String target) {
        if (list == null) {
            return 0;
        }
        int removed = 0;
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            // 用 Objects.equals 兼容 null，避免 target 为 null 时 NPE
            if (Objects.equals(target, iterator.next())) {
                iterator.remove();
                removed++;
            }
        }
        return removed;
    }
}
