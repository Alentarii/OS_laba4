import java.util.HashMap;
import java.util.Map;

public class GameBoard {

    public static final int COUNTSHIPINPLACE = 3;

    private int livingTheShip = COUNTSHIPINPLACE;

    private char[] graphic = new char[16];

    private Map<Integer, Ship> addressLivingTheShip = new HashMap<>();

    public void start() {
        setPlaceShips();
        drawBoard();
    }

    public void drawBoard() {
        System.out.println(
                graphic[0] + " | " + graphic[1] + " | " + graphic[2] + " | " + graphic[3] + "\n" +
                        "---" + "+" + "---" + "+" + "--" + "+" + "---" + "\n" +
                        graphic[4] + " | " + graphic[5] + " | " + graphic[6] + " | " + graphic[7] + "\n" +
                        "---" + "+" + "---" + "+" + "--" + "+" + "---" + "\n" +
                        graphic[8] + " | " + graphic[9] + " | " + graphic[10] + " | " + graphic[11] + "\n" +
                        "---" + "+" + "---" + "+" + "--" + "+" + "---" + "\n" +
                        graphic[12] + " | " + graphic[13] + " | " + graphic[14] + " | " + graphic[15] + "\n"
        );
    }

    public int getCountShip() {
        return livingTheShip;
    }

    public int getLivingTheShip() {
        return livingTheShip;
    }

    public void setLivingTheShip(int livingTheShip) {
        this.livingTheShip = livingTheShip;
    }

    public char[] getGraphic() {
        return graphic;
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
        public void hitTheShip(int point) {
            graphic[point] = 'X';
            killTheShip(point);
            drawBoard();
        }

        private void killTheShip(int point) {
            addressLivingTheShip.remove(point);
            livingTheShip--;
        }
    }

    private class SecondDeckShip implements Ship {

        boolean[] lives = {true, true};

        @Override
        public void hitTheShip(int point) {
            if (lives[0] && lives[1]) {
                System.out.println("Вы подбили двухпалубный корабль!");
                lives[0] = false;
                graphic[point] = 'X';
                addressLivingTheShip.remove(point);
            } else killTheShip(point);
            drawBoard();
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
