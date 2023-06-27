package ink.flybird.fcommon.event;



import ink.flybird.fcommon.TaskProgressUpdateListener;
import ink.flybird.fcommon.container.Pair;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

/**
 * use a cache for method and listener to advance speed.<br/>
 * - takes more mem use for higher speed.<br/>
 * - recommend for more listeners,or strict delay<br/>
 *
 * @author GrassBlock2022
 */
public class CachedEventBus implements EventBus {
    //event type/[handler/handle method]
    private final HashMap<Class<Event>, HashMap<String, Pair<Object, Method>>> map = new HashMap<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void callEvent(Event event, TaskProgressUpdateListener listener, String... subscribedArg) {
        HashMap<String, Pair<Object, Method>> list = this.map.get(event.getClass());
        if (list != null) {
            int i = 0;
            for (Pair<?, ?> node : new ArrayList<>(list.values())) {
                Method m = (Method) node.t2();
                SubscribedEvent subscribedEvent = m.getAnnotation(SubscribedEvent.class);
                if (subscribedEvent != null) {
                    if (subscribedArg.length == 0) {
                        continue;
                    }
                    if (!Objects.equals(subscribedArg[0], subscribedEvent.value())) {
                        continue;
                    }
                }
                i++;
                try {
                    if (listener != null) {
                        listener.onProgressChange(i / list.size());
                        listener.refreshScreen();
                    }

                    m.invoke(node.t1(), event);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void registerEventListener(EventListener el) {
        Method[] ms = el.getClass().getMethods();
        for (Method m : ms) {
            if (Arrays.stream(m.getAnnotations()).anyMatch(annotation -> annotation instanceof EventHandler)) {
                Class<Event> type = (Class<Event>) m.getParameterTypes()[0];
                this.map.computeIfAbsent(type, k -> new HashMap<>(128));
                this.map.get(type).put(el.hashCode() + "/" + m.getName(), new Pair<>(el, m));
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void unregisterEventListener(EventListener el) {
        Method[] ms = el.getClass().getMethods();
        for (Method m : ms) {
            if (Arrays.stream(m.getAnnotations()).anyMatch(annotation -> annotation instanceof EventHandler)) {
                Class<Event> type = (Class<Event>) m.getParameterTypes()[0];
                this.map.get(type).remove(el.hashCode() + "/" + m.getName());
            }
        }
    }
}
