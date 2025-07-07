package view;

import javax.swing.*;

import javax.swing.table.DefaultTableModel;

import util.FileExporter;
import controller.NhanSuController;
import util.NhanSuThread;

import java.awt.*;
import java.util.ArrayList;

import model.Nhansu;

public class QuanLyNhanSu extends JFrame {

	private JPanel tab1, tab3;

	BangLuongPanel tab2;
	
	private JButton btnThem, btnXoa, btnSua, btnTim, btnLammoi, btnXuatCSV,btnChamCong;
	private JComboBox cmbTim;
	private JTextField txtTuKhoa;

	private JTable table;
	private DefaultTableModel tableModel;

	public QuanLyNhanSu() {
		setTitle("QUẢN LÝ NHÂN SỰ");
		setSize(1000, 650);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		JTabbedPane tabPanel = new JTabbedPane();
		
		//tab 1
		tab1 = new JPanel();
		tab1.setLayout(new BorderLayout(10, 5));

		// === Nhập thông tin tìm kiếm ===
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
		topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		topPanel.setBackground(new Color(130, 173, 209));

		// Hàng 1
		JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
		searchPanel.setBackground(Color.WHITE);
		JLabel searchLabel = new JLabel("Tìm kiếm theo:");
		searchLabel.setFont(new Font("Arial", Font.BOLD, 16));
		searchPanel.add(searchLabel);
		
		Color btncolor = new Color(76, 138, 184);
		
		ImageIcon timIcon = new ImageIcon("src/Images/tim.png");
		Image scaledImg1 = timIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		btnTim = new JButton("Tìm kiếm", new ImageIcon(scaledImg1));
		btnTim.setPreferredSize(new Dimension(120, 30));
		btnTim.setBackground(btncolor);
		btnTim.setForeground(Color.WHITE);

		cmbTim = new JComboBox<>(new String[] { "Tên nhân viên", "Giới tính" });
		cmbTim.setFont(new Font("Arial", Font.PLAIN, 15));
		cmbTim.setBackground(Color.WHITE);
		cmbTim.setForeground(Color.BLACK);
		cmbTim.setBorder(BorderFactory.createEmptyBorder());

		txtTuKhoa = new JTextField();
		txtTuKhoa.setPreferredSize(new Dimension(200, 30));
		txtTuKhoa.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(0, 128, 128), 2, true), 																											
																													
																				
						BorderFactory.createEmptyBorder(5, 10, 5, 10) // Padding bên trong
				));
		txtTuKhoa.setFont(new Font("Arial", Font.PLAIN, 16));
		txtTuKhoa.setBackground(Color.WHITE);
		txtTuKhoa.setForeground(btncolor);
		cmbTim.setPreferredSize(new Dimension(150, 30));

		searchPanel.add(cmbTim);
		searchPanel.add(txtTuKhoa);
		searchPanel.add(btnTim);

		// Hàng 2: Nút
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
		buttonPanel.setBackground(Color.WHITE);

		ImageIcon themIcon = new ImageIcon("src/Images/them.png");
		Image scaledImg2 = themIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		btnThem = new JButton("Thêm", new ImageIcon(scaledImg2));
		btnThem.setPreferredSize(new Dimension(120, 30));
		btnThem.setBackground(btncolor);
		btnThem.setForeground(Color.WHITE);
		btnThem.setFont(new Font("Arial", Font.BOLD, 14));
		btnThem.setFont(new Font("Arial", Font.PLAIN, 16));

		ImageIcon xoaIcon = new ImageIcon("src/Images/xoa.png");
		Image scaledImg3 = xoaIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		btnXoa = new JButton("Xoá", new ImageIcon(scaledImg3));
		btnXoa.setPreferredSize(new Dimension(120, 30));
		btnXoa.setBackground(btncolor);
		btnXoa.setForeground(Color.WHITE);
		btnXoa.setFont(new Font("Arial", Font.PLAIN, 16));

		ImageIcon suaIcon = new ImageIcon("src/Images/sua.png");
		Image scaledImg4 = suaIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		btnSua = new JButton("Sửa", new ImageIcon(scaledImg4));
		btnSua.setPreferredSize(new Dimension(120, 30));
		btnSua.setBackground(btncolor);
		btnSua.setForeground(Color.WHITE);
		btnSua.setFont(new Font("Arial", Font.PLAIN, 16));

		ImageIcon lamMoiIcon = new ImageIcon("src/Images/lamMoi.png");
		Image scaledImg5 = lamMoiIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		btnLammoi = new JButton("Làm mới", new ImageIcon(scaledImg5));
		btnLammoi.setPreferredSize(new Dimension(130, 30));
		btnLammoi.setBackground(btncolor);
		btnLammoi.setForeground(Color.WHITE);
		btnLammoi.setFont(new Font("Arial", Font.PLAIN, 16));
		
		ImageIcon csvIcon = new ImageIcon("src/Images/file.png");
		Image scaledImg6 = csvIcon.getImage().getScaledInstance(23, 23, Image.SCALE_SMOOTH);
		btnXuatCSV = new JButton("Xuất CSV", new ImageIcon(scaledImg6));
		btnXuatCSV.setPreferredSize(new Dimension(130, 30));
		btnXuatCSV.setBackground(new Color(176, 226, 255));
		btnXuatCSV.setForeground(Color.BLACK);
		btnXuatCSV.setFont(new Font("Arial", Font.PLAIN, 15));
		
		ImageIcon chamCongIcon = new ImageIcon("src/Images/lich.png");
		Image scaledImg7 = chamCongIcon.getImage().getScaledInstance(23, 23, Image.SCALE_SMOOTH);
		 btnChamCong = new JButton("Chấm công", new ImageIcon(scaledImg7));
		 btnChamCong.setPreferredSize(new Dimension(150, 30));
		 btnChamCong.setBackground(new Color(176, 226, 255));
		 btnChamCong.setForeground(Color.BLACK);
		 btnChamCong.setFont(new Font("Arial", Font.PLAIN, 15));

		buttonPanel.add(btnThem);
		buttonPanel.add(btnXoa);
		buttonPanel.add(btnSua);
		buttonPanel.add(btnLammoi);
		buttonPanel.add(btnXuatCSV);
		buttonPanel.add(btnChamCong);

		topPanel.add(buttonPanel);
		topPanel.add(searchPanel);
		
		tab1.add(topPanel, BorderLayout.NORTH);

		// Bảng
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());

		JPanel titlePanel = new JPanel();
		titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 7, 10));
		JLabel title = new JLabel("Quản Lý Nhân Sự");
		title.setFont(new Font("Verdana", Font.BOLD, 25));
		titlePanel.add(title);
		titlePanel.setBackground(Color.WHITE);
		centerPanel.add(titlePanel, BorderLayout.NORTH);

		String[] col = { "Mã nhân viên", "Họ và tên", "Giới tính", "Ngày sinh", "Phòng ban", "Chức vụ", "Ngày vào làm",
				"Tình trạng làm việc" };
		tableModel = new DefaultTableModel(col, 0);
		table = new JTable(tableModel);
		table.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 13));
		table.getTableHeader().setBackground(Color.WHITE);

		NhanSuThread thread = new NhanSuThread(tableModel);
		thread.start();

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		centerPanel.add(scrollPane, BorderLayout.CENTER);

		tab1.add(centerPanel, BorderLayout.CENTER);
   //========== Chức năng ================
		btnThem.addActionListener(e -> {
			thongtinDialog dialog = new thongtinDialog(this, tableModel);
			dialog.setVisible(true);
		});

		btnLammoi.addActionListener(e -> {
			new NhanSuThread(tableModel).start();
		});

		btnSua.addActionListener(e -> {
			int selectedRow = table.getSelectedRow();
			if (selectedRow != -1) {
				String maNV = tableModel.getValueAt(selectedRow, 0).toString();
				NhanSuController controller = new NhanSuController();
				Nhansu ns = controller.timNhanSuTheoMa(maNV);
				
				if (ns != null) {
					thongtinDialog dialog = new thongtinDialog(this, tableModel, ns);
					dialog.setVisible(true);
					new NhanSuThread(tableModel).start(); // làm mới bảng sau khi sửa
				}
			} else {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân sự cần sửa.");
			}
		});

		btnXoa.addActionListener(e -> {
			int selectedRow = table.getSelectedRow();
			if (selectedRow == -1) {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần xoá.");
				return;
			}
			int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xoá nhân viên này?", "Xác nhận xoá",
					JOptionPane.YES_NO_OPTION);
			if (confirm == JOptionPane.YES_OPTION) {
				String maNV = tableModel.getValueAt(selectedRow, 0).toString();
				NhanSuController dao = new NhanSuController();
				if (dao.xoaNhanSu(maNV)) {
					tableModel.removeRow(selectedRow);
					JOptionPane.showMessageDialog(this, "Xoá thành công!");
				} else {
					JOptionPane.showMessageDialog(this, "Xoá thất bại.");
				}
			}
		});

		btnTim.addActionListener(e -> {
			String tuKhoa = txtTuKhoa.getText().trim();
			if (tuKhoa.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập từ khoá tìm kiếm.");
				return;
			}

			String tieuChi = cmbTim.getSelectedItem().toString();
			NhanSuController controller = new NhanSuController();
			ArrayList<Nhansu> ketQua = controller.timKiemNhanSu(tuKhoa, tieuChi);

			tableModel.setRowCount(0);
			for (Nhansu ns : ketQua) {
				tableModel.addRow(new Object[] { ns.getMaNV(), ns.getHoTen(), ns.getGioiTinh(), ns.getNgaySinh(),
						ns.getPhongBan(), ns.getChucVu(), ns.getNgayVaoLam(), ns.getTinhTrangLamViec() });
			}

			if (ketQua.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Không tìm thấy kết quả.");
			}
		});
		
		btnXuatCSV.addActionListener(e -> {
		    JFileChooser fc = new JFileChooser();
		    int result = fc.showSaveDialog(null);
		    if (result == JFileChooser.APPROVE_OPTION) {
		        String selectedPath = fc.getSelectedFile().getAbsolutePath();
		        String filePath = selectedPath.endsWith(".csv") ? selectedPath : selectedPath + ".csv";

		        // Đảm bảo không thay đổi filePath sau khi gán
		        new Thread(() -> {
		            boolean success = FileExporter.xuatNhanSuTuDatabaseCSV(filePath);
		            SwingUtilities.invokeLater(() -> {
		                if (success) {
		                    JOptionPane.showMessageDialog(null, "Xuất CSV thành công!");
		                } else {
		                    JOptionPane.showMessageDialog(null, "Xuất CSV thất bại!");
		                }
		            });
		        }).start();
		       
		    }
		});
		btnChamCong.addActionListener(e -> {
		    ChamCongDialog chamCongDialog = new ChamCongDialog(this);
		    chamCongDialog.setVisible(true);
		});
		
		//tab 2
		tab2 = new BangLuongPanel(); 
		//tab 3
		tab3 = new ThongKePanel(); 
		
		tabPanel.add(tab1, "Quản Lý Nhân Sự");
		tabPanel.add(tab2, "Quản Lý Lương");
		tabPanel.add(tab3, "Thống kê");
		add((Component) tabPanel);
	
		
		setVisible(true);

	}
}
