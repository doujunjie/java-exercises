# 第5章 数组

## 本章知识点

- 数组是定长容器，创建时用 `new int[n]` 指定长度，长度通过 `arr.length` 获取，不是方法。
- 下标从 0 开始，合法范围是 `0 ~ arr.length - 1`，越界会抛 `ArrayIndexOutOfBoundsException`。
- 数组元素有默认值：`int` 为 0，`double` 为 0.0，`boolean` 为 false，引用类型为 null。
- `Arrays.toString(arr)` 可以把一维数组转成便于打印的字符串，`Arrays.equals(a, b)` 比较内容是否相同。
- 数组是引用类型，方法传参传的是引用的副本，方法内修改元素会影响调用方看到的数组。
- 增强 for 循环（`for (int x : arr)`）适合只读遍历，循环变量是元素副本，修改它不会改变数组。
- 二维数组可以是不规则数组：先 `new int[rows][]`，再为每一行单独 `new int[行长度]`。
- 常见算法套路：求最值先设第一个元素为临时值；反转用双指针；冒泡排序比较相邻元素。
- 易错点一：空数组直接访问 `arr[0]` 会越界，求最值和平均前要先判断 `arr.length == 0`。
- 易错点二：`sum / arr.length` 是整数除法，求平均值前要把 `sum` 转成 `double`。
- 易错点三：`int[] b = a` 只复制引用，两个变量指向同一个数组；需要独立副本时用 `Arrays.copyOf`。

## 练习题

| 编号 | 文件 | 难度 | 一句话要求 | 提示 |
| --- | --- | --- | --- | --- |
| Ex01 | `src/ch05_arrays/Ex01MaxMinAvg.java` | ★ | 求数组最大值、最小值、平均值，并处理空数组 | 空数组返回 0；临时最值设为 `arr[0]`；平均用 `(double) sum / arr.length` |
| Ex02 | `src/ch05_arrays/Ex02Reverse.java` | ★★ | 分别实现原地反转和返回新数组的反转 | 双指针 `left`、`right` 向中间靠拢；新数组第 `i` 位取原数组第 `length - 1 - i` 位 |
| Ex03 | `src/ch05_arrays/Ex03BubbleSort.java` | ★★★ | 实现带提前结束优化的冒泡排序 | 外层控制轮数，内层比较 `arr[j]` 和 `arr[j + 1]`，用 `swapped` 标志判断是否已有序 |
| Ex04 | `src/ch05_arrays/Ex04YangHui.java` | ★★★ | 生成并打印杨辉三角前 10 行 | 先创建行数组，每行长度为 `i + 1`，首尾为 1，中间等于上一行左上加正上 |

## 进阶挑战

1. 选择排序：每一轮从未排序区间中选出最小元素，与未排序区间的第一个元素交换，思考它与冒泡排序在交换次数上的差异。
2. 二分查找：在有序数组中查找目标值，用 `left`、`right`、`mid` 不断缩小范围，注意 `mid` 的写法和循环结束条件；可再尝试用递归实现。

## 怎么运行

1. 在 IntelliJ IDEA 中打开本项目，进入 `src/ch05_arrays` 目录。
2. 打开任意练习文件，点击 `main` 方法左侧的绿色三角，选择 Run 即可运行自检。
3. 参考答案在 `solutions/ch05_arrays` 目录下的同名文件中，重点看双指针、边界处理和排序优化的注释。
4. 也可以在项目根目录执行 `javac -encoding UTF-8 -d out src/ch05_arrays/*.java`，再执行 `java -cp out ch05_arrays.Ex02Reverse`。
