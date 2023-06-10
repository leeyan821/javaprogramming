package view;

import controller.movie.MovieController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Seat extends JFrame implements ActionListener {
    MovieController movieController = new MovieController();
    JButton[][] seat = new JButton[10][10];
    int n = 0;
    JLabel selectedNum = new JLabel("인원: " + n + "명");
    JLabel price = new JLabel("결제금액: " + n + "원");
    char j = 65;
    List<String> selectedSeat = new ArrayList<>();
    JRadioButton[] pb = new JRadioButton[4];
    String sRatio;
    int num;
    List<String> bookingSeat;

    public Seat(String id, String selectedMovie, String selectedTheater, String selectedDate, String selectedTime) {
        this.setTitle("좌석선택");
        this.setResizable(true);
        this.setSize(760, 600);
        this.setLocationRelativeTo(null); //가운데 띄우기
        this.setLayout(new BorderLayout());

        JPanel chart = new JPanel(new GridLayout(10, 11, 3, 3));
        JPanel screen = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        JPanel info = new JPanel(new GridLayout(6, 1));
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel f = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel t = new JPanel(new GridLayout(2, 1));

        JLabel l1 = new JLabel(selectedMovie);
        JLabel l2= new JLabel("극장:" + selectedTheater);
        JLabel l3 = new JLabel("날짜: " + selectedDate);
        JLabel l4 = new JLabel("시간: " + selectedTime);
        JButton b = new JButton("다음");

        num = movieController.findByMovieListId(selectedMovie, selectedTheater, selectedDate, selectedTime);
        bookingSeat = movieController.getBookingList(num);

        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(selectedSeat.size() > 0 && (Integer.parseInt(sRatio) == selectedSeat.size())) {
                   // new Payment(id, num, selectedSeat);
                    setVisible(false);
                }
                else
                    JOptionPane.showMessageDialog(null, "선택한 인원/좌석을 확인해주세요.");
            }
        });

        JLabel p1 = new JLabel("인원선택");
        ButtonGroup buttonGroup = new ButtonGroup();
        p.add(p1);

        for (int i = 0; i < 4; i++) {
            int num = i;
            pb[i] = new JRadioButton(++num + "");
            pb[0].setSelected(true);
            sRatio = pb[0].getText();
            buttonGroup.add(pb[i]);
            p.add(pb[i]);
            pb[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JRadioButton s = (JRadioButton) e.getSource();
                    sRatio = s.getText();
                }
            });
        }

        info.add(l1);
        info.add(l2);
        info.add(l3);
        info.add(l4);
        info.add(selectedNum);
        info.add(price);

        t.add(screen);
        t.add(p);
        f.add(info);
        f.add(b);

        //좌석표
        for (int i = 0; i < 10; i++) {
            chart.add(new JLabel(j + ""));
            char a = j;
            j++;
            for (int j = 0; j < 10 ; j++) {
                int num = j;
                seat[i][j] = new JButton(a + "" + ++num + "");
                seat[i][j].setBackground(new Color(69, 76, 79));
                seat[i][j].setForeground(Color.white);
                seat[i][j].addActionListener(this);
                chart.add(seat[i][j]);
            }
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10 ; j++) {
                for(String s : bookingSeat) {
                    if(seat[i][j].getText().equals(s)) {
                        seat[i][j].setVisible(false);
                    }
                }
            }
        }

        screen.setBackground(Color.gray);
        screen.add(new JLabel("SCREEN"));

        add(chart, BorderLayout.CENTER);
        add(t, BorderLayout.NORTH);
        add(f, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton seat = (JButton)e.getSource();
        Color c = new Color(181, 203, 213);

        if(seat.getBackground().equals(c)) {
            seat.setBackground(new Color(69, 76, 79));
            seat.setForeground(Color.white);
            selectedNum.setText("인원: " + --n + "명");

            for (int i = 0; i < selectedSeat.size(); i++) {
                selectedSeat.remove(seat.getText());
            }
        }
        else {
            seat.setForeground(Color.black);
            seat.setBackground(c);
            selectedNum.setText("인원: " + ++n + "명");
            selectedSeat.add(seat.getText());
        }
        price.setText("결제금액: " + n * 10000 + "원");
    }
}
