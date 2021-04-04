package com.baykus.butget.utils.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.baykus.butget.models.Users;
import com.baykus.butget.models.expense;
import com.baykus.butget.models.income;
import com.baykus.butget.utils.ButgetUtil;
import com.baykus.butget.utils.dao.UsersDBDao;

public class Login extends JFrame {
	private JLabel lblMemoryCardGame;
	private JTextField txtKulAd;
	private JPasswordField txtSifre;
	private JLabel lblOyuncuAd;
	private JLabel lblSifre;
	private JLabel label;
	private JButton btnLgn;
	private JButton btnKayt;
	private JLabel lblNotgirisYapmakIcin;
	public List<Users> liste ;
	public UsersDBDao dao;
	
	
	public Login() {
		getContentPane().setForeground(Color.BLACK);
		Initialize();
	}

	private void Initialize() {
		getContentPane().setBackground(Color.WHITE);
		setFont(new Font("Blackadder ITC", Font.PLAIN, 12));
		setBackground(new Color(255, 200, 0));
		setTitle("GEL\u0130R G\u0130DER Y\u00D6NET\u0130M\u0130");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setForeground(Color.ORANGE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\Java\\workspace\\swingworkspace\\IncomeAndExpense\\images\\income-expense.png"));
		getContentPane().setLayout(null);
		getContentPane().add(getLblMemoryCardGame());
		getContentPane().add(getTxtKulAd());
		getContentPane().add(getTxtSifre());
		getContentPane().add(getLblOyuncuAd());
		getContentPane().add(getLblSifre());
		getContentPane().add(getLabel());
		getContentPane().add(getBtnLgn());
		getContentPane().add(getBtnKayt());
		getContentPane().add(getLblNotgirisYapmakIcin());
		setBounds(200, 100, 620, 550);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private JLabel getLblMemoryCardGame() {
		if (lblMemoryCardGame == null) {
			lblMemoryCardGame = new JLabel("GELiR GiDER Y\u00F6NETiMi");
			lblMemoryCardGame.setFont(new Font("Viner Hand ITC", Font.BOLD | Font.ITALIC, 45));
			lblMemoryCardGame.setForeground(Color.DARK_GRAY);
			lblMemoryCardGame.setBounds(20, 11, 584, 81);
		}
		return lblMemoryCardGame;
	}

	public JTextField getTxtKulAd() {
		if (txtKulAd == null) {
			txtKulAd = new JTextField();
			txtKulAd.setBounds(268, 356, 174, 28);
			txtKulAd.setColumns(10);
		}
		return txtKulAd;
	}

	private JPasswordField getTxtSifre() {
		if (txtSifre == null) {
			txtSifre = new JPasswordField();
			txtSifre.setBounds(268, 395, 174, 28);
		}
		return txtSifre;
	}

	private JLabel getLblOyuncuAd() {
		if (lblOyuncuAd == null) {
			lblOyuncuAd = new JLabel("Kullan\u0131c\u0131 Ad\u0131 :");
			lblOyuncuAd.setFont(new Font("Viner Hand ITC", Font.BOLD | Font.ITALIC, 18));
			lblOyuncuAd.setForeground(Color.DARK_GRAY);
			lblOyuncuAd.setBounds(101, 353, 167, 37);
		}
		return lblOyuncuAd;
	}

	private JLabel getLblSifre() {
		if (lblSifre == null) {
			lblSifre = new JLabel("             Sifre :");
			lblSifre.setForeground(Color.DARK_GRAY);
			lblSifre.setFont(new Font("Viner Hand ITC", Font.BOLD | Font.ITALIC, 18));
			lblSifre.setBounds(111, 388, 131, 42);
		}
		return lblSifre;
	}

	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("");
			label.setBackground(SystemColor.text);
			label.setIcon(new ImageIcon("D:\\Java\\workspace\\swingworkspace\\IncomeAndExpense\\images\\Gelir-Gider-1 (2).png"));
			label.setBounds(80, 29, 536, 345);
		}
		return label;
	}

	private JButton getBtnLgn() {
		if (btnLgn == null) {
			btnLgn = new JButton("L O G I N");
			btnLgn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					UsersDBDao dao = new UsersDBDao();
					Users temp = new Users();
					
					temp.setUserName(txtKulAd.getText());
					temp.setPassword(txtSifre.getText());
					liste = dao.search(temp);
					
					if (liste.size()>0&&txtKulAd.getText()!="") {
//						JOptionPane.showMessageDialog(Login.this,"GÝRÝÞ BAÞARILI");
						ButgetUtil.loginUser = liste.get(0);
						
						new MainScreen().setVisible(true);
						Login.this.dispose();
					}else  {
						JOptionPane.showMessageDialog(Login.this, "GÝRÝÞ BAÞARISIZ");
					}
					
					

				}
			});
			btnLgn.setFont(new Font("Viner Hand ITC", Font.BOLD | Font.ITALIC, 17));
			btnLgn.setBounds(362, 440, 123, 42);
			btnLgn.setBackground(Color.WHITE);
		}
		return btnLgn;
	}
	private JButton getBtnKayt() {
		if (btnKayt == null) {
			btnKayt = new JButton("SIGN  UP");
			btnKayt.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					
					
					
				}
			});
			btnKayt.setFont(new Font("Viner Hand ITC", Font.BOLD | Font.ITALIC, 16));
			btnKayt.setBackground(Color.WHITE);
			btnKayt.setBounds(132, 441, 123, 41);
		}
		return btnKayt;
	}
	private JLabel getLblNotgirisYapmakIcin() {
		if (lblNotgirisYapmakIcin == null) {
			lblNotgirisYapmakIcin = new JLabel("NOT:Giris yapmak icin l\u00FCtfen kayit yapiniz...");
			lblNotgirisYapmakIcin.setBackground(Color.WHITE);
			lblNotgirisYapmakIcin.setForeground(Color.BLACK);
			lblNotgirisYapmakIcin.setFont(new Font("Viner Hand ITC", Font.PLAIN, 11));
			lblNotgirisYapmakIcin.setBounds(74, 486, 253, 14);
		}
		return lblNotgirisYapmakIcin;
	}
}
