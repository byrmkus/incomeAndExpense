package com.baykus.butget.utils.ui;

import javax.swing.JPanel;
import javax.swing.JList;

public class Rapor extends JPanel {
	private JList list;
	public Rapor() {
		setLayout(null);
		add(getList());
	}
	private JList getList() {
		if (list == null) {
			list = new JList();
			list.setBounds(49, 22, 357, 245);
		}
		return list;
	}
}
