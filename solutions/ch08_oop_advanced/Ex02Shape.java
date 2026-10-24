package solutions.ch08_oop_advanced;

/**
 * 练习 2：抽象类（参考答案）
 *
 * 思路：
 * 1. Shape 用 abstract 修饰，area()/perimeter() 声明成抽象方法：父类只规定「必须能算面积和周长」，
 *    具体怎么算交给子类，这就是抽象类最常见的用法。
 * 2. toString() 写在抽象类里，内部调用抽象方法 area()、perimeter()，运行时自动执行子类实现，
 *    这相当于一个「模板方法」，子类不用重复写输出格式。
 * 3. 子类只负责三件事：用 super("名字") 传名字、保存自己的尺寸、实现两个抽象方法。
 * 4. 海伦公式：p 是半周长，面积 = √(p(p-a)(p-b)(p-c))。
 *
 * 易错点：
 * - 抽象类不能 new（new Shape("x") 编译失败），只能 new 具体子类。
 * - 子类漏实现任意一个抽象方法，子类自己也得声明成 abstract，否则编译不过。
 * - 海伦公式里 p 是「半」周长，写成 a+b+c 就错了。
 * - 浮点误差可能让 p-a 出现 -1e-16，Math.sqrt 得到 NaN，稳妥写法是 Math.sqrt(Math.max(0, ...))。
 * - toString 里用 String.format("%.2f", ...)，别用 (int) 强转，否则 28.27 会变成 28。
 * - 抽象类里的抽象方法不能是 private，否则子类没法实现。
 *
 * 说明：只有与文件名同名的 Ex02Shape 是 public 类。
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
        // 这里调用的 area()/perimeter() 是抽象方法，运行时才绑定到具体子类
        return name + "：面积=" + String.format("%.2f", area())
                + "，周长=" + String.format("%.2f", perimeter());
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
        return Math.PI * r * r;
    }

    @Override
    public double perimeter() {
        return 2 * Math.PI * r;
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
        return width * height;
    }

    @Override
    public double perimeter() {
        return 2 * (width + height);
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
        // 半周长 p，注意别漏掉 / 2
        double p = (a + b + c) / 2;
        // Math.max(0, ...) 兜住浮点误差，避免开根号出现 NaN
        return Math.sqrt(Math.max(0, p * (p - a) * (p - b) * (p - c)));
    }

    @Override
    public double perimeter() {
        return a + b + c;
    }
}
