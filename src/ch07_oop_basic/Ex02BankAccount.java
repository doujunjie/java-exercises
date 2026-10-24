package ch07_oop_basic;

/**
 * 练习 2：银行账户（封装）
 *
 * 【要求】
 * 1. 定义私有字段 owner（户主）、balance（余额）
 * 2. 构造方法：BankAccount(String owner, double initialBalance)
 * 3. deposit(double money)：金额必须大于 0，否则抛 IllegalArgumentException
 * 4. withdraw(double money)：金额必须大于 0；余额不足时打印提示并返回 false，
 *    余额充足时扣款并返回 true
 * 5. getBalance() 返回当前余额，外部不能直接改 balance
 *
 * 【示例结果】
 * 开户余额 1000.0
 * 存入 500.0 后余额 1500.0
 * 取出 200.0 成功，余额 1300.0
 * 取出 10000.0 失败（余额不足），余额仍为 1300.0
 * 自检全部「通过」
 *
 * 【知识点】封装（private 字段 + 公开方法）、this、参数校验、方法返回值、double 运算
 *
 * 说明：BankAccount 与 Ex02BankAccount 写在同一个文件里，只有 Ex02BankAccount 是 public。
 */
public class Ex02BankAccount {

    public static void main(String[] args) {
        BankAccount account = new BankAccount("张三", 1000.0);

        System.out.println("---- 开户 ----");
        System.out.println("户主：" + account.getOwner() + "，余额：" + account.getBalance());
        System.out.println("---- 存取款流水 ----");

        account.deposit(500.0);
        System.out.println("存入 500.0，余额：" + account.getBalance());

        boolean first = account.withdraw(200.0);
        System.out.println("取出 200.0，结果：" + first + "，余额：" + account.getBalance());

        boolean second = account.withdraw(10000.0);
        System.out.println("取出 10000.0，结果：" + second + "，余额：" + account.getBalance());

        System.out.println("---- 自检 ----");
        check("户主", "张三", account.getOwner());
        checkDouble("最终余额", 1300.0, account.getBalance());
        check("余额充足时 withdraw 返回 true", Boolean.TRUE, first);
        check("余额不足时 withdraw 返回 false", Boolean.FALSE, second);

        // 封装：下面这行在外部是编译不过的，因为 balance 是 private
        // account.balance = 999999.0;
        // 想改余额只能走 deposit / withdraw，金额校验才不会被绕过。

        try {
            account.deposit(-100.0);
            System.out.println("未通过：deposit(-100.0) 校验（期望 抛出 IllegalArgumentException，实际 没有抛异常）");
        } catch (IllegalArgumentException e) {
            System.out.println("通过：deposit(-100.0) 校验");
        } catch (RuntimeException e) {
            System.out.println("未通过：deposit(-100.0) 校验（期望 IllegalArgumentException，实际 "
                    + e.getClass().getSimpleName() + "）");
        }

        try {
            account.withdraw(0.0);
            System.out.println("未通过：withdraw(0.0) 校验（期望 抛出 IllegalArgumentException，实际 没有抛异常）");
        } catch (IllegalArgumentException e) {
            System.out.println("通过：withdraw(0.0) 校验");
        } catch (RuntimeException e) {
            System.out.println("未通过：withdraw(0.0) 校验（期望 IllegalArgumentException，实际 "
                    + e.getClass().getSimpleName() + "）");
        }
    }

    /** 比较期望值与实际值，打印「通过」或「未通过（期望 X，实际 Y）」。 */
    private static void check(String label, Object expected, Object actual) {
        boolean ok = expected == null ? actual == null : expected.equals(actual);
        if (ok) {
            System.out.println("通过：" + label);
        } else {
            System.out.println("未通过：" + label + "（期望 " + expected + "，实际 " + actual + "）");
        }
    }

    /** 金额是 double，不要用 == 判断相等，允许 1e-9 的误差。 */
    private static void checkDouble(String label, double expected, double actual) {
        if (Math.abs(expected - actual) < 1e-9) {
            System.out.println("通过：" + label);
        } else {
            System.out.println("未通过：" + label + "（期望 " + expected + "，实际 " + actual + "）");
        }
    }
}

/**
 * 银行账户：把余额藏在 private 字段里，只暴露「存款」「取款」「查余额」三个入口。
 */
class BankAccount {

    private String owner;
    private double balance;

    public BankAccount(String owner, double initialBalance) {
        // TODO 实现：保存户主，把初始余额赋给 balance
    }

    public String getOwner() {
        // TODO 实现：返回 owner
        return null;
    }

    public double getBalance() {
        // TODO 实现：返回 balance
        return 0.0;
    }

    /**
     * 存款。
     *
     * @param money 存款金额，必须大于 0
     * @throws IllegalArgumentException 金额小于等于 0 时抛出
     */
    public void deposit(double money) {
        // TODO 实现：校验 money > 0，合法则 balance += money
    }

    /**
     * 取款。
     *
     * @param money 取款金额，必须大于 0
     * @return 取款成功返回 true；余额不足打印提示并返回 false
     * @throws IllegalArgumentException 金额小于等于 0 时抛出
     */
    public boolean withdraw(double money) {
        // TODO 实现：先校验金额，再判断余额是否充足
        return false;
    }
}
