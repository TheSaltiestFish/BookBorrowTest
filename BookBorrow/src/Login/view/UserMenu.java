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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import bookborrow.dao.BookDao;
import bookborrow.dao.HistoryDao;
import bookborrow.dao.UserDao;
import bookborrow.dao.impl.BookDaoImpl;
import bookborrow.dao.impl.HistoryDaoImpl;
import bookborrow.dao.impl.UserDaoImpl;
import bookborrow.entity.Book;
import bookborrow.entity.User;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public class UserMenu extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField tf_bookName_search;
	private JTextField tf_bname;
	private JTextField tf_btype;
	private JTextField tf_bprice;
	private JTextField tf_bnum;
	public static String username=UserLogin.username;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserMenu frame = new UserMenu();
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

	/**
	 * Create the frame.
	 */
	public UserMenu() {
		setTitle("\u7528\u6237\u7A97\u53E3");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 748, 580);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu borrowedBookInfo = new JMenu("\u501F\u4E66\u8BB0\u5F55");
		menuBar.add(borrowedBookInfo);
		
		JMenuItem menuItem = new JMenuItem("\u501F\u9605\u4E66\u7C4D");
		menuItem.addActionListener(new ActionListener() {
			//借阅图书事件
			public void actionPerformed(ActionEvent e) {
				BookBorrowingRecords bookBorrowing = new BookBorrowingRecords();
				bookBorrowing.setVisible(true);
			}
		});
		borrowedBookInfo.add(menuItem);
		
		JMenu menu = new JMenu("\u7528\u6237\u4FE1\u606F");
		menuBar.add(menu);
		
		JMenuItem menuItem_2 = new JMenuItem("\u57FA\u672C\u4FE1\u606F");
		menuItem_2.addActionListener(new ActionListener() {
			//弹出用户信息窗口
			public void actionPerformed(ActionEvent e) {
				UserInfo userInfo = new UserInfo();
				userInfo.setVisible(true);
			}
		});
		menu.add(menuItem_2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton searchBookbutton = new JButton("\u641C\u7D22\u56FE\u4E66");
		searchBookbutton.addActionListener(new ActionListener() {
			//图书搜索
			public void actionPerformed(ActionEvent e) {
				String bookName = tf_bookName_search.getText();

				fillTable(bookName);
				
				System.out.print(bookName);
			}
		});
		searchBookbutton.setFont(new Font("宋体", Font.BOLD, 15));
		searchBookbutton.setBounds(428, 57, 113, 27);
		contentPane.add(searchBookbutton);
		
		JButton exitButton = new JButton("\u9000\u51FA");
		exitButton.setFont(new Font("宋体", Font.BOLD, 20));
		exitButton.addActionListener(new ActionListener() {
			//退出
			public void actionPerformed(ActionEvent arg0) {
				UserMenu.this.setVisible(false);
				Menu newmenu=new Menu();
				newmenu.setVisible(true);
			}
		});
		exitButton.setBounds(573, 415, 130, 40);
		contentPane.add(exitButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(75, 116, 599, 213);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			//点击表格的某一列产生事件
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.print("被选中了");
				System.out.print(UserLogin.username);
				int row = table.getSelectedRow();
				String bId = table.getValueAt(row, 0).toString();
				String bPrice = table.getValueAt(row, 1).toString();
				String bName = table.getValueAt(row, 2).toString();
				String bType = table.getValueAt(row, 3).toString();
				
				
				System.out.print(bId+bName+bType+bPrice);
				
				tf_bname.setText(bName);

				tf_btype.setText(bType);
				tf_bprice.setText(bPrice);
				tf_bnum.setText(bId);
				
				
				
			}
		});
		table.setFont(new Font("宋体", Font.BOLD, 16));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u4E66\u540D", "\u7C7B\u522B", "\u4EF7\u683C", "\u72B6\u6001"
			}
		));
		
		
		
		//查询数据库中的图书列表，将其放入界面表格中
		fillTable();
		
		
		
		scrollPane.setViewportView(table);
		
		JLabel label = new JLabel("\u4E66\u540D\uFF1A");
		label.setBounds(122, 61, 45, 18);
		contentPane.add(label);
		
		tf_bookName_search = new JTextField();
		tf_bookName_search.setBounds(167, 58, 133, 24);
		contentPane.add(tf_bookName_search);
		tf_bookName_search.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBounds(75, 342, 446, 152);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel label_3 = new JLabel("\u4E66\u540D\uFF1A");
		label_3.setBounds(14, 13, 72, 18);
		panel.add(label_3);
		
		JLabel label_5 = new JLabel("\u7C7B\u522B\uFF1A");
		label_5.setBounds(230, 10, 72, 18);
		panel.add(label_5);
		
		JLabel label_6 = new JLabel("\u4EF7\u683C\uFF1A");
		label_6.setBounds(14, 85, 72, 18);
		panel.add(label_6);
		
		tf_bname = new JTextField();
		tf_bname.setEditable(false);
		tf_bname.setBounds(64, 10, 152, 24);
		panel.add(tf_bname);
		tf_bname.setColumns(10);
		
		tf_btype = new JTextField();
		tf_btype.setEditable(false);
		tf_btype.setBounds(280, 7, 152, 24);
		panel.add(tf_btype);
		tf_btype.setColumns(10);
		
		tf_bprice = new JTextField();
		tf_bprice.setEditable(false);
		tf_bprice.setBounds(64, 82, 152, 24);
		panel.add(tf_bprice);
		tf_bprice.setColumns(10);
		
		JLabel lblNumLabel = new JLabel("\u7F16\u53F7\uFF1A");
		lblNumLabel.setBounds(230, 87, 58, 15);
		panel.add(lblNumLabel);
		
		tf_bnum = new JTextField();
		tf_bnum.setEditable(false);
		tf_bnum.setBounds(280, 84, 152, 21);
		panel.add(tf_bnum);
		tf_bnum.setColumns(10);
		
		JButton borrowBookButton = new JButton("\u501F\u9605\u6B64\u4E66");
		borrowBookButton.setBounds(573, 365, 130, 40);
		contentPane.add(borrowBookButton);
		borrowBookButton.setFont(new Font("宋体", Font.BOLD, 20));
		
		JButton btnRefreshButton = new JButton("\u5237\u65B0");
		btnRefreshButton.setFont(new Font("宋体", Font.BOLD, 15));
		btnRefreshButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillTable();
			}
		});
		btnRefreshButton.setBounds(573, 57, 113, 27);
		contentPane.add(btnRefreshButton);
		borrowBookButton.addActionListener(new ActionListener() {
			//借阅书籍
			public void actionPerformed(ActionEvent e) {
				String bookId = tf_bnum.getText();
				String bookName = tf_bname.getText();
				String bookType = tf_btype.getText();
				String bookPrice = tf_bprice.getText();
				BookDao bookdao1=new BookDaoImpl();
				Book book=new Book();
				String sql1="select * from book where id=? ";
				Object[] param1= {bookId};
				book=bookdao1.getBook(sql1, param1);//查询book
				if(book.getState().equals("Lend")){
					JOptionPane.showMessageDialog(UserMenu.this,"已经被借阅！");
				}else {
					UserDao userdao1=new UserDaoImpl();
					User user=new User();
					String sqlusername="select * from users where name=? ";
					Object[] paramuser= {UserLogin.username};
					user=userdao1.getUser(sqlusername, paramuser);//查询user
					//更新book状态
					String sql2="update book set state=? where id=?";
				    Object[] param2= {"Lend",bookId};
				    BookDao bookdao2=new BookDaoImpl();
				    int count1=bookdao2.updateBook(sql2, param2);
					//插入history
				    SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
				    sdf.applyPattern("yyyy-MM-dd");// a为am/pm的标记
				    Date date = new Date();// 获取系统当前时间
				    String lendtime=sdf.format(date); // 输出已经格式化的现在时间(24小时制)
				    String ddl=sdf.format(new Date((date.getTime()/1000+user.getLevel()*2* 24 * 60 * 60 )*1000));
				    String returntime=null;
				    String sql3="insert into history(bid,uname,bname,lendtime,ddl,returntime) values(?,?,?,?,?,?)";
				    Object[] param3= {bookId,user.getName(),book.getName(),lendtime,ddl,returntime};
				    HistoryDao historydao=new HistoryDaoImpl();
				    int count=historydao.updateHistory(sql3, param3);
				    if(count>0 && count1>0 ) {
				    	JOptionPane.showMessageDialog(UserMenu.this,"成功借阅:"+bookId+"号书籍");
				    	fillTable();
				    }
				}
			}
		});
	}
}
