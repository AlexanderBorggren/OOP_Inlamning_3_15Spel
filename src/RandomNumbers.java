import javax.swing.*;
import java.util.Objects;

public class RandomNumbers {
    public JButton[][] ChangePostion(String namn, JButton[][] currentList) {

        //x1,y1 = position vald knapp
        //x2,y2 = position tom plats
        int x1 = 4, x2 = 4, y1 = 4, y2 = 4;
        //find empty position
        JButton tempButton;

        for (int x = 0; x <= 3; x++) {
            for (int y = 0; y <= 3; y++) {
                tempButton = currentList[x][y];
                //hämtar position för emptyButton;
                if (Objects.equals(tempButton.getText(), " ")) {
                    x2 = x;
                    y2 = y;
                }
                //hämtar position för intryckt Button;
                if (Objects.equals(tempButton.getText(), namn)) {
                    x1 = x;
                    y1 = y;
                }
            }
        }

        //x1,y1 = position vald knapp
        //x2,y2 = position tom plats

        //flytta till vänster
        if (x1 == x2 && y1 > 0 && y1 - y2 == 1)
            currentList= SwapPositions(x1, y1, x2, y2,currentList);
        //flytta till höger
        if (x1 == x2 && y1 < 3 && y1 - y2 == -1)
            currentList= SwapPositions(x1, y1, x2, y2,currentList);
        //flytta upp
        if (x1 - x2 == 1 && x1 > 0 && y1 == y2)
            currentList=SwapPositions(x1, y1, x2, y2,currentList);
        //flytta ner
        if (x1 - x2 == -1 && x1 < 3 && y1 == y2)
            currentList=SwapPositions(x1, y1, x2, y2,currentList);
    return currentList;
    }
    private JButton[][] SwapPositions(int x1, int y1, int x2, int y2,JButton[][] list) {
        JButton tempListClicked = list[x1][y1];
        JButton tempListTom = list[x2][y2];

        list[x1][y1] = tempListTom;
        list[x2][y2] = tempListClicked;
        return list;
    }

    public boolean CheckIsWinner(JButton[][] currentList) {
        int count = 1;
        for (int x = 0; x <= 3; x++) {
            for (int y = 0; y <= 3; y++) {
                if (count <= 15) {
                    String buttonText = currentList[x][y].getText();
                    if (!buttonText.equals("")) {
                        if (!buttonText.equals(String.valueOf(count)))
                            return false;
                    }
                    count++;
                }
            }
        }
        return true;
    }
}