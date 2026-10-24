package ch10_collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 练习 1：ArrayList 增删改查
 *
 * 【要求】
 * 1. 创建 List&lt;String&gt; 存放学生名单，依次演示 add 添加、get 查询、set 修改、remove 删除、size 大小
 * 2. 分别用普通 for、增强 for、迭代器三种方式遍历并打印
 * 3. 演示「遍历时删除元素」的坑：增强 for 里直接 remove 会抛 ConcurrentModificationException，
 *    必须改用 Iterator.remove()
 *
 * 【示例输出】
 * 1. add 添加后：[张三, 李四, 王五]，size = 3
 * 2. get(1) 查询下标 1：李四
 * 3. set(1, "李四四") 修改后：[张三, 李四四, 王五]
 * 4. remove("李四四") 按元素删除后：[张三, 王五]
 * 5. remove(0) 按下标删除后：[王五, 赵六]，size = 2
 * 6. 普通 for 遍历：下标 0 -&gt; 王五 ...（略）
 * 9. 改用 Iterator.remove() 后：[张三, 王五]
 *
 * 【知识点】
 * List 接口、ArrayList 底层数组、下标随机访问、三种遍历方式、fail-fast 机制
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
     * 提示：创建 ArrayList 后依次调用 add / get / set / remove / size，
     * 遍历分别用普通 for、增强 for、Iterator。
     */
    public void demo() {
        // TODO 实现上面的要求，并打印每一步结果
    }

    /**
     * 用迭代器删除所有等于 target 的元素，返回删除的个数。
     * 提示：while (iterator.hasNext()) 中先取 next()，命中后调用 iterator.remove()。
     *
     * @param list   待处理的学生名单
     * @param target 要删除的元素
     * @return 实际删除的个数
     */
    public int removeByIterator(List<String> list, String target) {
        // TODO 实现：遍历时只能用 iterator.remove() 删除元素
        return 0;
    }
}
