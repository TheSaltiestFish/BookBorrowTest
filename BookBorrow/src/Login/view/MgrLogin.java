package Login.view;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bookborrow.dao.AdministratorDao;
import bookborrow.dao.impl.AdministratorDaoImpl;
import bookborrow.entity.Administrator;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MgrLogin extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MgrLogin frame = new MgrLogin();
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
	public MgrLogin() {
		setTitle("\u7BA1\u7406\u5458\u767B\u9646");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u7528\u6237\u540D\uFF1A");
		label.setBounds(77, 39, 72, 18);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u5BC6   \u7801\uFF1A");
		label_1.setBounds(77, 95, 72, 18);
		contentPane.add(label_1);
		
		textField = new JTextField();
		textField.setBounds(163, 36, 146, 24);
		contentPane.add(textField);
		textField.setColumns(20);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(20);
		passwordField.setBounds(163, 92, 146, 24);
		contentPane.add(passwordField);
		
		JButton loginButton = new JButton("\u767B\u5F55");
		loginButton.addActionListener(new ActionListener() {
			//��¼��ť�¼�
			public void actionPerformed(ActionEvent e) {
				String name = textField.getText();
				String password = new String(passwordField.getPassword());
				AdministratorDao ad=new AdministratorDaoImpl();
				Administrator admin=new Administrator();
				String sql1="select * from administrator where name=?";
				Object[] param1= {name};
				admin=ad.getAdministrator(sql1, param1);//��ѯ
				
				
				if(admin.getPassword().equals(password)) {  //������ȷ
					System.out.print("����������ȷ������");
					MgrMenu mgr = new MgrMenu();
					mgr.setVisible(true);
					MgrLogin.this.setVisible(false);
					
				}else { //�������
					JOptionPane.showMessageDialog(MgrLogin.this,"�û���������������󣡣���");
				}
			}
		});
		loginButton.setBounds(61, 145, 113, 27);
		contentPane.add(loginButton);
		
		JButton resetButton = new JButton("\u91CD\u7F6E");
		resetButton.addActionListener(new ActionListener() {
			//���ð�ť�¼�
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				passwordField.setText("");
			}
		});
		resetButton.setBounds(217, 145, 113, 27);
		contentPane.add(resetButton);
	}
}
