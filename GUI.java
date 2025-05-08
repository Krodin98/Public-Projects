
import java.awt.Color;
import java.awt.event.*;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.Border;

public class GUI implements ActionListener, KeyListener {

    JLabel label = new JLabel("Press the button to make me scroll");
    JButton button = new JButton("Start Animation");
    JFrame frame = new JFrame();
    private int action;
    private long startTime;
    private long endTime;

    public GUI() {
        Border panelBorder = BorderFactory.createLineBorder(Color.CYAN, 3);

        button.setBounds(250, 0, 250, 250);
        button.addActionListener(this);
        button.setFocusable(false);

        label.setHorizontalAlignment(JLabel.CENTER);
        label.setForeground(Color.CYAN);
        label.setBackground(Color.black);
        label.setOpaque(true);
        label.setBorder(panelBorder);
        label.setBounds(0, 0, 250, 250);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.add(label);
        frame.add(button);
        frame.addKeyListener(this);

    }// end of main

    @Override
    public void actionPerformed(ActionEvent e) {
        startTime = System.currentTimeMillis();
        action++;
        if (action % 2 != 0) {
            button.setText("Press any key to stop animation");
            if (e.getSource() == button) {
                System.out.println("Start button pressed!");
                button.setEnabled(false);
                new Thread() {
                    public void run() {
                        String string = "  I'm supposed to scroll.  ";
                        char character = ' ';
                        while (true && button.getText().equals("Press any key to stop animation")) {
                            character = string.charAt(0);
                            string = string.substring(1, string.length());
                            label.setText(string += character);
                            try {
                                Thread.sleep(250);
                            } catch (InterruptedException e) {

                            }
                        }
                    }
                }.start();// end of thread
            }
        }
        endTime = System.currentTimeMillis();
    }// end of actionPerformed

    @Override
    public void keyPressed(KeyEvent e) {
        button.setText("Animation Stopped");
        JOptionPane.showMessageDialog(null,
                "You pressed " + e.getKeyChar() + " to exit the animation. Execution time was " + (endTime - startTime)
                        + " seconds: ");
        System.exit(0);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}// end of class
