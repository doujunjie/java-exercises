package ch11_exception;

/**
 * 练习 2：自定义异常
 *
 * 【要求】
 * 1. 补全同文件里的非 public 类 AgeException extends Exception：提供带 message 的构造器
 * 2. 实现 void validateAge(int age) throws AgeException：年龄小于 0 或大于 150 时抛出 AgeException，
 *    异常消息里带上非法年龄
 * 3. main 分别 try-catch 演示合法年龄与非法年龄两种情况
 *
 * 【示例输出】
 * 年龄 0：校验通过
 * 年龄 20：校验通过
 * 年龄 150：校验通过
 * 年龄 -1：已抛出 AgeException，消息 = 年龄不合法：-1，合法范围是 0 到 150
 * 年龄 151：已抛出 AgeException，消息 = 年龄不合法：151，合法范围是 0 到 150
 *
 * 【知识点】
 * 受检异常、自定义异常、throw 与 throws 的区别、异常消息的传递
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
     * 提示：用 if 判断 age &lt; 0 || age &gt; 150，然后 throw new AgeException("年龄不合法：" + age)。
     * 注意：抛出的是受检异常，方法签名上必须写 throws AgeException。
     *
     * @param age 待校验的年龄
     * @throws AgeException 年龄小于 0 或大于 150 时抛出
     */
    public void validateAge(int age) throws AgeException {
        // TODO 实现...
    }

    /**
     * 自定义受检异常：年龄不合法时使用。
     * 辅助类写在同一文件里（非 public），直接使用即可。
     */
    static class AgeException extends Exception {

        private static final long serialVersionUID = 1L;

        /**
         * 构造器：把异常消息交给父类 Exception 保存，之后可以通过 getMessage() 取出。
         *
         * @param message 异常消息
         */
        public AgeException(String message) {
            // TODO 调用父类构造器，把 message 传进去
        }
    }
}
