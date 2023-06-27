package ink.flybird.fcommon.timer;

public class TimeCounter {
    long start;
    long end;

    public void startTiming(){
        start=System.currentTimeMillis();
    }

    public void stop(){
        end=System.currentTimeMillis();
    }

    public long getStartTime() {
        return start;
    }

    public long getEndTime() {
        return end;
    }

    public long getPassedTime(){
        return end-start;
    }
}
