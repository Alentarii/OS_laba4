import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MyList implements Runnable {
    ArrayList<Integer> myList = new ArrayList<>();

    public MyList() {
        myList.add(2);
        myList.add(3);
    }

   synchronized public void addRandomInList() {
        myList.add(rnd(1000));
//        System.out.print("\n");
//        for (var num : myList) {
//            System.out.print(" " + num);
//        }
    }

    synchronized public void delRaindomList() {
        if (myList.size() != 0) {
            int size = myList.size();
            int item = new Random().nextInt(size);
            myList.remove(item);
        } else {
            addRandomInList();
        }
//        System.out.print("\n");
//        for (var num : myList) {
//            System.out.print(" " + num);
//        }
    }

    synchronized public void sorting() {
        Collections.sort(myList);

        System.out.print("\n");
        for (var num : myList) {
            System.out.print(" " + num);
        }
    }

    public static int rnd(int max)
    {
        return (int) (Math.random() * ++max);
    }

    @Override
    public void run() {
        while (true) {
            if (rnd(1) == 1) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                addRandomInList();
            } else {
                delRaindomList();
            }
        }
    }
}
