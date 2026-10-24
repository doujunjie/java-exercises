# 第11章 异常处理

## 本章知识点

- 异常体系：`Throwable` 下分 `Error`（程序无法处理，如 `OutOfMemoryError`）和 `Exception`；`Exception` 又分受检异常和 `RuntimeException`（非受检）。
- `try-catch`：把可能出错的代码放进 `try`，用 `catch` 按类型捕获，捕获后程序继续往下执行。
- 多个 `catch` 从上往下匹配，子类异常必须写在父类异常前面，否则编译报错（后面的 `catch` 永远不可达）。
- `finally` 无论 `try` 是否抛异常、是否有 `return` 都会执行，常用来释放资源；`finally` 里写 `return` 会覆盖 `try` 的返回值，应当避免。
- 常见运行时异常：`ArithmeticException`（整数除以 0）、`ArrayIndexOutOfBoundsException`（下标越界）、`NullPointerException`、`NumberFormatException`。
- 自定义异常通常继承 `Exception`（受检）或 `RuntimeException`（非受检），构造器里用 `super(message)` 把消息交给父类。
- `throw` 抛出异常对象，`throws` 在方法签名上声明可能抛出的异常类型；受检异常不处理就必须声明。
- try-with-resources：资源类实现 `AutoCloseable` 后写在 `try (...)` 括号里，离开 `try` 块自动调用 `close()`，多个资源按声明逆序关闭。
- 异常抑制（suppressed）：`try` 与 `close()` 都抛异常时，`close()` 的异常会被挂到主异常的 `getSuppressed()` 上，不会覆盖主异常。
- 易错点一：`catch (Exception e) {}` 空实现会吞掉异常，排查问题时毫无线索，至少要打印日志。
- 易错点二：`finally` 一定执行，所以不要在 `finally` 里写 `return` 或再抛异常，否则会覆盖 `try` 里的结果。
- 易错点三：`catch` 顺序颠倒（父类在前）编译直接失败；只捕获能处理的异常，处理不了就继续往上抛。
- 易错点四：try-with-resources 括号里的变量必须实现 `AutoCloseable`，普通对象放进去编译不通过。

## 练习题

| 编号 | 文件 | 难度 | 一句话要求 | 提示 |
| --- | --- | --- | --- | --- |
| Ex01 | `src/ch11_exception/Ex01TryCatch.java` | ★ | 用 try-catch 处理除零和数组越界，并演示多个 catch 与 finally 的执行顺序 | `catch (ArithmeticException e)` / `catch (ArrayIndexOutOfBoundsException e)` 里 `return -1`；异常打印用 `[1]` `[2]` 这类标记观察顺序 |
| Ex02 | `src/ch11_exception/Ex02CustomException.java` | ★★ | 自定义 `AgeException`，校验年龄小于 0 或大于 150 时抛出 | `extends Exception`；构造器 `super(message)`；方法签名 `throws AgeException`；判断用 `age < 0 \|\| age > 150` |
| Ex03 | `src/ch11_exception/Ex03TryWithResources.java` | ★★★ | 用 try-with-resources 管理资源，再写传统 try-finally 版本对比 | `implements AutoCloseable`；`try (MyResource resource = ...)`；传统写法在 `finally` 里手动 `close()`；对比异常抑制 |

## 进阶挑战

1. 异常链：写一个 `OrderService`，底层抛出自定义的 `DataAccessException`，上层捕获后包装成 `ServiceException` 并带上原始异常（`new ServiceException(msg, cause)`），打印完整的 `printStackTrace()`，观察 cause 链。
2. 资源关闭顺序实验：让 `MyResource` 的 `work()` 和 `close()` 都抛异常，分别用 try-with-resources 与传统 finally 运行，打印 `getSuppressed()` 的内容，说明两种写法在异常信息保留上的差异。

## 怎么运行

1. 在 IntelliJ IDEA 中打开本项目，进入 `src/ch11_exception` 目录。
2. 打开任意练习文件，点击 `main` 方法左侧的绿色三角，选择 Run 即可运行自检，控制台最后会打印「通过」或「未通过（期望 ...，实际 ...）」。
3. 参考答案在 `solutions/ch11_exception` 目录下的同名文件中，重点看多个 `catch` 的匹配顺序、自定义异常的构造器以及 try-with-resources 的写法。
4. 也可以在项目根目录执行 `javac -encoding UTF-8 -d out src/ch11_exception/*.java`，再执行 `java -cp out ch11_exception.Ex01TryCatch`。
