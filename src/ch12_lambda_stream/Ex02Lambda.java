package ch12_lambda_stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 练习 2：函数式接口与 Lambda
 *
 * 【要求】
 * 1. 用 Lambda 实现函数式接口 Calculator 的加、减、乘、除四个运算
 * 2. 用方法引用 Integer::sum 实现加法，体会「Lambda 的简写」
 * 3. 分别用匿名内部类和 Lambda 写 Comparator&lt;Student&gt;，按分数降序排序，并在注释里对比两种写法
 *
 * 【示例输出】
 * 加法 3 + 4 = 7
 * 减法 3 - 4 = -1
 * 乘法 3 * 4 = 12
 * 除法 8 / 4 = 2
 * 方法引用 Integer::sum：(3, 4) -> 7
 * 排序前：[Student{name='张三', score=78}, ...]
 * 内部类 Comparator 降序：[Student{name='李四', score=92}, ...]
 *
 * 【知识点】
 * 函数式接口、@FunctionalInterface、Lambda 语法、方法引用、Comparator、内部类 vs Lambda
 */
public class Ex02Lambda {

    /**
     * 函数式接口：只有一个抽象方法，因此可以用 Lambda 或方法引用来实现。
     */
    @FunctionalInterface
    interface Calculator {
        int calc(int a, int b);
    }

    public static void main(String[] args) {
        Ex02Lambda exercise = new Ex02Lambda();

        Calculator add = exercise.add();
        Calculator subtract = exercise.subtract();
        Calculator multiply = exercise.multiply();
        Calculator divide = exercise.divide();
        Calculator sum = exercise.sumByMethodReference();

        System.out.println("加法 3 + 4 = " + add.calc(3, 4));
        System.out.println("减法 3 - 4 = " + subtract.calc(3, 4));
        System.out.println("乘法 3 * 4 = " + multiply.calc(3, 4));
        System.out.println("除法 8 / 4 = " + divide.calc(8, 4));
        System.out.println("方法引用 Integer::sum：(3, 4) -> " + sum.calc(3, 4));
        System.out.println();

        List<Student> students = new ArrayList<>(Arrays.asList(
                new Student("张三", 78),
                new Student("李四", 92),
                new Student("王五", 65),
                new Student("赵六", 88)
        ));
        System.out.println("排序前：" + students);

        List<Student> byInnerClass = new ArrayList<>(students);
        byInnerClass.sort(exercise.scoreComparatorByInnerClass());
        System.out.println("内部类 Comparator 降序：" + byInnerClass);

        List<Student> byLambda = new ArrayList<>(students);
        byLambda.sort(exercise.scoreComparatorByLambda());
        System.out.println("Lambda Comparator 降序：" + byLambda);
        System.out.println();

        List<String> innerClassNames = new ArrayList<>();
        for (Student student : byInnerClass) {
            innerClassNames.add(student.getName());
        }
        List<String> lambdaNames = new ArrayList<>();
        for (Student student : byLambda) {
            lambdaNames.add(student.getName());
        }

        System.out.println("===== 自检 =====");
        boolean pass = add.calc(3, 4) == 7
                && subtract.calc(3, 4) == -1
                && multiply.calc(3, 4) == 12
                && divide.calc(8, 4) == 2
                && sum.calc(3, 4) == 7
                && lambdaNames.equals(List.of("李四", "赵六", "张三", "王五"))
                && lambdaNames.equals(innerClassNames);
        if (pass) {
            System.out.println("通过");
        } else {
            System.out.println("未通过（期望 3+4=7、3-4=-1、3*4=12、8/4=2、方法引用 3+4=7，"
                    + "且两种 Comparator 都按分数降序得到 [李四, 赵六, 张三, 王五]；"
                    + "实际 " + add.calc(3, 4) + "、" + subtract.calc(3, 4) + "、"
                    + multiply.calc(3, 4) + "、" + divide.calc(8, 4) + "、" + sum.calc(3, 4)
                    + "，内部类排序 " + innerClassNames + "，Lambda 排序 " + lambdaNames + "）");
        }
    }

    /**
     * 加法。
     * 提示：返回 (a, b) -&gt; a + b。
     *
     * @return 加法计算器
     */
    public Calculator add() {
        // TODO 实现...
        return (a, b) -> 0;
    }

    /**
     * 减法。
     *
     * @return 减法计算器
     */
    public Calculator subtract() {
        // TODO 实现...
        return (a, b) -> 0;
    }

    /**
     * 乘法。
     *
     * @return 乘法计算器
     */
    public Calculator multiply() {
        // TODO 实现...
        return (a, b) -> 0;
    }

    /**
     * 除法。
     * 提示：方法体先判断 b == 0 时返回 0，避免 ArithmeticException。
     *
     * @return 除法计算器
     */
    public Calculator divide() {
        // TODO 实现...
        return (a, b) -> 0;
    }

    /**
     * 用方法引用实现加法。
     * 提示：Integer::sum 等价于 (a, b) -&gt; Integer.sum(a, b)，参数和返回值刚好匹配 calc。
     *
     * @return 加法计算器
     */
    public Calculator sumByMethodReference() {
        // TODO 实现：返回方法引用 Integer::sum
        return (a, b) -> 0;
    }

    /**
     * 用匿名内部类实现按分数降序的 Comparator。
     * 提示：compare 方法里写 Integer.compare(second.getScore(), first.getScore())。
     *
     * @return 分数降序比较器
     */
    public Comparator<Student> scoreComparatorByInnerClass() {
        // TODO 实现...
        return new Comparator<Student>() {
            @Override
            public int compare(Student first, Student second) {
                return 0;
            }
        };
    }

    /**
     * 用 Lambda 实现按分数降序的 Comparator。
     * 提示：Comparator.comparingInt(Student::getScore).reversed()，
     * 或者 (first, second) -&gt; Integer.compare(second.getScore(), first.getScore())。
     * 不要直接写 second.getScore() - first.getScore()，大数相减会溢出。
     *
     * @return 分数降序比较器
     */
    public Comparator<Student> scoreComparatorByLambda() {
        // TODO 实现...
        return (first, second) -> 0;
    }

    /**
     * 辅助类：学生（姓名 + 分数），写在同一文件里（非 public）。
     */
    static class Student {

        private final String name;

        private final int score;

        Student(String name, int score) {
            this.name = name;
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public int getScore() {
            return score;
        }

        @Override
        public String toString() {
            return "Student{name='" + name + "', score=" + score + "}";
        }
    }
}
