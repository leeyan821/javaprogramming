package view;

import javax.swing.*;
import java.awt.*;

public class Search extends JFrame {
    private JPanel sPanel;

    Search(){this(null,null);}
    Search(String id, String searchName){
        this.setTitle("Search");
        this.setSize(400, 500);
        this.setLocationRelativeTo(null);

        this.setLayout(new BorderLayout());
        sPanel = new JPanel();
        sPanel.setLayout(new BorderLayout());
        this.add(sPanel);

        setVisible(true);
    }
}
