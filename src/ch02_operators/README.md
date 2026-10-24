# 第2章 运算符与键盘输入

## 本章知识点

1. 算术运算符：`+`、`-`、`*`、`/`、`%`。`*`、`/`、`%` 的优先级高于 `+`、`-`，同级从左到右计算，括号可以改变顺序。
2. 两个整数相除，结果还是整数，小数部分直接舍弃：17 / 5 得 3，-7 / 3 得 -2（向零取整）。
3. 取余 `%` 的结果满足 a = (a / b) * b + a % b；余数的符号永远和被除数相同：-7 % 3 = -1，7 % -3 = 1。
4. 复合赋值运算符：`+=`、`-=`、`*=`、`/=`、`%=`，例如 `a += 3` 等价于 `a = a + 3`，但只计算一次左边的表达式。
5. 自增自减：`++i` 先加 1 再使用，`i++` 先使用再加 1；单独写一行时两者结果一样，混在表达式里才有区别。
6. 关系运算符 `>`、`<`、`>=`、`<=`、`==`、`!=` 的结果是 boolean；`==` 判断相等，`=` 是赋值，别写错。
7. 逻辑运算符 `&&`、`||`、`!`：`&&` 和 `||` 有短路特性，左边能确定结果时右边不再执行。
8. 三元运算符 `条件 ? 值1 : 值2`，条件为 true 取「值1」，否则取「值2」，适合简单的二选一。
9. 键盘输入：`import java.util.Scanner;` 后创建 `Scanner scanner = new Scanner(System.in);`，用 `nextInt()`、`nextDouble()`、`nextLine()` 读取。
10. 易错点一：`5 / 2` 得 2，不是 2.5；要小数结果至少让一边是 double，例如 `5 / 2.0`。
11. 易错点二：`nextInt()` 不消费换行符，紧接着调用 `nextLine()` 会读到空字符串；可在两者之间多调用一次 `nextLine()`。
12. 易错点三：int 有范围（约 -21 亿到 21 亿），`Integer.MAX_VALUE + 1` 会溢出变成负数；更大范围用 long。
13. 本章所有 `main` 都用固定数据直接运行，不真的读键盘，否则程序会停在等待输入的状态；Scanner 写法只放在注释里示范。

## 练习题

文件都在本章目录 `src/ch02_operators/` 下，「难度」用 ★ 表示，★ 越多越难。

| 编号 | 文件 | 难度★ | 一句话要求 | 提示 |
| --- | --- | --- | --- | --- |
| 01 | `Ex01Calculator.java` | ★ | 实现 `calc(int a, int b)`，返回两个整数的 +、-、*、/、% 五个结果 | 用 int 数组返回；整数除法用 `/`，取余用 `%`；注意 -7 / 3 和 -7 % 3 |
| 02 | `Ex02Swap.java` | ★★ | 实现 `swap(int a, int b)`，返回交换后的两个数，并比较三种交换写法 | 临时变量最通用；加减法要防溢出；异或只适合整数 |
| 03 | `Ex03LeapYear.java` | ★★ | 实现 `isLeapYear(int year)`，判断闰年 | 用 `%` 取余；条件 `year % 4 == 0 && year % 100 != 0` 加上 `year % 400 == 0` |

每个练习的 `main` 都能直接运行并自检：结果正确打印「通过」，否则打印「未通过（期望 X，实际 Y）」。

## 进阶挑战

1. 不用临时变量完成两个整数的交换，分别用加减法和异或实现，并测试 `Integer.MAX_VALUE` 和 `Integer.MIN_VALUE` 等边界值，观察加减法为什么可能出错。提示：画表格记录每一步两个变量的值。
2. 简易计算器：用 Scanner 读取两个整数和一个运算符（+、-、*、/），输出计算结果；除数为 0 时给出提示而不是让程序崩溃。先写好固定数据的测试版本，确认逻辑正确后再改成键盘输入。提示：`switch` 或 `if-else` 都可以；`scanner.close()` 要记得调用。

## 怎么运行

### 在 IntelliJ IDEA 中运行（推荐）

1. 用 IDEA 打开 `java-exercises` 目录，等待项目索引完成。
2. 在左侧 Project 视图展开 `src/ch02_operators`，双击要做的文件，例如 `Ex01Calculator.java`。
3. 点击 `main` 方法左侧行号旁的绿色三角，选择 `Run 'Ex01Calculator.main()'`。
4. 在下方 Run 窗口查看输出。自检题会打印「通过」或「未通过（期望 X，实际 Y）」。
5. 参考答案在项目根目录的 `solutions/ch02_operators/` 下，文件名与题目相同。先自己完成，再对照答案。
6. 如果 IDEA 不能直接运行 `solutions` 里的文件：该目录在模块配置中被标记为 Excluded，右键 `solutions` 目录，选择 `Mark Directory as` -> `Cancel Exclusion`（取消排除）后再运行。

### 用命令行运行（可选）

在项目根目录打开 PowerShell，依次执行：

```powershell
javac -encoding UTF-8 -d out src/ch02_operators/Ex01Calculator.java src/ch02_operators/Ex02Swap.java src/ch02_operators/Ex03LeapYear.java
java -cp out ch02_operators.Ex01Calculator
```

运行参考答案：

```powershell
javac -encoding UTF-8 -d out-solutions solutions/ch02_operators/Ex01Calculator.java solutions/ch02_operators/Ex02Swap.java solutions/ch02_operators/Ex03LeapYear.java
java -cp out-solutions solutions.ch02_operators.Ex01Calculator
```
