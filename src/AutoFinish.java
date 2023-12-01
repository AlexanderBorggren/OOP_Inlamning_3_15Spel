import javax.swing.*;

public class AutoFinish {

    //kollar om siffrorna i ordningen 1-15
    public JButton [][] ChangeListToAutoFinish(JButton[] currentList) {
        JButton [][] nyList = new JButton[4][4];
        int z = 0;
        for (int x = 0; x <= 3; x++)
            for (int y = 0; y <= 3; y++) {
                z++;
                if ((x == 3 && y == 2) || (x == 3 && y == 3)) {
                    nyList[3][3] = currentList[15];
                    nyList[3][2] = currentList[0];
                } else
                    nyList[x][y] = currentList[z];
            }
    return nyList;
    }
}