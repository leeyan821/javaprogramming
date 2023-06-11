package view;

import controller.movie.MovieController;
import controller.movieList.MovieListController;
import dto.movie.MovieChange;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

public class AdminMovie extends JFrame {
    private JPanel mPanel;
    private JComboBox com1;
    private JLabel l1, lMovie, re, blank, blank2;
    private JLabel change[];
    private JTextField change2[];
    private JTable table;
    private DefaultTableModel model;
    private SimpleDateFormat dateFormat;
    private JButton btn, btn2;
    private Integer num = 0;
    ArrayList<MovieChange> list;
    MovieController movieController = new MovieController();
    MovieListController movieListController = new MovieListController();
    AdminMovie(){
        super("Movie Setting");
        this.setResizable(true);
        this.setSize(750, 500);
        this.setLocationRelativeTo(null);

        this.setLayout(new BorderLayout());
        mPanel = new JPanel();
        mPanel.setLayout(new BorderLayout());
        this.add(mPanel);

        //north
        l1 = new JLabel("관리");
        l1.setFont(new Font("AppleSDGothicNeoR", Font.BOLD, 25));
        l1.setHorizontalAlignment(SwingConstants.CENTER);
        mPanel.add(l1,BorderLayout.NORTH);

        JPanel content = new JPanel(new BorderLayout());
        content.setBorder(BorderFactory.createEmptyBorder(0 , 30 , 30 , 10));

        //content
        //con north
        JPanel find = new JPanel(new GridLayout(2,2,0,20));
        find.setBorder(BorderFactory.createEmptyBorder(30 , 30 , 30 , 30));

        lMovie = new JLabel("영화 선택");
        lMovie.setFont(new Font("AppleSDGothicNeoR", Font.BOLD, 15));
        find.add(lMovie);

        com1 = new JComboBox<>(movieController.getAllMovie().toArray());
        find.add(com1);

        re = new JLabel();
        re.setFont(new Font("AppleSDGothicNeoR", Font.BOLD, 15));
        find.add(re);
        content.add(find,BorderLayout.NORTH);

        //con west
        String header[] = {"지점","상영관","날짜","시간"};

        Vector<String> vector = new Vector<>();
        vector.addElement("지점");
        vector.addElement("상영관");
        vector.add("날짜");
        vector.add("시간");
        model = new DefaultTableModel(vector,0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return (column!=0)?true:false;
            }
        };
        table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);
        dateFormat = new SimpleDateFormat("HH:mm");
        content.add(scroll,BorderLayout.WEST);

        //con east
        JPanel east = new JPanel(new GridLayout(5,2,20,30));
        east.setBorder(BorderFactory.createEmptyBorder(0 , 10 , 0 , 20));
        change = new JLabel[4];
        change2 = new JTextField[4];
        for(int i=0;i<4;i++){
            change[i] = new JLabel(header[i]);
            change[i].setFont(new Font("AppleSDGothicNeoR", Font.BOLD, 12));
            change[i].setHorizontalAlignment(SwingConstants.CENTER);
            change2[i] = new JTextField();
            east.add(change[i]);
            east.add(change2[i]);
        }

        btn = new JButton("삭제");
        east.add(btn);

        btn2 = new JButton("변경");
        east.add(btn2);

        content.add(east,BorderLayout.CENTER);
        mPanel.add(content,BorderLayout.CENTER);
        setVisible(true);

        com1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                re.setText(com1.getSelectedItem().toString());
                re.setForeground(Color.RED);
                model.setNumRows(0);
                list = (ArrayList<MovieChange>) movieListController.findMovieByName(com1.getSelectedItem().toString());
                for(MovieChange data: list){
                    Vector<String> v = new Vector<>();
                    v.add(data.getTheater());
                    v.add(data.getRoom().toString());
                    v.add(data.getDate());
                    v.add(dateFormat.format(data.getTime()));
                    model.addRow(v);
                }
            }
        });
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount()==1){
                    change2[0].setText(table.getValueAt(table.getSelectedRow(),0).toString());
                    change2[1].setText(table.getValueAt(table.getSelectedRow(),1).toString());
                    change2[2].setText(table.getValueAt(table.getSelectedRow(),2).toString());
                    change2[3].setText(table.getValueAt(table.getSelectedRow(),3).toString());
                    num = movieListController.find(com1.getSelectedItem().toString(), change2[0].getText(),
                            change2[2].getText(), change2[3].getText(), Integer.valueOf(change2[1].getText()));
                }
            }
        });

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!com1.getSelectedItem().toString().isEmpty() && (change2[0].getText().isEmpty() || change2[1].getText().isEmpty() ||
                        change2[2].getText().isEmpty() || change2[3].getText().isEmpty())){
                    System.out.println(1);
                    int answer = JOptionPane.showConfirmDialog(null, com1.getSelectedItem().toString()+"를 삭제하시겠습니까?", "confirm",JOptionPane.YES_NO_OPTION );
                    if(answer==JOptionPane.YES_OPTION){  //사용자가 yes를 눌렀을 경우
                        //영화 틀 삭제
                        Integer n = movieListController.deleteMovie(com1.getSelectedItem().toString());
                        if(n == 0) {
                            movieController.deleteMovie(com1.getSelectedItem().toString());
                            JOptionPane.showMessageDialog(null, "삭제 완료");
                            dispose();
                            new AdminMovie();
                        }else JOptionPane.showMessageDialog(null, "삭제 불가 : 예매중");
                    } else{  //사용자가 Yes 이외의 값을 눌렀을 경우
                        System.out.println("취소");
                    }
                }
                else if (com1.getSelectedItem().toString().isEmpty() || change2[0].getText().isEmpty() || change2[1].getText().isEmpty() ||
                        change2[2].getText().isEmpty() || change2[3].getText().isEmpty() ) {
                    System.out.println(2);
                    JOptionPane.showMessageDialog(null, "존재하지 않음");
                }
                else {
                    System.out.println(3);
                    Integer num = movieListController.find(com1.getSelectedItem().toString(), change2[0].getText(),
                            change2[2].getText(), change2[3].getText(), Integer.valueOf(change2[1].getText()));
                    if (num == 0) {
                        JOptionPane.showMessageDialog(null, "존재하지 않음");
                    } else {
                        Integer re = movieListController.check(num);
                        System.out.println("front:"+re);
                        if(re==0){
                            movieListController.delete(num);
                            JOptionPane.showMessageDialog(null, "삭제되었습니다.");
                            dispose();
                            new AdminMovie();
                        }else {
                            JOptionPane.showMessageDialog(null, "삭제 불가 : 예매중");
                        }
                    }
                }
            }
        });
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (com1.getSelectedItem().toString().isEmpty() || change2[0].getText().isEmpty() || change2[1].getText().isEmpty() ||
                        change2[2].getText().isEmpty() || change2[3].getText().isEmpty() ) {
                    JOptionPane.showMessageDialog(null, "존재하지 않음");
                } else {
                    if (num == 0) {
                        JOptionPane.showMessageDialog(null, "존재하지 않습니다.");
                    } else {
                        Integer check = movieListController.check(num);
                        if(check == 0) {
                            movieListController.update(num, change2[0].getText(), change2[2].getText(),change2[3].getText()
                              , Integer.valueOf(change2[1].getText()));
                             JOptionPane.showMessageDialog(null, "변경되었습니다.");
                            dispose();
                            new AdminMovie();
                        }else{
                            JOptionPane.showMessageDialog(null, "변경불가 : 예매중");
                        }
                    }
                }
            }
        });
    }
    public static void main(String[] args) {
        new AdminMovie();
    }
}
