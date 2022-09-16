package Login.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import bookborrow.dao.BookDao;
import bookborrow.dao.impl.BookDaoImpl;
import bookborrow.entity.*;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class MgrMenu extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField tf_bookSearch;
	private JTextField tf_bookId;
	private JTextField tf_bookName;
	private JTextField tf_bookType;
	private JTextField tf_bookPrice;
	private JTextField tf_bookStatus;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MgrMenu frame = new MgrMenu();
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
	public MgrMenu() {
		setTitle("\u7BA1\u7406\u5458\u7A97\u53E3");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 728, 596);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("\u56FE\u4E66\u7BA1\u7406");
		menuBar.add(menu);
		
		
		
		JMenuItem addBook = new JMenuItem("\u6DFB\u52A0\u56FE\u4E66");
		addBook.addActionListener(new ActionListener() {
			//添加图书
			public void actionPerformed(ActionEvent e) {
				AddBook add = new AddBook();
				add.setVisible(true);
			}
		});
		menu.add(addBook);
		
		JMenu menu_1 = new JMenu("\u7528\u6237\u7BA1\u7406");
		menuBar.add(menu_1);
		
		JMenuItem menuItem = new JMenuItem("\u7528\u6237\u4FE1\u606F");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserInfoLook userInfo = new UserInfoLook();
				userInfo.setVisible(true);
			}
		});
		menu_1.add(menuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
//		//一开始填充表格
//		fillTable(null);
		
		JButton searchBookButton = new JButton("\u56FE\u4E66\u641C\u7D22");
		searchBookButton.addActionListener(new ActionListener() {
			
			
			//图书搜索
			public void actionPerformed(ActionEvent e) {
				String bookName = tf_bookSearch.getText();
				System.out.print(bookName);
				fillTable(bookName);//填充表格
			}
		});
		searchBookButton.setBounds(517, 43, 113, 27);
		contentPane.add(searchBookButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBounds(92, 78, 538, 168);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			//显示选中信息
			public void mouseClicked(MouseEvent arg0) {
				System.out.print("被选中了");
				int row = table.getSelectedRow();
				String bId = table.getValueAt(row, 0).toString();
				String bName = table.getValueAt(row, 1).toString();
				String bType = table.getValueAt(row, 2).toString();
				String bPrice = table.getValueAt(row, 3).toString();
				String bState = table.getValueAt(row, 4).toString();
				System.out.print(bId+bName+bType+bPrice);
				
				tf_bookId.setText(bId);
				tf_bookName.setText(bName);
				tf_bookType.setText(bType);
				tf_bookPrice.setText(bPrice);
				tf_bookStatus.setText(bState);
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u4E66\u540D", "\u7C7B\u522B", "\u4EF7\u683C", "\u72B6\u6001"
			}
		));
		scrollPane.setViewportView(table);
		//查询数据库中的图书列表，将其放入界面表格中
		
		
		//一开始填充表格
		fillTable();
//		DefaultTableModel defaultTable = (DefaultTableModel)table.getModel();
//		defaultTable.addRow(new Object[] {"123","《悲惨的世界》","小说","56元"});//添加图书
		
		JLabel label = new JLabel("\u8F93\u5165\u4E66\u540D\u8FDB\u884C\u641C\u7D22\uFF1A");
		label.setBounds(97, 47, 135, 18);
		contentPane.add(label);
		
		tf_bookSearch = new JTextField();
		tf_bookSearch.setBounds(275, 44, 198, 24);
		contentPane.add(tf_bookSearch);
		tf_bookSearch.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBounds(92, 279, 425, 196);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel label_1 = new JLabel("\u7F16\u53F7\uFF1A");
		label_1.setBounds(10, 26, 99, 18);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("\u4E66\u540D\uFF1A");
		label_2.setBounds(10, 57, 72, 18);
		panel.add(label_2);
		
		JLabel label_4 = new JLabel("\u7C7B\u522B\uFF1A");
		label_4.setBounds(10, 91, 72, 18);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("\u4EF7\u683C\uFF1A");
		label_5.setBounds(10, 122, 72, 18);
		panel.add(label_5);
		
		tf_bookId = new JTextField();
		tf_bookId.setEditable(false);
		tf_bookId.setBounds(76, 23, 86, 24);
		panel.add(tf_bookId);
		tf_bookId.setColumns(10);
		
		tf_bookName = new JTextField();
		tf_bookName.setBounds(76, 54, 165, 24);
		panel.add(tf_bookName);
		tf_bookName.setColumns(10);
		
		tf_bookType = new JTextField();
		tf_bookType.setBounds(76, 88, 165, 24);
		panel.add(tf_bookType);
		tf_bookType.setColumns(10);
		
		tf_bookPrice = new JTextField();
		tf_bookPrice.setBounds(76, 119, 86, 24);
		panel.add(tf_bookPrice);
		tf_bookPrice.setColumns(10);
		
		JButton button = new JButton("\u4FEE\u6539\u56FE\u4E66\u4FE1\u606F");
		button.addActionListener(new ActionListener() {
			//修改图书信息
			public void actionPerformed(ActionEvent e) {
				//获取修改信息
				String bookId = tf_bookId.getText();
				String bookName = tf_bookName.getText();
				String bookType = tf_bookType.getText();
				String bookPrice = tf_bookPrice.getText();
				String bookStatus = tf_bookStatus.getText();
				
				BookDao bookDao=new BookDaoImpl();
				Book book=new Book();
				
				String sql="update book set name=? , type=? , price=?, state=? where id=?";
				Object[] param= {bookName,bookType,bookPrice,bookStatus,bookId};
				int count=bookDao.updateBook(sql, param);
				if(count>0) {
					JOptionPane.showMessageDialog(MgrMenu.this,"修改成功!");
					fillTable();
				}
				else {
					JOptionPane.showMessageDialog(MgrMenu.this,"修改失败!");
				}
				

			}
		});
		button.setFont(new Font("宋体", Font.BOLD, 18));
		button.setBounds(261, 129, 150, 50);
		panel.add(button);
		
		JButton btnDeletButton = new JButton("\u5220\u9664\u56FE\u4E66\r\n");
		btnDeletButton.setFont(new Font("宋体", Font.BOLD, 18));
		btnDeletButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bookId = tf_bookId.getText();
				String sql="delete from book where id=?";
				Object[] param={bookId};
				BookDao bookDao=new BookDaoImpl();
				int count=bookDao.deleteBook(sql,param);
				if(count>0) {
					JOptionPane.showMessageDialog(MgrMenu.this,"删除成功!");
					fillTable();
				}
				else {
					JOptionPane.showMessageDialog(MgrMenu.this,"删除失败!");
				}
			}
		});
		btnDeletButton.setBounds(261, 66, 150, 50);
		panel.add(btnDeletButton);
		
		JLabel lblStatusLabel = new JLabel("\u72B6\u6001\uFF1A");
		lblStatusLabel.setBounds(10, 155, 58, 15);
		panel.add(lblStatusLabel);
		
		tf_bookStatus = new JTextField();
		tf_bookStatus.setBounds(76, 152, 66, 21);
		panel.add(tf_bookStatus);
		tf_bookStatus.setColumns(10);
		
		JButton exitButton = new JButton("\u9000\u51FA");
		exitButton.setFont(new Font("宋体", Font.BOLD, 18));
		exitButton.addActionListener(new ActionListener() {
			//退出
			public void actionPerformed(ActionEvent e) {
				MgrMenu.this.setVisible(false);
				Menu newmenu=new Menu();
				newmenu.setVisible(true);
			}
		});
		exitButton.setBounds(565, 455, 100, 40);
		contentPane.add(exitButton);
		
		JButton Refresh = new JButton("\u5237\u65B0");
		Refresh.setFont(new Font("宋体", Font.BOLD, 18));
		Refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillTable();
			}
		});
		Refresh.setBounds(565, 411, 100, 40);
		contentPane.add(Refresh);
	}
	
	
	
	public void fillTable() {
		//初始化
		DefaultTableModel defaultTable = (DefaultTableModel)table.getModel();
		BookDao bookdao=new BookDaoImpl();
		List<Book> booklist= new ArrayList<Book>();
		defaultTable.setRowCount(0);
		booklist=bookdao.getAllBook();
		
		for(Book tempbook :booklist) {
			Vector v = new Vector<>();
			v.add(tempbook.getId());
			v.add(tempbook.getName());
			v.add(tempbook.getType());
			v.add(tempbook.getPrice());
			v.add(tempbook.getState());
			defaultTable.addRow(v);//添加图书
		}
	
	}
	
	//返会图书列表函数
	public void fillTable(String bookName) {
		
		DefaultTableModel defaultTable = (DefaultTableModel)table.getModel();
		defaultTable.setRowCount(0);
		
		BookDao bookdao=new BookDaoImpl();
		List<Book> booklist= new ArrayList<Book>();
		
		
//		Book book=new Book();
		String sql="select * from book where name = ? ";
		Object[] param= {bookName};
		booklist=bookdao.getSomeBook(sql, param);
//		book=bookdao.getBook(sql, param);//查询book
		
		for(Book tempbook :booklist) {
			
			Vector v = new Vector<>();
			v.add(tempbook.getId());
			v.add(tempbook.getName());
			v.add(tempbook.getType());
			v.add(tempbook.getPrice());
			v.add(tempbook.getState());
			defaultTable.addRow(v);//添加图书
		}
		
	
	}
}
