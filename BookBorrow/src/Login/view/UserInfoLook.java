package Login.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import bookborrow.dao.BookDao;
import bookborrow.dao.UserDao;
import bookborrow.dao.impl.BookDaoImpl;
import bookborrow.dao.impl.UserDaoImpl;
import bookborrow.entity.Book;
import bookborrow.entity.User;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class UserInfoLook extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField tf_userName;
	private JTextField tf_userPhone;
	private JTextField tf_userPassword;
	private JTextField tf_userLevel;
	private JTextField tf_userMoney;
	private JTextField tf_userName_search;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInfoLook frame = new UserInfoLook();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void fillTable() {
		//初始化
		DefaultTableModel defaultTable = (DefaultTableModel)table.getModel();
		UserDao userdao=new UserDaoImpl();
		List<User> userlist= new ArrayList<User>();
		defaultTable.setRowCount(0);
		userlist=userdao.getAllUser();
		
		for(User tempuser :userlist) {
			Vector v = new Vector<>();
			v.add(tempuser.getName());
			v.add(tempuser.getPhone());
			v.add(tempuser.getPassword());
			v.add(tempuser.getLevel());
			v.add(tempuser.getMoney());
			defaultTable.addRow(v);//添加用户
		}
	
	}
	
	public void fillTable(String userName) {
		//初始化
		DefaultTableModel defaultTable = (DefaultTableModel)table.getModel();
		UserDao userdao=new UserDaoImpl();
		List<User> userlist= new ArrayList<User>();
		defaultTable.setRowCount(0);
		userlist=userdao.getAllUser();
		String sql="select * from users where name = ? ";
		Object[] param= {userName};
		userlist=userdao.getSomeUser(sql, param);
		
		for(User tempuser :userlist) {
			
			Vector v = new Vector<>();
			v.add(tempuser.getName());
			v.add(tempuser.getPhone());
			v.add(tempuser.getPassword());
			v.add(tempuser.getLevel());
			v.add(tempuser.getMoney());
			defaultTable.addRow(v);//添加用户
		}
	
	}

	/**
	 * Create the frame.
	 */
	public UserInfoLook() {
		setTitle("\u7528\u6237\u4FE1\u606F");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 621, 511);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(47, 61, 498, 191);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.print("被选中了");
				int row = table.getSelectedRow();
				String userName = table.getValueAt(row, 0).toString();
				String userPhone = table.getValueAt(row, 1).toString();
				String userPassword = table.getValueAt(row, 2).toString();
				String userLevel = table.getValueAt(row, 3).toString();
				String userMoney = table.getValueAt(row, 4).toString();
				System.out.print(userName+userPhone+userPassword+userLevel+userMoney);
				
				tf_userName.setText(userName);
				tf_userPhone.setText(userPhone);
				tf_userPassword.setText(userPassword);
				tf_userLevel.setText(userLevel);
				tf_userMoney.setText(userMoney);
				
				
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7528\u6237\u540D", "\u7528\u6237\u7535\u8BDD\u53F7\u7801", "\u7528\u6237\u5BC6\u7801", "\u7528\u6237\u7B49\u7EA7", "\u5269\u4F59\u91D1\u989D"
			}
		));
		table.getColumnModel().getColumn(1).setPreferredWidth(110);
		scrollPane.setViewportView(table);
		
		//查询数据库中的用户列表，将其放入界面表格中
		fillTable();
		
		JButton exitButton = new JButton("\u9000\u51FA");
		exitButton.addActionListener(new ActionListener() {
			//退出
			public void actionPerformed(ActionEvent e) {
				UserInfoLook.this.setVisible(false);
			}
		});
		exitButton.setFont(new Font("宋体", Font.BOLD, 20));
		exitButton.setBounds(489, 401, 100, 50);
		contentPane.add(exitButton);
		
		JPanel panel = new JPanel();
		panel.setBounds(47, 265, 408, 186);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u7528\u6237\u540D\uFF1A");
		lblNewLabel.setBounds(14, 13, 72, 18);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u7528\u6237\u7535\u8BDD\uFF1A");
		lblNewLabel_1.setBounds(14, 44, 84, 18);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u7528\u6237\u5BC6\u7801\uFF1A");
		lblNewLabel_2.setBounds(14, 81, 84, 18);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\u7528\u6237\u7B49\u7EA7\uFF1A");
		lblNewLabel_3.setBounds(14, 112, 75, 18);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("\u5269\u4F59\u91D1\u989D\uFF1A");
		lblNewLabel_4.setBounds(14, 143, 84, 18);
		panel.add(lblNewLabel_4);
		
		tf_userName = new JTextField();
		tf_userName.setBounds(100, 10, 111, 24);
		panel.add(tf_userName);
		tf_userName.setColumns(10);
		
		tf_userPhone = new JTextField();
		tf_userPhone.setBounds(100, 41, 111, 24);
		panel.add(tf_userPhone);
		tf_userPhone.setColumns(10);
		
		tf_userPassword = new JTextField();
		tf_userPassword.setBounds(100, 75, 111, 24);
		panel.add(tf_userPassword);
		tf_userPassword.setColumns(10);
		
		tf_userLevel = new JTextField();
		tf_userLevel.setBounds(100, 106, 111, 24);
		panel.add(tf_userLevel);
		tf_userLevel.setColumns(10);
		
		tf_userMoney = new JTextField();
		tf_userMoney.setBounds(100, 140, 111, 24);
		panel.add(tf_userMoney);
		tf_userMoney.setColumns(10);
		
		JButton updataUserButton = new JButton("\u4FEE\u6539\u7528\u6237\u4FE1\u606F");
		updataUserButton.addActionListener(new ActionListener() {
			//修改用户信息
			public void actionPerformed(ActionEvent e) {
				String userName = tf_userName.getText();
				String userPhone = tf_userPhone.getText();
				String userPassword = tf_userPassword.getText();
				String userLevel = tf_userLevel.getText();
				String userMoney  = tf_userMoney.getText();
				
				String sql="update users set password=?, phone=?, level=?, money=? where name=?";
				Object[] param= {userPassword, userPhone,userLevel,userMoney,userName};
				UserDao udao=new UserDaoImpl();
				int count=udao.updateUser(sql, param);
				if(count>0) {
					JOptionPane.showMessageDialog(UserInfoLook.this,"修改成功！");
					fillTable();
				}
				else {
					JOptionPane.showMessageDialog(UserInfoLook.this,"修改失败！");
				}

				//在数据库中更新该用户信息
			}
		});
		updataUserButton.setFont(new Font("宋体", Font.BOLD, 18));
		updataUserButton.setBounds(238, 130, 152, 40);
		panel.add(updataUserButton);
		
		JButton addUserButton = new JButton("\u6DFB\u52A0\u7528\u6237");
		addUserButton.addActionListener(new ActionListener() {
			//添加用户
			public void actionPerformed(ActionEvent e) {
				String userName = tf_userName.getText();
				String userPhone = tf_userPhone.getText();
				String userPassword = tf_userPassword.getText();
				String userLevel = tf_userLevel.getText();
				String userMoney  = tf_userMoney.getText();
				
				int intLevel=Integer.parseInt(userLevel);
				Object[] param= {userName, userPassword,userPhone,intLevel,userMoney};
				UserDao udao=new UserDaoImpl();
				String sql="insert into users(name,password,phone,level,money) values(?,?,?,?,?)";
				int count=udao.addUser(sql, param);
				if(count>0) {
					JOptionPane.showMessageDialog(UserInfoLook.this,"添加成功！");
					fillTable();
				}
				else {
					JOptionPane.showMessageDialog(UserInfoLook.this,"添加失败！");
				}

				//从数据库中添加该用户
			}
		});
		addUserButton.setFont(new Font("宋体", Font.BOLD, 18));
		addUserButton.setBounds(238, 68, 120, 40);
		panel.add(addUserButton);
		
		JButton deleteUserButton = new JButton("\u5220\u9664\u7528\u6237");
		deleteUserButton.addActionListener(new ActionListener() {
			//删除用户
			public void actionPerformed(ActionEvent e) {
				String userName = tf_userName.getText();
				String sql="delete from users where name=?";
				Object[] param={userName};
				UserDao userDao=new UserDaoImpl();
				int count=userDao.updateUser( sql, param);
				if(count>0) {
					JOptionPane.showMessageDialog(UserInfoLook.this,"删除成功！");
					fillTable();
				}
				else {
					JOptionPane.showMessageDialog(UserInfoLook.this,"删除失败！");
				}

				//从数据库中移除该用户
			}
		});
		deleteUserButton.setFont(new Font("宋体", Font.BOLD, 18));
		deleteUserButton.setBounds(238, 13, 120, 40);
		panel.add(deleteUserButton);
		
		JLabel label = new JLabel("\u7528\u6237\u540D\uFF1A");
		label.setBounds(104, 25, 72, 18);
		contentPane.add(label);
		
		tf_userName_search = new JTextField();
		tf_userName_search.setBounds(202, 22, 156, 24);
		contentPane.add(tf_userName_search);
		tf_userName_search.setColumns(10);
		
		JButton searchButton = new JButton("\u641C\u7D22");
		searchButton.setFont(new Font("宋体", Font.BOLD, 16));
		searchButton.addActionListener(new ActionListener() {
			//搜索用户
			public void actionPerformed(ActionEvent e) {
				String userName = tf_userName_search.getText();
				fillTable(userName);
				
			}
		});
		searchButton.setBounds(386, 21, 100, 30);
		contentPane.add(searchButton);
	}

}
