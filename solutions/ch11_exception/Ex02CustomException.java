package solutions.ch11_exception;

/**
 * 练习 2 参考答案：自定义异常
 *
 * 思路：
 * 1. 自定义受检异常继承 Exception（继承 RuntimeException 则是非受检异常）。
 * 2. 构造器里用 super(message) 把消息交给父类保存，getMessage() 才能取到。
 * 3. validateAge 里用 throw 主动抛异常；因为 AgeException 是受检异常，
 *    方法签名必须写 throws AgeException，提醒调用方处理。
 *
 * 易错点：
 * 1. 忘记 super(message)，getMessage() 返回 null，日志里只剩异常类名。
 * 2. throw 和 throws 不是一回事：throw 抛出异常对象，throws 声明方法可能抛出的异常类型。
 * 3. 边界值 0 和 150 是合法的，判断要用 &lt; 0 和 &gt; 150，不能写 &lt;= 0。
 */
public class Ex02CustomException {

    public static void main(String[] args) {
        Ex02CustomException exercise = new Ex02CustomException();

        System.out.println("===== 情况 1：合法年龄 =====");
        boolean validOk = true;
        int[] validAges = {0, 20, 150};
        for (int age : validAges) {
            try {
                exercise.validateAge(age);
                System.out.println("年龄 " + age + "：校验通过");
            } catch (AgeException e) {
                validOk = false;
                System.out.println("年龄 " + age + "：校验失败（本不该抛异常）：" + e.getMessage());
            }
        }

        System.out.println();
        System.out.println("===== 情况 2：非法年龄 =====");
        boolean invalidOk = true;
        int[] invalidAges = {-1, 151};
        for (int age : invalidAges) {
            try {
                exercise.validateAge(age);
                invalidOk = false;
                System.out.println("年龄 " + age + "：未抛出异常（不符合预期）");
            } catch (AgeException e) {
                boolean messageOk = e.getMessage() != null && !e.getMessage().isEmpty();
                invalidOk = invalidOk && messageOk;
                System.out.println("年龄 " + age + "：已抛出 AgeException，消息 = " + e.getMessage());
            }
        }

        System.out.println();
        System.out.println("===== 自检 =====");
        boolean pass = validOk && invalidOk;
        if (pass) {
            System.out.println("通过");
        } else {
            System.out.println("未通过（期望：0、20、150 不抛异常；-1、151 抛出带消息的 AgeException。"
                    + "实际：合法年龄是否全部通过 = " + validOk + "，非法年龄是否全部抛异常 = " + invalidOk + "）");
        }
    }

    /**
     * 校验年龄是否合法，不合法时抛出 AgeException。
     */
    public void validateAge(int age) throws AgeException {
        if (age < 0 || age > 150) {
            throw new AgeException("年龄不合法：" + age + "，合法范围是 0 到 150");
        }
    }

    /**
     * 自定义受检异常：年龄不合法时使用。
     * 辅助类写在同一文件里（非 public），直接使用即可。
     */
    static class AgeException extends Exception {

        private static final long serialVersionUID = 1L;

        /**
         * 构造器：把异常消息交给父类 Exception 保存。
         *
         * @param message 异常消息
         */
        public AgeException(String message) {
            // 父类 Exception 提供 Exception(String message) 构造器
            super(message);
        }
    }
}
