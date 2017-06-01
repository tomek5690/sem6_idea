package studia;

import javax.swing.*;

public class SaperBox extends JButton {
    boolean disabled = false;
    int id;
    SaperBox(String arg){
        super(arg);
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getStringId(){
        return ""+id;
    }
    void setDisabled(){
        disabled = true;
    }
    boolean isDisabled(){
        return disabled;
    }
}
