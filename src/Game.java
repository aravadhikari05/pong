import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Game {

    public static final int PADDLE_HEIGHT = 50;
    public static final int PADDLE_WIDTH = 5;
    public static final int BALL_RADIUS = 5;

    private static final int PADDLE_SPEED = 10;

    private final int p1X = 50;
    private final int p2X = Canvas.WINDOW_WIDTH - 50;

    private int p1Y, p2Y, ballX, ballY, ballXSpeed, ballYSpeed, ballXDir, ballYDir, p1Score, p2Score;

    private ArrayList<Integer> activeKeys;

    public Game() {
        p1Y = Canvas.WINDOW_HEIGHT/2 - PADDLE_HEIGHT / 2;
        p2Y = Canvas.WINDOW_HEIGHT/2 - PADDLE_HEIGHT / 2;
        reset();
        activeKeys = new ArrayList<>();
        ballXSpeed = 5;
        p1Score = 0;
        p2Score = 0;
    }

    public void process() {
        updatePaddle();
        updateBall();
        checkBounds();
        bounceBall();
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
        if(ballX > Canvas.WINDOW_WIDTH) {
            p1Score++;
            reset();
        }
        if (ballX < 0) {
            p2Score++;
            reset();
        }
    }

    private void bounceBall() {
        //hits paddle 1
        if(ballX == p1X + PADDLE_WIDTH && (ballY > p1Y - BALL_RADIUS*2 && ballY < (p1Y + PADDLE_HEIGHT-1))) {
            ballXDir = 1;
        }
        //hits paddle 2
        if(ballX + BALL_RADIUS*2 == p2X && (ballY > p2Y - BALL_RADIUS*2 && ballY < (p2Y + PADDLE_HEIGHT-1))) {
            ballXDir = -1;
        }
        if(ballY <= 0) {
            ballYDir = 1;
        }
        if(ballY >= Canvas.WINDOW_HEIGHT - BALL_RADIUS*2) {
            ballYDir = -1;
        }

    }

    private void reset() {
        ballX = Canvas.WINDOW_WIDTH/2 - BALL_RADIUS;
        ballY = Canvas.WINDOW_HEIGHT/2 - BALL_RADIUS;
        ballXDir = 1;
        ballYDir = 1;
    }

    private void updateBall() {
        ballX += ballXDir * ballXSpeed;
        ballY += 10 * ballYDir;
    }

    private void updatePaddle() {
        for (int key : activeKeys) {
            switch (key) {
                case KeyEvent.VK_UP:
                    p2Y -= PADDLE_SPEED;
                    break;
                case KeyEvent.VK_DOWN:
                    p2Y += PADDLE_SPEED;
                    break;
                case KeyEvent.VK_W:
                    p1Y -= PADDLE_SPEED;
                    break;
                case KeyEvent.VK_S:
                    p1Y += PADDLE_SPEED;
                    break;
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

    public int getP1Score() {
        return p1Score;
    }

    public int getP2Score() {
        return p2Score;
    }
}
