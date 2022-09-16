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
import bookborrow.dao.HistoryDao;
import bookborrow.dao.UserDao;
import bookborrow.dao.impl.BookDaoImpl;
import bookborrow.dao.impl.HistoryDaoImpl;
import bookborrow.dao.impl.UserDaoImpl;
import bookborrow.entity.Book;
import bookborrow.entity.History;
import bookborrow.entity.User;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;

public class BookBorrowingRecords extends JFrame {

	private JPanel contentPane;
	private JTable table;
	public static String username=UserMenu.username;
	private JTextField tf_Bid;
	private JTextField tf_Bname;
	private JTextField tf_LendTime;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookBorrowingRecords frame = new BookBorrowingRecords();
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
		HistoryDao historydao=new HistoryDaoImpl();
		List<History> historylist= new ArrayList<History>();
		defaultTable.setRowCount(0);
		String sql="select * from history where uname = ? ";
		Object[] param= {username};
		historylist=historydao.getSomeHistory(sql, param);
		
		for(History temphistory :historylist) {
			Vector v = new Vector<>();
			v.add(temphistory.getBid());
			v.add(temphistory.getBname());
			v.add(temphistory.getLendtime());
			v.add(temphistory.getDdl());
			v.add(temphistory.getReturntime());
			defaultTable.addRow(v);//添加历史
		}
	
	}

	/**
	 * Create the frame.
	 */
	public BookBorrowingRecords() {
		setTitle("\u56FE\u4E66\u501F\u9605\u8BB0\u5F55");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 626, 454);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(56, 49, 505, 158);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				String bBid = table.getValueAt(row, 0).toString();
				String bBname = table.getValueAt(row, 1).toString();
				String bLendTime = table.getValueAt(row, 2).toString();
				

				tf_Bid.setText(bBid );
				tf_Bname.setText(bBname);
				tf_LendTime.setText(bLendTime);
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u7801", "\u4E66\u540D", "\u501F\u4E66\u65E5\u671F", "\u622A\u6B62\u65E5\u671F", "\u8FD8\u4E66\u65E5\u671F"
			}
		));
		scrollPane.setViewportView(table);
		
		
		JButton returnBookButton = new JButton("\u5F52\u8FD8\u56FE\u4E66");
		
		fillTable();
		
		returnBookButton.addActionListener(new ActionListener() {
			//用户归还图书
			public void actionPerformed(ActionEvent e) {
				
				UserDao userdao1=new UserDaoImpl();
				User user=new User();
				String sqlusername="select * from users where name=? ";
				Object[] paramuser= {username};
				user=userdao1.getUser(sqlusername, paramuser);//查询user
				
				
				
				//将选中的图书归还
				String bookId = tf_Bid.getText();
				
				
				
				//更新book状态
				String sql2="update book set state=? where id=?";
			    Object[] param2= {"Free",bookId};
			    BookDao bookdao2=new BookDaoImpl();
			    int count1=bookdao2.updateBook(sql2, param2);
			    
			    SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
			    sdf.applyPattern("yyyy-MM-dd");
			    Date date = new Date();// 获取系统当前时间
			    String returntime=sdf.format(date); // 输出已经格式化的现在时间(24小时制)
			    
			  //更新history
			    String sql3="update history set returntime=? where bid=? and uname=?";
			    Object[] param3= {returntime,bookId,user.getName()};
			    HistoryDao historydao=new HistoryDaoImpl();
			    int count=historydao.updateHistory(sql3, param3);
			    if(count>0 && count1>0) {
				    JOptionPane.showMessageDialog(BookBorrowingRecords.this,"成功归还");
				    fillTable();
			    }
			    
			    
			  //更新level
			    String sql="select * from history where uname=? and bid=? and returntime=?";
			    Object[] param= {user.getName(),bookId,returntime};
				HistoryDao hdao=new HistoryDaoImpl();
				History history=new History();
				history=hdao.getHistory(sql, param);
				int level=user.getLevel();
			    if(returntime.compareTo(history.getDdl())>0) {
			    	level=level-5;
			    	user.setLevel(level);	
			    }
			    else {
			    	if(level<30) {
			    		level=level+1;
			    	    user.setLevel(level);
			    	}
			    }
			    String sql4="update users set level=? where name=?";
			    Object[] param4= {level,user.getName()};
			    UserDao udao=new UserDaoImpl();
			    int count2=udao.updateUser(sql4, param4);
			   
			    
			    
			    //-------------------------

			}
		});
		returnBookButton.setFont(new Font("宋体", Font.BOLD, 18));
		returnBookButton.setBounds(421, 233, 140, 40);
		contentPane.add(returnBookButton);
		
		JButton button = new JButton("\u9000\u51FA");
		button.addActionListener(new ActionListener() {
			//退出
			public void actionPerformed(ActionEvent e) {
				BookBorrowingRecords.this.setVisible(false);
			}
		});
		button.setFont(new Font("宋体", Font.BOLD, 18));
		button.setBounds(421, 283, 140, 40);
		contentPane.add(button);
		
		JPanel panel = new JPanel();
		panel.setBounds(56, 217, 358, 113);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lb_Num = new JLabel("\u7F16\u7801\uFF1A");
		lb_Num.setBounds(10, 10, 58, 15);
		panel.add(lb_Num);
		
		JLabel lblNewLabel_1 = new JLabel("\u4E66\u540D\uFF1A");
		lblNewLabel_1.setBounds(10, 35, 58, 15);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u501F\u4E66\u65E5\u671F");
		lblNewLabel_2.setBounds(10, 60, 58, 15);
		panel.add(lblNewLabel_2);
		
		tf_Bid = new JTextField();
		tf_Bid.setEditable(false);
		tf_Bid.setBounds(78, 7, 129, 21);
		panel.add(tf_Bid);
		tf_Bid.setColumns(10);
		
		tf_Bname = new JTextField();
		tf_Bname.setEditable(false);
		tf_Bname.setBounds(78, 32, 129, 21);
		panel.add(tf_Bname);
		tf_Bname.setColumns(10);
		
		tf_LendTime = new JTextField();
		tf_LendTime.setEditable(false);
		tf_LendTime.setBounds(78, 57, 129, 21);
		panel.add(tf_LendTime);
		tf_LendTime.setColumns(10);
	}
}
