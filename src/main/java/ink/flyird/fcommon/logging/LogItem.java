package ink.flyird.fcommon.logging;

import java.util.Date;

record LogItem(String message, LogType type, long time, String source,LoggerContext context,long tid) implements Comparable<LogItem> {
    @Override
    public int compareTo(LogItem o) {
        return o.time >= this.time ? 1 : -1;
    }

    public String getFormattedMassage() {
        Date date = new Date(time);
        return this.context.getLogFormat().format(source, message, type, date,tid);
    }

    public String getOutputMessage() {
        Date date = new Date(time);
        return this.context.getLogFormat().formatWriting(source, message, type, date,tid);
    }
}
