package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminMain extends JFrame {
    private JPanel mPanel;
    private JMenuBar mb;
    private JMenu movie;
    private JButton btnPlus;
    private JMenuItem a,b;
    AdminMain(){
        super("Main");
        this.setResizable(true);
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);

        this.setLayout(new FlowLayout());
        mPanel = new JPanel();
        mPanel.setLayout(new FlowLayout());
        this.add(mPanel);

        mb = new JMenuBar();
        movie = new JMenu("Menu");

        a = new JMenuItem("영화 관리");
        b = new JMenuItem("회원 관리");
        movie.add(a);
        movie.add(b);
        mb.add(movie);
        this.setJMenuBar(mb);

        btnPlus = new JButton("영화 추가");
        mPanel.add(btnPlus);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        //영화 추가
        btnPlus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PlusMovie();
            }
        });
    }
    public static void main(String[] args) {
        new AdminMain();
    }

}
