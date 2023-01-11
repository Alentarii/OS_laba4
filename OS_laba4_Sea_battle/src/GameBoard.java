import java.util.HashMap;
import java.util.Map;

public class GameBoard {

    public static final int COUNTSHIPINPLACE = 3;

    private int livingTheShip = COUNTSHIPINPLACE;

    private char[] graphics = new char[32];
    private char[] graphic = new char[16];

    private Map<Integer, Ship> addressLivingTheShip = new HashMap<>();

    public void start(String name) {
        setPlaceShips();
        drawBoard(name);
    }

    public void drawBoard(String name) {
        set_gr(name);
        System.out.println(
                graphics[0] + " | " + graphics[1] + " | " + graphics[2] + " | " + graphics[3] + "   #   " + graphics[16] + " | " + graphics[17] + " | " + graphics[18] + " | " + graphics[19] + "\n" +
                        "---" + "+" + "---" + "+" + "--" + "+" + "---" + "   #   " + "---" + "+" + "---" + "+" + "--" + "+" + "---" + "\n" +
                        graphics[4] + " | " + graphics[5] + " | " + graphics[6] + " | " + graphics[7] + "   #   " + graphics[20] + " | " + graphics[21] + " | " + graphics[22] + " | " + graphics[23] + "\n" +
                        "---" + "+" + "---" + "+" + "--" + "+" + "---" + "   #   " + "---" + "+" + "---" + "+" + "--" + "+" + "---" + "\n" +
                        graphics[8] + " | " + graphics[9] + " | " + graphics[10] + " | " + graphics[11] + "   #   " + graphics[24] + " | " + graphics[25] + " | " + graphics[26] + " | " + graphics[27] + "\n" +
                        "---" + "+" + "---" + "+" + "--" + "+" + "---" + "   #   " + "---" + "+" + "---" + "+" + "--" + "+" + "---" + "\n" +
                        graphics[12] + " | " + graphics[13] + " | " + graphics[14] + " | " + graphics[15] + "   #   " + graphics[28] + " | " + graphics[29] + " | " + graphics[30] + " | " + graphics[31] + "\n"
        );
    }

    public int getLivingTheShip() {
        return livingTheShip;
    }

    public char[] getGraphic() {
        return graphic;
    }

    public void set_gr(String name) {
        if (name == "1")
            System.arraycopy(this.graphic, 0, this.graphics, 0, 16);
        else
            System.arraycopy(this.graphic, 0, this.graphics, 16, 16);
    }

    private void setSecondDeckShipOnBoard() {
        SecondDeckShip ship = new SecondDeckShip();

        int[] location = new int[2];
        location[0] = rnd(graphic.length - 1);

        if (location[0] % 3 == 0) {
            location[1] = location[0] + 1;
        } else if ((location[0] + 1) % 3 == 0) {
            location[1] = location[0] - 1;
        } else {
            if (1 == rnd(1)) {
                location[1] = location[0] + 1;
            } else {
                location[1] = location[0] - 1;
            }
        }

        for (var point :location) {
            addressLivingTheShip.put(point, ship);
        }
    }

    private void setSingleDeckShipOnBoard() {
        int freePoint;

        for(int i = 0; i < 2; i++) {
            freePoint = getFreePoint();
            addressLivingTheShip.put(freePoint, new SingleDeckShip());
        }
    }

    private int getFreePoint(){
        int point = rnd(graphic.length - 1);

        for (var ship :addressLivingTheShip.entrySet()) {
            if (point == ship.getKey()) {
                point = getFreePoint();
            }
        }
        return point;
    }

    public void setPlaceShips() {
        setSecondDeckShipOnBoard();
        setSingleDeckShipOnBoard();
    }

    public Map<Integer, Ship> getAddressLivingTheShip() {
        return addressLivingTheShip;
    }

    private class SingleDeckShip implements Ship {

        @Override
        public void hitTheShip(int point, String name) {
            graphic[point] = 'X';
            killTheShip(point);
            drawBoard(name);
        }

        private void killTheShip(int point) {
            addressLivingTheShip.remove(point);
            livingTheShip--;
        }
    }

    private class SecondDeckShip implements Ship {

        boolean[] lives = {true, true};

        @Override
        public void hitTheShip(int point, String name) {
            if (lives[0] && lives[1]) {
                System.out.println("Вы подбили двухпалубный корабль!");
                lives[0] = false;
                graphic[point] = 'X';
                addressLivingTheShip.remove(point);
            } else killTheShip(point);
            drawBoard(name);
        }

        private void killTheShip(int point) {
            graphic[point] = 'X';
            addressLivingTheShip.remove(point);
            livingTheShip--;
        }
    }

    public static int rnd(int max)
    {
        return (int) (Math.random() * ++max);
    }
}
