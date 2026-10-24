package solutions.ch11_exception;

/**
 * 练习 3 参考答案：try-with-resources 资源自动关闭
 *
 * 思路：
 * 1. 只要类实现了 AutoCloseable（或 Closeable），就能写在 try 的圆括号里；
 *    离开 try 块时 JVM 会自动调用 close()，不需要手写 finally。
 * 2. 传统写法要在 finally 里手动 close()，而且还要判断资源是否为 null。
 * 3. Java 9 之后可以把已经声明好的变量直接写进 try 括号，前提是变量 effectively final。
 *
 * 为什么 try-with-resources 更好：
 * 1. 代码更短：不用写 finally，也不会忘记关闭。
 * 2. 多个资源按「声明顺序的逆序」自动关闭，符合栈式释放的直觉。
 * 3. 异常抑制：close() 抛异常时，如果 try 里也抛了异常，业务异常不会被关闭异常覆盖，
 *    关闭异常会被加到业务异常的 suppressed 列表里，用 e.getSuppressed() 可以取到。
 *    传统 finally 里如果 close() 抛异常，就会覆盖掉原来的异常，排查问题更困难。
 *
 * 易错点：
 * 1. try 括号里的资源类型必须实现 AutoCloseable，否则编译不通过。
 * 2. 在 try 里 return 时，是先把返回值算好，再执行 close()，所以 close() 里的副作用在 return 之后才体现。
 * 3. 自己 new 了资源却没用 try-with-resources，又忘记 close，会造成资源泄漏。
 */
public class Ex03TryWithResources {

    public static void main(String[] args) {
        Ex03TryWithResources exercise = new Ex03TryWithResources();

        System.out.println("===== 方式一：try-with-resources =====");
        boolean tryWithResourcesClosed = exercise.useTryWithResources();
        System.out.println("资源是否已关闭：" + tryWithResourcesClosed);
        System.out.println();

        System.out.println("===== 方式二：传统 try-finally =====");
        boolean tryFinallyClosed = exercise.useTryFinally();
        System.out.println("资源是否已关闭：" + tryFinallyClosed);
        System.out.println();

        System.out.println("===== 自检 =====");
        boolean pass = tryWithResourcesClosed && tryFinallyClosed;
        if (pass) {
            System.out.println("通过");
        } else {
            System.out.println("未通过（期望两种写法结束时资源都已关闭，实际 try-with-resources = "
                    + tryWithResourcesClosed + "，try-finally = " + tryFinallyClosed + "）");
        }
    }

    /**
     * 用 try-with-resources 使用资源，返回资源是否已被关闭。
     */
    public boolean useTryWithResources() {
        // 需要在 try 块外检查关闭状态，所以先声明；Java 9+ 允许把 effectively final 变量直接放进 try 括号
        MyResource resource = new MyResource("资源A");
        try (resource) {
            resource.work();
        }
        // 走到这里 close() 已经自动执行
        return resource.isClosed();
    }

    /**
     * 用传统 try-finally 使用资源，返回资源是否已被关闭。
     */
    public boolean useTryFinally() {
        MyResource resource = new MyResource("资源B");
        try {
            resource.work();
        } finally {
            // 必须手动关闭；如果 try 里抛异常，finally 依然会执行
            resource.close();
        }
        return resource.isClosed();
    }

    /**
     * 辅助类：模拟一个需要手动关闭的资源，已给出，无需修改。
     */
    static class MyResource implements AutoCloseable {

        private final String name;

        private boolean closed;

        MyResource(String name) {
            this.name = name;
            System.out.println("打开资源：" + name);
        }

        void work() {
            System.out.println("使用资源：" + name);
        }

        @Override
        public void close() {
            closed = true;
            System.out.println("关闭资源：" + name);
        }

        boolean isClosed() {
            return closed;
        }
    }
}
