package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    private JPanel mPanel;
    private JMenuBar mb;
    private JMenu movie, buy, myPage;
    private JLabel search;
    private JTextField tSearch;
    private JButton btnSearch;

    Main(){
        this(null);
    }
    Main(String id){
        super("Main");
        this.setResizable(true);
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);

        //레이아웃
        this.setLayout(new BorderLayout());
        mPanel = new JPanel();
        mPanel.setLayout(new BorderLayout());
        this.add(mPanel);

        
        //메뉴
        mb = new JMenuBar();
        movie = new JMenu("영화");
        buy = new JMenu("예매");
        myPage = new JMenu("마이페이지");
        mb.add(movie);
        mb.add(buy);
        mb.add(myPage);
        this.setJMenuBar(mb);

        //검색
        JPanel searchMovie = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 20));
        search = new JLabel("제목 검색");
        search.setFont(new Font("AppleSDGothicNeoR", Font.BOLD, 14));
        search.setHorizontalAlignment(SwingConstants.CENTER);
        searchMovie.add(search);

        tSearch = new JTextField();
        tSearch.setColumns(25);
        tSearch.setFont(new Font("AppleSDGothicNeoR", Font.PLAIN, 15));
        searchMovie.add(tSearch);

        btnSearch = new JButton("검색");
        searchMovie.add(btnSearch);
        mPanel.add(searchMovie,BorderLayout.NORTH);

        //영화 리스트

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        //액션
        //검색 하는 중
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = tSearch.getText();
                System.out.println(name);
                new Search(id,name);

            }
        });
    }


    public static void main(String[] args) {
        Main m = new Main();
    }
}
