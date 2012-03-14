import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Samegame
{
  static JFrame f;
  static boolean noscore;
  static Steine feld;

  public static void main(String[] paramArrayOfString)
  {
    f = new JFrame("Samegame");
    f.setDefaultCloseOperation(3);
    f.setSize(600, 320);

    Dimension localDimension = Toolkit.getDefaultToolkit().getScreenSize();

    f.setLocation((localDimension.width - 600) / 2, (localDimension.height - 320) / 2);

    start();

    f.setVisible(true);

    f.addMouseListener(new MouseListener()
    {
      public void mouseReleased(MouseEvent paramMouseEvent) {
        Samegame.feld.delete(paramMouseEvent.getX() / Samegame.feld.res, (paramMouseEvent.getY() - 20) / Samegame.feld.res);
        Samegame.feld.repaint();
        if (Samegame.feld.machbar == 0) {
          if (!Samegame.noscore)
            SaveScore.save(200 - Samegame.feld.rest(), "Samegame");
          else
            JOptionPane.showMessageDialog(Samegame.f, Samegame.feld.rest() + " left.");
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
    f.addKeyListener(new KeyListener() {
      public void keyTyped(KeyEvent paramKeyEvent) {
        int i = paramKeyEvent.getKeyChar();
        if (i == 99) {
          Samegame.feld.farbWechsel();
          Samegame.feld.repaint();
        }
      }

      public void keyPressed(KeyEvent paramKeyEvent) {
      }

      public void keyReleased(KeyEvent paramKeyEvent) {
      } } );
  }

  public static void ende() {
    f.setVisible(false);
    start();
  }

  public static void start() {
    f.setVisible(false);
    if (feld != null)
      f.getContentPane().remove(feld);
    feld = new Steine();
    feld.init();
    f.getContentPane().add(feld);
    f.setVisible(true);
  }
}