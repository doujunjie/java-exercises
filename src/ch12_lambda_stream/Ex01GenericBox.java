package ch12_lambda_stream;

import java.util.Arrays;

/**
 * 练习 1：泛型类与泛型方法
 *
 * 【要求】
 * 1. 补全泛型类 Box&lt;T&gt;：字段 T value，提供 get / set / toString
 * 2. 实现泛型方法 static &lt;T&gt; void swap(T[] arr, int i, int j)：交换数组两个下标的元素
 * 3. 实现泛型方法 static &lt;T extends Comparable&lt;T&gt;&gt; T max(T a, T b)：返回较大的那个
 * 4. main 用 Box&lt;String&gt;、Box&lt;Integer&gt; 演示，并对 String[] 排序前后做对比
 *
 * 【示例输出】
 * Box<String>：Box{value=Hello}
 * Box<String>.get()：Hello
 * Box<Integer>：Box{value=100}
 * 交换前：[pear, apple, orange, banana]
 * swap(words, 0, 3) 后：[banana, apple, orange, pear]
 * Arrays.sort 后：[apple, banana, orange, pear]
 * max(3, 7) = 7
 * max("apple", "banana") = banana
 *
 * 【知识点】
 * 泛型类、泛型方法、类型参数、Comparable 上界、类型擦除
 */
public class Ex01GenericBox {

    public static void main(String[] args) {
        Box<String> stringBox = new Box<>();
        stringBox.set("Hello");
        System.out.println("Box<String>：" + stringBox);
        System.out.println("Box<String>.get()：" + stringBox.get());

        Box<Integer> integerBox = new Box<>();
        integerBox.set(100);
        System.out.println("Box<Integer>：" + integerBox);
        System.out.println();

        String[] words = {"pear", "apple", "orange", "banana"};
        System.out.println("交换前：" + Arrays.toString(words));
        swap(words, 0, 3);
        System.out.println("swap(words, 0, 3) 后：" + Arrays.toString(words));
        boolean swapOk = "banana".equals(words[0]) && "pear".equals(words[3]);

        Arrays.sort(words);
        System.out.println("Arrays.sort 后：" + Arrays.toString(words));
        boolean sortOk = "apple".equals(words[0]) && "pear".equals(words[3]);
        System.out.println();

        System.out.println("max(3, 7) = " + max(3, 7));
        System.out.println("max(\"apple\", \"banana\") = " + max("apple", "banana"));
        System.out.println();

        System.out.println("===== 自检 =====");
        Integer maxInt = max(3, 7);
        String maxString = max("apple", "banana");

        boolean pass = "Hello".equals(stringBox.get())
                && Integer.valueOf(100).equals(integerBox.get())
                && swapOk
                && sortOk
                && Integer.valueOf(7).equals(maxInt)
                && "banana".equals(maxString);
        if (pass) {
            System.out.println("通过");
        } else {
            System.out.println("未通过（期望 Box<String> 的值为 Hello、Box<Integer> 的值为 100、"
                    + "交换后 [banana, apple, orange, pear]、排序后 [apple, banana, orange, pear]、"
                    + "max(3, 7) = 7、max(\"apple\", \"banana\") = banana；"
                    + "实际 Box 值 " + stringBox.get() + " / " + integerBox.get()
                    + "，交换是否成功 " + swapOk + "，排序是否成功 " + sortOk
                    + "，max 结果 " + maxInt + " / " + maxString + "）");
        }
    }

    /**
     * 泛型方法：交换数组中下标 i 与下标 j 的元素。
     * 提示：用一个临时变量 T temp 做中转，再判断下标是否合法（越界直接返回）。
     *
     * @param arr 数组
     * @param i   第一个下标
     * @param j   第二个下标
     * @param <T> 元素类型
     */
    public static <T> void swap(T[] arr, int i, int j) {
        // TODO 实现...
    }

    /**
     * 泛型方法：返回 a、b 中较大的那个。
     * 提示：&lt;T extends Comparable&lt;T&gt;&gt; 表示 T 必须能比较大小，
     * 方法体里用 a.compareTo(b) &gt;= 0 判断。
     *
     * @param a   第一个值
     * @param b   第二个值
     * @param <T> 可比较的元素类型
     * @return 较大的值
     */
    public static <T extends Comparable<T>> T max(T a, T b) {
        // TODO 实现...
        return null;
    }

    /**
     * 辅助类：泛型盒子，写在同一文件里（非 public），用来练习泛型类的定义。
     * 静态内部类不会和同包其它练习里的辅助类重名。
     *
     * @param <T> 盒子中存放的元素类型
     */
    static class Box<T> {

        private T value;

        public T get() {
            // TODO 实现...
            return null;
        }

        public void set(T value) {
            // TODO 实现...
        }

        @Override
        public String toString() {
            // TODO 实现：返回形如 Box{value=Hello} 的字符串
            return "Box";
        }
    }
}
