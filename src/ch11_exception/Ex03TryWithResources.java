package ch11_exception;

/**
 * 练习 3：try-with-resources 资源自动关闭
 *
 * 【要求】
 * 1. 实现 useTryWithResources()：用 try-with-resources 创建并打开 MyResource，调用 work()，
 *    结束后返回资源是否已关闭
 * 2. 实现 useTryFinally()：用传统 try-finally 写一遍同样的逻辑（finally 里手动 close），
 *    同样返回资源是否已关闭
 * 3. 在注释里说明为什么 try-with-resources 更好（自动关闭、多个资源逆序关闭、异常抑制 suppressed）
 *
 * 【示例输出】
 * 打开资源：资源A
 * 使用资源：资源A
 * 关闭资源：资源A
 * 资源是否已关闭：true
 *
 * 【知识点】
 * AutoCloseable、try-with-resources、finally、Throwable.getSuppressed()
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
     * 提示：
     * MyResource resource = new MyResource("资源A");
     * try (resource) { resource.work(); }  // Java 9+ 可以直接写已声明的变量
     * 出了 try 块后 close() 已被自动调用，此时再 return resource.isClosed()。
     *
     * @return 资源是否已关闭
     */
    public boolean useTryWithResources() {
        // TODO 实现...
        return false;
    }

    /**
     * 用传统 try-finally 使用资源，返回资源是否已被关闭。
     * 提示：资源在 try 之前创建，finally 里调用 close()，
     * 这样即使 try 里抛异常也能保证关闭。
     *
     * @return 资源是否已关闭
     */
    public boolean useTryFinally() {
        // TODO 实现...
        return false;
    }

    /**
     * 辅助类：模拟一个需要手动关闭的资源，已给出，无需修改。
     * 实现 AutoCloseable 后就能放在 try-with-resources 的括号里。
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
