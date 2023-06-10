package view;

import controller.user.UserController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JoinUser extends JFrame {
    private JPanel mPanel;
    private JLabel id, pwd, name, blank;
    private JTextField tId, tName;
    private JPasswordField tPwd;
    private JButton btn, btnCheck;
    private Integer checkId = 0;
    UserController userController = new UserController();
    JoinUser(){
        super("Join");
        this.setResizable(true);
        this.setSize(400, 300);
        this.setLocationRelativeTo(null);

        this.setLayout(new BorderLayout(10,10));
        mPanel = new JPanel();
        mPanel.setLayout(new BorderLayout());
        this.add(mPanel);

        //north
        JLabel title = new JLabel("회원가입");
        title.setFont(new Font("AppleSDGothicNeoR", Font.BOLD, 20));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        mPanel.add(title,BorderLayout.NORTH);

        //center
        JPanel center = new JPanel(new GridLayout(4,2,20,20));
        center.setBorder(BorderFactory.createEmptyBorder(30 , 30 , 30 , 30));
        id = new JLabel("아이디");
        id.setFont(new Font("AppleSDGothicNeoR", Font.BOLD, 15));
        center.add(id);

        tId = new JTextField();
        tId.setColumns(15);
        center.add(tId);

        blank = new JLabel();
        blank.setForeground(Color.RED);
        center.add(blank);

        btnCheck = new JButton("중복체크");
        btnCheck.setFont(new Font("AppleSDGothicNeoR", Font.BOLD, 15));
        center.add(btnCheck);

        pwd = new JLabel("패스워드");
        pwd.setFont(new Font("AppleSDGothicNeoR", Font.BOLD, 15));
        center.add(pwd);

        tPwd = new JPasswordField();
        tPwd.setColumns(15);
        center.add(tPwd);

        name = new JLabel("이름");
        name.setFont(new Font("AppleSDGothicNeoR", Font.BOLD, 15));
        center.add(name);

        tName = new JTextField();
        tName.setColumns(15);
        center.add(tName);

        mPanel.add(center,BorderLayout.CENTER);

        //south
        btn = new JButton("저장");
        btn.setFont(new Font("AppleSDGothicNeoR", Font.BOLD, 15));
        mPanel.add(btn,BorderLayout.SOUTH);

        setVisible(true);

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String aId = tId.getText();
                String aPwd = tPwd.getText();
                String aName = tName.getText();

                if(aId.isEmpty() || aPwd.isEmpty() || aName.isEmpty())
                    JOptionPane.showMessageDialog(null, "모두 입력하시오.");
                else if(checkId==0)
                    JOptionPane.showMessageDialog(null, "아이디 사용 불가");
                else {
                    userController.addUser(aId,aPwd,aName);
                    JOptionPane.showMessageDialog(null, "회원가입 완료");
                    dispose();
                }
            }
        });
        btnCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String aId = tId.getText();
                if (aId.isEmpty()) JOptionPane.showMessageDialog(null, "아이디를 입력하시오.");
                else {
                    checkId = userController.checkId(aId);
                    if (checkId == 1) blank.setText("사용 가능");
                    else if (checkId == 0) blank.setText("사용 불가능");
                }
            }
        });
    }
}
