package myLaba;

import java.util.concurrent.Semaphore;

public class N_3 {

    public static void main (String[] args) throws InterruptedException {
        Semaphore petrol = new Semaphore(4);
        int n = 10;
        Car[] car = new Car[100];
        for (int i = 0; i < n; i++) {
            Thread.sleep((long) ((Math.random() * 2 + 1) * 1000));
            car[i] = new Car();
            car[i].petrol = petrol;
            car[i].i = i;
            car[i].start();
        }
        Thread.sleep(((n + 8) * 1000));
        int summ = 0;
        for (int j = 0; j < n; j++) {
            if (car[j].i == -1)
                summ++;
        }
        System.out.println("Проехало мимо: " + summ);
    }

}

class Car extends Thread {
    Semaphore petrol;
    int i = 0;

    public void run() {
        try {
            if (petrol.availablePermits() > 0) {
                petrol.acquire();
                System.out.println(this.getName() + "Eat.....");
                sleep((long) ((Math.random() * 4 + 1) * 1000));
                sleep(8000);
                System.out.println(this.getName() + "GO");
                petrol.release();
            } else {
                this.i = -1;
                System.out.println(this.getName() + "GO.....");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}