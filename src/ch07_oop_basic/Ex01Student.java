package ch07_oop_basic;

/**
 * 练习 1：学生类
 *
 * 【要求】
 * 1. 定义私有字段 name（姓名）、age（年龄）、score（成绩）
 * 2. 提供无参构造方法与全参构造方法
 * 3. 提供 getter/setter：setAge 校验 0~150，setScore 校验 0~100，
 *    不合法时抛出 IllegalArgumentException（在方法注释里用 @throws 说明）
 * 4. 重写 toString，输出格式 "Student{name='张三', age=18, score=95.0}"
 *
 * 【示例输出】
 * ---- 3 个学生 ----
 * Student{name='张三', age=18, score=95.0}
 * Student{name='李四', age=20, score=88.5}
 * Student{name='王五', age=19, score=76.0}
 * ---- 自检 ----
 * 通过：toString 格式
 * 通过：getName()
 * 通过：getAge()
 * 通过：getScore()
 * 通过：setAge(130) 后 getAge()
 * 通过：setAge(200) 越界校验
 * 通过：setScore(101.0) 越界校验
 *
 * 【知识点】封装、构造方法重载、this 关键字、getter/setter、参数校验、toString
 *
 * 说明：本文件为了演示方便，把 Student 类和测试类 Ex01Student 放在同一个 .java
 * 文件里。一个文件只能有一个 public 类，且 public 类的名字必须和文件名相同，
 * 所以 Student 类不加 public（包内可见）。
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
        // TODO 实现：无参构造（可以不赋值，字段使用默认值）
    }

    public Student(String name, int age, double score) {
        // TODO 实现：全参构造，把三个参数分别赋给同名字段
    }

    public String getName() {
        // TODO 实现：返回 name
        return null;
    }

    public void setName(String name) {
        // TODO 实现：给 name 赋值
    }

    public int getAge() {
        // TODO 实现：返回 age
        return 0;
    }

    /**
     * 设置年龄。
     *
     * @param age 年龄，合法范围 0~150
     * @throws IllegalArgumentException 年龄不在 0~150 之间时抛出
     */
    public void setAge(int age) {
        // TODO 实现：先校验范围，不合法抛 IllegalArgumentException；合法再赋值
    }

    public double getScore() {
        // TODO 实现：返回 score
        return 0.0;
    }

    /**
     * 设置成绩。
     *
     * @param score 成绩，合法范围 0~100
     * @throws IllegalArgumentException 成绩不在 0~100 之间时抛出
     */
    public void setScore(double score) {
        // TODO 实现：先校验范围，不合法抛 IllegalArgumentException；合法再赋值
    }

    @Override
    public String toString() {
        // TODO 实现：返回 "Student{name='姓名', age=年龄, score=成绩}"
        return "Student{}";
    }
}
