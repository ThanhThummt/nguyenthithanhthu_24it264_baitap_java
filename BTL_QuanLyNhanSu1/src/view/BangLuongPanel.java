package view;

import dao.LuongDAO;
import model.Luong;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;

public class BangLuongPanel extends JPanel {
	private JComboBox<Integer> cbThang, cbNam;
	private JButton btnTinh, btnXemBang;
	private JTable table;
	private DefaultTableModel model;

	public BangLuongPanel() {
		setLayout(new BorderLayout(10, 10));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// Lấy tháng và năm hiện tại
	    Calendar cal = Calendar.getInstance();
	    int currentMonth = cal.get(Calendar.MONTH) + 1; // Calendar.MONTH là 0-based
	    int currentYear = cal.get(Calendar.YEAR);
		
		// Top: chọn tháng/năm
		JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		topPanel.setBackground(Color.WHITE);
		topPanel.add(new JLabel("Tháng:"));
		cbThang = new JComboBox<>();
		for (int i = 1; i <= 12; i++)
			cbThang.addItem(i);
		cbThang.setSelectedItem(currentMonth); 
		cbThang.setBackground(Color.WHITE);
		cbThang.setPreferredSize(new Dimension(60, 25));
		topPanel.add(cbThang);

		topPanel.add(new JLabel("Năm:"));
		cbNam = new JComboBox<>();
		for (int y = 2020; y <= 2030; y++)
			cbNam.addItem(y);
		cbNam.setSelectedItem(currentYear); 
		cbNam.setBackground(Color.WHITE);
		cbNam.setPreferredSize(new Dimension(60, 25));
		topPanel.add(cbNam);

		Color btncolor = Color.WHITE;
		btnTinh = new JButton("Tính lương");
		btnTinh.setBackground(btncolor);
		btnTinh.setPreferredSize(new Dimension(120, 30));
		btnTinh.setBorder(BorderFactory.createLineBorder(new Color(76, 138, 184), 4, true));
		topPanel.add(btnTinh);

		btnXemBang = new JButton("Bảng lương");
		btnXemBang.setBackground(btncolor);
		btnXemBang.setPreferredSize(new Dimension(120, 30));
		btnXemBang.setBorder(BorderFactory.createLineBorder(new Color(76, 138, 184), 4, true));
		topPanel.add(btnXemBang);

		add(topPanel, BorderLayout.NORTH);

		// Bảng lương
		String[] cols = { "Mã NV", "Họ tên", "Chức vụ", "Số ngày công", "Tổng lương" };
		model = new DefaultTableModel(cols, 0);
		table = new JTable(model);
		table.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 13));
		table.getTableHeader().setBackground(Color.WHITE);
		add(new JScrollPane(table), BorderLayout.CENTER);

		btnTinh.addActionListener(e -> {
			int thang = (int) cbThang.getSelectedItem();
			int nam = (int) cbNam.getSelectedItem();

			LuongDAO dao = new LuongDAO();
			ArrayList<Luong> ds = dao.tinhLuong(thang, nam);

			model.setRowCount(0);
			for (Luong l : ds) {
				model.addRow(
						new Object[] { l.getMaNV(), l.getHoTen(), l.getChucVu(), l.getSoNgayCong(), l.getTongLuong() });
			}

			dao.luuBangLuong(ds, thang, nam);

			JOptionPane.showMessageDialog(this, "Tính và lưu bảng lương thành công!");
		});

		btnXemBang.addActionListener(e -> {
			int thang = (int) cbThang.getSelectedItem();
			int nam = (int) cbNam.getSelectedItem();
			JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
			new XemLuongDialog(frame, thang, nam).setVisible(true);
		});

	}
}
