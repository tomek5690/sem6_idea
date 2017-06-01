package studia;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.*;
import java.util.ArrayList;

public class GameFrame extends JFrame{
    ArrayList<SaperBox> boxesList;
    public GameFrame (GameController game) {
        super( "Saper" );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocation(50,50);
        boxesList = new ArrayList<>();
        setLayout(new GridLayout(9, 9));
        for (int i = 0; i < 81; i++) {
            SaperBox button = new SaperBox("");
            button.setId((i+1));
            button.addActionListener(game);add(button);
            boxesList.add(button);
        }
        setVisible(true);
    }
    public void setBoom(){
        for (SaperBox btn : boxesList) {
            btn.setText("*");
            btn.setBackground(Color.red);
        }
    }
    public void setWinState(){
        for (SaperBox box : boxesList){
            if (box.getBackground()!=Color.green) {
                box.setText("*");
                box.setBackground(Color.gray);
            }
        }
    }

}
