package solutions.ch07_oop_basic;

/**
 * 练习 1：学生类（参考答案）
 *
 * 思路：
 * 1. 字段全部 private，外界只能通过 getter/setter 访问，这就是「封装」。
 * 2. 无参构造和全参构造构成「构造方法重载」：同名、参数列表不同。
 * 3. 全参构造里复用 setAge/setScore，校验逻辑只写一份，new 对象时也生效。
 * 4. setter 先校验再赋值；不合法时抛 IllegalArgumentException，让调用方立刻发现问题。
 * 5. toString 用字符串拼接，double 的 95.0 会原样拼成 "95.0"。
 *
 * 易错点：
 * - 只写了全参构造，编译器就不再自动生成无参构造，new Student() 会编译失败。
 * - setAge 里写成 age = age（参数遮蔽字段），必须写 this.age = age。
 * - toString 的返回类型必须是 String、参数列表必须为空，否则只是重载不是重写。
 * - 校验时边界值 0 和 150、0 和 100 都算合法，判断要用 < 和 > 而不是 <= 和 >=。
 *
 * 说明：本文件为了演示方便，把 Student 类和测试类放在同一个 .java 文件里，
 * 只有与文件名同名的 Ex01Student 是 public 类。
 */
public class Ex01Student {

    public static void main(String[] args) {
        Student[] students = new Student[3];
        students[0] = new Student("张三", 18, 95.0);
        students[1] = new Student("李四", 20, 88.5);
        students[2] = new Student("王五", 19, 76.0);

        System.out.println("---- 3 个学生 ----");
        for (Student student : students) {
            System.out.println(String.valueOf(student));
        }

        System.out.println("---- 自检 ----");
        check("toString 格式", "Student{name='张三', age=18, score=95.0}", String.valueOf(students[0]));
        check("getName()", "李四", students[1].getName());
        check("getAge()", 20, students[1].getAge());
        check("getScore()", 88.5, students[1].getScore());

        students[1].setAge(130);
        check("setAge(130) 后 getAge()", 130, students[1].getAge());

        try {
            students[0].setAge(200);
            System.out.println("未通过：setAge(200) 越界校验（期望 抛出 IllegalArgumentException，实际 没有抛异常）");
        } catch (IllegalArgumentException e) {
            System.out.println("通过：setAge(200) 越界校验");
        } catch (RuntimeException e) {
            System.out.println("未通过：setAge(200) 越界校验（期望 IllegalArgumentException，实际 "
                    + e.getClass().getSimpleName() + "）");
        }

        try {
            students[0].setScore(101.0);
            System.out.println("未通过：setScore(101.0) 越界校验（期望 抛出 IllegalArgumentException，实际 没有抛异常）");
        } catch (IllegalArgumentException e) {
            System.out.println("通过：setScore(101.0) 越界校验");
        } catch (RuntimeException e) {
            System.out.println("未通过：setScore(101.0) 越界校验（期望 IllegalArgumentException，实际 "
                    + e.getClass().getSimpleName() + "）");
        }
    }

    /** 比较期望值与实际值，打印「通过」或「未通过（期望 X，实际 Y）」。 */
    private static void check(String label, Object expected, Object actual) {
        boolean ok = expected == null ? actual == null : expected.equals(actual);
        if (ok) {
            System.out.println("通过：" + label);
        } else {
            System.out.println("未通过：" + label + "（期望 " + expected + "，实际 " + actual + "）");
        }
    }
}

/**
 * 学生类：演示封装与构造方法。
 */
class Student {

    private String name;
    private int age;
    private double score;

    public Student() {
        // 无参构造：字段保持默认值 null / 0 / 0.0
        // 注意：只要显式写了有参构造，就必须自己写出这个无参构造
    }

    public Student(String name, int age, double score) {
        this.name = name;
        // 借用 setter 完成校验，避免把 0~150、0~100 的判断写两遍
        setAge(age);
        setScore(score);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    /**
     * 设置年龄。
     *
     * @param age 年龄，合法范围 0~150
     * @throws IllegalArgumentException 年龄不在 0~150 之间时抛出
     */
    public void setAge(int age) {
        if (age < 0 || age > 150) {
            throw new IllegalArgumentException("年龄必须在 0~150 之间，实际为 " + age);
        }
        this.age = age;
    }

    public double getScore() {
        return score;
    }

    /**
     * 设置成绩。
     *
     * @param score 成绩，合法范围 0~100
     * @throws IllegalArgumentException 成绩不在 0~100 之间时抛出
     */
    public void setScore(double score) {
        if (score < 0 || score > 100) {
            throw new IllegalArgumentException("成绩必须在 0~100 之间，实际为 " + score);
        }
        this.score = score;
    }

    @Override
    public String toString() {
        // 注意格式里的空格和单引号要和题目要求完全一致，否则自检会判「未通过」
        return "Student{name='" + name + "', age=" + age + ", score=" + score + "}";
    }
}
