package ch11_exception;

/**
 * 练习 1：try-catch 基本用法
 *
 * 【要求】
 * 1. 实现 int divide(int a, int b)：用 try-catch 捕获 ArithmeticException，发生异常时返回 -1
 * 2. 实现 int getElement(int[] arr, int index)：用 try-catch 捕获 ArrayIndexOutOfBoundsException，
 *    发生异常时返回 -1
 * 3. 实现 void demoOrder()：演示「try / 多个 catch / finally」的执行顺序，每一步打印标记，
 *    并在方法注释里写出输出顺序
 *
 * 【示例输出】
 * 10 / 2 = 5
 * 10 / 0 = -1
 * numbers[1] = 20
 * numbers[5] = -1
 * [1] try 之前
 * [2] try 内部：准备解析 "abc"
 * [3] 捕获 NumberFormatException：For input string: "abc"
 * [4] finally 一定执行
 * [5] try 语句之后
 *
 * 【知识点】
 * 受检异常与运行时异常、try-catch、多个 catch 的匹配顺序、finally 必定执行
 */
public class Ex01TryCatch {

    public static void main(String[] args) {
        Ex01TryCatch exercise = new Ex01TryCatch();

        System.out.println("10 / 2 = " + exercise.divide(10, 2));
        System.out.println("10 / 0 = " + exercise.divide(10, 0));
        System.out.println();

        int[] numbers = {10, 20, 30};
        System.out.println("numbers[1] = " + exercise.getElement(numbers, 1));
        System.out.println("numbers[5] = " + exercise.getElement(numbers, 5));
        System.out.println();

        System.out.println("===== try / 多个 catch / finally 的执行顺序 =====");
        exercise.demoOrder();
        System.out.println();

        System.out.println("===== 自检 =====");
        int normal = exercise.divide(10, 2);
        int divideByZero = exercise.divide(10, 0);
        int inRange = exercise.getElement(numbers, 1);
        int outOfRange = exercise.getElement(numbers, 5);

        boolean pass = normal == 5 && divideByZero == -1 && inRange == 20 && outOfRange == -1;
        if (pass) {
            System.out.println("通过");
        } else {
            System.out.println("未通过（期望 divide(10,2)=5、divide(10,0)=-1、getElement 越界返回 -1，"
                    + "实际 " + normal + "、" + divideByZero + "、" + inRange + "、" + outOfRange + "）");
        }
    }

    /**
     * 整数除法，除数为 0 时捕获 ArithmeticException 并返回 -1。
     * 提示：try 里写 return a / b，catch (ArithmeticException e) 里写 return -1。
     *
     * @param a 被除数
     * @param b 除数
     * @return 商，除数为 0 时返回 -1
     */
    public int divide(int a, int b) {
        // TODO 实现...
        return 0;
    }

    /**
     * 按下标取数组元素，下标越界时捕获 ArrayIndexOutOfBoundsException 并返回 -1。
     * 提示：arr 为 null 的情况不在本练习范围内，调用方保证非 null。
     *
     * @param arr   数组
     * @param index 下标
     * @return 元素值，下标越界时返回 -1
     */
    public int getElement(int[] arr, int index) {
        // TODO 实现...
        return 0;
    }

    /**
     * 演示 try / 多个 catch / finally 的执行顺序。
     * 提示：try 里执行 Integer.parseInt("abc") 触发 NumberFormatException，
     * 先写 catch (NumberFormatException e)，再写 catch (RuntimeException e)，
     * 最后写 finally。每一步用 [1] [2] ... 打印标记。
     *
     * 注意：多个 catch 从上往下匹配，子类异常必须写在父类异常前面，
     * 否则子类的 catch 永远轮不到，编译会报错。
     */
    public void demoOrder() {
        // TODO 实现：打印 [1] 到 [5] 五个标记，展示 try -> catch -> finally -> 后续代码 的顺序
    }
}
