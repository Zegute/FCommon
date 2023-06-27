package ink.flyird.fcommon.query;

public interface ObjectQueryCallback<T,E> {
    T query(E target,String value);
}
