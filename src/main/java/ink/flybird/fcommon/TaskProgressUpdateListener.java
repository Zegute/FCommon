package ink.flybird.fcommon;

public interface TaskProgressUpdateListener {
    void onProgressChange(int prog);
    void onProgressStageChanged(String newStage);
    default void refreshScreen(){}
}
