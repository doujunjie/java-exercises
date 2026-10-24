package solutions.ch11_exception;

/**
 * 练习 1 参考答案：try-catch 基本用法
 *
 * 思路：
 * 1. 把可能出错的代码放进 try，catch 只写能处理的异常类型。
 * 2. 除数为 0 时整数除法抛 ArithmeticException；下标越界抛 ArrayIndexOutOfBoundsException。
 * 3. finally 无论 try 是否抛异常都会执行，常用来释放资源。
 *
 * 多个 catch 与 finally 的执行顺序：
 * [1] try 之前
 * [2] try 内部
 * [3] 匹配到的 catch（NumberFormatException）
 * [4] finally
 * [5] try 语句之后的代码
 * 如果 try 里没有异常，则跳过所有 catch，直接执行 finally，再执行后续代码。
 *
 * 易错点：
 * 1. catch 从上往下匹配，父类异常（Exception）必须写在子类异常后面，否则编译报错。
 * 2. finally 里写 return 会覆盖 try 里的 return，还会吞掉异常，实际开发中要避免。
 * 3. 只有匹配到类型的异常才会被捕获，类型不匹配时异常继续向上抛。
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
     */
    public int divide(int a, int b) {
        try {
            return a / b;
        } catch (ArithmeticException e) {
            System.out.println("    [捕获 ArithmeticException] " + e.getMessage());
            return -1;
        }
    }

    /**
     * 按下标取数组元素，下标越界时捕获 ArrayIndexOutOfBoundsException 并返回 -1。
     */
    public int getElement(int[] arr, int index) {
        try {
            return arr[index];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("    [捕获 ArrayIndexOutOfBoundsException] 下标 " + index
                    + " 超出范围，数组长度 " + arr.length);
            return -1;
        }
    }

    /**
     * 演示 try / 多个 catch / finally 的执行顺序。
     */
    public void demoOrder() {
        System.out.println("[1] try 之前");
        try {
            System.out.println("[2] try 内部：准备解析 \"abc\"");
            int value = Integer.parseInt("abc");
            // 上一行抛异常，下面这行不会执行
            System.out.println("不会执行到这里：" + value);
        } catch (NumberFormatException e) {
            System.out.println("[3] 捕获 NumberFormatException：" + e.getMessage());
        } catch (RuntimeException e) {
            // 父类异常的 catch 必须放在子类后面，这里只是演示匹配顺序
            System.out.println("[3] 捕获 RuntimeException：" + e.getMessage());
        } finally {
            System.out.println("[4] finally 一定执行");
        }
        System.out.println("[5] try 语句之后");
    }
}
