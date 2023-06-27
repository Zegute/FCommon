package ink.flyird.fcommon.query;

import java.util.HashMap;

public class ObjectQueryHandler<E> {
    private final HashMap<String,ObjectQueryCallback<?,E>>callbacks=new HashMap<>();

    public <T>T query(String value,E target,Class<T> typeOfT){
        ObjectQueryCallback<?,E> callback=this.callbacks.get(value);
        if(callback==null){
            return null;
        }
        return (T) callback.query(target,value);
    }



    public int queryI(String value,E target){
        return 0;
    }
}
