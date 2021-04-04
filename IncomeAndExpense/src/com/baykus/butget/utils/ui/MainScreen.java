package com.baykus.butget.utils.ui;

import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import com.baykus.butget.models.Users;
import com.baykus.butget.models.expense;
import com.baykus.butget.models.income;
import com.baykus.butget.utils.ButgetUtil;
import com.baykus.butget.utils.dao.DbServicessBase;
import com.baykus.butget.utils.dao.ExpenseDBDao;
import com.baykus.butget.utils.dao.incomeDBDao;

import javax.swing.JTree;
import javax.swing.JToggleButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

import org.hibernate.dialect.identity.GetGeneratedKeysDelegate;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.JSlider;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.Toolkit;
import javax.swing.JRadioButton;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import com.toedter.calendar.JDateChooser;

public class MainScreen extends JFrame {
	private JButton btnGelir;
	private JButton btnGider;
	private JButton btnRapor;
	private JButton btngrafikGelirButton;
	private JButton btnGrafikGiderButon;
	private JPanel pnlAylýk;
	private JLabel lblGider;
	private JLabel lblGelir;
	private float gider;
	private float gelir;
	private float betweenexpence;
	private float betweenIncome;
	private int gelirOrt;
	private JMenuBar menuBar;
	private JProgressBar progressBarGider;
	private JProgressBar progressBarGelir;
	private JSeparator separator;
	private JSeparator separator_1;
	private JSeparator separator_2;
	private JLabel lblToplamGelirMiktar;
	private JLabel lblToplamGiderMiktar;
	private JButton btnGncelle;
	private JLabel lblGelirTutar;
	private JLabel lblGiderTutar;
	private JPanel panel;
	private JLabel lblGrafikResim;
	private JButton button;
	private JPanel panel_1;
	private JRadioButton rdbtnHaftalkGelirgider;
	JpanelMonth jpanelMonth = new JpanelMonth();
	Rapor rapor = new Rapor();
	private JDateChooser BaslangicdateChooser;
	private JDateChooser BitisdateChooser;
	private JLabel lblBaslangTarihi;
	private JLabel lblBitisTarihi;
	private Date startDate;
	private Date endDate;
	private JLabel lblUyari;

	public MainScreen() {

		intialize();
	}

	private void intialize() {
		setTitle("Gelir Gider Yönetimi " + "         " + ButgetUtil.loginUser.getUserName() + " - "
				+ ButgetUtil.loginUser.getUserRole());
		getContentPane().setLayout(null);
		setBounds(200, 100, 620, 571);
		getContentPane().add(getBtnGelir());
		getContentPane().add(getBtnGider());
		getContentPane().add(getBtnRapor());
		getContentPane().add(getPnlAylýk());
		getContentPane().add(getBtnGncelle());
		getContentPane().add(getLblGrafikResim());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setJMenuBar(getMenuBar_1());
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("D:\\Java\\workspace\\swingworkspace\\IncomeAndExpense\\images\\income-expense (1).png"));
		getContentPane().setBackground(SystemColor.control);
		getContentPane().add(getLblUyari());
	}

	private JButton getBtnGelir() {
		if (btnGelir == null) {
			btnGelir = new JButton("GEL\u0130R");
			btnGelir.setBackground(new Color(255, 255, 255));
			btnGelir.setIcon(new ImageIcon("D:\\Java\\workspace\\swingworkspace\\IncomeAndExpense\\images\\gelir.png"));
			btnGelir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new IncomeScreen().setVisible(true);
					MainScreen.this.dispose();
				}
			});
			btnGelir.setBounds(426, 342, 133, 43);
		}
		return btnGelir;
	}

	private JButton getBtnGider() {
		if (btnGider == null) {
			btnGider = new JButton("G\u0130DER");
			btnGider.setBackground(new Color(255, 255, 255));

			btnGider.setIcon(
					new ImageIcon("D:\\Java\\workspace\\swingworkspace\\IncomeAndExpense\\images\\gider1 (1).png"));
			btnGider.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new ExpenseScreen().setVisible(true);
					MainScreen.this.dispose();
				}
			});
			btnGider.setBounds(426, 272, 133, 43);
		}
		return btnGider;
	}

	private JButton getBtnRapor() {
		if (btnRapor == null) {
			btnRapor = new JButton("RAPOR");
			btnRapor.setFont(new Font("Tahoma", Font.BOLD, 10));
			btnRapor.setBackground(new Color(255, 255, 255));
			btnRapor.setIcon(new ImageIcon("D:\\Java\\workspace\\swingworkspace\\IncomeAndExpense\\images\\rapor.png"));
			btnRapor.setBounds(426, 407, 133, 43);
			btnRapor.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					getContentPane().add(rapor).setVisible(true);
					rapor.setBounds(100, 0, 432, 191);
					
				}
			});
		}
		return btnRapor;
	}

	private float getToplaGelir() {
		gelir = 0;
		income income = new income();
		Users u = new Users();
		incomeDBDao inDao = new incomeDBDao();

		u.setId(ButgetUtil.loginUser.getId());
		income.setUser(u);
		List<income> incomeList = inDao.searchforUsers(income);
		for (int i = 0; i < incomeList.size(); i++) {
			float b = incomeList.get(i).getSum();
			gelir = b + gelir;
		}

		return gelir;
	}

	private float getToplamGider() {

		gider = 0;
		expense expense = new expense();
		ExpenseDBDao exDao = new ExpenseDBDao();
		Users u = new Users();
		u.setId(ButgetUtil.loginUser.getId());
		expense.setUsers(u);
		List<expense> list = exDao.searchforUsers(expense);

		for (int j = 0; j < list.size(); j++) {

			float c = list.get(j).getSum();
			gider = c + gider;

		}

		return gider;

	}

	private float getDateBetweenExpense() {

		betweenexpence = 0;
		expense expense = new expense();
		ExpenseDBDao expenseDBDao = new ExpenseDBDao();
		Users u = new Users();
		u.setId(ButgetUtil.loginUser.getId());
		expense.setUsers(u);

		List<expense> list1 = expenseDBDao.searchBetween(expense, startDate, endDate);

		for (int i = 0; i < list1.size(); i++) {
			float c = list1.get(i).getSum();
			betweenexpence = c + betweenexpence;
		}
		System.out.println("Ýki tarih arasý gider : " + betweenexpence);

		return betweenexpence;
	}

	private float getDateBetweenIncome() {
		betweenIncome = 0;
		income income = new income();
		incomeDBDao incomeDBDao = new incomeDBDao();
		Users u = new Users();
		u.setId(ButgetUtil.loginUser.getId());
		income.setUser(u);

		List<income> list = incomeDBDao.searchBetween(income, startDate, endDate);

		for (int i = 0; i < list.size(); i++) {
			float c = list.get(i).getSum();
			betweenIncome = c + betweenIncome;
		}
		System.out.println("Ýki tarih arasý gelir : " + betweenIncome);

		return betweenIncome;
	}

	private JPanel getPnlAylýk() {
		if (pnlAylýk == null) {
			pnlAylýk = new JPanel();
			pnlAylýk.setBorder(
					new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Toplam Gelir Gider Y\u00FCzdeleri",
							TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			pnlAylýk.setBounds(34, 205, 350, 245);
			pnlAylýk.setLayout(null);

			pnlAylýk.add(getLblGider());
			pnlAylýk.add(getLblGelir());
			pnlAylýk.add(getProgressBarGider());
			pnlAylýk.add(getProgressBarGelir());
			pnlAylýk.add(getSeparator());
			pnlAylýk.add(getSeparator_1());
			pnlAylýk.add(getSeparator_2());
			pnlAylýk.add(getLblToplamGelirMiktar());
			pnlAylýk.add(getLblToplamGiderMiktar());
			pnlAylýk.add(getLblGelirTutar());
			pnlAylýk.add(getLblGiderTutar());
		}
		return pnlAylýk;
	}

	private JLabel getLblGider() {
		if (lblGider == null) {
			lblGider = new JLabel("G\u0130DER");
			lblGider.setForeground(Color.RED);
			lblGider.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblGider.setBounds(13, 40, 46, 14);
		}
		return lblGider;
	}

	private JLabel getLblGelir() {
		if (lblGelir == null) {
			lblGelir = new JLabel("GEL\u0130R");
			lblGelir.setForeground(Color.GREEN);
			lblGelir.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblGelir.setBounds(13, 105, 46, 14);
		}
		return lblGelir;
	}

	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getButton());
			menuBar.add(getPanel_1());
		}
		return menuBar;
	}

	private JProgressBar getProgressBarGider() {
		getToplaGelir();
		getToplamGider();

		if (progressBarGider == null) {
			progressBarGider = new JProgressBar();
			progressBarGider.setBounds(69, 33, 271, 33);
			progressBarGider.setStringPainted(true);
			progressBarGider.setForeground(Color.RED);
			progressBarGider.setValue((int) (100 - ((gelir / (gider + gelir)) * 100)));

		}
		return progressBarGider;
	}

	private JProgressBar getProgressBarGelir() {
		getToplaGelir();
		getToplamGider();

		if (progressBarGelir == null) {
			progressBarGelir = new JProgressBar();
			progressBarGelir.setValue((int) ((gelir / (gider + gelir)) * 100));
			progressBarGelir.setStringPainted(true);
			progressBarGelir.setForeground(Color.GREEN);
			progressBarGelir.setBounds(69, 89, 271, 33);
		}
		return progressBarGelir;
	}

	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
			separator.setBounds(13, 132, 327, 2);
		}
		return separator;
	}

	private JSeparator getSeparator_1() {
		if (separator_1 == null) {
			separator_1 = new JSeparator();
			separator_1.setBounds(13, 76, 327, 2);
		}
		return separator_1;
	}

	private JSeparator getSeparator_2() {
		if (separator_2 == null) {
			separator_2 = new JSeparator();
			separator_2.setBounds(13, 27, 327, 2);
		}
		return separator_2;
	}

	private JLabel getLblToplamGelirMiktar() {
		if (lblToplamGelirMiktar == null) {
			lblToplamGelirMiktar = new JLabel("TOPLAM GEL\u0130R M\u0130KTARI  :" + " ");
			lblToplamGelirMiktar.setForeground(Color.BLACK);
			lblToplamGelirMiktar.setFont(new Font("Tahoma", Font.PLAIN, 10));
			lblToplamGelirMiktar.setBounds(23, 170, 143, 14);
		}
		return lblToplamGelirMiktar;
	}

	private JLabel getLblToplamGiderMiktar() {
		if (lblToplamGiderMiktar == null) {
			lblToplamGiderMiktar = new JLabel("TOPLAM G\u0130DER M\u0130KTARI  :" + " ");
			lblToplamGiderMiktar.setForeground(Color.BLACK);
			lblToplamGiderMiktar.setFont(new Font("Tahoma", Font.PLAIN, 10));
			lblToplamGiderMiktar.setBounds(23, 195, 143, 14);
		}
		return lblToplamGiderMiktar;
	}

	private JButton getBtnGncelle() {
		if (btnGncelle == null) {
			btnGncelle = new JButton("G\u00DCNCELLE");
			btnGncelle.setFont(new Font("Tahoma", Font.BOLD, 10));
			btnGncelle.setIcon(
					new ImageIcon("D:\\Java\\workspace\\swingworkspace\\IncomeAndExpense\\images\\g\u00FCncelle.png"));
			btnGncelle.setBackground(new Color(255, 255, 255));
			btnGncelle.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MainScreen.this.dispose();
					MainScreen.this.setVisible(true);
				}
			});
			btnGncelle.setBounds(426, 201, 133, 43);
		}
		return btnGncelle;
	}

	private JLabel getLblGelirTutar() {
		if (lblGelirTutar == null) {
			lblGelirTutar = new JLabel("" + getToplaGelir());
			lblGelirTutar.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblGelirTutar.setForeground(Color.GREEN);
			lblGelirTutar.setBounds(176, 169, 95, 14);
		}
		return lblGelirTutar;
	}

	private JLabel getLblGiderTutar() {
		if (lblGiderTutar == null) {
			lblGiderTutar = new JLabel("" + getToplamGider());
			lblGiderTutar.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblGiderTutar.setForeground(Color.RED);
			lblGiderTutar.setBounds(176, 195, 95, 14);
		}
		return lblGiderTutar;
	}

	private JLabel getLblGrafikResim() {
		if (lblGrafikResim == null) {
			lblGrafikResim = new JLabel("");
			lblGrafikResim.setIcon(
					new ImageIcon("D:\\Java\\workspace\\swingworkspace\\IncomeAndExpense\\images\\grafik1.png"));
			lblGrafikResim.setBounds(101, 3, 414, 191);
		}
		return lblGrafikResim;
	}

	private JButton getButton() {
		if (button == null) {
			button = new JButton("");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MainScreen.this.dispose();
					new Login().setVisible(true);
				}
			});
			button.setIcon(new ImageIcon("D:\\Java\\workspace\\swingworkspace\\IncomeAndExpense\\images\\geri.png"));
			button.setBackground(new Color(255, 255, 255));
		}
		return button;
	}

	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setLayout(null);
			panel_1.add(getBaslangicdateChooser());
			panel_1.add(getBitisdateChooser());
			panel_1.add(getLblBaslangTarihi());
			panel_1.add(getLblBitisTarihi());
			panel_1.add(getRdbtnHaftalkGelirgider());
		}
		return panel_1;
	}

	private JRadioButton getRdbtnHaftalkGelirgider() {
		if (rdbtnHaftalkGelirgider == null) {
			rdbtnHaftalkGelirgider = new JRadioButton("\u0130ki Tarih Aras\u0131 Gelir/Gider");
			rdbtnHaftalkGelirgider.setBounds(239, 11, 174, 35);
			rdbtnHaftalkGelirgider.setFont(new Font("Tahoma", Font.BOLD, 11));

			rdbtnHaftalkGelirgider.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (rdbtnHaftalkGelirgider.isSelected()) {

						getContentPane().add(jpanelMonth).setVisible(true);
						jpanelMonth.getPanel()
								.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
										" Tarihler Arasi Gelir/Gider", TitledBorder.LEADING, TitledBorder.TOP, null,
										new Color(0, 0, 0)));
						ikiTarihArasiGetir();
						jpanelMonth.setBounds(100, 0, 432, 191);
						getLblGrafikResim().setVisible(false);

						getDateBetweenExpense();
						getDateBetweenIncome();
						jpanelMonth.getProgressBarBetweenGider()
								.setValue((int) (100 - ((betweenIncome / (betweenexpence + betweenIncome)) * 100)));

						jpanelMonth.getProgressBarbetweenGelir()
								.setValue((int) (100 - ((betweenIncome / (betweenIncome + betweenIncome)) * 100)));

						jpanelMonth.getLabelBetweenTopGelir().setText(String.valueOf(betweenIncome));
						jpanelMonth.getLabelBetweenTopGider().setText(String.valueOf(betweenexpence));
						lblUyari.setText(" ");


					} else {
						getContentPane().add(jpanelMonth).setVisible(false);
						System.out.println("Tarih giriniz...");
						lblUyari.setText("Lütfen Bir Tarih Aralýðý Giriniz.");
						rdbtnHaftalkGelirgider.setSelected(false);
						getLblGrafikResim().setVisible(true);
					}
				}
			});
		}
		return rdbtnHaftalkGelirgider;
	}

	private JDateChooser getBaslangicdateChooser() {
		if (BaslangicdateChooser == null) {
			BaslangicdateChooser = new JDateChooser();
			BaslangicdateChooser.getCalendarButton().setBackground(Color.WHITE);
			BaslangicdateChooser.setDateFormatString("yyyy-MM-dd");
			BaslangicdateChooser.setBounds(10, 26, 87, 20);
		}
		return BaslangicdateChooser;
	}

	private JDateChooser getBitisdateChooser() {
		if (BitisdateChooser == null) {
			BitisdateChooser = new JDateChooser();
			BitisdateChooser.getCalendarButton().setBackground(Color.WHITE);
			BitisdateChooser.setDateFormatString("yyyy-MM-dd");
			BitisdateChooser.setBounds(127, 26, 87, 20);
		}
		return BitisdateChooser;
	}

	private JLabel getLblBaslangTarihi() {
		if (lblBaslangTarihi == null) {
			lblBaslangTarihi = new JLabel("Ba\u015Flang\u0131\u00E7 Tarihi :");
			lblBaslangTarihi.setForeground(new Color(128, 128, 128));
			lblBaslangTarihi.setBounds(10, 9, 87, 14);
		}
		return lblBaslangTarihi;
	}

	private JLabel getLblBitisTarihi() {
		if (lblBitisTarihi == null) {
			lblBitisTarihi = new JLabel("Biti\u015F Tarihi :");
			lblBitisTarihi.setForeground(new Color(128, 128, 128));
			lblBitisTarihi.setBounds(127,9, 87, 14);
		}
		return lblBitisTarihi;
	}

	public void ikiTarihArasiGetir() {
		startDate = null;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String startChoose = df.format(getBaslangicdateChooser().getDate());
		System.out.println(startChoose);
		try {
			startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startChoose);
			System.out.println(startDate);

		} catch (Exception e) {
			e.printStackTrace();
		}
		endDate = null;
		DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
		String endChoose = df.format(getBitisdateChooser().getDate());
		System.out.println(startChoose);
		try {
			endDate = new SimpleDateFormat("yyyy-MM-dd").parse(endChoose);
			System.out.println(endDate);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	private JLabel getLblUyari() {
		if (lblUyari == null) {
			lblUyari = new JLabel("");
			lblUyari.setForeground(Color.RED);
			lblUyari.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblUyari.setBounds(45, 184, 242, 21);
		}
		return lblUyari;
	}
}
