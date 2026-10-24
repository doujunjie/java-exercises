# 第1章 变量与数据类型

## 本章知识点

1. 变量三要素：数据类型、变量名、初始值。局部变量必须先赋值再使用，否则编译报错「可能尚未初始化变量」。
2. Java 有 8 种基本类型：byte、short、int、long、float、double、char、boolean；String 是引用类型，不是基本类型。
3. 整数字面量默认是 int，要表示超过 int 范围的数需加 L（如 10000000000L）；小数字面量默认是 double，用 float 要加 F。
4. 自动类型转换只发生在「小范围到大范围」：byte/short/char -> int -> long -> float -> double，不需要写转换符号。
5. 强制类型转换用于「大范围到小范围」，格式是 (目标类型) 值；double 转 int 会直接截断小数部分，不四舍五入。
6. char 是 16 位无符号整数，'A' 就是 65，'a' 是 97；char 与 int 可以互相转换，参与运算时按整数处理。
7. 字符串转数字用 Integer.parseInt、Double.parseDouble；字符串里出现非数字字符会抛 NumberFormatException。
8. 字符串拼接用 +，从左到右结合："" + 1 + 2 得到 "12"，而 1 + 2 + "" 得到 "3"。
9. 易错点一：9 / 5 是整数除法，结果是 1 而不是 1.8；只要有一个操作数是 double，才会得到 1.8，例如 9 / 5.0。
10. 易错点二：(int) 3.99 的结果是 3，不是 4；负数 (int) -3.99 得到 -3，截断方向是「向零」。
11. 易错点三：比较 String 的内容要用 equals（如 "A".equals(s)），不能用 ==；== 比较的是两个引用是否指向同一个对象。
12. 变量作用域由所在的一对花括号决定：在哪个 {} 里声明，就只能在哪个 {} 里使用。

## 练习题

文件都在本章目录 `src/ch01_basics/` 下，「难度」用 ★ 表示，★ 越多越难。

| 编号 | 文件 | 难度★ | 一句话要求 | 提示 |
| --- | --- | --- | --- | --- |
| 01 | `Ex01Profile.java` | ★ | 声明 String/int/double/boolean 四个变量，逐行打印个人信息卡 | 用 `System.out.println` 输出，用 `+` 拼接字符串和变量 |
| 02 | `Ex02TypeConvert.java` | ★★ | 演示 int→long→double 自动转换、double→int 强制转换、char 与 int 互转、字符串转数字 | 强制转换写 `(int)`、`(char)`；字符串转数字用 `Integer.parseInt` |
| 03 | `Ex03Temperature.java` | ★ | 实现摄氏转华氏，公式 F = C * 9 / 5 + 32 | 注意 `9 / 5` 是整数除法，可写 `9.0 / 5` 或 `* 9 / 5.0` |

每个练习的 `main` 都能直接运行并自检：结果正确打印「通过」，否则打印「未通过（期望 X，实际 Y）」。

## 进阶挑战

1. 成绩单小工具：不读键盘，用固定数据描述 3 个学生（姓名、年龄、身高），输出每个人的信息，再计算并打印平均身高，保留两位小数。提示：可以用 `String.format("%.2f", value)` 格式化输出。
2. 浮点数探秘：计算 0.1 + 0.2，打印结果，并和 0.3 比较。思考为什么 `0.1 + 0.2 == 0.3` 是 false，改用 `Math.abs(a - b) < 1e-6` 判断，并用自己的话解释二进制浮点数的表示误差。

## 怎么运行

### 在 IntelliJ IDEA 中运行（推荐）

1. 用 IDEA 打开 `java-exercises` 目录，等待项目索引完成。
2. 在左侧 Project 视图展开 `src/ch01_basics`，双击要做的文件，例如 `Ex01Profile.java`。
3. 点击 `main` 方法左侧行号旁的绿色三角，选择 `Run 'Ex01Profile.main()'`。
4. 在下方 Run 窗口查看输出。自检题会打印「通过」或「未通过（期望 X，实际 Y）」。
5. 参考答案在项目根目录的 `solutions/ch01_basics/` 下，文件名与题目相同。先自己完成，再对照答案。
6. 如果 IDEA 不能直接运行 `solutions` 里的文件：该目录在模块配置中被标记为 Excluded，右键 `solutions` 目录，选择 `Mark Directory as` -> `Cancel Exclusion`（取消排除）后再运行。

### 用命令行运行（可选）

在项目根目录打开 PowerShell，依次执行：

```powershell
javac -encoding UTF-8 -d out src/ch01_basics/Ex01Profile.java src/ch01_basics/Ex02TypeConvert.java src/ch01_basics/Ex03Temperature.java
java -cp out ch01_basics.Ex03Temperature
```

运行参考答案：

```powershell
javac -encoding UTF-8 -d out-solutions solutions/ch01_basics/Ex01Profile.java solutions/ch01_basics/Ex02TypeConvert.java solutions/ch01_basics/Ex03Temperature.java
java -cp out-solutions solutions.ch01_basics.Ex03Temperature
```
