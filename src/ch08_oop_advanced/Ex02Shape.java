package ch08_oop_advanced;

/**
 * 练习 2：抽象类
 *
 * 【要求】
 * 1. 定义抽象类 Shape：字段 name，抽象方法 double area()、double perimeter()，
 *    并提供重写后的 toString()，返回「名字：面积=xx.xx，周长=xx.xx」（保留两位小数）
 * 2. 子类 Circle(r)、Rectangle(w, h)、Triangle(a, b, c)：
 *    Circle 面积 πr²、周长 2πr；Rectangle 面积 w*h、周长 2*(w+h)；
 *    Triangle 用海伦公式（半周长 p=(a+b+c)/2，面积 = √(p(p-a)(p-b)(p-c))）
 * 3. main 用 Shape[] 求总面积、总周长，并找出面积最大的图形
 *
 * 【示例输出】
 * 圆形：面积=28.27，周长=18.85
 * 长方形：面积=20.00，周长=18.00
 * 三角形：面积=6.00，周长=12.00
 * 总面积：54.27433388230814
 * 总周长：48.84955592153876
 * 面积最大：圆形
 * 通过：总面积
 * 通过：总周长
 *
 * 【知识点】abstract class、抽象方法、模板方法、多态、toString、Math.sqrt / Math.PI
 *
 * 说明：Shape 与三个子类都写在本文件里，只有 Ex02Shape 是 public。
 */
public class Ex02Shape {

    public static void main(String[] args) {
        Shape[] shapes = {new Circle(3), new Rectangle(4, 5), new Triangle(3, 4, 5)};

        double totalArea = 0;
        double totalPerimeter = 0;
        Shape maxAreaShape = null;

        System.out.println("---- 图形清单 ----");
        for (Shape shape : shapes) {
            System.out.println(shape);
            totalArea += shape.area();
            totalPerimeter += shape.perimeter();
            if (maxAreaShape == null || shape.area() > maxAreaShape.area()) {
                maxAreaShape = shape;
            }
        }

        System.out.println("总面积：" + totalArea);
        System.out.println("总周长：" + totalPerimeter);
        System.out.println("面积最大：" + maxAreaShape.getName());

        System.out.println("---- 自检 ----");
        checkDouble("总面积", 54.27433388230814, totalArea);
        checkDouble("总周长", 48.84955592153876, totalPerimeter);
        check("面积最大的图形", "圆形", maxAreaShape.getName());
        checkDouble("Circle(3).area()", 28.274333882308138, shapes[0].area());
        checkDouble("Triangle(3,4,5).area()", 6.0, shapes[2].area());
        check("Rectangle 的 toString", "长方形：面积=20.00，周长=18.00", String.valueOf(shapes[1]));
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

    /** 面积、周长都是 double，用误差范围比较，不写 ==。 */
    private static void checkDouble(String label, double expected, double actual) {
        if (Math.abs(expected - actual) < 1e-9) {
            System.out.println("通过：" + label);
        } else {
            System.out.println("未通过：" + label + "（期望 " + expected + "，实际 " + actual + "）");
        }
    }
}

/**
 * 抽象类：形状。它知道「每个图形都有名字、面积、周长」，但算不出来，所以把算法留给子类。
 */
abstract class Shape {

    private String name;

    public Shape(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /** 抽象方法：没有方法体，由子类实现。 */
    public abstract double area();

    /** 抽象方法：没有方法体，由子类实现。 */
    public abstract double perimeter();

    @Override
    public String toString() {
        // TODO 实现：返回 "名字：面积=xx.xx，周长=xx.xx"，面积和周长保留两位小数
        // 提示：String.format("%.2f", 数值) 可以格式化 double
        return name;
    }
}

/**
 * 圆形。
 */
class Circle extends Shape {

    private final double r;

    public Circle(double r) {
        super("圆形");
        this.r = r;
    }

    @Override
    public double area() {
        // TODO 实现：返回 πr²
        return 0.0;
    }

    @Override
    public double perimeter() {
        // TODO 实现：返回 2πr
        return 0.0;
    }
}

/**
 * 长方形。
 */
class Rectangle extends Shape {

    private final double width;
    private final double height;

    public Rectangle(double width, double height) {
        super("长方形");
        this.width = width;
        this.height = height;
    }

    @Override
    public double area() {
        // TODO 实现：返回 width * height
        return 0.0;
    }

    @Override
    public double perimeter() {
        // TODO 实现：返回 2 * (width + height)
        return 0.0;
    }
}

/**
 * 三角形：三边 a、b、c，面积用海伦公式。
 */
class Triangle extends Shape {

    private final double a;
    private final double b;
    private final double c;

    public Triangle(double a, double b, double c) {
        super("三角形");
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double area() {
        // TODO 实现：p = (a+b+c)/2，返回 Math.sqrt(p*(p-a)*(p-b)*(p-c))
        return 0.0;
    }

    @Override
    public double perimeter() {
        // TODO 实现：返回 a + b + c
        return 0.0;
    }
}
