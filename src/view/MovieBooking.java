package view;

import controller.movie.MovieController;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovieBooking extends JFrame {
    MovieController movieController = new MovieController();
    String selectedMovie;
    String selectedTheater;
    String selectedDate;
    String selectedTime;

    public MovieBooking(String id) {
        this.setTitle("영화예매");
        this.setResizable(true);
        this.setSize(800, 400);
        this.setLocationRelativeTo(null); //가운데 띄우기
        this.setLayout(new BorderLayout());

        JList<String> movieList = new JList<>(movieController.getAllMovie().toArray(new String[0]));
        JList<String> theaterList = new JList<>();
        JList<String> dateList = new JList<>();
        JList<String> timeList = new JList<>();

        JPanel movieInfo = new JPanel(new GridLayout(1, 4));

        JPanel l = new JPanel(new GridLayout(1, 4));
        JLabel i1 = new JLabel("영화");
        JLabel i2 = new JLabel("극장");
        JLabel i3 = new JLabel("날짜");
        JLabel i4 = new JLabel("시간");
        i1.setFont(new Font("", Font.BOLD, 20));
        i2.setFont(new Font("", Font.BOLD, 20));
        i3.setFont(new Font("", Font.BOLD, 20));
        i4.setFont(new Font("", Font.BOLD, 20));
        l.add(i1);
        l.add(i2);
        l.add(i3);
        l.add(i4);

        JButton button = new JButton("좌석선택");
        JPanel f = new JPanel(new GridLayout(1, 4));
        f.add(button);

        movieList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        movieList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!e.getValueIsAdjusting()) {
                    selectedMovie = movieList.getSelectedValue();
                    theaterList.setListData(movieController.getAllTheater(selectedMovie).toArray(new String[0]));
                }
            }
        });

        theaterList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        theaterList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!e.getValueIsAdjusting()) {
                    selectedTheater = theaterList.getSelectedValue();
                    dateList.setListData(movieController.getAllDate(selectedMovie, selectedTheater).toArray(new String[0]));
                }
            }
        });

        dateList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        dateList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!e.getValueIsAdjusting()) {
                    selectedDate= dateList.getSelectedValue();
                    timeList.setListData(movieController.getAllTime(selectedMovie, selectedTheater, selectedDate).toArray(new String[0]));
                }
            }
        });

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedTime = timeList.getSelectedValue();
                if(selectedMovie != null && selectedTheater != null && selectedDate != null && selectedTime != null) {
                    new Seat(id, selectedMovie, selectedTheater, selectedDate, selectedTime);
                    setVisible(false);
                }
            }
        });

        movieInfo.add(movieList);
        movieInfo.add(theaterList);
        movieInfo.add(dateList);
        movieInfo.add(timeList);

        add(movieInfo, BorderLayout.CENTER);
        add(l, BorderLayout.NORTH);
        add(f, BorderLayout.SOUTH); //버튼

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
