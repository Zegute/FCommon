package ink.flybird.fcommon.logging;

enum LogType {
    INFO("info"),
    WARN("warn"),
    ERROR("error"),
    EXCEPTION("exception");

    final String desc;

    LogType(String desc) {
        this.desc = desc;
    }
}
