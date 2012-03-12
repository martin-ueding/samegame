import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;

public class Samegame
{
  static JFrame f;

  public static void main(String[] paramArrayOfString)
  {
    start();
  }

  public static void ende() {
    f.setVisible(false);
    start();
  }

  public static void start() {
    Steine localSteine = new Steine();
    localSteine.init();

    f = new JFrame("Samegame");
    f.setDefaultCloseOperation(3);
    f.setSize(600, 320);

    Dimension localDimension = Toolkit.getDefaultToolkit().getScreenSize();

    f.setLocation((localDimension.width - 600) / 2, (localDimension.height - 320) / 2);

    f.getContentPane().add(localSteine);

    f.setVisible(true);

    f.addMouseListener(new MouseListener(localSteine)
    {
      public void mouseReleased(MouseEvent paramMouseEvent) {
        this.val$feld.delete(paramMouseEvent.getX() / this.val$feld.res, (paramMouseEvent.getY() - 20) / this.val$feld.res);
        this.val$feld.repaint();
        if (this.val$feld.machbar == 0) {
          SaveScore.save(200 - this.val$feld.rest(), "Samegame");
          Samegame.ende();
        }
      }

      public void mouseClicked(MouseEvent paramMouseEvent)
      {
      }

      public void mouseEntered(MouseEvent paramMouseEvent)
      {
      }

      public void mouseExited(MouseEvent paramMouseEvent)
      {
      }

      public void mousePressed(MouseEvent paramMouseEvent)
      {
      }
    });
    f.addKeyListener(new KeyListener(localSteine) {
      public void keyTyped(KeyEvent paramKeyEvent) {
        int i = paramKeyEvent.getKeyChar();
        if (i == 99) {
          this.val$feld.farbWechsel();
          this.val$feld.repaint();
        }
      }

      public void keyPressed(KeyEvent paramKeyEvent)
      {
      }

      public void keyReleased(KeyEvent paramKeyEvent)
      {
      }
    });
  }
}
