package Login.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bookborrow.dao.UserDao;
import bookborrow.dao.impl.UserDaoImpl;
import bookborrow.entity.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserInfo extends JFrame {

	private JPanel contentPane;
	private JTextField tf_userName;
	private JTextField tf_userPassword;
	private JTextField tf_userPhone;
	private JTextField tf_userLevel;
	private JTextField tf_userMoney;
	public static String username=UserMenu.username;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInfo frame = new UserInfo();
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
	public UserInfo() {
		setTitle("\u7528\u6237\u57FA\u672C\u4FE1\u606F");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 457, 317);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u7528\u6237\u540D\uFF1A");
		lblNewLabel.setBounds(25, 27, 72, 18);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("\u7528\u6237\u5BC6\u7801\uFF1A");
		label.setBounds(25, 58, 75, 18);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u7528\u6237\u7535\u8BDD\uFF1A");
		label_1.setBounds(25, 89, 80, 18);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("\u7528\u6237\u7B49\u7EA7\uFF1A");
		label_2.setBounds(25, 120, 80, 18);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("\u5269\u4F59\u91D1\u989D\uFF1A");
		label_3.setBounds(25, 151, 80, 18);
		contentPane.add(label_3);
		
		tf_userName = new JTextField();
		tf_userName.setBounds(103, 24, 173, 24);
		contentPane.add(tf_userName);
		tf_userName.setColumns(10);
		
		tf_userPassword = new JTextField();
		tf_userPassword.setBounds(103, 55, 173, 24);
		contentPane.add(tf_userPassword);
		tf_userPassword.setColumns(10);
		
		tf_userPhone = new JTextField();
		tf_userPhone.setBounds(103, 86, 173, 24);
		contentPane.add(tf_userPhone);
		tf_userPhone.setColumns(10);
		
		tf_userLevel = new JTextField();
		tf_userLevel.setEditable(false);
		tf_userLevel.setBounds(103, 120, 173, 24);
		contentPane.add(tf_userLevel);
		tf_userLevel.setColumns(10);
		
		tf_userMoney = new JTextField();
		tf_userMoney.setEditable(false);
		tf_userMoney.setBounds(103, 151, 173, 24);
		contentPane.add(tf_userMoney);
		tf_userMoney.setColumns(10);
		
		
		//显示用户信息,将数据库中的用户信息导出
		UserDao userdao1=new UserDaoImpl();
		User user=new User();
		String sqlusername="select * from users where name=? ";
		Object[] paramuser= {UserLogin.username};
		user=userdao1.getUser(sqlusername, paramuser);//查询user
		
		tf_userName.setText(username);
		tf_userPassword.setText(user.getPassword());
		tf_userPhone.setText(user.getPhone());
		tf_userLevel.setText(user.getLevel()+"");
		tf_userMoney.setText(user.getMoney()+"元");
		
		
		JButton updataButton = new JButton("\u786E\u8BA4\u4FEE\u6539");
		updataButton.addActionListener(new ActionListener() {
			//修改用户信息
			public void actionPerformed(ActionEvent e) {
				String userName = tf_userName.getText();
				String userPassword = tf_userPassword.getText();
				String userPhone = tf_userPhone.getText();

				
				String sql="update users set password=?, phone=? where name=?";
				Object[] param= {userPassword, userPhone,userName};
				UserDao udao=new UserDaoImpl();
				int count=udao.updateUser(sql, param);
				if(count>0) {
					JOptionPane.showMessageDialog(UserInfo.this,"修改成功！");
					
				}
				else {
					JOptionPane.showMessageDialog(UserInfo.this,"修改失败！");
				}
				
				
				
				
				//将获取的信息存入数据库中
			}
			
		});
		updataButton.setFont(new Font("宋体", Font.BOLD, 20));
		updataButton.setBounds(290, 138, 130, 40);
		contentPane.add(updataButton);
		
		JButton button = new JButton("\u9000\u51FA");
		button.addActionListener(new ActionListener() {
			//退出
			public void actionPerformed(ActionEvent e) {
				UserInfo.this.setVisible(false);
			}
		});
		button.setFont(new Font("宋体", Font.BOLD, 18));
		button.setBounds(320, 222, 100, 40);
		contentPane.add(button);
	}
}
