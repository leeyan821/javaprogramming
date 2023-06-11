package view;

import controller.user.UserController;
import dto.user.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

public class AdminUser extends JFrame {
    private JPanel mPanel;
    private JLabel l1;
    private JTable table;
    private DefaultTableModel model;
    private JButton btn;
    ArrayList<User> list;
    Integer num = 0;
    UserController userController = new UserController();
    AdminUser(){
        super("User Setting");
        this.setResizable(true);
        this.setSize(600, 400);
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
        content.setBorder(BorderFactory.createEmptyBorder(10 , 30 , 10 , 30));
        //content
        String header[] = {"ID","PassWord","Name"};
        Vector<String> vector = new Vector<>();
        vector.addElement("ID");
        vector.addElement("PassWord");
        vector.add("Name");

        model = new DefaultTableModel(vector,0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return (column!=0)?true:false;
            }
        };
        table = new JTable(model);
        list = (ArrayList<User>) userController.getAll();
        for (User data: list) {
            Vector<String> v = new Vector<>();
            v.add(data.getId());
            v.add(data.getPwd());
            v.add(data.getName());
            model.addRow(v);
        }

        JScrollPane scroll = new JScrollPane(table);
        content.add(scroll,BorderLayout.CENTER);
        mPanel.add(content,BorderLayout.CENTER);

        btn = new JButton("삭제");
        mPanel.add(btn,BorderLayout.SOUTH);

        setVisible(true);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() > 0){
                    num = e.getClickCount();
                }
            }
        });
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(num > 0){
                    Integer result = userController.checkDelete(table.getValueAt(table.getSelectedRow(),0).toString());
                    if(result == 0){
                        userController.deleteUser(table.getValueAt(table.getSelectedRow(),0).toString());
                        JOptionPane.showMessageDialog(null, "삭제 완료");
                        dispose();
                        new AdminUser();
                    }else{
                        JOptionPane.showMessageDialog(null, "삭제 불가 : 사용중");
                    }
                }else JOptionPane.showMessageDialog(null, "회원을 선택하시오.");
            }
        });
    }
    public static void main(String[] args) {
        new AdminUser();
    }
}
