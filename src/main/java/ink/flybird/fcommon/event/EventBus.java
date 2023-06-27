package ink.flybird.fcommon.event;


import ink.flybird.fcommon.TaskProgressUpdateListener;

/**
 * abstraction of event bus implementations.<br/>
 */

public interface EventBus {

    /**
     * call event
     *
     * @param event    event
     * @param listener
     */
    void callEvent(Event event, TaskProgressUpdateListener listener, String... subscribedArg);

    /**
     * register a event listener
     *
     * @param el handler
     */
    void registerEventListener(EventListener el);

    /**
     * unregister a event handler
     *
     * @param el handler
     */
    void unregisterEventListener(EventListener el);

    default void callEvent(Event e) {
        this.callEvent(e, null);
    }

    default void callEvent(Event e, String subscribeArg) {
        this.callEvent(e, null, subscribeArg);
    }
}