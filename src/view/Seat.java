package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Seat extends JFrame {
    JButton[][] seat = new JButton[10][10];
    JLabel jLabel;
    char j = 65;
    int selectedSeat;

    public Seat(String selectedMovie, String selectedTheater, String selectedDate, String selectedTime) {
        this.setTitle("좌석선택");
        this.setResizable(true);
        this.setSize(1000, 600);
        this.setLocationRelativeTo(null); //가운데 띄우기
        this.setLayout(new BorderLayout());

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
               // seat[i][j] = new JButton();
             //   seat[i][j].setVisible(true);
            //    seat[i][j].setBounds(i, j,3, 3);
          //      add(seat[i][j]);
            }
        }


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public Seat() {
        this.setTitle("좌석선택");
        this.setResizable(true);
        this.setSize(600, 400);
        this.setLocationRelativeTo(null); //가운데 띄우기
        this.setLayout(new BorderLayout());

        JPanel chart = new JPanel(new GridLayout(10, 11, 3, 3));
        JPanel screen = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 30));
        JPanel info = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 30));

        for (int i = 0; i < 10; i++) {
            chart.add(new JLabel(j + ""));
            j++;
            for (int j = 0; j < 10 ; j++) {
                int num = j;
                seat[i][j] = new JButton(++num + "");
                chart.add(seat[i][j]);
            }
        }

        screen.setBackground(Color.gray);
        screen.add(new JLabel("SCREEN"));

        add(chart, BorderLayout.CENTER);
        add(screen, BorderLayout.NORTH);
        add(info, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Seat();
    }
}
