# 第7章 类与对象、封装

## 本章知识点

- 类是模板，对象是用 `new` 创建出来的实例；引用变量保存的是对象在堆上的地址。
- 字段（成员变量）有默认值：整数 0、小数 0.0、布尔 false、引用 null；局部变量必须先赋值才能使用。
- 封装的标准做法：字段全部 `private`，对外只暴露 `public` 的 getter/setter，并在 setter 里做校验。
- 构造方法的名字与类名相同、没有返回值类型，可以重载；`new` 时自动调用。
- 只要显式写了有参构造，编译器就不再自动生成无参构造，需要自己补一个。
- `this` 表示当前对象；`this.name = name` 用于参数名与字段名相同时区分二者。
- `static` 字段属于类，所有对象共享一份；`static` 方法用类名调用，里面不能使用 `this` 和实例字段。
- 实例成员每个对象独立一份，静态成员全类只有一份，这是本章最常考的区别。
- `Object` 的 `toString()` 默认输出「类名@哈希值」，重写后 `System.out.println(对象)` 会自动调用它。
- 参数校验失败时抛 `IllegalArgumentException`，并在方法注释里用 `@throws` 写清楚触发条件。
- 易错点一：setter 里写成 `age = age;`，参数遮蔽了字段，等于什么都没改，必须写 `this.age = age;`。
- 易错点二：用 `==` 比较 `double` 金额或 `String` 内容，前者有浮点误差，后者比的是地址，应改用误差范围或 `equals`。
- 易错点三：在静态方法里直接访问实例字段或 `this`，编译不通过，因为静态方法先于对象存在。

## 练习题

| 编号 | 文件 | 难度 | 一句话要求 | 提示 |
| --- | --- | --- | --- | --- |
| Ex01 | `src/ch07_oop_basic/Ex01Student.java` | ★★ | 写 Student 类：私有字段、两个构造方法、带校验的 setter、重写 toString | 构造方法里复用 setter；`@throws IllegalArgumentException`；toString 注意单引号和空格的位置 |
| Ex02 | `src/ch07_oop_basic/Ex02BankAccount.java` | ★★ | 写 BankAccount：deposit 校验金额，withdraw 余额不足返回 false 并提示 | 先判断再扣款；不提供 `setBalance`；金额比较别用 `==`；`balance += money` |
| Ex03 | `src/ch07_oop_basic/Ex03StaticCounter.java` | ★ | 用 static 字段 count 统计创建了多少个对象，并写 static 的 add 方法 | 构造方法里 `count++`；`static int getCount()`；用类名调用，不用先 new |

## 进阶挑战

1. 给 Student 增加 `static` 字段 `school` 和 `static` 方法 `getStudentCount()`：每 new 一个学生就计数一次，并让所有学生共享同一个学校名。想一想哪些字段该用 static、哪些不该，为什么。
2. 给 BankAccount 增加 `transfer(BankAccount target, double money)` 方法和一个 `List<String>` 类型的流水记录：转账要同时考虑「余额不足」「金额非法」「不能转给自己」三种情况，每次成功操作都往流水里追加一行，最后打印完整流水。

## 怎么运行

1. 在 IntelliJ IDEA 中打开本项目，展开 `src/ch07_oop_basic`，打开任意 `ExNN_xxx.java`。
2. 点击 `main` 方法左侧的绿色三角，选择 Run；控制台输出「通过」说明做对了，「未通过（期望 X，实际 Y）」说明还要改。
3. 参考答案在 `solutions/ch07_oop_basic` 目录下的同名文件中，重点看构造方法如何复用 setter、withdraw 的判断顺序。
4. 也可以在项目根目录执行 `javac -encoding UTF-8 -d out src/ch07_oop_basic/*.java`，再执行 `java -cp out ch07_oop_basic.Ex01Student`。
