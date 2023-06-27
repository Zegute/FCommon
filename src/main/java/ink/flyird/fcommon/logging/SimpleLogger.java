package ink.flyird.fcommon.logging;

public record SimpleLogger(LoggerContext context, String name) implements Logger {
    public SimpleLogger(String name) {
        this(LoggerContext.getSharedContext(),name);
    }

    public void info(String str, Object... args) {
        this.context.log(str.formatted(args), this.name, LogType.INFO);
    }

    public void warn(String str, Object... args) {
        this.context.log(str.formatted(args), this.name, LogType.WARN);
    }

    public void error(String str, Object... args) {
        this.context.log(str.formatted(args), this.name, LogType.ERROR);
    }

    public void exception(String msg, Object... args) {
        this.context.log(msg.formatted(args), this.name, LogType.EXCEPTION);
    }

    public void error(Error e) {
        this.warn("|> Error Was Found : %s", e.getLocalizedMessage());
        this.error("|> Maybe Cause : %s", e.getCause());
        for (StackTraceElement line : e.getStackTrace())
            this.error(
                    "|- at %s.%s (at line %s from file %s) ",
                    line.getClassName(),
                    line.getMethodName(),
                    line.getLineNumber(),
                    line.getFileName()
            );
    }

    public void exception(Exception e) {
        this.exception("|> Error Was Found : %s", e.getLocalizedMessage());
        this.exception("|> Maybe Cause : %s", e.getCause());
        for (StackTraceElement line : e.getStackTrace())
            this.exception(
                    "|- at %s.%s (at line %s from file %s) ",
                    line.getClassName(),
                    line.getMethodName(),
                    line.getLineNumber(),
                    line.getFileName()
            );
    }
}
