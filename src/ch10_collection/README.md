# 第10章 集合框架

## 本章知识点

- `List` 有序、可重复：`ArrayList` 底层是数组，随机访问快，中间插入删除要挪动元素；`LinkedList` 底层是双向链表，头尾增删快、随机访问慢。
- `ArrayList` 的常用方法：`add(E)` 追加、`add(int, E)` 插入、`get(int)` 取、`set(int, E)` 改、`remove(int)` 按索引删、`remove(Object)` 按元素删、`size()` 求大小。
- 三种遍历方式：普通 `for` 配 `get(i)`（需要下标时用）、增强 `for`（只读时最简洁）、`Iterator`（遍历中要删除元素时必须用）。
- `Set` 元素不重复：`HashSet` 无序、`LinkedHashSet` 保留插入顺序、`TreeSet` 按自然顺序（或 `Comparator`）排序。
- `Set` 去重依赖 `hashCode()` 与 `equals()`（`TreeSet` 用 `compareTo`），自定义对象要一起重写，否则去重失效。
- `Map` 存键值对：`put` 返回旧值，`get` 取不到返回 `null`，`getOrDefault` 可以给默认值，`containsKey` 判断键是否存在。
- 词频统计的惯用写法：`map.put(word, map.getOrDefault(word, 0) + 1)`，或者 `map.merge(word, 1, Integer::sum)`。
- `HashMap` 无序、`LinkedHashMap` 保留插入顺序、`TreeMap` 按键排序；`HashMap` 允许一个 `null` 键和多个 `null` 值。
- 给 `Map` 排序要先转成 `List<Map.Entry<K, V>>` 再 `sort`，直接对 `keySet()` 排序不会改变 `Map` 的遍历顺序。
- 遍历中删除元素：`list.remove(...)` 会触发 fail-fast 抛 `ConcurrentModificationException`，应改用 `Iterator.remove()` 或 `removeIf`。
- 易错点一：`List.of(...)` 返回不可变集合，对它 `add` / `remove` 会抛 `UnsupportedOperationException`，要可变就用 `new ArrayList<>(List.of(...))`。
- 易错点二：`List<Integer>` 上写 `remove(1)` 删的是下标 1，删元素 1 要写 `remove(Integer.valueOf(1))`，两个重载很容易混。
- 易错点三：`TreeSet` / `TreeMap` 的键不能为 `null`（要比较排序），`HashSet` / `HashMap` 可以存一个 `null`。
- 易错点四：`"apple"` 和 `"Apple"` 是不同元素，`Set` 去重区分大小写；需要忽略大小写时用 `TreeSet<>(String.CASE_INSENSITIVE_ORDER)`。

## 练习题

| 编号 | 文件 | 难度 | 一句话要求 | 提示 |
| --- | --- | --- | --- | --- |
| Ex01 | `src/ch10_collection/Ex01ListCrud.java` | ★ | 用 ArrayList 完成增删改查，用三种方式遍历，并演示遍历中删除的坑 | `add` / `get` / `set` / `remove` / `size`；删除用 `Iterator.remove()`，增强 for 直接删会抛 `ConcurrentModificationException` |
| Ex02 | `src/ch10_collection/Ex02SetDedup.java` | ★★ | 分别用 HashSet、LinkedHashSet、TreeSet 给单词数组去重并对比顺序 | 三个构造器都能接收 `Arrays.asList(words)`；`HashSet` 无序、`LinkedHashSet` 按插入顺序、`TreeSet` 按字典序 |
| Ex03 | `src/ch10_collection/Ex03MapWordCount.java` | ★★★ | 统计一段英文文本的词频，并按次数降序打印前 n 个单词 | `split("\\s+")` 拆分；`getOrDefault(word, 0) + 1` 累加；`entrySet()` 转 `List` 后用 `Comparator` 先比 value 再比 key |

## 进阶挑战

1. 学生成绩单：用 `Map<String, List<Integer>>` 保存每个学生的多门成绩，统计每个人的总分与平均分，再按平均分从高到低输出排名；要求用 `computeIfAbsent` 简化分组代码。
2. 英文文章分析器：读入一段文本，统计词频并输出前 10 名，同时统计每个单词首次出现的位置；再实现一个忽略大小写与标点符号的版本，比较两个版本的结果差异。

## 怎么运行

1. 在 IntelliJ IDEA 中打开本项目，进入 `src/ch10_collection` 目录。
2. 打开任意练习文件，点击 `main` 方法左侧的绿色三角，选择 Run 即可运行自检，控制台最后会打印「通过」或「未通过（期望 ...，实际 ...）」。
3. 参考答案在 `solutions/ch10_collection` 目录下的同名文件中，重点看遍历时删除的正确写法、三种 Set 的顺序差异以及 `Map` 排序的思路。
4. 也可以在项目根目录执行 `javac -encoding UTF-8 -d out src/ch10_collection/*.java`，再执行 `java -cp out ch10_collection.Ex01ListCrud`。
