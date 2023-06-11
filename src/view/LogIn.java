package view;

import controller.admin.AdminController;
import controller.user.UserController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogIn extends JFrame {
	private JPanel lPanel,Main,btnMain;
	private JLabel lLogin, lId, lPassword;
	private JTextField tId;
	private JPasswordField tPassword;
	private JButton login, register, exit;
	private JRadioButton tUser, tAdmin;
	AdminController admin = new AdminController();
	UserController user = new UserController();

	LogIn(){
		this.setTitle("Start");
		this.setResizable(true);
		this.setSize(350, 400);
		this.setLocationRelativeTo(null); //가운데 띄우기

		//레이아웃
		this.setLayout(new BorderLayout());
		lPanel = new JPanel();
		lPanel.setLayout(new BorderLayout());
		this.add(lPanel);

		//로그인 문장
		lLogin = new JLabel("로그인");
		lLogin.setFont(new Font("AppleSDGothicNeoR", Font.BOLD, 30));
		lLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lLogin.setPreferredSize(new Dimension(120, 120));
		lPanel.add(lLogin, BorderLayout.NORTH);

		//아이디 비번 입력
		Main = new JPanel(new GridLayout(3, 2, 15, 15));

		lId = new JLabel("아이디");
		lId.setFont(new Font("AppleSDGothicNeoR", Font.BOLD, 15));
		lId.setHorizontalAlignment(SwingConstants.CENTER);
		Main.add(lId);

		tId = new JTextField();
		tId.setColumns(15);
		Main.add(tId);

		lPassword = new JLabel("비밀번호");
		lPassword.setFont(new Font("AppleSDGothicNeoR", Font.BOLD, 15));
		lPassword.setHorizontalAlignment(SwingConstants.CENTER);
		Main.add(lPassword);

		tPassword = new JPasswordField();
		tPassword.setColumns(15);
		Main.add(tPassword);

		//라디오버튼
		tUser = new JRadioButton("User");
		tUser.setHorizontalAlignment(SwingConstants.CENTER);
		tAdmin = new JRadioButton("Admin");
		tAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		tUser.setSelected(true);

		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(tUser);
		buttonGroup.add(tAdmin);

		Main.add(tUser);
		Main.add(tAdmin);

		lPanel.add(Main, BorderLayout.WEST);

		//밑 버튼
		btnMain = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 60));

		login = new JButton("로그인");
		btnMain.add(login);
		register = new JButton("회원가입");
		btnMain.add(register);
		exit = new JButton("닫기");
		btnMain.add(exit);
		lPanel.add(btnMain, BorderLayout.SOUTH);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String id = tId.getText();
				String password = tPassword.getText();

				if(!(id.isEmpty()) && !(password.isEmpty())) {
					if(tAdmin.isSelected()) {
						int value = admin.logIn(id, password);
						if (value == 0) {
							JOptionPane.showMessageDialog(null, "로그인 완료");
							dispose();
							new AdminMain();
						} else {
							JOptionPane.showMessageDialog(null, "관리자 로그인 실패");
						}
					}else{
						int value = user.userLogIn(id, password);
						if (value == 0) {
							JOptionPane.showMessageDialog(null, "로그인 완료");
							dispose();
							view.Main2 m = new Main2(id);
						} else {
							JOptionPane.showMessageDialog(null, "사용자 로그인 실패");
						}
					}
				}else {
					JOptionPane.showMessageDialog(null, "ID PWD 입력");
				}
			}
		});

		//회원가입 액션
		register.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new JoinUser();
			}
		});

		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

	public static void main(String[] args) {
		LogIn st = new LogIn();
	}

}
