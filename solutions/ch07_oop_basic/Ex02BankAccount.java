package solutions.ch07_oop_basic;

/**
 * 练习 2：银行账户（参考答案）
 *
 * 思路：
 * 1. owner、balance 都是 private，外界拿不到引用来直接改值，这就是封装的入口控制。
 * 2. 所有对余额的修改都必须经过 deposit / withdraw，校验逻辑无法被绕过。
 * 3. 金额校验放在方法最前面（fail-fast），非法参数立刻抛异常，不会留下「扣了一半」的中间状态。
 * 4. withdraw 用余额比较后再扣款，余额不足直接 return false，不再往下执行。
 *
 * 易错点：
 * - 用 == 比较金额（double），浮点误差会让 0.1 + 0.2 != 0.3，金额比较应使用误差范围。
 * - 先扣款再判断余额，导致余额被扣成负数；顺序必须是「先判断，再扣款」。
 * - withdraw 里忘了写 return，编译报错；余额不足分支和成功分支都要有返回值。
 * - 存款金额校验写成 money < 0，漏掉了 0 的情况。
 * - 初始余额可以为 0，这不算错误，不要顺手把 0 也判成非法。
 *
 * 说明：只有与文件名同名的 Ex02BankAccount 是 public 类。
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
        this.owner = owner;
        this.balance = initialBalance;
    }

    public String getOwner() {
        return owner;
    }

    public double getBalance() {
        // 只提供读方法，不提供 setBalance，外部就没有办法随意改余额
        return balance;
    }

    /**
     * 存款。
     *
     * @param money 存款金额，必须大于 0
     * @throws IllegalArgumentException 金额小于等于 0 时抛出
     */
    public void deposit(double money) {
        if (money <= 0) {
            throw new IllegalArgumentException("存款金额必须大于 0，实际为 " + money);
        }
        balance += money;
    }

    /**
     * 取款。
     *
     * @param money 取款金额，必须大于 0
     * @return 取款成功返回 true；余额不足打印提示并返回 false
     * @throws IllegalArgumentException 金额小于等于 0 时抛出
     */
    public boolean withdraw(double money) {
        if (money <= 0) {
            throw new IllegalArgumentException("取款金额必须大于 0，实际为 " + money);
        }
        if (money > balance) {
            // 余额不足：只提示，不抛异常，因为「钱不够」是业务上可预期的情况
            System.out.println("余额不足，取款失败。当前余额：" + balance + "，本次取款：" + money);
            return false;
        }
        balance -= money;
        return true;
    }
}
