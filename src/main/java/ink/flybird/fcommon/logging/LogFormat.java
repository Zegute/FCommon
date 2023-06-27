package ink.flybird.fcommon.logging;

import ink.flybird.fcommon.ColorUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

record LogFormat(
        String info,
        String warn,
        String error,
        String exception,
        String timeFormat,
        String fileFormat
) {
    public String format(String source, String msg, LogType type, Date date,long tid){
        SimpleDateFormat formatter = new SimpleDateFormat(timeFormat);
        String selectedTemplete=switch (type){
            case INFO -> info;
            case WARN -> warn;
            case ERROR -> error;
            case EXCEPTION -> exception;
        };
        return selectedTemplete
                .replace("{time}",formatter.format(date))
                .replace("{source}",source)
                .replace("{msg}",msg)
                .replace("{default}", ColorUtil.DEFAULT)
                .replace("{black}", ColorUtil.BLACK)
                .replace("{red}",ColorUtil.RED)
                .replace("{green}",ColorUtil.GREEN)
                .replace("{yellow}", ColorUtil.YELLOW)
                .replace("{blue}",ColorUtil.BLUE)
                .replace("{purple}",ColorUtil.PURPLE)
                .replace("{cyan}",ColorUtil.CYAN)
                .replace("{white}",ColorUtil.WHITE)
                .replace("{thread}",String.valueOf(tid));
    }

    public String formatWriting(String source, String msg, LogType type, Date date,long tid){
        SimpleDateFormat formatter = new SimpleDateFormat(timeFormat);
        String selectedTemplate=switch (type){
            case INFO -> info;
            case WARN -> warn;
            case ERROR -> error;
            case EXCEPTION -> exception;
        };
        return selectedTemplate
                .replace("{type}",type.desc)
                .replace("{time}",formatter.format(date))
                .replace("{source}",source)
                .replace("{msg}",msg)
                .replace("{default}", "")
                .replace("{black}", "")
                .replace("{red}","")
                .replace("{green}","")
                .replace("{yellow}","")
                .replace("{blue}","")
                .replace("{purple}","")
                .replace("{cyan}","")
                .replace("{white}","")
                .replace("{thread}",String.valueOf(tid));
    }
}
