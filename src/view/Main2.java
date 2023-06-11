package view;

import controller.movie.MovieController;
import controller.movieList.MovieListController;
import dto.movie.MoviList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Main2 extends JFrame {
    private JPanel mPanel;
    private JMenuBar mb;
    private JMenu movie;
    private JLabel search;
    private JTextField tSearch;
    private JButton btnSearch;
    private JMenuItem a,b,c;
    private JTable table;
    private DefaultTableModel model;
    MovieController movieController = new MovieController();
    MovieListController movieListController = new MovieListController();

    Main2(){
        this(null);
    }
    Main2(String id){
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
        movie = new JMenu("Menu");

        b = new JMenuItem("예매");
        c = new JMenuItem("마이페이지");
         movie.add(b);
        movie.add(c);
        mb.add(movie);
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
        String header[] = {"Title"};
        Vector<String> vector = new Vector<>();
        vector.addElement("Title");

        model = new DefaultTableModel(vector,0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return (column!=0)?true:false;
            }
        };
        table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createEmptyBorder(0 , 50 , 30 , 50));

        mPanel.add(scroll,BorderLayout.CENTER);



        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        //액션
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = tSearch.getText();
                //new Search(id,name);
                model.setNumRows(0);
                List<String> list = movieController.search(name);
                for(String data : list){
                    Vector<String> v = new Vector<>();
                    v.add(data);
                    model.addRow(v);
                }
            }
        });

        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MovieBooking(id);
            }
        });

        c.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UserMovieBookingList(id);
            }
        });
    }

    public static void main(String[] args) {
        Main2 m = new Main2();
    }
}
