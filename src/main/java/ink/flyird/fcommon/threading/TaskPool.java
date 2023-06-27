package ink.flyird.fcommon.threading;

import io.flybird.util.container.ArrayQueue;

import java.util.ArrayList;
import java.util.Comparator;

public class TaskPool {
    private final ArrayQueue<Task> queue = new ArrayQueue<>();

    private final ArrayList<Task> tasks = new ArrayList<>();
    private final ArrayList<Task> removingList = new ArrayList<>();

    private final ArrayList<Daemon> daemons = new ArrayList<>();


    public TaskPool(int threads, String prefix) {
        for (int i = 0; i < threads; i++) {
            Daemon t = new Daemon(this.queue, tasks, removingList);
            t.setName(prefix + i);
            this.daemons.add(t);
        }
    }

    public ArrayQueue<? extends Task> getQueue() {
        return queue;
    }

    public ArrayList<Daemon> getDaemons() {
        return daemons;
    }

    public void submit(Task task) {
        this.queue.add(task);
    }

    public void setPoolSorter(Comparator<Task> comparator) {
        for (Daemon d : this.daemons) {
            d.setPoolSorter(comparator);
        }
    }

    public static class Daemon extends Thread {
        private final ArrayList<Task> tasks;
        private final ArrayList<Task> removingList;



        private Comparator<Task> poolSorter;

        public Daemon(ArrayQueue<Task> queue, ArrayList<Task> tasks, ArrayList<Task> removingList) {
            this.tasks = tasks;
            this.removingList = removingList;
            this.setDaemon(true);
            this.setName("wait_task_daemon");
            this.start();
        }

        @Override
        public void run() {
            /*
            while (true) {
                ArrayList<Task> list=new ArrayList<>(this.tasks);
                ArrayList<String> list = null;
                if (this.poolSorter != null) {
                    items.sort(this.poolSorter);
                }
                for (Task t : items) {
                    t.runTask();
                }
                for (String s : this.cache.keySet()) {
                    if (this.cache.get(s).isFinishAble()) {
                        list.add(s);
                    }
                }
                for (String s : list) {
                    this.cache.remove(s);
                }
                while (this.queue.size() > 0) {
                    Task task = this.queue.poll();
                    if (task == null) {
                        break;
                    }
                    this.cache.put(String.valueOf(task.hashCode()), task);
                }
                Thread.yield();
            }

             */
        }

        public void setPoolSorter(Comparator<? extends Task> poolSorter) {
            this.poolSorter = (Comparator<Task>) poolSorter;
        }
    }
}
