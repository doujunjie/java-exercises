package solutions.ch08_oop_advanced;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * 练习 1：多态（参考答案）
 *
 * 思路：
 * 1. 三个子类都用 super(name) 把名字交给父类保存，name 是 private，子类也改不了，
 *    只能通过父类的 getName() 读，这正是封装带来的好处。
 * 2. introduce() 只写一次，内部调用 sound()。编译时看的是 Animal 有没有 sound()，
 *    运行时才根据对象的真实类型决定调用 Dog/Cat/Bird 的版本，这就是「动态绑定」。
 * 3. Animal[] 数组里装的其实都是子类对象，用父类引用调用 introduce() 会自动找到子类实现。
 *
 * 易错点：
 * - 重写时方法签名必须完全一致（返回 String、无参数），写成 sound(String name) 就是重载，多态不会生效。
 * - 忘了写 @Override，签名写错时编译器不会提醒你，运行时才发现调的还是父类方法。
 * - 子类构造方法没写 super(name)，而父类又没有无参构造，编译报错。
 * - 把 private 方法当多态：private 方法不能被重写，父类引用调用的一定是父类自己的版本。
 * - 用 == 比较字符串内容，应该用 equals。
 *
 * 说明：只有与文件名同名的 Ex01Animal 是 public 类，其余类是包内可见。
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
        super(name);
    }

    @Override
    public String sound() {
        return "汪汪";
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
        return "喵喵";
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
        return "啾啾";
    }
}
