package ink.flybird.fcommon.query;

public interface ObjectQueryCallback<T,E> {
    T query(E target,String value);
}
