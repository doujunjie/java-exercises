package solutions.ch12_lambda_stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 练习 3 参考答案：Stream 综合
 *
 * 思路：
 * 1. Stream 的套路是「创建 -&gt; 中间操作（filter/map/sorted） -&gt; 终止操作（collect/max/average）」。
 * 2. 中间操作是惰性的，只有遇到终止操作才会真正执行。
 * 3. Collectors 是收集器工具类：toList、joining、averagingDouble、groupingBy 都在这里。
 * 4. 空集合要单独处理，average() 返回的 OptionalDouble 为空，max() 返回的 Optional 也为空。
 *
 * 易错点：
 * 1. Stream 只能消费一次，执行过终止操作后再用同一个 Stream 会抛 IllegalStateException。
 * 2. 这里必须用 mapToInt / mapToDouble 才是数值流，普通 map 后 average() 不可用。
 * 3. groupingBy 默认返回 HashMap，分组顺序不保证；要固定顺序得传 LinkedHashMap::new。
 * 4. 空列表直接调用 average().getAsDouble() 会抛 NoSuchElementException，用 orElse(0.0)。
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
     */
    public List<String> passedNames(List<Student> students) {
        if (students == null || students.isEmpty()) {
            return new ArrayList<>();
        }
        return students.stream()
                .filter(student -> student.getScore() >= 60)
                .map(Student::getName)
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * 统计平均分。
     */
    public double averageScore(List<Student> students) {
        if (students == null || students.isEmpty()) {
            return 0.0;
        }
        // averagingDouble 直接返回 double，空集合时返回 0，不会抛异常
        return students.stream().collect(Collectors.averagingDouble(Student::getScore));
    }

    /**
     * 找出分数最高的学生。
     */
    public Student topStudent(List<Student> students) {
        if (students == null || students.isEmpty()) {
            return null;
        }
        return students.stream()
                .max(Comparator.comparingInt(Student::getScore))
                .orElse(null);
    }

    /**
     * 按年龄段分组。
     */
    public Map<String, List<Student>> groupByAgeRange(List<Student> students) {
        if (students == null || students.isEmpty()) {
            return new HashMap<>();
        }
        return students.stream()
                .collect(Collectors.groupingBy(student -> ageRange(student.getAge())));
        // 想要固定分组顺序可以写：
        // Collectors.groupingBy(student -> ageRange(student.getAge()),
        //         LinkedHashMap::new, Collectors.toList())
    }

    /**
     * 用逗号加空格把学生姓名拼接成一个字符串。
     */
    public String joinNames(List<Student> students) {
        if (students == null || students.isEmpty()) {
            return "";
        }
        return students.stream()
                .map(Student::getName)
                .collect(Collectors.joining(", "));
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
