package view;

import model.Nhansu;

import controller.ChamCongController;
import controller.NhanSuController;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ChamCongDialog extends JDialog {
	private JTable table;
	private DefaultTableModel model;
	private JButton btnLuu, btnDong, btnXemLichSu;
	private JTextField txtNgay;
	
	private boolean updating = false;  // biến cờ để tránh lặp vô hạn


	public ChamCongDialog(JFrame parent) {
		super(parent, "Chấm Công Nhân Viên", true);
		setSize(700, 500);
		setLocationRelativeTo(parent);
		setLayout(new BorderLayout(10, 10));

		// ====Top: ngày chấm công====
		JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		topPanel.setBackground(Color.WHITE);

		JLabel lblNgayChamCong = new JLabel("Ngày chấm công (yyyy-mm-dd):");
		lblNgayChamCong.setFont(new Font("Arial", Font.BOLD, 14)); 
		lblNgayChamCong.setForeground(Color.BLACK); 

		txtNgay = new JTextField();
		txtNgay.setPreferredSize(new Dimension(120, 30));
		txtNgay.setText(LocalDate.now().toString()); 
		topPanel.add(lblNgayChamCong);
		topPanel.add(txtNgay);
		add(topPanel, BorderLayout.NORTH);

		// === Table ====
		String[] cols = { "Mã NV", "Họ tên", "Đi làm (✔)", "Nghỉ phép (P)", "Đi muộn (L)" };
		model = new DefaultTableModel(cols, 0) {
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				return (columnIndex >= 2) ? Boolean.class : String.class;
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				return column >= 2; 
			}
		};

		table = new JTable(model) {
		    @Override
		    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
		        Component c = super.prepareRenderer(renderer, row, column);

		        if (isRowSelected(row)) {
		            c.setBackground(new Color(110, 153, 190));
		        } else {
		            c.setBackground(row % 2 == 0 ?new Color(220, 235, 245): Color.WHITE );
		        }

		        return c;
		    }
		};

		// Chỉ chọn 1 trạng thái duy nhất mỗi nhân viên (dòng)
		model.addTableModelListener(e -> {
		    if (updating) return; // nếu đang xử lý thì không làm gì

		    int row = e.getFirstRow();
		    int col = e.getColumn();

		    if (col >= 2 && col <= 4) {
		        updating = true;  // đánh dấu đang xử lý

		        for (int i = 2; i <= 4; i++) {
		            if (i != col) model.setValueAt(false, row, i);
		        }

		        updating = false; // kết thúc
		    }
		});


		
		JTableHeader header = table.getTableHeader();
		header.setBackground(Color.WHITE); // Màu header
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		add(scrollPane, BorderLayout.CENTER);

		// ==== Nút ===
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBackground(Color.WHITE);
		Color btncolor = new Color(76, 138, 184);
		
		btnLuu = new JButton("Lưu chấm công");
		btnLuu.setPreferredSize(new Dimension(135, 30));
		btnLuu.setBackground(btncolor);
		btnLuu.setForeground(Color.WHITE);
		btnLuu.setFont(new Font("Arial", Font.PLAIN, 14));

		btnXemLichSu = new JButton("Lịch sử");
		btnXemLichSu.setPreferredSize(new Dimension(120, 30));
		btnXemLichSu.setBackground(btncolor);
		btnXemLichSu.setForeground(Color.WHITE);
		btnXemLichSu.setFont(new Font("Arial", Font.PLAIN, 14));

		btnDong = new JButton("Đóng");
		btnDong.setPreferredSize(new Dimension(120, 30));
		btnDong.setBackground(btncolor);
		btnDong.setForeground(Color.WHITE);
		btnDong.setFont(new Font("Arial", Font.PLAIN, 14));

		bottomPanel.add(btnLuu);
		bottomPanel.add(btnXemLichSu);
		bottomPanel.add(btnDong);
		add(bottomPanel, BorderLayout.SOUTH);

		// Load danh sách nhân viên
		loadDanhSachNhanVien();

		 btnDong.addActionListener(e -> dispose());

	        btnLuu.addActionListener(e -> {
	            luuChamCong();
	        });
	        btnXemLichSu.addActionListener(e ->{
	        	LichSuChamCongDialog  lichsuDialog = new LichSuChamCongDialog(this);
	        	lichsuDialog.setVisible(true);
	        });
	    }


	private void loadDanhSachNhanVien() {
		  model.setRowCount(0); // clear table
		NhanSuController controller = new NhanSuController();
		ArrayList<Nhansu> list = controller.layDanhSachNhanSu();
		for (Nhansu ns : list) {
			model.addRow(new Object[] { ns.getMaNV(), ns.getHoTen(), false, false, false });
		}
	}


	private void luuChamCong() {
	    String ngay = txtNgay.getText().trim();
	    ChamCongController controller = new ChamCongController();

	    for (int i = 0; i < model.getRowCount(); i++) {
	        String maNV = (String) model.getValueAt(i, 0);
	        boolean dilam = (boolean) model.getValueAt(i, 2);
	        boolean nghiphep = (boolean) model.getValueAt(i, 3);
	        boolean dimuon = (boolean) model.getValueAt(i, 4);

	        if (dilam) {
	            controller.chamCong(maNV, ngay, "Đi làm");
	        } else if (nghiphep) {
	            controller.chamCong(maNV, ngay, "Nghỉ phép");
	        } else if (dimuon) {
	            controller.chamCong(maNV, ngay, "Đi muộn");
	        }
	    }

	    JOptionPane.showMessageDialog(this, "Đã lưu chấm công!");
	    dispose();
	}


}