package view;

import javax.swing.*;
import java.awt.*;

public class JoinUser extends JFrame {
    private JPanel mPanel;
    private JLabel id, pwd, name;
    private JTextField tSearch;
    JoinUser(){
        super("Join");
        this.setResizable(true);
        this.setSize(400, 500);
        this.setLocationRelativeTo(null);

        this.setLayout(new BorderLayout());
        mPanel = new JPanel();
        mPanel.setLayout(new BorderLayout());
        this.add(mPanel);

        //north
        JLabel title = new JLabel("회원가입");
        title.setFont(new Font("AppleSDGothicNeoR", Font.BOLD, 20));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        mPanel.add(title,BorderLayout.NORTH);

        //center
        JPanel center = new JPanel(new GridLayout(3,2,10,10));


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public static void main(String[] args) {
        new JoinUser();
    }
}
