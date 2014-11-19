package fire;

import com.sun.awt.AWTUtilities;
import java.awt.*;
import static java.awt.Color.ORANGE;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author bp12214
 */
public class Engine extends Canvas implements Runnable, KeyListener {

    private JTextField tf;
    private int x;
    private int y;
    private int s;
    private int l, l2;
    private int c;
    private int c2;
    private BufferStrategy strategy;
    private JFrame ventana;
    private float angdeg =0.0f;
    private int w,h,iw,ih;

    public Engine() {
        this.setBackground(Color.black);
        this.setForeground(Color.green);
        this.addKeyListener(this);
        x = 400;
        y = 300;
        s = 5;
        l = 22;
        l2 = 12;
        c = -10;
        c2 = 0;



        createWindow();
        createBufferStrategy(2);
        strategy = getBufferStrategy();
        this.requestFocus();

    }

    private void createWindow() {
        ventana = new JFrame("Flight 666");
         w = ventana.getWidth();
         h = ventana.getHeight();
         
         
            iw = w > 3 ? w/3 : 1;
        ih = h > 3 ? h/3 : 1;
         
        this.setSize(ventana.getWidth(), ventana.getHeight());
        //ventana.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        AWTUtilities.setWindowOpacity(ventana, 0.7777F);
        JPanel panel = (JPanel) ventana.getContentPane();
        setBounds(0, 0, hslStage.WIDTHs, hslStage.HEIGHTs);
        panel.setPreferredSize(new Dimension(hslStage.WIDTHs, hslStage.HEIGHTs));
        panel.setLayout(null);
        panel.add(this);
        ventana.setBounds(0, 0, hslStage.WIDTHs, hslStage.HEIGHTs);
       // ventana.setUndecorated(true);
       // ventana.setResizable(false);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
       
    }
    public void draw() {

        Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.WHITE);


 //       g.drawString("Hudson Schumaker", 300, 128);
//        g.fillRect(100, 100, 50, 200);
//        g.fillRect(151, 120, 50, 180);
//        g.fillRect(202, 110, 50, 190);
        g.setColor(Color.BLUE);
        g.setStroke(new BasicStroke(s));
//        g.drawLine(90, 130, 500, 130);
//        g.drawLine(90, 150, 500, 150);
//        g.drawLine(90, 170, 500, 170);
//        g.drawLine(90, 190, 500, 190);



        // g.fillRect(x, y, 10, 20);
        g.fillRect(x - 15, y + 20, 40, 20);
       
        
        
//        g.fillRect(100,100,200, 50);
//        g.setColor(Color.orange);
//        g.fillRect(100,100,c2, 50);

        g.setColor(Color.WHITE);
        g.setStroke(new BasicStroke(l));
        g.drawArc(500, 300, 123, 123, -20, 360);

        
        if (c < 0) {
            g.setColor(Color.RED);
        }
        if (c > 0) {
            g.setColor(Color.BLUE);
        }

        
        
        g.setStroke(new BasicStroke(l2));
        g.drawArc(510, 310, 100, 100, -20, c);
        //   g.fillRect(x, y+40, 10, 20);
        
        
        g.rotate(Math.toRadians(angdeg)/1.5,w/8,h/8);
           g.translate(w/2-iw/2,h/2-ih/2);
        
        
        g.setColor(ORANGE);
        g.fillRect(300, 300, 50, 50);
       
        
        strategy.show();

    }

    public void start() {
        Thread t = new Thread(this);
        t.setPriority(10);
        t.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                draw();
                Thread.sleep(50);
                angdeg++;
//                if(c>=180){
//                    c=c-6;
//                }
//                if(c<180 && c>=0){
//                   c = c-6;  
//                }
//               
//                c2 =  c2+3;
            } catch (InterruptedException e) {
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            y = y - s;
            c++;
            c2--;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            y = y + s;
            c--;
            c2++;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            x = x - s;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            x = x + s;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
