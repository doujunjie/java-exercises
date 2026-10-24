# 第9章 字符串与常用 API

## 本章知识点

- `String` 是不可变对象：拼接、替换、截取都会产生新字符串，原对象不会改变。
- 字符串字面量放在常量池中，`new String("abc")` 会在堆上另建对象，所以 `==` 比的是地址，内容比较必须用 `equals`。
- 常用方法：`length()`、`charAt(i)`、`substring(begin, end)`（左闭右开）、`indexOf`、`contains`、`trim`、`isEmpty`。
- `equalsIgnoreCase` 忽略大小写比较；`toUpperCase` / `toLowerCase` 返回新字符串而不是改原串。
- 循环里大量拼接不要用 `+=`，应使用 `StringBuilder`；它的 `append`、`reverse`、`insert`、`toString` 是常用操作。
- `split` 的参数是正则表达式；`replace` 按字面量替换，`replaceAll` 按正则替换。
- `Character` 工具方法：`isLetter`、`isDigit`、`isLetterOrDigit`、`isUpperCase`、`isLowerCase`、`toLowerCase`。
- 遍历字符串两种方式：`for` + `charAt(i)`，或 `toCharArray()` 后用 for-each。
- 双指针适合处理回文、反转这类「从两端向中间」的问题。
- 用 `HashMap` 统计字符：`getOrDefault(c, 0) + 1` 可以避免第一次取值时的空指针。
- `HashMap` 本身无序，要排序必须先把 `entrySet` 复制到 `List` 再调用 `sort`。
- 打印数组要用 `Arrays.toString(arr)`，直接拼接只会得到 `[I@1b6d3586` 这样的地址。
- 易错点一：用 `==` 比较两个字符串的内容，应改用 `equals`。
- 易错点二：`substring(begin, end)` 的 `end` 取不到，以及下标越界会抛 `StringIndexOutOfBoundsException`。
- 易错点三：以为对 `HashMap` 调用 `sort` 就能排序，实际必须转成 `List`；降序写成 `Integer.compare(a, b)` 会变成升序。

## 练习题

| 编号 | 文件 | 难度 | 一句话要求 | 提示 |
| --- | --- | --- | --- | --- |
| Ex01 | `src/ch09_string/Ex01Palindrome.java` | ★★ | 用双指针判断回文，忽略大小写、空格和标点 | `Character.isLetterOrDigit` 跳过无效字符；`Character.toLowerCase`；跳过时记得 `continue`；空串直接返回 true |
| Ex02 | `src/ch09_string/Ex02CharCount.java` | ★★★ | 用 HashMap 统计字符次数，并按次数降序、同次数按字符升序打印 | `getOrDefault(c, 0) + 1`；`new ArrayList<>(map.entrySet())`；`List.sort` + `Integer.compare(b, a)` / `Character.compare` |
| Ex03 | `src/ch09_string/Ex03StringApi.java` | ★★ | 三种方式反转字符串，并统计大写/小写/数字/空格个数 | `new StringBuilder(s).reverse()`；`toCharArray` + 首尾交换；递归出口 `length() <= 1`；`Character.isUpperCase` 等 |

## 进阶挑战

1. 实现一个简易的「字符频率排行榜」：读入一段英文，忽略大小写和标点，输出出现次数前 5 的字母及其占比（保留两位小数），并处理「不足 5 种字母」和「空输入」的情况。
2. 实现 `String compress(String s)`：把 `"aaabbc"` 压缩成 `"a3b2c1"`，若压缩后反而更长就返回原串；再写一个 `decompress` 还原，思考如何用 `StringBuilder` 和 `Character.isDigit` 处理多位数字。

## 怎么运行

1. 在 IntelliJ IDEA 中打开本项目，展开 `src/ch09_string`，打开任意 `ExNN_xxx.java`。
2. 点击 `main` 方法左侧的绿色三角运行，控制台会先打印实际结果，再逐条输出「通过 / 未通过」。
3. 参考答案在 `solutions/ch09_string` 目录下的同名文件中，重点看双指针的边界处理、比较器的写法、递归的出口。
4. 也可以在项目根目录执行 `javac -encoding UTF-8 -d out src/ch09_string/*.java`，再执行 `java -cp out ch09_string.Ex01Palindrome`。
