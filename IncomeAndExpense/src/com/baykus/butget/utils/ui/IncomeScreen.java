package com.baykus.butget.utils.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.baykus.butget.models.Categori;
import com.baykus.butget.models.Users;
import com.baykus.butget.models.expense;
import com.baykus.butget.models.income;
import com.baykus.butget.utils.ButgetUtil;
import com.baykus.butget.utils.dao.ExpenseDBDao;
import com.baykus.butget.utils.dao.UsersDBDao;
import com.baykus.butget.utils.dao.incomeDBDao;
import com.toedter.calendar.JDateChooser;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JMenuBar;

public class IncomeScreen extends JFrame {
	private JScrollPane scrollPane;
	private JTable tableGelir;
	private JTextField txtTutar;
	private JLabel lblGelirMiktari;
	private JLabel lblAciklama;
	private JLabel lblTarih;
	private JDateChooser dateGider;
	private JButton btnKaydet;
	private JButton btnSil;
	private JButton btnGncelle;
	private JLabel lblAra;
	private JTextField textField;
	private JLabel lblMesaj;
	private int row;
	private JTextField txtAciklama;
	private JButton btnGeri;
	private JMenuBar menuBar;
	public IncomeScreen() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\Java\\workspace\\swingworkspace\\IncomeAndExpense\\images\\income-expense.png"));
		setTitle("GEL\u0130R");
		setJMenuBar(getMenuBar_1());

		Initialize();

	}

	private void Initialize() {
		getContentPane().setLayout(null);
		getContentPane().add(getScrollPane());
		getContentPane().add(getTxtTutar());
		getContentPane().add(getLblGelirMiktari());
		getContentPane().add(getLblAciklama());
		getContentPane().add(getLblTarih());
		getContentPane().add(getDateGider());
		getContentPane().add(getBtnKaydet());
		getContentPane().add(getBtnSil());
		getContentPane().add(getBtnGncelle());
		getContentPane().add(getLblAra());
		getContentPane().add(getTextField());
		getContentPane().add(getLblMesaj());
		getContentPane().add(getTxtAciklama());
		setBounds(200, 100, 620, 550);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 224, 584, 225);
			scrollPane.setViewportView(getTableGelir());
		}
		return scrollPane;
	}

	private JTable getTableGelir() {
		if (tableGelir == null) {
			tableGelir = new JTable();
		
			tableGelir.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					 row = tableGelir.getSelectedRow();
//					expense expense = new expense();
//					String e = expense.toString();
//					System.out.println(e);
//					String t=(String)tableGelir.getValueAt(row, 1);
//					String[] e1=e.split("-");
//					expense.setGiderId(Integer.valueOf(e1[0]));
//					expense.setCategori(Categori.valueOf(t));
					
					try {
						Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String)tableGelir.getValueAt(row, 2));
						dateGider.setDate(date);
					} catch (ParseException e2) {
						
						e2.printStackTrace();
					}
									
					txtTutar.setText(tableGelir.getValueAt(row, 3).toString());					
					txtAciklama.setText(tableGelir.getValueAt(row, 1).toString());
					getDateGider();
				}
			});
			TabloGetir();
		}
		return tableGelir;
	}

	private void TabloGetir() {
		incomeDBDao dao = new incomeDBDao();
		
		income income = new income();
		Users u1 = new Users();
		u1.setId(ButgetUtil.loginUser.getId());
		income.setUser(u1);
		
		
		List<income> liste = dao.searchforUsers(income);
		
		Object[] columnNames = {"Id", "Açýklama", "Tarih", "Tutar" };
		
		String[][] data = new String[liste.size()][columnNames.length];
		
		
	
		for (int i = 0; i < data.length; i++) {
			data[i][0] = Integer.toString(liste.get(i).getGelirId());
			data[i][1] = liste.get(i).getExplanation();
			data[i][2] = liste.get(i).getDate().toString();
			
			data[i][3] = Float.toString(liste.get(i).getSum());

		}
		DefaultTableModel model = new DefaultTableModel(data, columnNames);

		tableGelir.setModel(model);
	}

	private JTextField getTxtTutar() {
		if (txtTutar == null) {
			txtTutar = new JTextField();
			txtTutar.setBackground(new Color(255, 255, 255));
			txtTutar.setBounds(143, 21, 163, 25);
			txtTutar.setColumns(10);
		}
		return txtTutar;
	}

	private JLabel getLblGelirMiktari() {
		if (lblGelirMiktari == null) {
			lblGelirMiktari = new JLabel("Gelir Miktar\u0131  :");
			lblGelirMiktari.setBackground(new Color(255, 255, 255));
			lblGelirMiktari.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblGelirMiktari.setHorizontalAlignment(SwingConstants.TRAILING);
			lblGelirMiktari.setBounds(39, 26, 94, 14);
		}
		return lblGelirMiktari;
	}

	private JLabel getLblAciklama() {
		if (lblAciklama == null) {
			lblAciklama = new JLabel("A\u00E7\u0131klama       :");
			lblAciklama.setBackground(new Color(255, 255, 255));
			lblAciklama.setHorizontalAlignment(SwingConstants.TRAILING);
			lblAciklama.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblAciklama.setBounds(36, 57, 97, 25);

		}
		return lblAciklama;
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
					incomeDBDao dao = new incomeDBDao();
					UsersDBDao userDao = new UsersDBDao();
					income temp = new income();
					Users users = new Users();
					temp.setSum(Float.valueOf(getTxtTutar().getText()));
					temp.setDate(getDateGider().getDate());
					temp.setExplanation(getTxtAciklama().getText());
					users.setId(ButgetUtil.loginUser.getId());
					temp.setUser(users);
					
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
					incomeDBDao dao = new incomeDBDao();
					income temp = new income();
					Users users = new Users();
					temp.setSum(Float.valueOf(getTxtTutar().getText()));
					temp.setDate(getDateGider().getDate());
					temp.setExplanation(txtAciklama.getText());
					temp.setGelirId(Integer.valueOf(tableGelir.getValueAt(row, 0).toString()));
					users.setId(ButgetUtil.loginUser.getId());
					temp.setUser(users);
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
			btnSil.setBounds(504, 21, 66, 129);
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
					incomeDBDao dao = new incomeDBDao();
					income temp = new income();
					Users users1 = new Users();
					
					temp.setSum(Float.valueOf(getTxtTutar().getText()));
					temp.setExplanation(txtAciklama.getText());
					temp.setGelirId(Integer.valueOf(tableGelir.getValueAt(row, 0).toString()));
					temp.setDate(dateGider.getDate());
					users1.setId(ButgetUtil.loginUser.getId());
					temp.setUser(users1);
					
					
					if (dao.update(temp)) {
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
	private JTextField getTxtAciklama() {
		if (txtAciklama == null) {
			txtAciklama = new JTextField();
			txtAciklama.setBackground(new Color(255, 255, 255));
			txtAciklama.setColumns(10);
			txtAciklama.setBounds(143, 57, 163, 25);
		}
		return txtAciklama;
	}
	private JButton getBtnGeri() {
		if (btnGeri == null) {
			btnGeri = new JButton("");
			btnGeri.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
										
					new MainScreen().setVisible(true);
					IncomeScreen.this.dispose();
					
					
				
				}
			});
			btnGeri.setIcon(new ImageIcon("D:\\Java\\workspace\\swingworkspace\\IncomeAndExpense\\images\\geri.png"));
			btnGeri.setBackground(Color.WHITE);
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
