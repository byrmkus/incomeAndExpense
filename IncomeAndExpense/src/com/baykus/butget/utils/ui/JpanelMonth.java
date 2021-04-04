package com.baykus.butget.utils.ui;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JProgressBar;

public class JpanelMonth extends JPanel {
	private JPanel panel;
	private JLabel label;
	private JLabel label_1;
	private JSeparator separator;
	private JSeparator separator_1;
	private JSeparator separator_2;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel labelBetweenTopGelir;
	private JLabel labelBetweenTopGider;
	
	private MainScreen mainScreen;
	private JProgressBar progressBarBetweenGider;
	private JProgressBar progressBarbetweenGelir;
	public JpanelMonth() {
		setToolTipText("");
		setLayout(null);
		add(getPanel());
	}

	public JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(null);
			
			panel.setBounds(0, 0, 350, 189);
			panel.add(getLabel());
			panel.add(getLabel_1());
			panel.add(getSeparator());
			panel.add(getSeparator_1());
			panel.add(getSeparator_2());
			panel.add(getLabel_2());
			panel.add(getLabel_3());
			panel.add(getLabelBetweenTopGelir());
			panel.add(getLabelBetweenTopGider());
			panel.add(getProgressBarBetweenGider());
			panel.add(getProgressBarbetweenGelir());
		
		}
		return panel;
	}
	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("G\u0130DER");
			label.setForeground(Color.RED);
			label.setFont(new Font("Tahoma", Font.BOLD, 13));
			label.setBounds(13, 40, 46, 14);
		}
		return label;
	}
	private JLabel getLabel_1() {
		if (label_1 == null) {
			label_1 = new JLabel("GEL\u0130R");
			label_1.setForeground(Color.GREEN);
			label_1.setFont(new Font("Tahoma", Font.BOLD, 13));
			label_1.setBounds(13, 78, 46, 14);
		}
		return label_1;
	}
	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
			separator.setBounds(13, 103, 327, 2);
		}
		return separator;
	}
	private JSeparator getSeparator_1() {
		if (separator_1 == null) {
			separator_1 = new JSeparator();
			separator_1.setBounds(13, 65, 327, 2);
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
	private JLabel getLabel_2() {
		if (label_2 == null) {
			label_2 = new JLabel("TOPLAM GEL\u0130R M\u0130KTARI  : ");
			label_2.setForeground(Color.BLACK);
			label_2.setFont(new Font("Tahoma", Font.PLAIN, 10));
			label_2.setBounds(23, 131, 143, 14);
		}
		return label_2;
	}
	private JLabel getLabel_3() {
		if (label_3 == null) {
			label_3 = new JLabel("TOPLAM G\u0130DER M\u0130KTARI  : ");
			label_3.setForeground(Color.BLACK);
			label_3.setFont(new Font("Tahoma", Font.PLAIN, 10));
			label_3.setBounds(23, 156, 143, 14);
		}
		return label_3;
	}
	public JLabel getLabelBetweenTopGelir() {
		if (labelBetweenTopGelir == null) {
			labelBetweenTopGelir = new JLabel("0.0");
			labelBetweenTopGelir.setForeground(Color.GREEN);
			labelBetweenTopGelir.setFont(new Font("Tahoma", Font.BOLD, 12));
			labelBetweenTopGelir.setBounds(176, 130, 95, 14);
		}
		return labelBetweenTopGelir;
	}
	public JLabel getLabelBetweenTopGider() {
		if (labelBetweenTopGider == null) {
			labelBetweenTopGider = new JLabel("0.0");
			labelBetweenTopGider.setForeground(Color.RED);
			labelBetweenTopGider.setFont(new Font("Tahoma", Font.BOLD, 12));
			labelBetweenTopGider.setBounds(176, 156, 95, 14);
		}
		return labelBetweenTopGider;
	}
	public JProgressBar getProgressBarBetweenGider() {
		if (progressBarBetweenGider == null) {
			progressBarBetweenGider = new JProgressBar();
			progressBarBetweenGider.setStringPainted(true);
			progressBarBetweenGider.setForeground(Color.RED);
			progressBarBetweenGider.setBounds(69, 32, 270, 29);
		}
		return progressBarBetweenGider;
	}
	public JProgressBar getProgressBarbetweenGelir() {
		if (progressBarbetweenGelir == null) {
			progressBarbetweenGelir = new JProgressBar();
			progressBarbetweenGelir.setStringPainted(true);
			progressBarbetweenGelir.setForeground(Color.GREEN);

			progressBarbetweenGelir.setBounds(69, 70, 270, 29);
		}
		return progressBarbetweenGelir;
	}
}
