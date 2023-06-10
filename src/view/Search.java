package view;

import controller.movie.MovieController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Search extends JFrame implements ActionListener {
    private JPanel sPanel, mPanel[], conPanel;
    private JLabel mResult[];
    private JButton mButton[];
    private JScrollPane scroll;
    int count = 1;
    GridBagLayout Gbag = new GridBagLayout();
    MovieController movie = new MovieController();

    Search(){this(null,null);}
    Search(String id, String searchName){
        this.setTitle("Search");
        this.setSize(300,300);
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        sPanel = new JPanel();
        sPanel.setBorder(BorderFactory.createEmptyBorder(0 , 20 , 20 , 20));

        sPanel.setLayout(Gbag);
        JLabel te = new JLabel("< 검색 결과 >");
        te.setFont(new Font("AppleSDGothicNeoR", Font.BOLD, 18));
        create_form(te, 0,0,3,1);

        List<String> list = movie.search(searchName);
        int reCnt = list.size();
        mPanel = new JPanel[reCnt];
        mResult = new JLabel[reCnt];
        mButton = new JButton[reCnt];
        conPanel = new JPanel(new GridBagLayout());
        conPanel.setSize(300,300);

        for (int i=0; i< list.size(); i++) {
            mResult[i] = new JLabel(list.get(i));
            mResult[i].setFont(new Font("AppleSDGothicNeoR", Font.BOLD, 15));
            create_form(mResult[i],0,count++,2,1);

            mButton[i] = new JButton("에매하기");
            mButton[i].setFont(new Font("AppleSDGothicNeoR", Font.BOLD, 15));
            create_form(mButton[i],2,count++,1,1);

            mButton[i].addActionListener(this);
        }

        scroll = new JScrollPane(sPanel);  // 스크롤패널을 선언
        scroll.setBounds(0,0,280,250);
        this.add(scroll);
        setVisible(true);


    }

    public void create_form(Component cmpt, int x, int y, int w, int h){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.gridheight = h;
        this.Gbag.setConstraints(cmpt, gbc);
        sPanel.add(cmpt);
        sPanel.updateUI();
    }
    //액션 
    //예매창 이동
    @Override
    public void actionPerformed(ActionEvent e) {

    }
    public static void main(String[] args) {
        new Search();
    }
}
