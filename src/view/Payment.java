package view;

import controller.movie.MovieController;
import dto.movie.MovieInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Payment extends JFrame {
    MovieController movieController = new MovieController();
    String seat = "";
    public Payment(String userId, int movieListId, List<String> selectedSeat) {
        this.setTitle("결제");
        this.setResizable(true);
        this.setSize(400, 300);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(0, 20));

        JPanel mInfo = new JPanel(new GridLayout(2, 1));
        JPanel mTitle = new JPanel(new FlowLayout());
        JPanel mOther = new JPanel(new GridLayout(6, 2));
        JPanel btn = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton pBtn = new JButton("결제하기");
        JButton tBtn = new JButton("예매내역");

        MovieInfo m = movieController.getMovieInfo(movieListId);
        JLabel m1 = new JLabel(m.getTitle());
        m1.setFont(new Font("", Font.BOLD, 25));
        mTitle.add(m1);

        mOther.add(new JLabel("극장"));
        mOther.add(new JLabel(m.getTheater()));
        mOther.add(new JLabel("관람 날짜"));
        mOther.add(new JLabel(m.getDate()));
        mOther.add(new JLabel("관람 시간"));
        mOther.add(new JLabel(m.getTime().toString()));
        mOther.add(new JLabel("상영관"));
        mOther.add(new JLabel(m.getRoom() + "관"));
        mOther.add(new JLabel("선택 좌석"));

        for (int i = 0; i < selectedSeat.size(); i++) {
            seat += selectedSeat.get(i);
            if(i != selectedSeat.size()-1) {
                seat += ", ";
            }
        }
        int size = selectedSeat.size();

        mOther.add(new JLabel(seat));
        mOther.add(new JLabel("결제금액"));
        mOther.add(new JLabel(size * 10000 + "원"));

        mInfo.add(mTitle);
        mInfo.add(mOther);
        pBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                movieController.addBooking(userId, movieListId, selectedSeat);
                JOptionPane.showMessageDialog(null, "예매가 완료되었습니다.");
                pBtn.setVisible(false);
                tBtn.setVisible(true);
            }
        });
        btn.add(pBtn);
        tBtn.setVisible(false);
        tBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UserMovieBookingList(userId);
                setVisible(false);
            }
        });
        btn.add(tBtn);

        add(btn, BorderLayout.SOUTH);
        add(mInfo, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}

