/**
 * Created by yuliang on 15-5-23.
 * 当操作两个list（一个是线程安全，另一个是非线程安全）的时候，非线程安全list的add（）方法就会存在问题，导致有时候add()操作会
 * 失败，或者多个线程之间的add()操作并没有完全执行成功。
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ThreadSafeDemo {
    public static int demo(final List list, final int testCount) throws InterruptedException {
        ThreadGroup group = new ThreadGroup(list.getClass().getName() + "@" + list.hashCode());
        final Random rand = new Random();

        Runnable listAppender = new Runnable() {
            public void run() {
                try {
                    Thread.sleep(rand.nextInt(2));
                } catch (InterruptedException e) {
                    return;
                }
                list.add("0");
            }
        };

        for (int i = 0; i < testCount; i++) {
            new Thread(group, listAppender, "InsertList-" + i).start();
        }

        while (group.activeCount() > 0) {
            Thread.sleep(10);
        }

        return list.size();
    }
    public static void main(String[] args) throws InterruptedException {
        List unsafeList = new ArrayList();
        List safeList = Collections.synchronizedList(new ArrayList());
        final int N = 10000;
        for (int i = 0; i < 10; i++) {
            unsafeList.clear();
            safeList.clear();
            int unsafeSize = demo(unsafeList, N);
            int safeSize = demo(safeList, N);
            System.out.println("unsafe/safe: " + unsafeSize + "/" + safeSize);
        }
    }
}

// Output will be something like this:
// unsafe/safe: 9999/10000
// unsafe/safe: 9997/10000
// unsafe/safe: 10000/10000
// unsafe/safe: 10000/10000
// unsafe/safe: 9999/10000
// unsafe/safe: 9999/10000
// unsafe/safe: 10000/10000
// unsafe/safe: 9998/10000
// unsafe/safe: 10000/10000
// unsafe/safe: 10000/10000


