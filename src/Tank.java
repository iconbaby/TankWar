import com.sun.jnlp.ApiDialog;
import com.sun.org.apache.xerces.internal.impl.xs.XSElementDecl;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.sql.Driver;

/**
 * Created by slkk on 2017/7/10.
 */
public class Tank {
    int x, y;//tank's position
    private boolean bU, bD, bL, bR = false;
    private static final int XSPEED = 3;
    private static final int YSPEED = 3;

    public void keyReleased(KeyEvent e) {
        int keycode = e.getKeyCode();
        switch (keycode) {
            case KeyEvent.VK_RIGHT:
                bR = false;
                break;
            case KeyEvent.VK_LEFT:
                bL = false;
                break;
            case KeyEvent.VK_DOWN:
                bD = false;
                break;
            case KeyEvent.VK_UP:
                bU = false;
                break;
        }
        LocateDirection();
    }

    enum Direction {U, RU, R, RD, D, LD, L, LU, STOP}

    ;
    private Direction dir = Direction.STOP;

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x, y, 30, 30);
        g.setColor(color);
        move();
    }

    public void keyPressed(KeyEvent e) {
        int keycode = e.getKeyCode();
        switch (keycode) {
            case KeyEvent.VK_RIGHT:
                bR = true;

                break;
            case KeyEvent.VK_LEFT:
                bL = true;
                break;
            case KeyEvent.VK_DOWN:
                bD = true;
                break;
            case KeyEvent.VK_UP:
                bU = true;
                break;
        }
        LocateDirection();
    }

    private void LocateDirection() {
        if (bR && !bL && !bD && !bU) {
            dir = Direction.R;
        } else if (bR && !bL && bD && !bU) {
            dir = Direction.RD;
        } else if (bR && !bL && bD && !bU) {
            dir = Direction.RD;
        } else if (!bR && !bL && bD && !bU) {
            dir = Direction.D;
        } else if (!bR && bL && bD && !bU) {
            dir = Direction.LD;
        } else if (!bR && bL && !bD && !bU) {
            dir = Direction.L;
        } else if (!bR && bL && !bD && bU) {
            dir = Direction.LU;
        } else if (!bR && !bL && !bD && bU) {
            dir = Direction.U;
        } else if (bR && !bL && !bD && bU) {
            dir = Direction.RU;
        }
    }

    private void move() {
        switch (dir) {
            case U:
                y -= YSPEED;
                break;
            case RU:
                y -= YSPEED;
                x += XSPEED;
                break;
            case R:
                x += XSPEED;
                break;
            case RD:
                x += XSPEED;
                y += YSPEED;
                break;
            case D:
                y += YSPEED;
                break;
            case LD:
                x -= XSPEED;
                y += YSPEED;
                break;
            case L:
                x -= XSPEED;
                break;
            case LU:
                x -= XSPEED;
                y -= YSPEED;
                break;
            case STOP:
                break;

        }
    }
}
