# 第8章 继承、多态、抽象类、接口

## 本章知识点

- `extends` 是单继承：一个类只能有一个父类，子类自动拥有父类非 private 的成员。
- 子类构造方法必须先调用父类构造方法，`super(...)` 必须写在第一行；不写时编译器会尝试调用父类无参构造。
- 方法重写（override）要求方法名、参数列表、返回类型兼容，访问权限不能比父类更小，建议加 `@Override` 让编译器帮你检查。
- 多态：父类引用可以指向子类对象，方法调用在运行时才确定执行哪个版本，这叫动态绑定。
- 「编译看左边，运行看右边」：能不能调用由引用类型决定，实际执行哪个方法由对象类型决定。
- 向上转型自动完成；向下转型需要强制类型转换，转之前先用 `instanceof` 判断。
- 重载（overload）与重写（override）不同：重载看参数列表，编译期就确定；重写看运行时对象类型。
- 抽象类用 `abstract` 修饰，不能 `new`，但可以有字段、构造方法和普通方法；抽象方法只有声明没有方法体。
- 子类必须实现父类的全部抽象方法，否则子类自己也要声明成 `abstract`。
- 接口里的方法默认是 `public abstract`，字段默认是 `public static final` 常量。
- `default` 方法让接口可以提供默认实现，实现类不写也能直接调用，也可以选择重写。
- 一个类只能继承一个父类，但可以实现多个接口；接口可以多继承接口。
- 易错点一：重写时漏写 `@Override` 又把参数写错，方法变成重载，多态失效却很难发现。
- 易错点二：`new` 抽象类或接口（如 `new Shape()`、`new Playable()`），编译直接报错。
- 易错点三：实现接口方法时漏写 `public`，访问权限被降低，编译报「attempting to assign weaker access privileges」。

## 练习题

| 编号 | 文件 | 难度 | 一句话要求 | 提示 |
| --- | --- | --- | --- | --- |
| Ex01 | `src/ch08_oop_advanced/Ex01Animal.java` | ★★ | 父类 Animal + 三个子类重写 sound()，用 Animal[] 循环调用 introduce() | 子类构造方法 `super(name)`；`@Override`；introduce 里调用 sound() 就自动多态 |
| Ex02 | `src/ch08_oop_advanced/Ex02Shape.java` | ★★★ | 抽象类 Shape，三个子类实现 area/perimeter，统计总面积、总周长并找最大面积 | 抽象方法不能有方法体；`Math.PI`、`Math.sqrt`；海伦公式的 `p` 是半周长；`String.format("%.2f", x)` |
| Ex03 | `src/ch08_oop_advanced/Ex03Interface.java` | ★★ | 接口 Playable + Piano、Guitar 两个实现类，用 Playable[] 遍历并演示 default 方法 | 接口方法默认 public abstract；实现类重写要写 `public`；`default String description()`；接口引用数组 |

## 进阶挑战

1. 在 Ex01Animal 的基础上增加 `Swimmable`、`Flyable` 两个接口：让 Dog 实现 Swimmable、Bird 实现 Flyable，在 main 里用 `instanceof` 判断并调用对应能力，体会「接口弥补单继承的不足」。
2. 在 Ex02Shape 中增加一个抽象方法 `double volume()`（默认返回 0）和一个子类 Sphere，再让 main 支持「二维/三维图形混排」，思考抽象类里放默认实现和放抽象方法分别适合什么场景。

## 怎么运行

1. 在 IntelliJ IDEA 中打开本项目，展开 `src/ch08_oop_advanced`，打开任意 `ExNN_xxx.java`。
2. 点击 `main` 方法左侧的绿色三角运行；看最后的「通过 / 未通过」逐条自检结果。
3. 参考答案在 `solutions/ch08_oop_advanced` 目录下的同名文件中，重点看继承链上 super 的调用顺序、抽象方法的实现方式、default 方法的使用。
4. 也可以在项目根目录执行 `javac -encoding UTF-8 -d out src/ch08_oop_advanced/*.java`，再执行 `java -cp out ch08_oop_advanced.Ex01Animal`。
