package com.baykus.butget.utils.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JComboBox;

import com.baykus.butget.models.Categori;
import com.baykus.butget.models.Users;
import com.baykus.butget.models.expense;
import com.baykus.butget.utils.ButgetUtil;
import com.baykus.butget.utils.dao.ExpenseDBDao;
import com.baykus.butget.utils.dao.UsersDBDao;

import com.toedter.calendar.JDateChooser;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JMenuBar;

public class ExpenseScreen extends JFrame {
	private JScrollPane scrollPane;
	private JTable tableGider;
	private JTextField txtTutar;
	private JLabel lblTutar;
	private JLabel lblKatagori;
	private JLabel lblTarih;
	private JComboBox cmboxKategori;
	private JDateChooser dateGider;
	private JButton btnKaydet;
	private JButton btnSil;
	private JButton btnGncelle;
	private JLabel lblAra;
	private JTextField textField;
	private JLabel lblMesaj;
	private int row;
	private JButton btnGeri;
	private JMenuBar menuBar;

	public ExpenseScreen() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\Java\\workspace\\swingworkspace\\IncomeAndExpense\\images\\gelir.jpg"));
		setTitle("G\u0130DER");
		setJMenuBar(getMenuBar_1());

		Initialize();

	}

	private void Initialize() {
		getContentPane().setLayout(null);
		getContentPane().add(getScrollPane());
		getContentPane().add(getTxtTutar());
		getContentPane().add(getLblTutar());
		getContentPane().add(getLblKatagori());
		getContentPane().add(getLblTarih());
		getContentPane().add(getCmboxKategori());
		getContentPane().add(getDateGider());
		getContentPane().add(getBtnKaydet());
		getContentPane().add(getBtnSil());
		getContentPane().add(getBtnGncelle());
		getContentPane().add(getLblAra());
		getContentPane().add(getTextField());
		getContentPane().add(getLblMesaj());
		setBounds(200, 100, 620, 550);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 224, 584, 225);
			scrollPane.setViewportView(getTableGider());
		}
		return scrollPane;
	}

	private JTable getTableGider() {
		if (tableGider == null) {
			tableGider = new JTable();

			tableGider.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					row = tableGider.getSelectedRow();
					expense expense = new expense();
					String e = expense.toString();
					System.out.println(e);
					String t = (String) tableGider.getValueAt(row, 1);
					String[] e1 = e.split("-");
					expense.setGiderId(Integer.valueOf(e1[0]));
					expense.setCategori(Categori.valueOf(t));

					try {
						Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String) tableGider.getValueAt(row, 2));
						dateGider.setDate(date);
					} catch (ParseException e2) {

						e2.printStackTrace();
					}
					cmboxKategori.getModel().setSelectedItem(expense.getCategori());
					txtTutar.setText(tableGider.getValueAt(row, 3).toString());

					cmboxKategori.setSelectedItem(tableGider.getValueAt(row, 1).toString());
					getDateGider();
				}
			});
			TabloGetir();
		}
		return tableGider;
	}

	private void TabloGetir() {
		ExpenseDBDao dao = new ExpenseDBDao();
		expense expense = new expense();
		Users u = new Users();
		u.setId(ButgetUtil.loginUser.getId());
		expense.setUsers(u);

		List<expense> liste = dao.searchforUsers(expense);

		Object[] columnNames = { "Id", "Kategori", "Tarih", "Tutar" };

		String[][] data = new String[liste.size()][columnNames.length];
		for (int i = 0; i < data.length; i++) {
			data[i][0] = Integer.toString(liste.get(i).getGiderId());
			data[i][1] = liste.get(i).getCategori().toString();
			data[i][2] = liste.get(i).getDate().toString();
			data[i][3] = Float.toString(liste.get(i).getSum());

		}
		DefaultTableModel model = new DefaultTableModel(data, columnNames);

		tableGider.setModel(model);
	}

	private JTextField getTxtTutar() {
		if (txtTutar == null) {
			txtTutar = new JTextField();
			txtTutar.setBounds(143, 21, 163, 25);
			txtTutar.setColumns(10);
		}
		return txtTutar;
	}

	private JLabel getLblTutar() {
		if (lblTutar == null) {
			lblTutar = new JLabel("Gider Miktar\u0131  :");
			lblTutar.setBackground(new Color(255, 255, 255));
			lblTutar.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblTutar.setHorizontalAlignment(SwingConstants.TRAILING);
			lblTutar.setBounds(39, 26, 94, 14);
		}
		return lblTutar;
	}

	private JLabel getLblKatagori() {
		if (lblKatagori == null) {
			lblKatagori = new JLabel("Kategori        :");
			lblKatagori.setBackground(new Color(255, 255, 255));
			lblKatagori.setHorizontalAlignment(SwingConstants.TRAILING);
			lblKatagori.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblKatagori.setBounds(36, 57, 97, 25);

		}
		return lblKatagori;
	}

	private JLabel getLblTarih() {
		if (lblTarih == null) {
			lblTarih = new JLabel("Tarih             :");
			lblTarih.setBackground(new Color(255, 255, 255));
			lblTarih.setHorizontalAlignment(SwingConstants.TRAILING);
			lblTarih.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblTarih.setBounds(36, 93, 97, 19);
		}
		return lblTarih;
	}

	private JComboBox getCmboxKategori() {
		if (cmboxKategori == null) {
			cmboxKategori = new JComboBox();
			cmboxKategori.setBackground(new Color(255, 255, 255));
			cmboxKategori.setBounds(143, 57, 163, 25);

			DefaultComboBoxModel model = new DefaultComboBoxModel(Categori.values());
			cmboxKategori.setModel(model);
		}
		return cmboxKategori;
	}

	private JDateChooser getDateGider() {
		if (dateGider == null) {
			dateGider = new JDateChooser();
			dateGider.getCalendarButton().setBackground(new Color(255, 255, 255));
			dateGider.setDateFormatString("yyyy-MM-dd");
			dateGider.setBounds(143, 92, 163, 25);

		}
		return dateGider;
	}

	private JButton getBtnKaydet() {
		if (btnKaydet == null) {
			btnKaydet = new JButton("KAYDET");
			btnKaydet.setBackground(new Color(255, 255, 255));
			btnKaydet.setIcon(new ImageIcon("D:\\Java\\workspace\\swingworkspace\\IncomeAndExpense\\images\\kaydet.png"));
			btnKaydet.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ExpenseDBDao dao = new ExpenseDBDao();
					expense temp = new expense();
					Users users = new Users();
					users.setId(ButgetUtil.loginUser.getId());
					temp.setUsers(users);

					temp.setSum(Float.valueOf(getTxtTutar().getText()));
					temp.setDate(getDateGider().getDate());
					temp.setCategori((Categori) getCmboxKategori().getSelectedItem());

					if (dao.save(temp)) {
						TabloGetir();
						getLblMesaj().setForeground(Color.GREEN);
						getLblMesaj().setText("KAYDETME ÝÞLEMÝ BAÞARI ÝLE GERÇEKLEÞTÝ");
					} else {
						getLblMesaj().setForeground(Color.RED);
						getLblMesaj().setText("KAYIT BAÞARISIZ");
					}

				}
			});
			btnKaydet.setForeground(new Color(0, 0, 0));
			btnKaydet.setFont(new Font("Tahoma", Font.BOLD, 12));
			btnKaydet.setBounds(341, 21, 140, 57);
		}
		return btnKaydet;
	}

	private JButton getBtnSil() {
		if (btnSil == null) {
			btnSil = new JButton("");
			btnSil.setBackground(new Color(255, 255, 255));
			btnSil.setIcon(new ImageIcon("D:\\Java\\workspace\\swingworkspace\\IncomeAndExpense\\images\\sil.jpg"));
			btnSil.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ExpenseDBDao dao = new ExpenseDBDao();
					expense temp = new expense();
					Users users = new Users();
					users.setId(ButgetUtil.loginUser.getId());
					temp.setUsers(users);
					temp.setSum(Float.valueOf(getTxtTutar().getText()));
					temp.setDate(getDateGider().getDate());
					temp.setCategori((Categori) getCmboxKategori().getSelectedItem());
					temp.setGiderId(Integer.valueOf(tableGider.getValueAt(row, 0).toString()));
					if (dao.delete(temp)) {
						TabloGetir();
						getLblMesaj().setForeground(Color.GREEN);
						getLblMesaj().setText("SÝLME ÝÞLEMÝ BAÞARI ÝLE GERÇEKLEÞTÝ");
					} else {
						getLblMesaj().setForeground(Color.RED);
						getLblMesaj().setText("SÝLME ÝÞLEMÝ BAÞARISIZ");
					}
				}
			});
			btnSil.setForeground(Color.RED);
			btnSil.setFont(new Font("Tahoma", Font.BOLD, 12));
			btnSil.setBounds(504, 21, 69, 129);
		}
		return btnSil;
	}

	private JButton getBtnGncelle() {
		if (btnGncelle == null) {
			btnGncelle = new JButton("G\u00DCNCELLE");
			btnGncelle.setBackground(new Color(255, 255, 255));
			btnGncelle.setIcon(new ImageIcon("D:\\Java\\workspace\\swingworkspace\\IncomeAndExpense\\images\\g\u00FCncelle.png"));
			btnGncelle.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ExpenseDBDao dao1 = new ExpenseDBDao();
					expense temp = new expense();
					Users users = new Users();
					users.setId(ButgetUtil.loginUser.getId());
					temp.setUsers(users);
					temp.setGiderId(Integer.valueOf(tableGider.getValueAt(row, 0).toString()));
					temp.setSum(Float.valueOf(getTxtTutar().getText()));
					temp.setCategori((Categori) cmboxKategori.getSelectedItem());
					temp.setDate(dateGider.getDate());

					if (dao1.update(temp)) {
						TabloGetir();
						getLblMesaj().setForeground(Color.GREEN);
						getLblMesaj().setText("GÜNCELLEME ÝÞLEMÝ BAÞARI ÝLE GERÇEKLEÞTÝ");
					} else {
						getLblMesaj().setForeground(Color.RED);
						getLblMesaj().setText("GÜNCELLEME  BAÞARISIZ");
					}
				}
			});
			btnGncelle.setForeground(new Color(0, 0, 0));
			btnGncelle.setFont(new Font("Tahoma", Font.BOLD, 11));
			btnGncelle.setBounds(341, 93, 140, 57);
		}
		return btnGncelle;
	}

	private JLabel getLblAra() {
		if (lblAra == null) {
			lblAra = new JLabel("ARA :");
			lblAra.setBackground(new Color(255, 255, 255));
			lblAra.setHorizontalAlignment(SwingConstants.TRAILING);
			lblAra.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblAra.setBounds(10, 181, 39, 19);
		}
		return lblAra;
	}

	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setBounds(59, 181, 247, 20);
			textField.setColumns(10);
		}
		return textField;
	}

	private JLabel getLblMesaj() {
		if (lblMesaj == null) {
			lblMesaj = new JLabel("");

			lblMesaj.setBounds(10, 475, 490, 25);
		}
		return lblMesaj;
	}
	private JButton getBtnGeri() {
		if (btnGeri == null) {
			btnGeri = new JButton("");
			btnGeri.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					new MainScreen().setVisible(true);
					ExpenseScreen.this.dispose();

				}
			});
			btnGeri.setBackground(new Color(255, 255, 255));
			btnGeri.setIcon(new ImageIcon("D:\\Java\\workspace\\swingworkspace\\IncomeAndExpense\\images\\geri.png"));
		}
		return btnGeri;
	}
	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getBtnGeri());
		}
		return menuBar;
	}
}
