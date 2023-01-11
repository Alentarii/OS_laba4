package myLaba;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class N_1 {

    public static void main (String[] args) throws IOException{
        FileReader fileReader = new FileReader("D:\\OSLABA_4\\OS_laba4\\src\\resources\\N_1");
        Path path = Path.of("D:\\OSLABA_4\\OS_laba4\\src\\resources\\N_1");
        fileReader.close();
        String list = Files.readAllLines(path).toString();
        list = list.substring(1, list.length()-1);
        MyThread pott0 = new MyThread();
        pott0.Set(list);
        MyThread pott1 = new MyThread();
        pott1.Set(list);

        pott0.start();
        while (pott0.isAlive()){}
        pott1.start();
    }

}

class MyThread extends Thread {

    private String list;

    public void run () {
        for(int i = 0; i < this.list.length(); i++)
        {
            try{
                sleep(1000);		//Приостанавливает поток на 1 секунду
            }catch(InterruptedException e){}

            System.out.println(this.list.charAt(i));
        }
    }

    public void Set(String list) {
        this.list = list;
    }
}
