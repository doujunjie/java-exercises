package solutions.ch12_lambda_stream;

import java.util.Arrays;

/**
 * 练习 1 参考答案：泛型类与泛型方法
 *
 * 思路：
 * 1. 泛型类 Box&lt;T&gt; 把类型参数写在类名后面，字段、方法都能使用这个 T。
 * 2. 泛型方法把 &lt;T&gt; 写在返回值类型前面，和类上的类型参数互不影响。
 * 3. max 要求 T 可比较，所以用上界 &lt;T extends Comparable&lt;T&gt;&gt;，
 *    这样方法体里才能调用 compareTo。
 *
 * 易错点：
 * 1. 泛型不支持基本类型，Box&lt;int&gt; 编译不通过，要写 Box&lt;Integer&gt;（自动装箱）。
 * 2. 泛型方法里的 &lt;T&gt; 不能漏写，否则 T 会被当成一个不存在的类名。
 * 3. 泛型是编译期检查，运行时会类型擦除，所以不能 new T[]、不能对 T 用 instanceof。
 * 4. max 用 compareTo 比较，String 按字典序，返回的是较大的那个对象本身。
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
     */
    public static <T> void swap(T[] arr, int i, int j) {
        if (arr == null || i < 0 || j < 0 || i >= arr.length || j >= arr.length) {
            return;
        }
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 泛型方法：返回 a、b 中较大的那个。
     */
    public static <T extends Comparable<T>> T max(T a, T b) {
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        return a.compareTo(b) >= 0 ? a : b;
    }

    /**
     * 辅助类：泛型盒子，写在同一文件里（非 public），用来练习泛型类的定义。
     */
    static class Box<T> {

        private T value;

        public T get() {
            return value;
        }

        public void set(T value) {
            this.value = value;
        }

        @Override
        public String toString() {
            // 这里 value 是 T 类型，拼接字符串时会自动调用它的 toString()
            return "Box{value=" + value + "}";
        }
    }
}
