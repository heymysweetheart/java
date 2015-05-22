import java.util.Calendar;

/**
 * Created by yuliang on 15-5-22.
 * This class is to prove merge Strings using StringBuffer is much faster than using String.
 */


public class StringAndStringBufferTest {

    /*
        This method is to merge String array using String class.
     */
    static String merge(String[] strings, String seperator, boolean seperatorAsTail) {
        if (strings == null) {
            return null;
        }

        if (seperator == null) {
            seperator = "";
        }

        String result = new String();

        for (int i = 0; i < strings.length - 1; i++) {
            result += (strings[i] + seperator);
        }

        result += strings[strings.length - 1];

        if (seperatorAsTail) {
            result += seperator;
        }
        return result;
    }

    /*
    This method is to merge String array using StringBuffer class.
     */
    static String mergeBuffer(String[] strings, String seperator, boolean seperatorAsTail) {
        if (strings == null) {
            return null;
        }

        if (seperator == null) {
            seperator = "";
        }

        StringBuffer result = new StringBuffer();

        for (int i = 0; i < strings.length - 1; i++) {
            result.append(strings[i]).append(seperator);
        }

        result.append(strings[strings.length-1]);
        if (seperatorAsTail) {
            result.append(seperator);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(StringAndStringBufferTest.merge(new String[] {"hello", "world", "nice", "to", "meet", "you"}, "&", false));
        System.out.println(StringAndStringBufferTest.mergeBuffer(new String[]{"hello", "world", "nice", "to", "meet", "you"}, "&", false));

        final int stringLength = 10000;
        final String separator = System.getProperty("line.separator");
        String[] strings = new String[stringLength];

        for (int i = 0; i < stringLength; i++) {
            strings[i] = Integer.toBinaryString(i);
        }

        long startTime = 0;
        long endTime = 0;

        for (int i = 0; i < 5; i++) {

            startTime = Calendar.getInstance().getTimeInMillis();
            System.out.println("merge by String starttime:" + startTime);
            StringAndStringBufferTest.merge(strings, "_", false);
            endTime = Calendar.getInstance().getTimeInMillis();
            System.out.println("merge time:" + (endTime - startTime));
            System.out.println("merge by String endtime:" + endTime);

            startTime = Calendar.getInstance().getTimeInMillis();
            System.out.println("merge by StringBuffer starttime:" + startTime);
            StringAndStringBufferTest.mergeBuffer(strings, "_", false);
            endTime = Calendar.getInstance().getTimeInMillis();
            System.out.println("merge time:" + (endTime - startTime));
            System.out.println("merge by StringBuffer endtime:" + endTime);
        }
    }
}
