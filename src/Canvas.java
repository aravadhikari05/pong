import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Canvas extends JPanel {
    public static final int WINDOW_WIDTH = 600;
    public static final int WINDOW_HEIGHT = 400;
    public static final int INTERVAL = 100;

    private Game game;
    private java.util.Timer timer;

    public Canvas() {
        game = new Game();
        initPanel();
        initTimer();
    }

    private void initPanel() {
        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        setBackground(Color.BLACK);
        addKeyListener(game. new Input());
        setFocusable(true);
    }

    private void initTimer() {
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                game.process();
                repaint();
            }
        };
        timer.scheduleAtFixedRate(task, 0, INTERVAL);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;


        g2d.setColor(Color.WHITE);
        g2d.fillRect(game.getP1X(), game.getP1Y(), Game.PADDLE_WIDTH, Game.PADDLE_HEIGHT);
        g2d.fillRect(game.getP2X(), game.getP2Y(), Game.PADDLE_WIDTH, Game.PADDLE_HEIGHT);
        g2d.fillRect(game.getBallX(), game.getBallY(), Game.BALL_RADIUS * 2, Game.BALL_RADIUS * 2);

    }
}
