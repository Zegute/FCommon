package ink.flybird.fcommon.event;


import ink.flybird.fcommon.TaskProgressUpdateListener;
import ink.flybird.fcommon.container.Pair;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Objects;

/**
 * use direct reflect access to listeners.<br/>
 * - takes less speed for lower mem usage<br/>
 * - recommend for fewer listeners,or not-strict delay<br/>
 *
 * @author GrassBlock2022
 */
public class DirectEventBus implements EventBus {
    protected final ArrayList<EventListener> listeners = new ArrayList<>();

    /**
     * {@inheritDoc}
     */
    public void callEvent(Event event, TaskProgressUpdateListener listener, String... subscribedArg) {
        ArrayList<Pair<Method, EventListener>> list = new ArrayList<>();
        for (EventListener el : ((ArrayList<EventListener>) this.listeners.clone())) {
            Method[] ms = el.getClass().getMethods();
            for (Method m : ms) {
                EventHandler eventHandler = m.getAnnotation(EventHandler.class);
                SubscribedEvent subscribedEvent = m.getAnnotation(SubscribedEvent.class);
                if (eventHandler == null) {
                    continue;
                }
                if (subscribedEvent == null) {
                    if (m.getParameters()[0].getType() == event.getClass()) {
                        list.add(new Pair<>(m, el));
                    }
                    continue;
                }
                if (Objects.equals(subscribedEvent.value(), subscribedArg[0])) {
                    list.add(new Pair<>(m, el));
                }
            }
        }

        int i = 0;
        for (Pair<Method, EventListener> pair : list) {
            i++;
            try {
                pair.t1().invoke(pair.t2(), event);
                if (listener == null) {
                    continue;
                }
                listener.onProgressChange(i / list.size());
                listener.refreshScreen();
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void registerEventListener(EventListener el) {
        this.listeners.add(el);
    }

    /**
     * {@inheritDoc}
     */
    public void unregisterEventListener(EventListener el) {
        this.listeners.remove(el);
    }
}
