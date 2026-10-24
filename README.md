# Java 练习册 · java-exercises

按知识点编排的 Java 入门练习集，配合 IntelliJ IDEA 使用。每道题都有「题目骨架 → 自己动手 → 对照答案」三步，适合边学边练。

- **JDK**：21（项目使用 `D:/jdk`，语言级别 21）
- **IDE**：IntelliJ IDEA 2024.1.2
- **构建**：不依赖 Maven / Gradle，打开即可运行，零配置

---

## 一、目录结构

```
java-exercises/
├─ src/                          ← 练习代码（IDEA 源码根目录）
│  ├─ ch01_basics/               ← 第 1 章 变量与数据类型
│  │  ├─ README.md               ← 本章知识点 + 题目说明
│  │  ├─ Ex01Profile.java        ← 题目骨架（带 TODO）
│  │  ├─ Ex02TypeConvert.java
│  │  └─ Ex03Temperature.java
│  ├─ ch02_operators/            ← 第 2 章 运算符
│  ├─ ch03_if_switch/            ← 第 3 章 分支结构
│  ├─ ch04_loops/                ← 第 4 章 循环结构
│  ├─ ch05_arrays/               ← 第 5 章 数组
│  ├─ ch06_methods/              ← 第 6 章 方法与递归
│  ├─ ch07_oop_basic/            ← 第 7 章 类与对象、封装
│  ├─ ch08_oop_advanced/         ← 第 8 章 继承、多态、抽象类、接口
│  ├─ ch09_string/               ← 第 9 章 字符串与常用 API
│  ├─ ch10_collection/           ← 第 10 章 集合框架
│  ├─ ch11_exception/            ← 第 11 章 异常处理
│  └─ ch12_lambda_stream/        ← 第 12 章 泛型、Lambda、Stream
├─ solutions/                    ← 参考答案（结构同 src，包名以 solutions. 开头）
├─ .idea/                        ← IDEA 工程配置（已配好 JDK 21 与 UTF-8）
└─ java-exercises.iml
```

`solutions` 目录也是源码根目录，可以直接点 `main` 运行参考答案，对照输出结果。

---

## 二、怎么用

### 1. 打开项目
IDEA 菜单 `File → Open`，选择 `java-exercises` 文件夹即可（首次打开如需确认 Trust Project，点 Trust）。

### 2. 做一道题
1. 先读 `src/chXX_xxx/README.md`，看本章知识点和题目要求；
2. 打开对应 `ExNN_xxx.java`，找到 `// TODO`，把方法实现出来；
3. 点 `main` 方法左侧的绿色三角 ▶ 运行，看控制台输出：
   - 输出 `通过` → 这题做对了；
   - 输出 `未通过（期望 X，实际 Y）` → 再改改；
4. 实在卡住，再打开 `solutions/` 下同名文件对照答案。

> 提示：每道题的 `main` 都写好了自检代码，不需要自己写测试，运行就能知道对错。

### 3. 只看讲义
12 个 `README.md` 就是一份精简的知识点笔记，复习时直接翻。

---

## 三、知识点索引

| 章节 | 知识点 | 练习数 |
| --- | --- | --- |
| ch01_basics | 变量、基本数据类型、类型转换、字符串拼接 | 3 |
| ch02_operators | 算术/赋值/比较/逻辑运算符、位运算、键盘输入 | 3 |
| ch03_if_switch | if-else、switch、三元运算符 | 3 |
| ch04_loops | for / while / do-while、嵌套循环、break / continue | 3 |
| ch05_arrays | 一维数组、二维数组、遍历、排序、杨辉三角 | 4 |
| ch06_methods | 方法定义、参数传递、重载、递归 | 3 |
| ch07_oop_basic | 类与对象、封装、构造方法、this、static | 3 |
| ch08_oop_advanced | 继承、重写、多态、抽象类、接口 | 3 |
| ch09_string | String / StringBuilder、常用 API、回文 | 3 |
| ch10_collection | List / Set / Map、遍历与去重、词频统计 | 3 |
| ch11_exception | try-catch-finally、自定义异常、try-with-resources | 3 |
| ch12_lambda_stream | 泛型、函数式接口、Lambda、Stream | 3 |

---

## 四、学习建议

1. **按顺序做**：第 1~6 章是语法地基，第 7 章之后开始面向对象，前后有依赖。
2. **先自己写，再看答案**：看答案前至少动手 15 分钟，卡住时先看 README 里的「提示」。
3. **改一改再跑**：把示例数据换掉、把边界值试一遍（0、负数、空数组、null），比只跑通一遍收获大。
4. **每题写一句总结**：用注释记下「这题考的是什么、我踩了什么坑」，期末复习全靠它。
5. 每章 README 末尾还有「进阶挑战」，做完基础题可以继续挑战。

---

## 五、把作业提交到 Gitee

项目已关联远程仓库 `https://gitee.com/doujunjie53/java-exercises.git`。做完一批题后：

```bash
git status                 # 看看改了哪些文件
git add .                  # 暂存全部修改
git commit -m "完成第5章 数组练习"
git push                   # 推送到 Gitee
```

在 IDEA 里也可以点右上角的 ✔（Commit）和 ↑（Push）按钮完成同样的事。

提交信息建议格式：`完成第X章 XXX练习` / `修正第X章 ExNN 的 bug`。

---

## 六、常见问题

**Q：运行时报「找不到或无法加载主类」？**
A：先 `Build → Rebuild Project`，确认 `src` 是蓝色源码目录（本工程已配好）。

**Q：中文乱码？**
A：工程已设 UTF-8。若控制台仍乱码，检查 `Settings → Editor → File Encodings` 三项都是 UTF-8，并勾选 `Build → Compiler → Java Compiler → Additional command line parameters: -encoding UTF-8`。

**Q：参考答案能运行吗？**
A：能。`solutions` 同样是源码根目录，包名以 `solutions.` 开头，点 `main` 左侧绿色三角即可运行，用来对照输出。
