package studia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GameController implements ActionListener {
    SaperConnection connection;
    GameController(SaperConnection conn){
        connection = conn;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        SaperBox btnSource = (SaperBox) source;
        if (!(btnSource.isDisabled()))
            try {
                connection.sendRequest(btnSource.getStringId());
                String response = connection.getLine();
                if (response.equals("bum")) {
                    GameFrame game = (GameFrame) SwingUtilities.windowForComponent(btnSource);
                    game.setBoom();
                    JOptionPane.showMessageDialog(game, "Przegrałeś!");
                    System.exit(0);
                }
                else {
                    btnSource.setText(response);
                    btnSource.setDisabled();
                    btnSource.setBackground(Color.green);
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            finally {
                if (connection.getOpenedBoxes() == 71){
                    GameFrame game = (GameFrame) SwingUtilities.windowForComponent(btnSource);
                    game.setWinState();
                    JOptionPane.showMessageDialog(game, "Gratulacje! Wygrałeś!");
                    System.exit(0);

                }

            }
    }
}
