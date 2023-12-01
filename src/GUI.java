import javax.swing.*;
        import javax.swing.border.Border;
        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import java.awt.event.MouseAdapter;
        import java.awt.event.MouseEvent;
        import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.Collections;

public class GUI extends JFrame implements ActionListener {
    JButton startButton = new JButton("Nytt spel");
    JButton autoFinishButton = new JButton("Auto-finish");
    JPanel gridPanel = new JPanel();
    JPanel backgroundPanel = new JPanel(new BorderLayout());
    JPanel menuPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JPanel resultPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JLabel resultLabel = new JLabel("Du vann");
    JButton[] buttons = new JButton[16];
    private JButton[][] list = new JButton[4][4];
    RandomNumbers rn = new RandomNumbers();
    AutoFinish af = new AutoFinish();

    GUI() {
        gridPanel.setLayout(new GridLayout(4, 4));
        gridPanel.setPreferredSize(new Dimension(400, 400));
        JPanel gridOuterPanel = new JPanel();
        gridOuterPanel.add(gridPanel);

        autoFinishButton.addActionListener(this);
        startButton.addActionListener(this);
        startButton.setSize(75, 50);
        autoFinishButton.setSize(75, 50);
        resultPanel.setVisible(false);

        //Buttons creation for all buttons

        for (int i = 0; i < buttons.length; i++) {
            if (i == 0) {
                buttons[i] = new JButton(" ");
            } else {
                buttons[i] = new JButton(String.valueOf(i));
            }
        }
        //Buttons properties for all buttons
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setSize(100, 100);
            buttons[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));

            // General mouse events/listeners for all buttons
            buttons[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    JButton button = (JButton) e.getSource();
                    button.setContentAreaFilled(false);
                    button.setOpaque(true);
                    button.setBackground(Color.GRAY);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    JButton button = (JButton) e.getSource();
                    button.setContentAreaFilled(true);
                    button.setOpaque(false);
                }
                @Override
                public void mousePressed(MouseEvent e) {
                    JButton button = (JButton) e.getSource();
                    button.setContentAreaFilled(false);
                    button.setOpaque(true);
                    button.setBackground(Color.DARK_GRAY);
                    gridPanel.removeAll(); //remove component from jpanel
                    list= rn.ChangePostion(((JButton) e.getSource()).getText(),list);

                    for (int x = 0; x <= 3; x++) {
                        for (int y = 0; y <= 3; y++) {
                            gridPanel.add(list[x][y]);
                        }
                    gridPanel.revalidate();
                    gridPanel.repaint();

                    }
                    //gör resultPanel till visible när man vinner
                    if (rn.CheckIsWinner(list))
                        resultPanel.setVisible(true);
                }
            });
        }
        RandomButtonsBuilder(buttons);
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 40));
        resultLabel.setSize(100, 100);

        resultPanel.add(resultLabel);
        menuPanel.add(startButton);
        menuPanel.add(autoFinishButton);

        gridPanel.setSize(400, 400);
        backgroundPanel.add(menuPanel, BorderLayout.NORTH);
        backgroundPanel.add(gridOuterPanel, BorderLayout.CENTER);
        backgroundPanel.add(resultPanel, BorderLayout.SOUTH);

        this.add(backgroundPanel);
        setSize(500, 540);
        setTitle("Spel 15");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
        @Override
    public void actionPerformed(ActionEvent e) {
        //ActionListener för autoFinish-knappen
        if (e.getSource() == autoFinishButton) {
            list = af.ChangeListToAutoFinish(buttons);//sorterar om list från 1-15, 0 i slutet.
            gridPanel.removeAll(); //remove component from jpanel
            //lägger till knapperna igen
            for (int x = 0; x <= 3; x++) {
                for (int y = 0; y <= 3; y++) {
                    gridPanel.add(list[x][y]);
                }
            }
            gridPanel.revalidate();
            gridPanel.repaint();
        }
        if (e.getSource() == startButton) {
            gridPanel.removeAll(); //remove component from jpanel
            resultPanel.setVisible(false);
            RandomButtonsBuilder(buttons);
            gridPanel.revalidate();
            gridPanel.repaint();
        }
    }
    private void RandomButtonsBuilder(JButton[] buttons) {
        int num = 15;
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int n = 0; n < 16; n++)
            numbers.add(n);
        Collections.shuffle(numbers);

        //Add randomized JButtons to panel
        for (int x = 0; x <= 3; x++) {
            for (int y = 0; y <= 3; y++) {
                list[x][y] = buttons[numbers.get(num)];
                gridPanel.add(buttons[numbers.get(num)]);
                num--;
            }
        }
    }

}