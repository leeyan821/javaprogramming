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

public class Main extends JFrame {
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
        movie = new JMenu("Menu");

        a = new JMenuItem("영화 목록");
        b = new JMenuItem("예매");
        c = new JMenuItem("마이페이지");
        movie.add(a);
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
        List<MoviList> result = new ArrayList<>();
        result = movieController.getAll();
        List<String> re2 = movieListController.top();
        String contents[][] = new String[result.size()][2];
        //String contents[][] = new String[re2.size()][1];

        for(int i=0;i< result.size();i++){
            int j=0;
            contents[i][j] = result.get(i).getMovieName();
        }
        /*for(int i=0;i< re2.size();i++){
            int j=0;
            contents[i][j] = re2.get(i);
        }*/

        JTable table = new JTable(contents,header);
        JScrollPane scrolledTable = new JScrollPane(table);
        scrolledTable.setBorder(BorderFactory.createEmptyBorder(10,50,10,50));
        table.getColumn("Title").setPreferredWidth(300);

        mPanel.add(scrolledTable,BorderLayout.CENTER);

        /*String header[] = {"Title"};

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
        */

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        //액션
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = tSearch.getText();
                new Search(id,name);
            }
        });

        a.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
        Main m = new Main();
    }
}
