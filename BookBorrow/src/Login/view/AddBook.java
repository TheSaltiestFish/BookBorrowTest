package Login.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bookborrow.dao.AdministratorDao;
import bookborrow.dao.BookDao;
import bookborrow.dao.impl.AdministratorDaoImpl;
import bookborrow.dao.impl.BookDaoImpl;
import bookborrow.entity.Administrator;
import bookborrow.entity.Book;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class AddBook extends JFrame {

	private JPanel contentPane;
	private JTextField bookName;
	private JTextField bookType;
	private JTextField bookPrice;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddBook frame = new AddBook();
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
	public AddBook() {
		setTitle("\u65B0\u589E\u56FE\u4E66");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label_1 = new JLabel("\u4E66\u540D\uFF1A");
		label_1.setBounds(43, 64, 72, 18);
		contentPane.add(label_1);
		
		JLabel label_3 = new JLabel("\u7C7B\u522B\uFF1A");
		label_3.setBounds(43, 96, 72, 18);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("\u4EF7\u683C\uFF1A");
		label_4.setBounds(43, 127, 72, 18);
		contentPane.add(label_4);
		
		JButton button = new JButton("\u63D0\u4EA4");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name = bookName.getText();
				String type = bookType.getText();
				String price = bookPrice.getText();
				//____________________________________________________
				BookDao bookdao=new BookDaoImpl();
				List<Book> booklist= new ArrayList<Book>();
				
				int num=0;
				booklist=bookdao.getAllBook();//查询book
				if(booklist.size()==0) {
					num=1;
				}
				else {
						for (int i = 0; i < booklist.size()-1; i++) {
						Book book1 = booklist.get(i);
						Book book2 = booklist.get(i+1);
						if((book1.getId()+1)!=book2.getId()) {
							num=book1.getId()+1;
						}
						}
						if(num==0) {
							Book book = booklist.get(booklist.size()-1);
							num=book.getId()+1;
						}
				}
				
				int intprice=Integer.parseInt(price);
				Book book=new Book(num,intprice,name,type,"Free");
				String sql="insert into book(id,price,name,type,state) values(?,?,?,?,?)";
				Object[] param={book.getId(),book.getPrice(),book.getName(),book.getType(),book.getState()};
				BookDao bookDao=new BookDaoImpl();
				int count=bookDao.addBook(sql,param);
				if(count>0) {
					System.out.println("成功添加！");

				}
				else
					System.out.println("添加失败！");
					//__________________________________________

				
				
				
				//新增图书提交到数据库
				JOptionPane.showMessageDialog(AddBook.this,"增添图书成功！");
			}
		});
		button.setBounds(43, 198, 113, 27);
		contentPane.add(button);
		
		JButton button_1 = new JButton("\u91CD\u7F6E");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//文本重置

				bookName.setText("");
				bookType.setText("");
				bookPrice.setText("");

				
			}
		});
		button_1.setBounds(170, 198, 113, 27);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("\u53D6\u6D88");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//取消添加图书
				AddBook.this.setVisible(false);
			}
		});
		button_2.setBounds(290, 198, 113, 27);
		contentPane.add(button_2);
		
		bookName = new JTextField();
		bookName.setBounds(132, 61, 246, 24);
		contentPane.add(bookName);
		bookName.setColumns(10);
		
		bookType = new JTextField();
		bookType.setBounds(132, 90, 246, 24);
		contentPane.add(bookType);
		bookType.setColumns(10);
		
		bookPrice = new JTextField();
		bookPrice.setBounds(132, 121, 246, 24);
		contentPane.add(bookPrice);
		bookPrice.setColumns(10);
	}

}
