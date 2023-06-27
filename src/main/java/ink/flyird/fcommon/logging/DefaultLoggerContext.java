package ink.flyird.fcommon.logging;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class DefaultLoggerContext implements LoggerContext {
    private static LogFormat DEFAULT_FORMAT = new LogFormat(
            "[{time}] [thread-{thread}] [{cyan}I{default}] {source} : {msg}",
            "[{time}] [thread-{thread}]  [{yellow}W{default}] {source} : {msg}",
            "[{time}] [thread-{thread}]  [{red}E{default}] {source} : {msg}",
            "[{time}] [thread-{thread}] [{red}E{default}] {source} : {msg}",
            "yyyy-MM-dd HH:mm:ss",
            "yyyy-MM-dd_HH-mm-ss"
    );
    private final ArrayList<LogItem> items = new ArrayList<>();
    private final String file;
    private LogFormat logFormat = DEFAULT_FORMAT;

    public DefaultLoggerContext(String file) {
        this.file = file;
    }


    @Override
    public void log(String msg, String source, LogType type) {
        LogItem item=new LogItem(msg, type, System.currentTimeMillis(), source, this,Thread.currentThread().getId());
        System.out.println(item.getFormattedMassage());
        items.add(item);
    }

    @Override
    public LogFormat getLogFormat() {
        return this.logFormat;
    }

    @Override
    public void setLogFormat(LogFormat format) {
        this.logFormat = format;
    }

    @Override
    public void allSave() {
        ArrayList<LogItem> list = new ArrayList<>(this.items);
        FileWriter writer;
        try {
            writer = new FileWriter(this.file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        list.sort(Comparator.comparingLong(LogItem::time));
        for (LogItem e : list) {
            try {
                writer.write(e.getOutputMessage() + "\n");
                this.items.remove(e);
            } catch (IOException ex) {
                System.out.println("can not write log:" + e);
            }
        }
        try {
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Logger createLogger(String name) {
        return new SimpleLogger(this, name);
    }
}

