package view;

import controller.movie.MovieController;
import dto.user.UserBookingInfo;
import dto.user.UserBookingList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class UserMovieBookingList extends JFrame {
    MovieController movieController = new MovieController();
    Object num;
    JLabel l1, l2, l3, l4;
    UserBookingInfo ui;

    public UserMovieBookingList(String userId) {
        this.setTitle("예매내역");
        this.setResizable(true);
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(0, 20));

        JPanel j = new JPanel(new GridLayout(2, 1));
        JPanel j1 = new JPanel(new BorderLayout(10, 0));
        JPanel info = new JPanel(new GridLayout(4, 2));

        String[] h = {"예매번호", "예매일", "영화명"};
        List<UserBookingList> list = movieController.getUserBookingList(userId);
        Object[][] data = new Object[list.size()][];

        for (int i = 0; i < list.size(); i++) {
            UserBookingList u = list.get(i);
            data[i] = new Object[] {
              u.getBookingNum(),
              u.getBookingDate(),
              u.getMovieName()
            };
        }

        l1 = new JLabel();
        l2 = new JLabel();
        l3 = new JLabel();
        l4 = new JLabel();

        info.add(new JLabel("영화명"));
        info.add(l1);
        info.add(new JLabel("극장명"));
        info.add(l2);
        info.add(new JLabel("관람일시"));
        info.add(l3);
        info.add(new JLabel("상영관"));
        info.add(l4);

        DefaultTableModel model = new DefaultTableModel(data, h);
        JTable table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int r = table.getSelectedRow();
                num = table.getValueAt(r, 0);
                ui = movieController.getUserBookingInfo(num);

                l1.setText(table.getValueAt(r, 2).toString());
                l2.setText(ui.getTheater());
                l3.setText(ui.getDate() + " " + ui.getTime());
                l4.setText(ui.getRoom() + "관");
            }
        });
        j.add(table);
        JButton d = new JButton("예매취소");
        d.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(num != null) {
                    movieController.deleteBooking(num);
                    JOptionPane.showMessageDialog(null, "예매가 취소되었습니다.");
                    setVisible(false);
                    new UserMovieBookingList(userId);
                }
            }
        });

        j1.add(info,BorderLayout.WEST);
        j1.add(d, BorderLayout.EAST);

        add(j, BorderLayout.WEST);
        add(j1, BorderLayout.SOUTH);

        add(new JScrollPane(table));
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
