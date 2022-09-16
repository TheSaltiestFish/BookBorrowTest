package Login.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bookborrow.dao.AdministratorDao;
import bookborrow.dao.UserDao;
import bookborrow.dao.impl.AdministratorDaoImpl;
import bookborrow.dao.impl.UserDaoImpl;
import bookborrow.entity.Administrator;
import bookborrow.entity.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserLogin extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
    public static String username;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserLogin frame = new UserLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UserLogin() {
		setTitle("\u7528\u6237\u767B\u5F55");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u7528\u6237\u540D\uFF1A");
		label.setBounds(64, 46, 72, 18);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u5BC6  \u7801\uFF1A");
		label_1.setBounds(64, 112, 72, 18);
		contentPane.add(label_1);
		
		textField = new JTextField();
		textField.setBounds(160, 43, 154, 24);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(160, 109, 154, 24);
		contentPane.add(passwordField);
		
		JButton loginButton = new JButton("\u767B\u5F55");
		loginButton.addActionListener(new ActionListener() {
			//用户登录按钮事件
			public void actionPerformed(ActionEvent e) {
				String name = textField.getText();
				String password = new String(passwordField.getPassword());
				UserDao ud=new UserDaoImpl();
				User us=new User();
				String sql1="select * from users where name=?";
				Object[] param1= {name};
				us=ud.getUser(sql1, param1);
				username=name;

					
				if(us.getPassword().equals(password)) {  //密码正确
					UserMenu user = new UserMenu();
					user.setVisible(true);
					UserLogin.this.setVisible(false);
					
				}else { //密码错误
					JOptionPane.showMessageDialog(UserLogin.this,"用户名或密码输入错误");
				}
			}
		});
		loginButton.setBounds(64, 155, 113, 27);
		contentPane.add(loginButton);
		
		JButton resetButton = new JButton("\u91CD\u7F6E");
		resetButton.addActionListener(new ActionListener() {
			//重置按钮事件
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				passwordField.setText("");
			}
		});
		resetButton.setBounds(201, 155, 113, 27);
		contentPane.add(resetButton);
	}

}
