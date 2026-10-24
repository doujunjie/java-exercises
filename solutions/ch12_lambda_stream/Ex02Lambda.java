package solutions.ch12_lambda_stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 练习 2 参考答案：函数式接口与 Lambda
 *
 * 思路：
 * 1. Calculator 只有一个抽象方法，编译器能根据上下文推断参数类型，
 *    所以 (a, b) -&gt; a + b 就够了，不用写 (int a, int b)。
 * 2. 方法引用 Integer::sum 是 (a, b) -&gt; Integer.sum(a, b) 的简写，
 *    参数个数和返回值类型与 calc 完全匹配。
 * 3. Comparator 的匿名内部类写法要 new + 重写 compare，Lambda 只需一行，
 *    语义完全一样，Lambda 更短也更清晰。
 *
 * 内部类 vs Lambda 对比：
 * - 匿名内部类：能访问外部类的 this，能定义多个方法、字段，可读性偏好但样板代码多。
 * - Lambda：只能实现函数式接口，不能有成员变量，this 指向外部类实例，代码短。
 *
 * 易错点：
 * 1. 函数式接口只能有一个抽象方法，多了就用不了 Lambda（默认方法、静态方法不算）。
 * 2. 比较器里写 second.getScore() - first.getScore() 有整数溢出风险，
 *    要用 Integer.compare 或 Comparator.comparingInt(...).reversed()。
 * 3. 除法要自己判断除数是否为 0，Lambda 里同样会抛 ArithmeticException。
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
     */
    public Calculator add() {
        return (a, b) -> a + b;
    }

    /**
     * 减法。
     */
    public Calculator subtract() {
        return (a, b) -> a - b;
    }

    /**
     * 乘法。
     */
    public Calculator multiply() {
        return (a, b) -> a * b;
    }

    /**
     * 除法。
     */
    public Calculator divide() {
        return (a, b) -> {
            if (b == 0) {
                System.out.println("    [提示] 除数不能为 0，返回 0");
                return 0;
            }
            return a / b;
        };
    }

    /**
     * 用方法引用实现加法。
     */
    public Calculator sumByMethodReference() {
        // Integer.sum(int, int) 的签名与 calc 一致，可以直接当作实现
        return Integer::sum;
    }

    /**
     * 用匿名内部类实现按分数降序的 Comparator。
     */
    public Comparator<Student> scoreComparatorByInnerClass() {
        return new Comparator<Student>() {
            @Override
            public int compare(Student first, Student second) {
                // 降序：用后一个的分数减前一个，等价于 Integer.compare(second, first)
                return Integer.compare(second.getScore(), first.getScore());
            }
        };
    }

    /**
     * 用 Lambda 实现按分数降序的 Comparator。
     */
    public Comparator<Student> scoreComparatorByLambda() {
        return Comparator.comparingInt(Student::getScore).reversed();
        // 等价写法：(first, second) -> Integer.compare(second.getScore(), first.getScore())
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
