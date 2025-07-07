package view;

import javax.swing.*;

import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import controller.NhanSuController;
import model.Nhansu;

import java.awt.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.stream.IntStream;

public class thongtinDialog extends JDialog {
	public JTextField txtMaNV, txtHoTen, txtCMND, txtDiaChi, txtSoDT, txtEmail;
	public JComboBox<String> cbPhongBan, cbChucVu, cbHocVan, cbTinhTrang;
	public JRadioButton rbNam, rbNu;
	public ButtonGroup bgGioiTinh;

	public JComboBox<Integer> cbNgaySinh, cbThangSinh, cbNamSinh;
	public JComboBox<Integer> cbNgayVaoLam, cbThangVaoLam, cbNamVaoLam;

	public JButton btnLuu, btnHuy;
	private DefaultTableModel tableModel;

	private boolean isEditMode = false; // mặc định là thêm mới

	public thongtinDialog(JFrame parent, DefaultTableModel tableModel) {
		super(parent, "Thêm Nhân Sự", true);
		this.tableModel = tableModel;
		setSize(500, 630);
		setLocationRelativeTo(parent);
		setLayout(new BorderLayout(10, 10));

		JPanel formPanel = new JPanel(new GridLayout(13, 2, 10, 10));
		formPanel.setBackground(Color.WHITE);
	
		Font titleFont = formPanel.getFont().deriveFont(Font.BOLD, 16f); 
		Color titleColor = new Color(76, 138, 184); 
		Color borderColor = new Color(0, 128, 128);
		Border coloredBorder = BorderFactory.createLineBorder(borderColor, 2); 

		TitledBorder titledBorder = BorderFactory.createTitledBorder(coloredBorder, "Thông tin chi tiết",
				TitledBorder.LEFT, TitledBorder.TOP, titleFont, titleColor);
		formPanel.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(2, 5, 5, 2), titledBorder));
        formPanel.setBackground(Color.WHITE);
		
		txtMaNV = new JTextField();
		txtHoTen = new JTextField();

		rbNam = new JRadioButton("Nam");
		rbNam.setFont(new Font("Arial", Font.BOLD, 15));
		rbNam.setBackground(Color.WHITE);
		rbNu = new JRadioButton("Nữ");
		rbNu.setFont(new Font("Arial", Font.BOLD, 15));
        rbNu.setBackground(Color.WHITE);
		bgGioiTinh = new ButtonGroup();
		bgGioiTinh.add(rbNam);
		bgGioiTinh.add(rbNu);
		JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		genderPanel.setBackground(Color.WHITE);
		genderPanel.add(rbNam);
		genderPanel.add(rbNu);

		cbNgaySinh = new JComboBox<>(IntStream.rangeClosed(1, 31).boxed().toArray(Integer[]::new));
		cbThangSinh = new JComboBox<>(IntStream.rangeClosed(1, 12).boxed().toArray(Integer[]::new));
		cbNamSinh = new JComboBox<>(
				IntStream.rangeClosed(1980, LocalDate.now().getYear()).boxed().toArray(Integer[]::new));
		cbNgaySinh.setBackground(Color.WHITE);
		cbThangSinh.setBackground(Color.WHITE);
		cbNamSinh.setBackground(Color.WHITE);
		JPanel ngaySinhPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		ngaySinhPanel.setBackground(Color.WHITE);
		ngaySinhPanel.add(cbNgaySinh);
		ngaySinhPanel.add(cbThangSinh);
		ngaySinhPanel.add(cbNamSinh);

		cbNgayVaoLam = new JComboBox<>(IntStream.rangeClosed(1, 31).boxed().toArray(Integer[]::new));
		cbThangVaoLam = new JComboBox<>(IntStream.rangeClosed(1, 12).boxed().toArray(Integer[]::new));
		cbNamVaoLam = new JComboBox<>(
				IntStream.rangeClosed(2000, LocalDate.now().getYear()).boxed().toArray(Integer[]::new));
		cbNgayVaoLam.setBackground(Color.WHITE);
		cbThangVaoLam.setBackground(Color.WHITE);
		cbNamVaoLam.setBackground(Color.WHITE);
		JPanel ngayVaoLamPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		ngayVaoLamPanel.setBackground(Color.WHITE);
		ngayVaoLamPanel.add(cbNgayVaoLam);
		ngayVaoLamPanel.add(cbThangVaoLam);
		ngayVaoLamPanel.add(cbNamVaoLam);

		txtCMND = new JTextField();
		txtDiaChi = new JTextField();
		txtSoDT = new JTextField();
		txtEmail = new JTextField();
		Font comboFont = new Font("Arial", Font.BOLD, 14);
		
		cbPhongBan = new JComboBox<>(new String[] { "Nhân sự", "Kế toán", "Kỹ thuật" });
		cbPhongBan.setBackground(Color.WHITE);
        cbPhongBan.setFont(comboFont);
        
		cbChucVu = new JComboBox<>(new String[] { "Nhân viên","Thực tập" , "Trưởng phòng","Phó trưởng phòng"});
		cbChucVu.setBackground(Color.WHITE);
        cbChucVu.setFont(comboFont);
        
		cbHocVan = new JComboBox<>(new String[] { "THPT", "Cao đẳng", "Đại học", "Thạc sĩ" ,"Tiến sĩ" });
		cbHocVan.setBackground(Color.WHITE);
        cbHocVan.setFont(comboFont);

		cbTinhTrang = new JComboBox<>(new String[] { "Đang làm", "Nghỉ việc", "Tạm nghỉ" });
		cbTinhTrang.setBackground(Color.WHITE);
        cbTinhTrang.setFont(comboFont);

        JLabel lblMaNV = new JLabel("Mã nhân viên:");
        lblMaNV.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(lblMaNV);
        formPanel.add(txtMaNV);

        JLabel lblHovaTen = new JLabel("Họ và tên:");
        lblHovaTen.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(lblHovaTen);
        formPanel.add(txtHoTen);

        JLabel lblGioiTinh = new JLabel("Giới tính:");
        lblGioiTinh.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(lblGioiTinh);
        formPanel.add(genderPanel);

        JLabel lblNgaySinh = new JLabel("Ngày sinh:");
        lblNgaySinh.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(lblNgaySinh);
        formPanel.add(ngaySinhPanel);

        JLabel lblCMND = new JLabel("Số CMND/CCCD:");
        lblCMND.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(lblCMND);
        formPanel.add(txtCMND);

        JLabel lblDiaChi = new JLabel("Địa chỉ:");
        lblDiaChi.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(lblDiaChi);
        formPanel.add(txtDiaChi);

        JLabel lblSoDT = new JLabel("Số điện thoại:");
        lblSoDT.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(lblSoDT);
        formPanel.add(txtSoDT);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(lblEmail);
        formPanel.add(txtEmail);

        JLabel lblPhongBan = new JLabel("Phòng ban:");
        lblPhongBan.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(lblPhongBan);
        formPanel.add(cbPhongBan);

        JLabel lblChucVu = new JLabel("Chức vụ:");
        lblChucVu.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(lblChucVu);
        formPanel.add(cbChucVu);

        JLabel lblNgayVaoLam = new JLabel("Ngày vào làm:");
        lblNgayVaoLam.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(lblNgayVaoLam);
        formPanel.add(ngayVaoLamPanel);

        JLabel lblHocVan = new JLabel("Trình độ học vấn:");
        lblHocVan.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(lblHocVan);
        formPanel.add(cbHocVan);

        JLabel lblTinhTrang = new JLabel("Tình trạng làm việc:");
        lblTinhTrang.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(lblTinhTrang);
        formPanel.add(cbTinhTrang);

		add(formPanel, BorderLayout.CENTER);

		Color btncolor = new Color(76, 138, 184);
		JPanel btnPanel = new JPanel();	
		btnPanel.setBackground(Color.WHITE);
		btnPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		btnLuu = new JButton("Lưu");
		ImageIcon luuIcon = new ImageIcon("src/Images/luu.png");
		Image scaledImg1 = luuIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        btnLuu = new JButton("Lưu",new ImageIcon(scaledImg1));
        btnLuu.setPreferredSize(new Dimension(120, 30));
        btnLuu.setBackground(btncolor); 
        btnLuu.setForeground(Color.WHITE); 
        btnLuu.setFont(new Font("Arial", Font.PLAIN, 16));
        
		btnHuy = new JButton("Hủy");
		ImageIcon huyIcon = new ImageIcon("src/Images/huy.png");
		Image scaledImg2 = huyIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        btnHuy = new JButton("Hủy",new ImageIcon(scaledImg2));
        btnHuy.setPreferredSize(new Dimension(120, 30));
        btnHuy.setBackground(btncolor); 
        btnHuy.setForeground(Color.WHITE); 
        btnHuy.setFont(new Font("Arial", Font.PLAIN, 16));
        
		btnPanel.add(btnLuu);
		btnPanel.add(btnHuy);
		add(btnPanel, BorderLayout.SOUTH);

		btnLuu.addActionListener(e -> {
		    Nhansu ns = layThongTinNhanSuTuForm();
		    if (ns != null) {
		        NhanSuController controller = new NhanSuController();
		        boolean result;

		        try {
		            if (isEditMode) {
		                result = controller.capNhatNhanSu(ns);
		                if (result) {
		                    JOptionPane.showMessageDialog(this, "✅ Cập nhật nhân sự thành công!");
		                    dispose();
		                } else {
		                    JOptionPane.showMessageDialog(this, "❌ Cập nhật thất bại!");
		                }
		            } else {
		                result = controller.themNhanSu(ns);  
		                if (result) {
		                    tableModel.addRow(new Object[] {
		                        ns.getMaNV(), ns.getHoTen(), ns.getGioiTinh(),
		                        ns.getNgaySinh(), ns.getPhongBan(), ns.getChucVu(),
		                        ns.getNgayVaoLam(), ns.getTinhTrangLamViec()
		                    });
		                    JOptionPane.showMessageDialog(this, "✅ Thêm nhân sự thành công!");
		                    dispose();
		                } else {
		                    JOptionPane.showMessageDialog(this, "❌ Thêm thất bại!");
		                }
		            }

		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(this, ex.getMessage(), "❌ Lỗi", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});

		btnHuy.addActionListener(e -> dispose());
	}

	private Nhansu layThongTinNhanSuTuForm() {
		try {
			String gioiTinh = rbNam.isSelected() ? "Nam" : (rbNu.isSelected() ? "Nữ" : "");
			if (gioiTinh.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn giới tính.");
				return null;
			}

			int ngaySinh = (int) cbNgaySinh.getSelectedItem();
			int thangSinh = (int) cbThangSinh.getSelectedItem();
			int namSinh = (int) cbNamSinh.getSelectedItem();
			Date dateSinh = Date.valueOf(LocalDate.of(namSinh, thangSinh, ngaySinh));

			int ngayVL = (int) cbNgayVaoLam.getSelectedItem();
			int thangVL = (int) cbThangVaoLam.getSelectedItem();
			int namVL = (int) cbNamVaoLam.getSelectedItem();
			Date dateVaoLam = Date.valueOf(LocalDate.of(namVL, thangVL, ngayVL));

			return new Nhansu(txtMaNV.getText().trim(), txtHoTen.getText().trim(), gioiTinh, dateSinh,
					txtCMND.getText().trim(), txtDiaChi.getText().trim(), txtSoDT.getText().trim(),
					txtEmail.getText().trim(), cbPhongBan.getSelectedItem().toString(),
					cbChucVu.getSelectedItem().toString(), dateVaoLam, cbHocVan.getSelectedItem().toString(),
					cbTinhTrang.getSelectedItem().toString());
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn đầy đủ thông tin ngày/tháng/năm!");
			return null;
		}
	}

	public void setNhanSu(Nhansu ns) {
		txtMaNV.setText(ns.getMaNV());
		txtHoTen.setText(ns.getHoTen());
		if ("Nam".equalsIgnoreCase(ns.getGioiTinh())) {
			rbNam.setSelected(true);
		} else if ("Nữ".equalsIgnoreCase(ns.getGioiTinh())) {
			rbNu.setSelected(true);
		}
		LocalDate ngaySinh = ns.getNgaySinh().toLocalDate();
		cbNgaySinh.setSelectedItem(ngaySinh.getDayOfMonth());
		cbThangSinh.setSelectedItem(ngaySinh.getMonthValue());
		cbNamSinh.setSelectedItem(ngaySinh.getYear());

		txtCMND.setText(ns.getCmnd());
		txtDiaChi.setText(ns.getDiaChi());
		txtSoDT.setText(ns.getSoDienThoai());
		txtEmail.setText(ns.getEmail());
		cbPhongBan.setSelectedItem(ns.getPhongBan());
		cbChucVu.setSelectedItem(ns.getChucVu());

		LocalDate ngayVaoLam = ns.getNgayVaoLam().toLocalDate();
		cbNgayVaoLam.setSelectedItem(ngayVaoLam.getDayOfMonth());
		cbThangVaoLam.setSelectedItem(ngayVaoLam.getMonthValue());
		cbNamVaoLam.setSelectedItem(ngayVaoLam.getYear());

		cbHocVan.setSelectedItem(ns.getTrinhDoHocVan());
		cbTinhTrang.setSelectedItem(ns.getTinhTrangLamViec());

		txtMaNV.setEditable(false); // Không cho sửa mã nhân viên
	}

	public thongtinDialog(JFrame parent, DefaultTableModel tableModel, Nhansu nsToEdit) {
		this(parent, tableModel); 
		setTitle("Cập nhật Nhân Sự");
		isEditMode = true;
		setNhanSu(nsToEdit); 
	}

}