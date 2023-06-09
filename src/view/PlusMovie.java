package view;

import controller.movie.MovieController;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;

public class PlusMovie extends JFrame {
    private JPanel mPanel, content, timePanel;
    private JLabel label, movieName, theater, date, room, time, blank;
    private JComboBox list;
    private JTextField tMovieName;
    private JButton btn1, btn2;
    private JSpinner spinner, h,m;
    private String[] th = {"T1","T3","A5","A8","S1","S7"};
    private JComboBox<String> list2 = new JComboBox<String>(th);
    MovieController movieController = new MovieController();

    //추가 중
    PlusMovie(){
        super("Plus");
        this.setResizable(true);
        this.setSize(500, 450);
        this.setLocationRelativeTo(null);

        this.setLayout(new BorderLayout());
        mPanel = new JPanel();
        mPanel.setBounds(100,0,300,300);
        //mPanel.setLayout(new BorderLayout());
        this.add(mPanel);

        label = new JLabel("영화 추가");
        label.setFont(new Font("AppleSDGothicNeoR", Font.BOLD, 25));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(label,BorderLayout.NORTH);

        content = new JPanel(new GridLayout(6,2,30,20));

        //1
        movieName = new JLabel("영화 선택");
        movieName.setFont(new Font("AppleSDGothicNeoR", Font.BOLD, 15));
        content.add(movieName);

        //list.
        list = new JComboBox(movieController.getAllMovie().toArray());
        content.add(list);

        //2
        tMovieName = new JTextField((String) list.getSelectedItem());
        tMovieName.setColumns(20);
        content.add(tMovieName);

        btn1 = new JButton("영화 추가");
        content.add(btn1);

        //3
        theater = new JLabel("극장 선택");
        content.add(theater);

        content.add(list2);

        //4
        date = new JLabel("날짜 선택");
        content.add(date);

        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
        content.add(datePicker);

        //5
        time = new JLabel("시간 선택");
        content.add(time);

        Integer[] hour = new Integer[24];
        Integer[] minute = new Integer[60];
        for (int i=0;i<24;i++) hour[i] = i;
        for (int i=0;i<60;i++) minute[i] = i;

        timePanel = new JPanel();
        h = new JSpinner();
        m = new JSpinner();
        SpinnerListModel ti = new SpinnerListModel(hour);
        SpinnerListModel ti2 = new SpinnerListModel(minute);
        h.setModel(ti);
        m.setModel(ti2);
        timePanel.add(h);
        JLabel p1 = new JLabel("시");
        timePanel.add(p1);
        timePanel.add(m);
        JLabel p2 = new JLabel("분");
        timePanel.add(p2);
        content.add(timePanel);

        //6
        room = new JLabel("상영관 선택");
        content.add(room);

        Integer[] ro = {1,2,3,4,5,6,7,8,9};
        spinner = new JSpinner();

        SpinnerListModel l = new SpinnerListModel(ro);
        spinner.setModel(l);
        content.add(spinner);

        mPanel.add(content);

        btn2 = new JButton("저장");
        btn2.setFont(new Font("AppleSDGothicNeoR", Font.BOLD, 15));
        this.add(btn2,BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public static void main(String[] args) {
        new PlusMovie();
    }

}
