package ink.flyird.fcommon.logging;


public interface Logger {
    String name();

    LoggerContext context();

    void info(String str, Object... args);

    void warn(String str, Object... args);

    void error(String str, Object... args);

    void exception(String msg, Object... args);

    void error(Error e);

    void exception(Exception e);
}
