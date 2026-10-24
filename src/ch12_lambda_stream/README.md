# 第12章 泛型、Lambda 与 Stream

## 本章知识点

- 泛型类把类型参数写在类名后面（`class Box<T>`），字段和方法都能使用这个 `T`，创建时再指定具体类型。
- 泛型方法把 `<T>` 写在返回值类型前面（`static <T> void swap(T[] arr, int i, int j)`），与类上的类型参数互不影响。
- 泛型的上界 `<T extends Comparable<T>>` 限定类型必须可比较，方法体里才能调用 `compareTo`。
- 泛型不支持基本类型，`Box<int>` 编译不通过，要写 `Box<Integer>`，由编译器自动装箱。
- 类型擦除：泛型只在编译期做检查，运行时类型参数被擦成 `Object`，因此不能 `new T[]`、不能对 `T` 做 `instanceof`。
- 函数式接口：只有一个抽象方法的接口，用 `@FunctionalInterface` 标注后编译器会帮忙检查，可以用 Lambda 或方法引用实现。
- Lambda 语法 `(参数) -> 表达式或代码块`；方法引用 `Integer::sum` 是 `(a, b) -> Integer.sum(a, b)` 的简写。
- `Comparator` 是典型的函数式接口：`comparingInt(Student::getScore).reversed()` 一行就能完成按分数降序，比匿名内部类短得多。
- Stream 三段式：创建（`stream()`）→ 中间操作（`filter` / `map` / `sorted`，惰性）→ 终止操作（`collect` / `max` / `average`，触发执行）。
- `Collectors` 常用收集器：`toList()`、`joining(", ")`、`averagingDouble(...)`、`groupingBy(...)`，它们只能用在 `collect` 里。
- 求平均、求和要用 `mapToInt` / `mapToDouble` 得到数值流；`max` / `average` 返回 `Optional`，空集合时要 `orElse` 兜底。
- 易错点一：Stream 只能消费一次，执行过终止操作后再用同一个 Stream 会抛 `IllegalStateException`。
- 易错点二：中间操作是惰性的，没有终止操作就什么都不会执行，调试时容易误以为 `filter` 没生效。
- 易错点三：比较器里用 `a - b` 排序有整数溢出风险，应使用 `Integer.compare(a, b)` 或 `Comparator.comparingInt`。
- 易错点四：`groupingBy` 默认返回 `HashMap`，分组顺序不保证；需要固定顺序就传 `LinkedHashMap::new`。

## 练习题

| 编号 | 文件 | 难度 | 一句话要求 | 提示 |
| --- | --- | --- | --- | --- |
| Ex01 | `src/ch12_lambda_stream/Ex01GenericBox.java` | ★ | 写泛型类 `Box<T>` 和泛型方法 `swap`、`max`，并用两种类型演示 | 字段 `T value` + `get` / `set` / `toString`；交换用临时变量；`max` 用 `a.compareTo(b) >= 0` 判断 |
| Ex02 | `src/ch12_lambda_stream/Ex02Lambda.java` | ★★ | 用 Lambda 实现加减乘除，用方法引用实现加法，再用两种写法写 `Comparator` 降序排序 | `(a, b) -> a + b`；方法引用 `Integer::sum`；降序用 `Comparator.comparingInt(Student::getScore).reversed()` |
| Ex03 | `src/ch12_lambda_stream/Ex03Stream.java` | ★★★ | 用 Stream 完成过滤、映射、排序、平均分、最高分、分组和字符串拼接 | `filter` / `map` / `sorted` / `collect`；`Collectors.averagingDouble`、`max(Comparator.comparingInt(...))`、`groupingBy`、`joining(", ")` |

## 进阶挑战

1. 泛型栈：实现 `class MyStack<T>`，提供 `push`、`pop`、`peek`、`isEmpty`、`size`，底层用 `ArrayList<T>`；再写一个泛型方法 `static <T> List<T> reverse(List<T> list)` 返回倒序的新列表，并用 `Integer`、`String` 两种类型测试。
2. Stream 版成绩报表：给定学生列表，用一次 Stream 流水线统计「及格人数、平均分、最高分学生、各分数段人数（90 以上 / 60-89 / 60 以下）」，把结果封装到一个 `Report` 对象里；要求不使用 for 循环，并处理空列表的情况。

## 怎么运行

1. 在 IntelliJ IDEA 中打开本项目，进入 `src/ch12_lambda_stream` 目录。
2. 打开任意练习文件，点击 `main` 方法左侧的绿色三角，选择 Run 即可运行自检，控制台最后会打印「通过」或「未通过（期望 ...，实际 ...）」。
3. 参考答案在 `solutions/ch12_lambda_stream` 目录下的同名文件中，重点看泛型方法的类型参数位置、方法引用的写法以及 Stream 的整条流水线。
4. 也可以在项目根目录执行 `javac -encoding UTF-8 -d out src/ch12_lambda_stream/*.java`，再执行 `java -cp out ch12_lambda_stream.Ex03Stream`。
