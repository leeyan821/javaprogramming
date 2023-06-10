package view;

import controller.movie.MovieController;
import controller.movieList.MovieListController;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PlusMovie extends JFrame {
    private JPanel mPanel, content, timePanel;
    private JLabel label, movieName, theater, date, room, time, newTheater;
    private JComboBox list, list2;
    private JTextField tMovieName, tTheaterName;
    private JButton btn1, btn2;
    private JSpinner spinner, h,m;

    MovieController movieController = new MovieController();
    MovieListController movieListController = new MovieListController();

    //추가 중
    PlusMovie(){
        super("Plus");
        this.setResizable(true);
        this.setSize(500, 450);
        this.setLocationRelativeTo(null);

        this.setLayout(new BorderLayout());
        mPanel = new JPanel();
        mPanel.setBounds(100,0,300,300);
        this.add(mPanel);

        label = new JLabel("영화 추가");
        label.setFont(new Font("AppleSDGothicNeoR", Font.BOLD, 25));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(label,BorderLayout.NORTH);

        content = new JPanel(new GridLayout(7,2,30,20));

        //1
        movieName = new JLabel("영화 선택");
        movieName.setFont(new Font("AppleSDGothicNeoR", Font.BOLD, 15));
        content.add(movieName);

        //list.
        list = new JComboBox(movieController.getAllMovie().toArray());
        content.add(list);

        //2
        tMovieName = new JTextField();
        tMovieName.setColumns(20);
        content.add(tMovieName);

        btn1 = new JButton("영화 추가");
        content.add(btn1);

        //3
        theater = new JLabel("극장 선택");
        theater.setFont(new Font("AppleSDGothicNeoR", Font.BOLD, 15));
        content.add(theater);

        list2 = new JComboBox(movieListController.getAllTheater().toArray());
        content.add(list2);

        //4
        newTheater = new JLabel("극장 직접 입력");
        newTheater.setFont(new Font("AppleSDGothicNeoR", Font.BOLD, 15));
        content.add(newTheater);

        tTheaterName = new JTextField();
        tTheaterName.setColumns(20);
        content.add(tTheaterName);

        //5
        date = new JLabel("날짜 선택");
        date.setFont(new Font("AppleSDGothicNeoR", Font.BOLD, 15));
        content.add(date);

        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
        content.add(datePicker);

        //6
        time = new JLabel("시간 선택");
        time.setFont(new Font("AppleSDGothicNeoR", Font.BOLD, 15));
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
        h.setValue(10);
        m.setValue(30);

        timePanel.add(h);
        JLabel p1 = new JLabel("시");
        timePanel.add(p1);
        timePanel.add(m);
        JLabel p2 = new JLabel("분");
        timePanel.add(p2);
        content.add(timePanel);

        //7
        room = new JLabel("상영관 선택");
        room.setFont(new Font("AppleSDGothicNeoR", Font.BOLD, 15));
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

        setVisible(true);

        //액션
        //리스트 선택
        list.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tMovieName.setText(list.getSelectedItem().toString());
            }
        });
        list2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tTheaterName.setText(list2.getSelectedItem().toString());
            }
        });
        //영화 추가
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = tMovieName.getText();
                Integer n = movieController.addMovie(name);
                if(n == 1) JOptionPane.showMessageDialog(null, "이미 존재합니다.");
                else if(n == 0) JOptionPane.showMessageDialog(null, "추가 완료");
                dispose();
                new PlusMovie();
            }
        });

        //최종 저장
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = tMovieName.getText();
                String theater = tTheaterName.getText();

                Integer year = model.getYear();
                Integer month = model.getMonth() + 1;
                Integer day = model.getDay();
                String date = year+"-"+month+"-"+day;

                Integer sHour = (Integer) h.getValue();
                Integer sMin = (Integer) m.getValue();
                String time = sHour + ":" + sMin + ":00";

                Integer room = (Integer) spinner.getValue();

                movieListController.save(name,theater,date,time,room);
                JOptionPane.showMessageDialog(null, "추가 완료");
                dispose();
            }
        });
    }

}
