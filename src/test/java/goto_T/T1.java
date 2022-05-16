package goto_T;

public class T1 {
    public static void main(String[] args) {
        int i = 20;
        int j = 10;
        retry:
        for (;;) {
            i-=2;
            for (;;) {
                if (j-- > 0) {
                    System.out.printf("i:%d =  %d * 2 \n", i, j);
                    continue retry;
                }else {
                    break retry;
                }

            }
        }
        System.out.println("end");
    }

}
