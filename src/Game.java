import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Game {

    public static final int PADDLE_HEIGHT = 25;
    public static final int PADDLE_WIDTH = 5;
    public static final int BALL_RADIUS = 5;

    private static final int PADDLE_SPEED = 10;
    private static final int BALL_X_SPEED = 20;

    private final int p1X = 50;
    private final int p2X = Canvas.WINDOW_WIDTH - 50;


    private int p1Y, p2Y, ballX, ballY, p1Dir, p2Dir, ballYSpeed, ballXDir;

    private ArrayList<Integer> activeKeys;

    public Game() {
        p1Y = Canvas.WINDOW_HEIGHT/2 - PADDLE_HEIGHT / 2;
        p2Y = Canvas.WINDOW_HEIGHT/2 - PADDLE_HEIGHT / 2;
        ballX = Canvas.WINDOW_WIDTH/2 - BALL_RADIUS;
        ballY = Canvas.WINDOW_HEIGHT/2 - BALL_RADIUS;
        ballXDir = 1;
        activeKeys = new ArrayList<>();
    }

    public void process() {
        System.out.println(activeKeys.toString());
        checkPaddle();
        p1Y += p1Dir * PADDLE_SPEED;
        p2Y += p2Dir * PADDLE_SPEED;

        p1Dir = p2Dir = 0;
        checkBounds();
    }
    private void checkBounds() {
        if (p1Y < 0) {
            p1Y = 0;
        } else if (p1Y > Canvas.WINDOW_HEIGHT - PADDLE_HEIGHT) {
            p1Y = Canvas.WINDOW_HEIGHT - PADDLE_HEIGHT;
        }
        if (p2Y < 0) {
            p2Y = 0;
        } else if (p2Y > Canvas.WINDOW_HEIGHT - PADDLE_HEIGHT) {
            p2Y = Canvas.WINDOW_HEIGHT - PADDLE_HEIGHT;
        }
    }

    private void moveBall() {

    }

    private void checkPaddle() {
        for (int key : activeKeys) {
            switch (key) {
                case KeyEvent.VK_UP -> p2Dir = -1;
                case KeyEvent.VK_DOWN -> p2Dir = 1;
                case KeyEvent.VK_W -> p1Dir = -1;
                case KeyEvent.VK_S -> p1Dir = 1;
            }
        }
    }


    public class Input extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if(!activeKeys.contains(key)) activeKeys.add(key);

        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            if (activeKeys.contains(key))  {
                activeKeys.remove((Integer) key);
            }
        }
    }

    public int getP1X() {
        return p1X;
    }

    public int getP2X() {
        return p2X;
    }

    public int getP1Y() {
        return p1Y;
    }

    public int getP2Y() {
        return p2Y;
    }

    public int getBallX() {
        return ballX;
    }

    public int getBallY() {
        return ballY;
    }
}
