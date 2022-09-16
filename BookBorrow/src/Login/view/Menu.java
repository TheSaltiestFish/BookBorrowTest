package Login.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import java.awt.Color;

public class Menu extends JFrame {

	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
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
	public Menu() {
		setResizable(false);
		setFont(new Font("Dialog", Font.BOLD, 12));
		setIconImage(Toolkit.getDefaultToolkit().getImage(Menu.class.getResource("/image/book1.png")));
		setTitle("\u793E\u533A\u56FE\u4E66\u9986\u501F\u9605\u7CFB\u7EDF");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 670, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel bookLabel = new JLabel("\u793E\u533A\u56FE\u4E66\u9986\u501F\u9605\u7CFB\u7EDF");
		bookLabel.setBackground(Color.GRAY);
		bookLabel.setForeground(Color.YELLOW);
		bookLabel.setIcon(new ImageIcon(Menu.class.getResource("/image/book1.png")));
		bookLabel.setFont(new Font("宋体", Font.BOLD, 36));
		bookLabel.setBounds(110, 31, 410, 70);
		contentPane.add(bookLabel);
		
		JButton mgrLoginButton = new JButton("\u7BA1\u7406\u5458\u767B\u9646");
		mgrLoginButton.setFont(new Font("宋体", Font.BOLD, 20));
		
		//管理员登陆
		mgrLoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MgrLogin mgr = new MgrLogin();
				mgr.setVisible(true);
				Menu.this.setVisible(false);
			}
		});
		mgrLoginButton.setBounds(118, 161, 150, 50);
		contentPane.add(mgrLoginButton);
		
		JButton userLoginButton = new JButton("\u7528\u6237\u767B\u5F55");
		
		//用户登录
		userLoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserLogin user = new UserLogin();
				user.setVisible(true);
				Menu.this.setVisible(false);
			}
		});
		userLoginButton.setFont(new Font("宋体", Font.BOLD, 20));
		userLoginButton.setBounds(370, 161, 150, 50);
		contentPane.add(userLoginButton);
		
		JLabel label = new JLabel("");
		label.setBackground(Color.WHITE);
		label.setIcon(new ImageIcon(Menu.class.getResource("/image/background.png")));
		label.setBounds(0, 0, 668, 410);
		contentPane.add(label);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
