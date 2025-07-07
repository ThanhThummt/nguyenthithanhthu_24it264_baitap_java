package view;

import controller.BaiHatController;

import model.BaiHat;
import util.CSVExporter;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.*;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.*;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import java.util.List;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class AdminForm extends JFrame {
	private JPanel contentPanel;
	private CardLayout cardLayout;
	private JTextField searchField;
	private BaiHatController controller = new BaiHatController();
	private DefaultTableModel tableModel;
	private JPanel chartPanel;
	private JLabel tongSoLabel;
	private ChartPanel barChartPanel;

	private void loadTable(DefaultTableModel tableModel) {
		tableModel.setRowCount(0); // Xóa dữ liệu cũ
		for (BaiHat bh : controller.layTatCaBaiHat()) {
			Object[] row = { bh.getId(), bh.getTenBaiHat(), bh.getCaSi(), bh.getTheLoai(), bh.getNamPhatHanh() };
			tableModel.addRow(row);
		}
	}


	public AdminForm() {
		setTitle("GIAO DIỆN ADMIN - ỨNG DỤNG TRA CỨU BÀI HÁT");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1100, 600);
		setLocationRelativeTo(null);

		// ================= Sidebar (Menu trái) =================
		JPanel sidebar = new JPanel();
		sidebar.setBackground(new Color(30, 60, 90));
		sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
		sidebar.setPreferredSize(new Dimension(200, getHeight()));

		String[] menuItems = { "TRANG CHỦ", "QUẢN LÍ BÀI HÁT", "THỐNG KÊ",
				"ĐĂNG XUẤT" };

		// ================= Content chính (CardLayout) =================
		contentPanel = new JPanel();
		cardLayout = new CardLayout();
		contentPanel.setLayout(cardLayout);

		// Add các màn hình vào contentPanel
		contentPanel.add(createLabelPanel("src/Images/trangchu.jpg"), "TRANG CHỦ");
		contentPanel.add(createSongPanel(), "QUẢN LÍ BÀI HÁT");
		contentPanel.add(createReportPanel(), "THỐNG KÊ");
		//contentPanel.add(createLabelPanel("🚪 Bạn đã đăng xuất"), "ĐĂNG XUẤT");
		for (String item : menuItems) {
		    JButton btn = new JButton(item);
		    btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
		    btn.setAlignmentX(Component.CENTER_ALIGNMENT);
		    btn.setFocusPainted(false);
		    btn.setForeground(Color.WHITE);
		    btn.setBackground(new Color(30, 60, 90));
		    btn.setBorderPainted(false);

		    btn.addActionListener(e -> {
		        if (item.equals("ĐĂNG XUẤT")) {
		            int confirm = JOptionPane.showConfirmDialog(
		                this,
		                "Bạn có chắc chắn muốn đăng xuất?",
		                "Xác nhận đăng xuất",
		                JOptionPane.YES_NO_OPTION
		            );
		            if (confirm == JOptionPane.YES_OPTION) {
		                dispose(); 
		                new RoleSelectionFrame().setVisible(true); 
		            }
		        } else {
		            cardLayout.show(contentPanel, item);
		        }
		    });

		    sidebar.add(btn);
		    sidebar.add(Box.createVerticalStrut(5));
		}


		// ================= Tổng hợp giao diện =================
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sidebar, contentPanel);
		splitPane.setDividerLocation(200);
		splitPane.setEnabled(false); // Không cho resize
		add(splitPane);
	}

	// ====================================================
	private JPanel createLabelPanel(String imagePath) {
	    JPanel panel = new JPanel(new BorderLayout());

	    ImageIcon icon = new ImageIcon(imagePath); 
	    Image img = icon.getImage().getScaledInstance(860, 600, Image.SCALE_SMOOTH);
	    JLabel imageLabel = new JLabel(new ImageIcon(img));

	    imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    panel.add(imageLabel, BorderLayout.CENTER);

	    return panel;
	}


	private JPanel createSongPanel() {
		JPanel panel = new JPanel(new BorderLayout());

		// === Thông tin ===
		JPanel formPanel = new JPanel(new GridBagLayout());
		formPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		formPanel.setBackground(Color.WHITE);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// Hàng 1: Tên bài hát
		gbc.gridx = 0;
		gbc.gridy = 0;
		formPanel.add(new JLabel("Tên bài hát:"), gbc);
		gbc.gridx = 1;
		JTextField txtTitle = new JTextField(20);
		formPanel.add(txtTitle, gbc);

		// Hàng 2: Ca sĩ
		gbc.gridx = 0;
		gbc.gridy = 1;
		formPanel.add(new JLabel("Ca sĩ:"), gbc);
		gbc.gridx = 1;
		JTextField txtArtist = new JTextField(20);
		formPanel.add(txtArtist, gbc);

		// Hàng 3: Thể loại
		gbc.gridx = 0;
		gbc.gridy = 2;
		formPanel.add(new JLabel("Thể loại:"), gbc);
		gbc.gridx = 1;
		JTextField txtGenre = new JTextField(20);
		formPanel.add(txtGenre, gbc);

		// Hàng 4: Năm phát hành
		gbc.gridx = 0;
		gbc.gridy = 3;
		formPanel.add(new JLabel("Năm phát hành:"), gbc);
		gbc.gridx = 1;
		JTextField txtYear = new JTextField(20);
		formPanel.add(txtYear, gbc);

		// === Bọc formPanel vào một FlowLayout.CENTER để căn giữa ===
		JPanel formWrapper = new JPanel(new FlowLayout(FlowLayout.LEFT));
		formWrapper.add(formPanel);
		formPanel.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY, 2), "Thông tin bài hát",
						TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14), Color.DARK_GRAY));
	
		ImageIcon imageIcon = new ImageIcon("src/Images/1.png");
		Image scaledImage = imageIcon.getImage().getScaledInstance(400, 150, Image.SCALE_SMOOTH);
		JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
		imageLabel.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 0));

		// === Kết hợp form và ảnh vào một panel ngang ===
		JPanel combinedPanel = new JPanel(new BorderLayout());
		combinedPanel.setBackground(Color.WHITE);
		combinedPanel.add(formPanel, BorderLayout.CENTER);
		combinedPanel.add(imageLabel, BorderLayout.EAST);

		formWrapper.add(combinedPanel);
		formWrapper.setBackground(Color.WHITE);

		panel.add(formWrapper, BorderLayout.NORTH);

		// ===== Bảng bài hát =====
		String[] columnNames = { "ID", "Tên bài hát", "Ca sĩ", "Thể loại", "Năm phát hành" };
		tableModel = new DefaultTableModel(columnNames, 0);
		JTable table = new JTable(tableModel);
		table.getTableHeader().setBackground(new Color(154, 214, 255)); 
		table.getTableHeader().setForeground(Color.WHITE); 
		table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));

		loadTable(tableModel);

		JScrollPane scrollPane = new JScrollPane(table);
		panel.add(scrollPane, BorderLayout.CENTER);

		// ===== Các nút chức năng =====
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.setPreferredSize(new Dimension(150, 50));
		JTextField searchtxt = new JTextField(25);
		searchtxt.setPreferredSize(new Dimension(100, 30));

		ImageIcon searchIcon = new ImageIcon("src/Images/search-icon.png");
		Image scaledImg1 = searchIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon = new ImageIcon(scaledImg1);
		JButton searchbtn = new JButton("Tìm kiếm", resizedIcon);
		searchbtn.setIconTextGap(3);

		ImageIcon addIcon = new ImageIcon("src/Images/add.png");
		Image scaledImg2 = addIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon2 = new ImageIcon(scaledImg2);
		JButton addbtn = new JButton("Thêm", resizedIcon2);

		ImageIcon editIcon = new ImageIcon("src/Images/edit.png");
		Image scaledImg3 = editIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon3 = new ImageIcon(scaledImg3);
		JButton editbtn = new JButton("Sửa", resizedIcon3);

		ImageIcon deleteIcon = new ImageIcon("src/Images/delete.png");
		Image scaledImg4 = deleteIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon4 = new ImageIcon(scaledImg4);
		JButton deletebtn = new JButton("Xóa", resizedIcon4);

		ImageIcon refreshIcon = new ImageIcon("src/Images/refresh.png");
		Image scaledImg = refreshIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		JButton refreshbtn = new JButton("Làm mới", new ImageIcon(scaledImg));

		Dimension btnSDimension = new Dimension(100, 30);
		searchbtn.setPreferredSize(btnSDimension);
		addbtn.setPreferredSize(btnSDimension);
		editbtn.setPreferredSize(btnSDimension);
		deletebtn.setPreferredSize(btnSDimension);
		refreshbtn.setPreferredSize(btnSDimension);

		Color textColor = new Color(47, 79, 117);
		searchbtn.setForeground(textColor);
		addbtn.setForeground(textColor);
		editbtn.setForeground(textColor);
		deletebtn.setForeground(textColor);
		refreshbtn.setForeground(textColor);

		Color bgColor = Color.WHITE;
		searchbtn.setBackground(bgColor);
		addbtn.setBackground(bgColor);
		editbtn.setBackground(bgColor);
		deletebtn.setBackground(bgColor);
		refreshbtn.setBackground(bgColor);

		Border border = new LineBorder(textColor, 2);
		searchbtn.setBorder(border);
		addbtn.setBorder(border);
		editbtn.setBorder(border);
		deletebtn.setBorder(border);
		refreshbtn.setBorder(border);

		buttonPanel.add(searchtxt);
		buttonPanel.add(searchbtn);
		buttonPanel.add(addbtn);
		buttonPanel.add(editbtn);
		buttonPanel.add(deletebtn);
		buttonPanel.add(refreshbtn);
		
		buttonPanel.setBackground(Color.WHITE);
		ImageIcon exportIcon = new ImageIcon("src/Images/export.png");
		Image scaledExport = exportIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		JButton exportBtn = new JButton("Xuất File", new ImageIcon(scaledExport));
		exportBtn.setPreferredSize(btnSDimension);
		exportBtn.setForeground(textColor);
		exportBtn.setBackground(bgColor);
		exportBtn.setBorder(border);
		buttonPanel.add(exportBtn);


		// thêm
		addbtn.addActionListener(e -> {
			try {
				controller.themBaiHat(txtTitle.getText(), txtArtist.getText(), txtGenre.getText(), txtYear.getText());
				loadTable(tableModel); // cập nhật lại bảng
				capNhatThongKe(); 
				JOptionPane.showMessageDialog(this, "Thêm bài hát thành công!", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(this, "Lỗi khi thêm bài hát!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
		});

		// Sửa bài hát
		editbtn.addActionListener(e -> {
			int selectedRow = table.getSelectedRow();
			if (selectedRow >= 0) {
				int id = (int) tableModel.getValueAt(selectedRow, 0);
				String tenMoi = txtTitle.getText();
				String caSiMoi = txtArtist.getText();
				String theLoaiMoi = txtGenre.getText();
				String namMoi = txtYear.getText();
				boolean ok = controller.suaBaiHat(id, tenMoi, caSiMoi, theLoaiMoi, namMoi);
				if (ok) {
					JOptionPane.showMessageDialog(this, "Đã cập nhật thành công.");
					loadTable(tableModel);
					capNhatThongKe(); 
				} else {
					JOptionPane.showMessageDialog(this, "Không tìm thấy bài hát để cập nhật.");
				}
			} else {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn bài hát để sửa.");
			}
		});

		// Xóa bài hát
		deletebtn.addActionListener(e -> {
			int selectedRow = table.getSelectedRow();
			if (selectedRow >= 0) {
				int id = (int) tableModel.getValueAt(selectedRow, 0);
				int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa?", "Xác nhận",
						JOptionPane.YES_NO_OPTION);
				if (confirm == JOptionPane.YES_OPTION) {
					boolean ok = controller.xoaBaiHat(id);
					if (ok) {
						JOptionPane.showMessageDialog(this, "Đã xóa bài hát.");
						loadTable(tableModel);
						capNhatThongKe(); 
					} else {
						JOptionPane.showMessageDialog(this, "Không tìm thấy bài hát để xóa.");
					}
				}
			} else {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn bài hát để xóa.");
			}
		});

		searchbtn.addActionListener(e -> {
			String tuKhoa = searchtxt.getText().trim();
			if (!tuKhoa.isEmpty()) {
				tableModel.setRowCount(0);
				for (BaiHat bh : controller.timKiemBaiHat(tuKhoa)) {
					Object[] row = { bh.getId(), bh.getTenBaiHat(), bh.getCaSi(), bh.getTheLoai(),
							bh.getNamPhatHanh() };
					tableModel.addRow(row);
				}
			} else {
				loadTable(tableModel);
				
			}
		});

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow >= 0) {
					txtTitle.setText(tableModel.getValueAt(selectedRow, 1).toString());
					txtArtist.setText(tableModel.getValueAt(selectedRow, 2).toString());
					txtGenre.setText(tableModel.getValueAt(selectedRow, 3).toString());
					txtYear.setText(tableModel.getValueAt(selectedRow, 4).toString());
				}
			}
		});

		refreshbtn.addActionListener(e -> {
			loadTable(tableModel);

			txtTitle.setText("");
			txtArtist.setText("");
			txtGenre.setText("");
			txtYear.setText("");
			searchtxt.setText("");
		});
	

		exportBtn.addActionListener(e -> {
		    JFileChooser fileChooser = new JFileChooser();
		    fileChooser.setDialogTitle("Chọn nơi lưu file CSV");

		    int userSelection = fileChooser.showSaveDialog(this);  // `this` là JFrame
		    if (userSelection == JFileChooser.APPROVE_OPTION) {
		        File fileToSave = fileChooser.getSelectedFile();
		        if (!fileToSave.getAbsolutePath().endsWith(".csv")) {
		            fileToSave = new File(fileToSave + ".csv");
		        }

		        boolean success = util.CSVExporter.exportTableToCSV(tableModel, fileToSave, this);
		        if (success) {
		            System.out.println("Exported successfully to: " + fileToSave.getAbsolutePath());
		        }
		    }
		});


		panel.add(buttonPanel, BorderLayout.SOUTH);
		return panel;

	}
	
	private JFreeChart createBarChart() {
	    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

	    List<BaiHat> danhSach = controller.layTatCaBaiHat();
	    Map<String, Integer> theLoaiMap = new HashMap<>();
	    for (BaiHat bh : danhSach) {
	        theLoaiMap.put(bh.getTheLoai(), theLoaiMap.getOrDefault(bh.getTheLoai(), 0) + 1);
	    }

	    for (Map.Entry<String, Integer> entry : theLoaiMap.entrySet()) {
	        dataset.addValue(entry.getValue(), "Số lượng", entry.getKey());
	    }

	    // Tạo biểu đồ
	    JFreeChart chart = ChartFactory.createBarChart(
	        "Số lượng bài hát theo thể loại", // Chart title
	        "Thể loại",                       // X-axis Label
	        "Số lượng",                       // Y-axis Label
	        dataset,
	        PlotOrientation.VERTICAL,
	        false, true, false
	    );

	    CategoryPlot plot = chart.getCategoryPlot();
	    NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
	    rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

	    return chart;
	}
	
	private void capNhatThongKe() {
	    List<BaiHat> danhSach = controller.layTatCaBaiHat();
	    int tongSo = danhSach.size();
	    tongSoLabel.setText("Tổng số bài hát: " + tongSo);

	    // Cập nhật biểu đồ mới
	    JFreeChart chart = createBarChart();
	    barChartPanel.setChart(chart);
	    barChartPanel.revalidate(); 
	    barChartPanel.repaint();  
	}

	private JPanel createReportPanel() {
	    JPanel panel = new JPanel(new BorderLayout());
	    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

	    tongSoLabel = new JLabel(); 
	    tongSoLabel.setFont(new Font("Arial", Font.BOLD, 16));
	    tongSoLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    tongSoLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

	    barChartPanel = new ChartPanel(createBarChart()); 
	    barChartPanel.setPreferredSize(new Dimension(600, 400));

	    panel.add(tongSoLabel, BorderLayout.NORTH);
	    panel.add(barChartPanel, BorderLayout.CENTER);

	    capNhatThongKe();

	    return panel;
	}
}
