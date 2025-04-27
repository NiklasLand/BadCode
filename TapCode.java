import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TapCode extends JFrame implements KeyListener {

    JLabel label;
    String[][] alpha = {
            {"a", "b", "c", "d", "e", "f"},
            {"g", "h", "i", "j", "k", "l"},
            {"m", "n", "o", "p", "q", "r"},
            {"s", "t", "u", "v", "w", "x"},
            {"y", "z", "å", "ä", "ö"},
    };
    int col = 0;
    int row = 0;
    long then = System.currentTimeMillis();
    String word = "";
    boolean vertical = true;

    public TapCode(String s) {
        super(s);
        JPanel p = new JPanel();
        label = new JLabel("Key Listener!");
        p.add(label);

        add(p);
        addKeyListener(this);
        setSize(400, 400);
        setVisible(true);

        then = System.currentTimeMillis();

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        long now = System.currentTimeMillis();
        String bokstav = alpha[col][row];

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            word += bokstav;
            vertical = true;
        }

        if((now - then) / 1000d > 1 && vertical) {
            vertical = false;
        } else if((now - then) / 1000d > 1 && !vertical) {
            word += bokstav;
            col = 0;
            row = 0;
            vertical = true;
        }
        if((now - then) / 1000d < 1) {
            if (vertical)
                col++;
             else
                row++;

        }
        col = col >= 5 ? col - 5 : col;
        row = row >= 5 ? row - 5 : row;

        String output = word +"<br/>";
        for (String[] alphaRow : alpha) {
            if (alphaRow == alpha[col]) {
                output += String.join("", alphaRow).replace(bokstav,bokstav.toUpperCase());
            } else {
                output += String.join("", alphaRow);
            }
            output += "<br/>";
        }
        label.setText("<html>" + output + "</html>");
        then = System.currentTimeMillis();


    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public static void main(String[] args) {
        new TapCode("Tap code program");
    }
}
