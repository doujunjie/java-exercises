package ch08_oop_advanced;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * 练习 1：多态
 *
 * 【要求】
 * 1. 父类 Animal：字段 name，方法 String sound() 返回 "..."，
 *    方法 void introduce() 打印 "我是xxx，叫声：..."
 * 2. 子类 Dog、Cat、Bird 继承 Animal，构造方法把名字交给父类，
 *    并重写 sound() 分别返回 "汪汪"、"喵喵"、"啾啾"
 * 3. main 里定义 Animal[] animals = {new Dog("旺财"), new Cat("咪咪"), new Bird("小飞")}，
 *    循环调用 introduce()，体会「父类引用指向子类对象」
 * 4. 自检每个 sound() 的返回值，以及 introduce() 打印出来的整行文字
 *
 * 【示例输出】
 * 我是旺财，叫声：汪汪
 * 我是咪咪，叫声：喵喵
 * 我是小飞，叫声：啾啾
 * 通过：Dog.sound()
 * 通过：Cat.sound()
 * 通过：Bird.sound()
 * 通过：Dog.introduce() 输出
 *
 * 【知识点】继承 extends、super、方法重写 @Override、多态、向上转型、动态绑定
 *
 * 说明：本文件把 Animal、Dog、Cat、Bird 和测试类放在同一个 .java 文件里，
 * 只有与文件名同名的 Ex01Animal 是 public，其它类都是包内可见（非 public）。
 */
public class Ex01Animal {

    public static void main(String[] args) {
        Animal[] animals = {new Dog("旺财"), new Cat("咪咪"), new Bird("小飞")};

        System.out.println("---- 依次介绍 ----");
        for (Animal animal : animals) {
            animal.introduce();
        }

        System.out.println("---- 自检 ----");
        check("Dog.sound()", "汪汪", animals[0].sound());
        check("Cat.sound()", "喵喵", animals[1].sound());
        check("Bird.sound()", "啾啾", animals[2].sound());
        check("父类 Animal.sound() 默认值", "...", new Animal("未知动物").sound());
        check("Dog.introduce() 输出", "我是旺财，叫声：汪汪", capture(animals[0]::introduce));
        check("Cat.introduce() 输出", "我是咪咪，叫声：喵喵", capture(animals[1]::introduce));
        check("Bird.introduce() 输出", "我是小飞，叫声：啾啾", capture(animals[2]::introduce));
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

    /**
     * 把一段代码打印到控制台的内容「抓」成字符串，方便比较。
     * 这样 void 方法（如 introduce、play）也能自检。
     */
    private static String capture(Runnable action) {
        PrintStream original = System.out;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try {
            System.setOut(new PrintStream(buffer, true, StandardCharsets.UTF_8));
            action.run();
        } finally {
            System.setOut(original);
        }
        return buffer.toString(StandardCharsets.UTF_8).trim();
    }
}

/**
 * 动物父类：name 是私有字段，sound() 是父类的默认叫声。
 */
class Animal {

    private String name;

    public Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /** 父类给出默认叫声，子类重写后返回各自的叫声。 */
    public String sound() {
        return "...";
    }

    /** 介绍自己：这里调用的是 sound()，运行时才决定执行哪个子类的版本（多态）。 */
    public void introduce() {
        System.out.println("我是" + name + "，叫声：" + sound());
    }
}

/**
 * 狗：重写 sound()。
 */
class Dog extends Animal {

    public Dog(String name) {
        // 子类构造方法必须先调用父类构造方法（没写 super 时编译器会帮你调无参版本，
        // 而 Animal 没有无参构造，所以这里必须显式写 super(name)）
        super(name);
    }

    @Override
    public String sound() {
        // TODO 实现：返回 "汪汪"
        return "...";
    }
}

/**
 * 猫：重写 sound()。
 */
class Cat extends Animal {

    public Cat(String name) {
        super(name);
    }

    @Override
    public String sound() {
        // TODO 实现：返回 "喵喵"
        return "...";
    }
}

/**
 * 鸟：重写 sound()。
 */
class Bird extends Animal {

    public Bird(String name) {
        super(name);
    }

    @Override
    public String sound() {
        // TODO 实现：返回 "啾啾"
        return "...";
    }
}
