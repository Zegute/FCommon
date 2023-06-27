package ink.flyird.fcommon.threading;

public abstract class Task {
    public static final TaskPool POOL = new TaskPool(4, "task_shared_pool");
    protected boolean finishAble = false;

    public static void submit(Task task) {
        POOL.submit(task);
    }

    protected void runTask() {
        this.run();
    }

    public abstract void run();

    public boolean isFinishAble() {
        return finishAble;
    }

    public void finish() {
        this.finishAble = true;
    }
}
