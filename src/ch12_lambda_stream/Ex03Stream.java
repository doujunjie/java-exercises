package ch12_lambda_stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 练习 3：Stream 综合
 *
 * 【要求】
 * 1. passedNames：过滤 score &gt;= 60，map 取出姓名，sorted 排序，collect 成 List
 * 2. averageScore：用 Collectors.averagingDouble 统计平均分（空列表返回 0）
 * 3. topStudent：用 max 找出分数最高的学生（空列表返回 null）
 * 4. groupByAgeRange：用 Collectors.groupingBy 按年龄段分组
 * 5. joinNames：用 Collectors.joining 把姓名用逗号拼接起来
 *
 * 【示例输出】
 * 1. 分数 &gt;= 60 的名字（排序后）：[Alice, Carol, Eve, Frank]
 * 2. 平均分：70.00
 * 3. 最高分学生：Alice（92 分）
 * 4. 按年龄段分组：
 *     18 岁以下 -&gt; [David]
 *     18-25 岁 -&gt; [Alice, Bob, Carol, Eve]
 *     26 岁以上 -&gt; [Frank]
 * 5. joining 拼接名字：Alice, Bob, Carol, David, Eve, Frank
 *
 * 【知识点】
 * Stream 的创建与消费、filter / map / sorted / collect、Optional、Collectors 工具类
 */
public class Ex03Stream {

    public static void main(String[] args) {
        Ex03Stream exercise = new Ex03Stream();
        List<Student> students = new ArrayList<>(Arrays.asList(
                new Student("Alice", 92, 20),
                new Student("Bob", 58, 19),
                new Student("Carol", 76, 22),
                new Student("David", 45, 17),
                new Student("Eve", 88, 24),
                new Student("Frank", 61, 26)
        ));

        System.out.println("学生列表：");
        for (Student student : students) {
            System.out.println("    " + student.getName()
                    + "，分数 " + student.getScore()
                    + "，年龄 " + student.getAge());
        }
        System.out.println();

        List<String> passedNames = exercise.passedNames(students);
        System.out.println("1. 分数 >= 60 的名字（排序后）：" + passedNames);

        double average = exercise.averageScore(students);
        System.out.println("2. 平均分：" + String.format("%.2f", average));

        Student top = exercise.topStudent(students);
        System.out.println("3. 最高分学生：" + (top == null ? "（空）" : top.getName() + "（" + top.getScore() + " 分）"));

        Map<String, List<Student>> groups = exercise.groupByAgeRange(students);
        System.out.println("4. 按年龄段分组：");
        if (groups.isEmpty()) {
            System.out.println("    （空 map，请先实现 groupByAgeRange）");
        } else {
            for (Map.Entry<String, List<Student>> entry : groups.entrySet()) {
                List<String> names = new ArrayList<>();
                for (Student student : entry.getValue()) {
                    names.add(student.getName());
                }
                System.out.println("    " + entry.getKey() + " -> " + names);
            }
        }

        String joined = exercise.joinNames(students);
        System.out.println("5. joining 拼接名字：" + joined);
        System.out.println();

        System.out.println("===== 自检 =====");
        boolean pass = passedNames.equals(List.of("Alice", "Carol", "Eve", "Frank"))
                && Math.abs(average - 70.0) < 0.0001
                && top != null
                && "Alice".equals(top.getName())
                && groups.size() == 3
                && groups.getOrDefault("18 岁以下", List.of()).size() == 1
                && groups.getOrDefault("18-25 岁", List.of()).size() == 4
                && groups.getOrDefault("26 岁以上", List.of()).size() == 1
                && "Alice, Bob, Carol, David, Eve, Frank".equals(joined);
        if (pass) {
            System.out.println("通过");
        } else {
            System.out.println("未通过（期望名单 [Alice, Carol, Eve, Frank]、平均分 70.00、最高分 Alice、"
                    + "分组 1 / 4 / 1 人、拼接结果 Alice, Bob, Carol, David, Eve, Frank；"
                    + "实际名单 " + passedNames + "、平均分 " + average
                    + "、最高分 " + (top == null ? "null" : top.getName())
                    + "、分组 " + groups.size() + " 组、拼接结果 " + joined + "）");
        }
    }

    /**
     * 过滤分数大于等于 60 的学生，取出姓名，排序后收集成 List。
     * 提示：students.stream().filter(...).map(Student::getName).sorted().collect(Collectors.toList())。
     *
     * @param students 学生列表
     * @return 及格学生的姓名，按字母序排列
     */
    public List<String> passedNames(List<Student> students) {
        // TODO 实现...
        return new ArrayList<>();
    }

    /**
     * 统计平均分。
     * 提示：Collectors.averagingDouble(Student::getScore)，列表为空时直接返回 0，避免 NaN。
     *
     * @param students 学生列表
     * @return 平均分，列表为空时返回 0
     */
    public double averageScore(List<Student> students) {
        // TODO 实现...
        return 0.0;
    }

    /**
     * 找出分数最高的学生。
     * 提示：max(Comparator.comparingInt(Student::getScore))，返回 Optional，用 orElse(null)。
     *
     * @param students 学生列表
     * @return 最高分学生，列表为空时返回 null
     */
    public Student topStudent(List<Student> students) {
        // TODO 实现...
        return null;
    }

    /**
     * 按年龄段分组。
     * 提示：Collectors.groupingBy(student -&gt; ageRange(student.getAge()))，
     * 年龄段直接用下面已经给出的 ageRange 方法。
     *
     * @param students 学生列表
     * @return 年龄段到学生列表的映射
     */
    public Map<String, List<Student>> groupByAgeRange(List<Student> students) {
        // TODO 实现...
        return new HashMap<>();
    }

    /**
     * 用逗号加空格把学生姓名拼接成一个字符串。
     * 提示：map(Student::getName) 之后用 Collectors.joining(", ")。
     *
     * @param students 学生列表
     * @return 拼接后的姓名
     */
    public String joinNames(List<Student> students) {
        // TODO 实现...
        return "";
    }

    /**
     * 辅助方法：把年龄映射成年龄段，已给出，无需修改。
     *
     * @param age 年龄
     * @return 年龄段名称
     */
    static String ageRange(int age) {
        if (age < 18) {
            return "18 岁以下";
        }
        if (age <= 25) {
            return "18-25 岁";
        }
        return "26 岁以上";
    }

    /**
     * 辅助类：学生（姓名 + 分数 + 年龄），写在同一文件里（非 public）。
     * 注意：这里用静态内部类而不是顶层类，避免与同包其它练习里的同名辅助类冲突。
     */
    static class Student {

        private final String name;

        private final int score;

        private final int age;

        Student(String name, int score, int age) {
            this.name = name;
            this.score = score;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getScore() {
            return score;
        }

        public int getAge() {
            return age;
        }

        @Override
        public String toString() {
            return "Student{name='" + name + "', score=" + score + ", age=" + age + "}";
        }
    }
}
