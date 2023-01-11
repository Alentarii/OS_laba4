import java.util.Collections;

public class Main {
    static MyList list = new MyList();
    static Thread thread = new Thread(list);
    public static void main(String[] args) {
            thread.start();
            startThrow(list);
    }

    public static void  startThrow(MyList list) {
        Thread thread1 = new Thread(new Runnable()
        {
            public void run()
            {
                int i = 0;
                while(i < 10) {
                    list.sorting();
                    i++;
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                System.exit (1);
            }
        });
        thread1.start();
    }

    static synchronized public void sorting(MyList list) {
        Collections.sort(list.myList);

        for (var num: list.myList) {
            System.out.print(" " + num);
        }
    }
}