package myLaba;

public class N_2 {

    public static void main (String[] args) {
        My pott0 = new My();
        SimpleGUI hi0 = new SimpleGUI();
        pott0.Set(hi0);
        pott0.start();


    }
}

class My extends Thread {

    private SimpleGUI hi0;
    private Drawing hi1;
    private int i;

    public void run () {
        if (this.i == 0) {
                this.hi0.setVisible(true);
            try{
                sleep(4000);
            }catch(InterruptedException e){}
        }
        if (this.i == 1)
                this.hi1.setVisible(true);

    }

    public void Set(SimpleGUI hi0) {
        this.hi0 = hi0;
        this.i = 0;
    }

    public void Set(Drawing hi1) {
        this.hi1 = hi1;
        this.i = 1;
    }

}

