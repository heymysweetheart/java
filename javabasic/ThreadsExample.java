/**
 * Created by yuliang on 15-5-23.
 * Add a comment
 */
public class ThreadsExample {
    public static void main(String[] args) {
        MyThread first = new MyThread("First");
        MyThread second = new MyThread("Second");
        first.start();
        second.start();
    }
}

/**
 *A Thread class whose run() method will sleep for 10 milliseconds after print something, so that the currently executing thread will
 *give up the cpu to sleep.
 */
class MyThread extends Thread{
    private String name;

    public MyThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Thread " + name + " is running.");
            try {
                Thread.sleep(10);  //Force the currently executing thread to sleep for the specified number of milliseconds, so that other threads could
                //obtain cpu to start to run.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
