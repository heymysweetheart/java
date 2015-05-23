/**
 * Created by yuliang on 15-5-23.
 */
import java.util.Random;

public class StringBufferVSStringBuilder {
    public static int demo(final Object stringJoiner, final int testCount) throws InterruptedException {
        ThreadGroup group = new ThreadGroup(stringJoiner.getClass().getName() + "@" + stringJoiner.hashCode());
        final Random rand = new Random();

        Runnable listAppender = new Runnable() {
            public void run() {
                try {
                    Thread.sleep(rand.nextInt(2));
                } catch (InterruptedException e) {
                    return;
                }
                if (stringJoiner instanceof StringBuffer) {
                    ((StringBuffer)stringJoiner).append("0");
                } else if (stringJoiner instanceof StringBuilder) {
                    ((StringBuilder)stringJoiner).append("0");
                }
            }
        };

        for (int i = 0; i < testCount; i++) {
            new Thread(group, listAppender, "InsertList-" + i).start();
        }

        while (group.activeCount() > 0) {
            Thread.sleep(10);
        }

        return stringJoiner.toString().length();
    }

    public static void main(String[] args) throws InterruptedException {
        StringBuilder stringBuilder = new StringBuilder();
        StringBuffer stringBuffer = new StringBuffer();
        final int N = 10000;
        for (int i = 0; i < 10; i++) {
            stringBuilder.delete(0, stringBuilder.length());
            stringBuffer.delete(0, stringBuffer.length());
            int builderLength = demo(stringBuilder, N);
            int bufferLength = demo(stringBuffer, N);
            System.out.println("StringBuilder/StringBuffer: " + builderLength + "/" + bufferLength);
        }
    }
}


// Output will be something like this:
// StringBuilder/StringBuffer: 9995/10000
// StringBuilder/StringBuffer: 9996/10000
// StringBuilder/StringBuffer: 9998/10000
// StringBuilder/StringBuffer: 9997/10000
// StringBuilder/StringBuffer: 9995/10000
// StringBuilder/StringBuffer: 9996/10000
// StringBuilder/StringBuffer: 9998/10000
// StringBuilder/StringBuffer: 9998/10000
// StringBuilder/StringBuffer: 9999/10000
// StringBuilder/StringBuffer: 9999/10000
