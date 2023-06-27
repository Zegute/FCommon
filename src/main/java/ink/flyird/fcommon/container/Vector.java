package ink.flyird.fcommon.container;

public class Vector<T> {
    private T t;

    public Vector(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }

    public void set(T t) {
        this.t = t;
    }
}
