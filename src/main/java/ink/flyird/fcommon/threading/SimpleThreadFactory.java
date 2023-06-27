package ink.flyird.fcommon.threading;

import java.util.concurrent.ThreadFactory;

public class SimpleThreadFactory implements ThreadFactory {
    private final String name;
    private final boolean daemon;

    public SimpleThreadFactory(String name, boolean daemon) {
        this.name = name;
        this.daemon = daemon;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t=new Thread(r);
        t.setName(name);
        t.setDaemon(daemon);
        return t;
    }
}
