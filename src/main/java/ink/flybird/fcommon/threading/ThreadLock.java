package ink.flybird.fcommon.threading;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadLock {
    private final AtomicInteger counter = new AtomicInteger();

    public void addLock() {
        this.counter.incrementAndGet();
    }

    public void removeLock() {
        this.counter.decrementAndGet();
    }

    public boolean isLocked() {
        return this.counter.get() > 0;
    }

    public int getLockCounter() {
        return this.counter.get();
    }
}
