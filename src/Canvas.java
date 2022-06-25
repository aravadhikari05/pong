import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Canvas extends JPanel {
    public static final int WINDOW_WIDTH = 600;
    public static final int WINDOW_HEIGHT = 400;
    public static final int INTERVAL = 30;

    private Game game;
    private java.util.Timer timer;

    public Canvas() {
        game = new Game();
        initPanel();
        initTimer();
    }
    //Creates Panel
    private void initPanel() {
        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        setBackground(Color.BLACK);
        addKeyListener(game. new Input());
        setFocusable(true);
    }
    //Creates Timer
    private void initTimer() {
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                game.process();
                repaint();
            }
        };
        timer.scheduleAtFixedRate(task, 3000, INTERVAL);
    }
    //Updates Graphics
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        //Draw
        g2d.setColor(Color.WHITE);
        //Game Over
        if(game.isGameOver()){
            g2d.setFont(new Font("Courier", Font.PLAIN, 72));
            g2d.drawString("Game Over", WINDOW_WIDTH/2 - g.getFontMetrics().stringWidth("Game Over")/2, 200);
            g2d.setFont(new Font("Courier", Font.PLAIN, 24));

            g2d.drawString("Press Any Button to Start Over", 90, 250);
        } else {
            //paddle
            g2d.fillRect(game.getP1X(), game.getP1Y(), Game.PADDLE_WIDTH, Game.PADDLE_HEIGHT);
            g2d.fillRect(game.getP2X(), game.getP2Y(), Game.PADDLE_WIDTH, Game.PADDLE_HEIGHT);
            //ball
            g2d.fillRect(game.getBallX(), game.getBallY(), Game.BALL_RADIUS * 2, Game.BALL_RADIUS * 2);
        }

        //dashed lines
        for(int i = 0; i < WINDOW_HEIGHT / 20; i++) {
            g2d.fillRect(WINDOW_WIDTH/2-2,i * (20)+2, 4, 15);
        }
        //score
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setFont(new Font("Courier", Font.PLAIN, 36));
        g2d.drawString(""+game.getP1Score(), WINDOW_WIDTH/4 - g.getFontMetrics().stringWidth(""+game.getP1Score())/2, 50);
        g2d.drawString(""+game.getP2Score(), WINDOW_WIDTH/4*3 - g.getFontMetrics().stringWidth(""+game.getP2Score())/2, 50);



    }
}
