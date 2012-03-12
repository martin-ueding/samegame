import java.awt.Color;
import java.awt.Graphics;
import java.io.PrintStream;
import java.util.Random;
import javax.swing.JPanel;

public class Steine extends JPanel
{
  private static final long serialVersionUID = 1L;
  private int[][] steine = new int[20][10];
  private Random r = new Random();
  private int i;
  private int j;
  private int k;
  private boolean bunt = false;
  public int res;
  public int machbar;

  public void farbWechsel()
  {
    this.bunt = (!this.bunt);
  }

  public void init()
  {
    for (this.i = 0; this.i < this.steine[0].length; this.i += 1)
      for (this.j = 0; this.j < this.steine.length; this.j += 1)
        this.steine[this.j][this.i] = (this.r.nextInt(4) + 1);
  }

  public void drucke()
  {
    for (this.i = 0; this.i < this.steine[0].length; this.i += 1) {
      for (this.j = 0; this.j < this.steine.length; this.j += 1) {
        if (this.steine[this.j][this.i] != 0) {
          System.out.print(this.steine[this.j][this.i]);
        }
        else
          System.out.print(" ");
      }
      System.out.println();
    }
    System.out.println();
  }

  protected void paintComponent(Graphics paramGraphics)
  {
    this.res = (getWidth() / this.steine.length);
    this.i = 0; this.j = 0;
    paramGraphics.clearRect(0, 0, getWidth(), getHeight());
    for (this.i = 0; this.i < this.steine[0].length; this.i += 1)
      for (this.j = 0; this.j < this.steine.length; this.j += 1)
        if (this.steine[this.j][this.i] != 0) {
          if (!this.bunt) {
            switch (this.steine[this.j][this.i]) { case 1:
              paramGraphics.setColor(Color.RED); break;
            case 2:
              paramGraphics.setColor(Color.YELLOW); break;
            case 3:
              paramGraphics.setColor(Color.GREEN); break;
            case 4:
              paramGraphics.setColor(Color.BLUE);
            }
          }
          else {
            switch (this.steine[this.j][this.i]) { case 1:
              paramGraphics.setColor(new Color(255, 0, 0)); break;
            case 2:
              paramGraphics.setColor(new Color(255, 63, 0)); break;
            case 3:
              paramGraphics.setColor(new Color(255, 127, 0)); break;
            case 4:
              paramGraphics.setColor(new Color(255, 255, 0));
            }
          }
          paramGraphics.fillRect(this.j * this.res, this.i * this.res, this.res, this.res);
        }
  }

  public void delete(int paramInt1, int paramInt2)
  {
    if ((paramInt1 >= 0) && (paramInt1 < this.steine.length) && (paramInt2 >= 0) && (paramInt2 < this.steine[0].length) && (this.steine[paramInt1][paramInt2] != 0) && (
      ((paramInt1 < this.steine.length - 1) && (this.steine[paramInt1][paramInt2] == this.steine[(paramInt1 + 1)][paramInt2])) || ((paramInt1 > 0) && (this.steine[paramInt1][paramInt2] == this.steine[(paramInt1 - 1)][paramInt2])) || ((paramInt2 < this.steine[0].length - 1) && (this.steine[paramInt1][paramInt2] == this.steine[paramInt1][(paramInt2 + 1)])) || ((paramInt2 > 0) && (this.steine[paramInt1][paramInt2] == this.steine[paramInt1][(paramInt2 - 1)]))))
    {
      nachbarn(paramInt1, paramInt2, this.steine[paramInt1][paramInt2]);

      fallen();
      machbar();
    }
  }

  public int rest()
  {
    int m = 0;
    for (this.i = 0; this.i < this.steine[0].length; this.i += 1) {
      for (this.j = 0; this.j < this.steine.length; this.j += 1) {
        if (this.steine[this.j][this.i] != 0) {
          m++;
        }
      }
    }
    return m;
  }

  private void nachbarn(int paramInt1, int paramInt2, int paramInt3)
  {
    this.steine[paramInt1][paramInt2] = 0;
    if ((paramInt1 < this.steine.length - 1) && (this.steine[(paramInt1 + 1)][paramInt2] == paramInt3)) {
      nachbarn(paramInt1 + 1, paramInt2, paramInt3);
    }
    if ((paramInt1 > 0) && (this.steine[(paramInt1 - 1)][paramInt2] == paramInt3)) {
      nachbarn(paramInt1 - 1, paramInt2, paramInt3);
    }
    if ((paramInt2 < this.steine[0].length - 1) && (this.steine[paramInt1][(paramInt2 + 1)] == paramInt3)) {
      nachbarn(paramInt1, paramInt2 + 1, paramInt3);
    }
    if ((paramInt2 > 0) && (this.steine[paramInt1][(paramInt2 - 1)] == paramInt3))
      nachbarn(paramInt1, paramInt2 - 1, paramInt3);
  }

  private void machbar()
  {
    this.machbar = 0;

    for (this.i = 0; this.i < this.steine[0].length; this.i += 1)
      for (this.j = 0; this.j < this.steine.length; this.j += 1) {
        if (((this.j >= this.steine.length - 1) || (this.steine[this.j][this.i] != this.steine[(this.j + 1)][this.i])) && ((this.j <= 0) || (this.steine[this.j][this.i] != this.steine[(this.j - 1)][this.i])) && ((this.i >= this.steine[0].length - 1) || (this.steine[this.j][this.i] != this.steine[this.j][(this.i + 1)])) && ((this.i <= 0) || (this.steine[this.j][this.i] != this.steine[this.j][(this.i - 1)]) || (this.steine[this.j][this.i] == 0)))
        {
          continue;
        }
        this.machbar += 1;
      }
  }

  private void fallen()
  {
    for (this.k = 0; this.k < this.steine[0].length; this.k += 1) {
      for (this.i = (this.steine[0].length - 1); this.i > 0; this.i -= 1) {
        for (this.j = (this.steine.length - 1); this.j >= 0; this.j -= 1) {
          if (this.steine[this.j][this.i] == 0) {
            this.steine[this.j][this.i] = this.steine[this.j][(this.i - 1)];
            this.steine[this.j][(this.i - 1)] = 0;
          }
        }
      }
    }

    for (this.k = 0; this.k < this.steine.length; this.k += 1)
      for (this.i = 0; this.i < this.steine.length - 1; this.i += 1)
        if (this.steine[this.i][(this.steine[0].length - 1)] == 0)
          for (this.j = 0; this.j < this.steine[0].length; this.j += 1) {
            this.steine[this.i][this.j] = this.steine[(this.i + 1)][this.j];
            this.steine[(this.i + 1)][this.j] = 0;
          }
  }

  public boolean possible(int paramInt1, int paramInt2)
  {
    return ((paramInt1 < this.steine.length - 1) && (this.steine[paramInt1][paramInt2] == this.steine[(paramInt1 + 1)][paramInt2])) || ((paramInt1 > 0) && (this.steine[paramInt1][paramInt2] == this.steine[(paramInt1 - 1)][paramInt2])) || ((paramInt2 < this.steine[0].length - 1) && (this.steine[paramInt1][paramInt2] == this.steine[paramInt1][(paramInt2 + 1)])) || ((paramInt2 > 0) && (this.steine[paramInt1][paramInt2] == this.steine[paramInt1][(paramInt2 - 1)]) && (this.steine[paramInt1][paramInt2] != 0));
  }
}
