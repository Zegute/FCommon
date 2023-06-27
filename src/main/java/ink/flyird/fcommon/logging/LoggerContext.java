package ink.flyird.fcommon.logging;


import io.flybird.util.container.Vector;

public interface LoggerContext {
    Vector<LoggerContext> SHARED_CONTEXT = new Vector<>(new DefaultLoggerContext(System.getProperty("user.dir") + "/log.log"));

    static LoggerContext getSharedContext() {
        return SHARED_CONTEXT.get();
    }

    static void setSharedContext(LoggerContext context) {
        SHARED_CONTEXT.set(context);
    }

    Logger createLogger(String name);

    void allSave();

    void log(String msg, String source, LogType type);

    LogFormat getLogFormat();

    void setLogFormat(LogFormat format);
}
