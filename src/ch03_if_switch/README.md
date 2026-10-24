# 第3章 分支结构

## 本章知识点

1. `if` 的括号里必须是 boolean 表达式，不能写 `if (1)`，这一点和 C/C++ 不同。
2. `if-else` 二选一；`if-else if-else` 多选一，从上往下依次判断，最多只会执行一个分支。
3. 区间判断要注意条件顺序：先判断 `score >= 90`，再判断 `score >= 80`，否则高分段会被前面的条件提前截住。
4. `if` 可以嵌套；嵌套时建议每层都写花括号，避免「悬空 else」——else 总是和最近的、还没配对的 if 结合。
5. `switch` 根据表达式的值跳转，支持 byte、short、char、int、String 和枚举，不支持 long、double、boolean。
6. 传统 `switch` 的 case 需要 `break` 或 `return`，否则会「穿透」到下一个 case；多个 case 共用逻辑时可以故意连续写。
7. `default` 处理所有没被 case 覆盖的值，分支题里通常用它处理非法输入。
8. Java 14 起可以使用箭头 switch：`case 3, 4, 5 -> "春";`，一个 case 写多个值，自动结束、不会穿透。
9. switch 表达式可以直接赋值或返回，例如 `String s = switch (month) { ... };`；多行分支用 `yield` 返回值。
10. 三元运算符 `? :` 适合简单的二选一；`Math.max(a, b)` 可以直接求两个数的较大值。
11. 比较 String 的内容必须用 `equals`，不能用 `==`；自检时把期望值写在前面可以避免空指针。
12. 易错点一：`if (score = 90)` 是赋值，不是判断，编译会报错；应该写 `score == 90`。
13. 易错点二：switch 的同一个 switch 里不能有两个相同的 case 值，否则编译报错「duplicate case label」。
14. 易错点三：case 分支里声明的变量作用域容易混乱，建议在 case 里用 `{ }` 包起来，或直接 return。

## 练习题

文件都在本章目录 `src/ch03_if_switch/` 下，「难度」用 ★ 表示，★ 越多越难。

| 编号 | 文件 | 难度★ | 一句话要求 | 提示 |
| --- | --- | --- | --- | --- |
| 01 | `Ex01Grade.java` | ★★ | 实现 `grade(int score)`，把分数转换成优秀(A)、良好(B)、中等(C)、及格(D)、不及格(E)，非法分数返回「分数不合法」 | 用 if-else if 链从高到低判断；先处理小于 0 和大于 100 |
| 02 | `Ex02Season.java` | ★★ | 实现 `season(int month)`，用 switch 按月份返回春、夏、秋、冬，非法月份返回「月份不合法」 | 多个 case 可以共用一段逻辑；别忘了 default；可对比 Java 14 箭头 switch |
| 03 | `Ex03MaxOfThree.java` | ★ | 实现 `max(int a, int b, int c)`，用 if 嵌套求三个整数的最大值 | 先比较 a 和 b，再把较大的与 c 比较；main 里再用 `Math.max` 和三元运算符对比 |

每个练习的 `main` 都能直接运行并自检：结果正确打印「通过」，否则打印「未通过（期望 X，实际 Y）」。

## 进阶挑战

1. 用枚举和 switch 表达式重写分数转等级：定义 `enum Level { A, B, C, D, E, INVALID }`，让 `grade` 返回 Level，非法分数返回 INVALID。提示：可以先算出 `score / 10`，再用 `switch` 表达式处理 10、9、8 等结果；注意 100 分要单独处理，0 ~ 59 分统一归为 E。
2. 石头剪刀布裁判：不读键盘，用固定数据模拟若干局（0 表示石头、1 表示剪刀、2 表示布），用 if 或 switch 判断每局胜负并统计比分，最后输出总胜者。提示：先列出所有平局情况，再列出玩家获胜的情况，剩下的就是电脑获胜。

## 怎么运行

### 在 IntelliJ IDEA 中运行（推荐）

1. 用 IDEA 打开 `java-exercises` 目录，等待项目索引完成。
2. 在左侧 Project 视图展开 `src/ch03_if_switch`，双击要做的文件，例如 `Ex01Grade.java`。
3. 点击 `main` 方法左侧行号旁的绿色三角，选择 `Run 'Ex01Grade.main()'`。
4. 在下方 Run 窗口查看输出。自检题会打印「通过」或「未通过（期望 X，实际 Y）」。
5. 参考答案在项目根目录的 `solutions/ch03_if_switch/` 下，文件名与题目相同。先自己完成，再对照答案。
6. 如果 IDEA 不能直接运行 `solutions` 里的文件：该目录在模块配置中被标记为 Excluded，右键 `solutions` 目录，选择 `Mark Directory as` -> `Cancel Exclusion`（取消排除）后再运行。

### 用命令行运行（可选）

在项目根目录打开 PowerShell，依次执行：

```powershell
javac -encoding UTF-8 -d out src/ch03_if_switch/Ex01Grade.java src/ch03_if_switch/Ex02Season.java src/ch03_if_switch/Ex03MaxOfThree.java
java -cp out ch03_if_switch.Ex01Grade
```

运行参考答案：

```powershell
javac -encoding UTF-8 -d out-solutions solutions/ch03_if_switch/Ex01Grade.java solutions/ch03_if_switch/Ex02Season.java solutions/ch03_if_switch/Ex03MaxOfThree.java
java -cp out-solutions solutions.ch03_if_switch.Ex01Grade
```
