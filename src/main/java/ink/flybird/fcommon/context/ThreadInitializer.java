package ink.flybird.fcommon.context;

public class ThreadInitializer {
    private final DynamicURLClassLoader sharedClassLoader;

    public ThreadInitializer(DynamicURLClassLoader sharedClassLoader) {
        this.sharedClassLoader = sharedClassLoader;
    }

    public DynamicURLClassLoader getSharedClassLoader() {
        return sharedClassLoader;
    }

    public Thread makeThread(String name, Runnable runnable){
        Thread t=new Thread(runnable, name);
        t.setContextClassLoader(this.sharedClassLoader);

        return t;
    }
}
