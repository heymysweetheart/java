/**
 * Created by yuliang on 15-5-23.
 */
public class StringTest {
    public static void main(String[] args) {
        String first = new String("first");
        String second = new String("second");
        second = first;
        System.out.println(first);
        System.out.println(second);

    }
}
