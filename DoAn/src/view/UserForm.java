package view;
import controller.BaiHatController;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.*;

import model.BaiHat;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Vector;


public class UserForm extends JFrame {
    private JTextField txtSearch;
    private JComboBox<String> cbSearchBy;
    private JComboBox<String> cbFilterDevice;
    private JTable tblSongs;
    private JTextArea txtSongDetails;
    private DefaultTableModel tableModel;
    private DefaultListModel<String> favoriteListModel;
    private JList<String> favoriteList;
   private BaiHatController controller = new BaiHatController();
   private BaiHatController controller2 = new BaiHatController();

    JEditorPane txtSongDetailsPane;
    private java.util.List<BaiHat> favoriteSongs = new ArrayList<>();

    private void loadTable(DefaultTableModel tableModel) {
		tableModel.setRowCount(0); 
		for (BaiHat bh : controller.layTatCaBaiHat()) {
			Object[] row = { bh.getId(), bh.getTenBaiHat(), bh.getCaSi(), bh.getTheLoai(), bh.getNamPhatHanh() };
			tableModel.addRow(row);
		}
	}
    

    public UserForm() {
        setTitle("GIAO DIỆN NGƯỜI DÙNG -ỨNG DỤNG TRA CỨU BÀI HÁT");
        setSize(1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); 

        txtSearch = new JTextField(27);
        txtSearch.setPreferredSize(new Dimension(100, 30));
        topPanel.add(Box.createHorizontalStrut(95));
        topPanel.setBackground(new Color(102, 153, 204));
        topPanel.add(txtSearch);
        ImageIcon searchIcon = new ImageIcon("src/Images/searchuser.png");
		Image scaledImg1 = searchIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon = new ImageIcon(scaledImg1);
		JButton searchbtn = new JButton("Tìm kiếm", resizedIcon);
		searchbtn.setIconTextGap(3);
		Dimension btnSDimension = new Dimension(100, 30);
		searchbtn.setPreferredSize(btnSDimension);
		Color textColor = new Color(47, 79, 117);
		searchbtn.setForeground(textColor);
		searchbtn.setBackground(Color.WHITE);
		Border border = new LineBorder(textColor, 3);
		searchbtn.setBorder(border);
		topPanel.add(searchbtn);
		topPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5,60)); 
		
		ImageIcon refreshIcon = new ImageIcon("src/Images/refresh.png");
		Image scaledRefresh = refreshIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		ImageIcon resizedRefresh = new ImageIcon(scaledRefresh);

		JButton btnRefresh = new JButton("Làm mới", resizedRefresh);
		btnRefresh.setIconTextGap(3);
		btnRefresh.setPreferredSize(btnSDimension);
		btnRefresh.setForeground(textColor);
		btnRefresh.setBackground(Color.WHITE);
		btnRefresh.setBorder(border);

		topPanel.add(btnRefresh);

		add(topPanel, BorderLayout.NORTH);

		searchbtn.addActionListener(e -> {
			String tuKhoa = txtSearch.getText().trim();
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
     //====== Chi tiết bài hát ==========
        JSplitPane centerSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        centerSplit.setDividerLocation(550);

        // Bảng danh sách bài hát
        String[] columns = {"ID","Tên bài hát", "Ca sĩ", "Thể loại","Năm phát hành"};
        tableModel = new DefaultTableModel(columns, 0);
        
        tblSongs = new JTable(tableModel);
        tblSongs.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JTableHeader header = tblSongs.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 14)); 
        header.setForeground(Color.BLACK); 
        header.setBackground( Color.WHITE);
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        header.setPreferredSize(new Dimension(header.getWidth(), 35)); 
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        tblSongs.setRowHeight(28);
        tblSongs.setFont(new Font("SansSerif", Font.PLAIN, 13));
        tblSongs.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? new Color(154, 214, 240) : Color.WHITE);
                } else {
                    c.setBackground(new Color(184, 207, 229)); 
                }
                return c;
            }
        });
        tblSongs.setRowHeight(20);  
        tblSongs.setFont(new Font("SansSerif", Font.PLAIN, 12)); 
        loadTable(tableModel);
        // Khi chọn 1 bài hát trong bảng, hiển thị chi tiết
        tblSongs.getSelectionModel().addListSelectionListener(event -> {
            int selectedRow = tblSongs.getSelectedRow();
            if (selectedRow != -1 && event.getValueIsAdjusting() == false) {
                String id = tableModel.getValueAt(selectedRow, 0).toString();
                String ten = tableModel.getValueAt(selectedRow, 1).toString();
                String caSi = tableModel.getValueAt(selectedRow, 2).toString();
                String theLoai = tableModel.getValueAt(selectedRow, 3).toString();
                String nam = tableModel.getValueAt(selectedRow, 4).toString();

                // Hiển thị HTML format
                String html = "<html><body style='font-family:Arial; font-size:11px; color:#213555;'>"
                		 + "<div style='padding:5px; line-height:1.5;'>"
                        + "<b>Mã bài hát:</b> " + id + "<br>"
                        + "<b>Tên bài hát:</b> " + ten + "<br>"
                        + "<b>Ca sĩ:</b> " + caSi + "<br>"
                        + "<b>Thể loại:</b> " + theLoai + "<br>"
                        + "<b>Năm phát hành:</b> " + nam
                        + "</body></html>";

                txtSongDetailsPane.setText(html);
            }
        });

        JScrollPane scrollTable = new JScrollPane(tblSongs);
              // Panel chi tiết bài hát
        JPanel detailsPanel = new JPanel(new BorderLayout());
        detailsPanel.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0x708090),5), "Thông tin bài hát",
						TitledBorder.LEFT, TitledBorder.TOP, new Font("Segoe Script", Font.BOLD, 14), Color.DARK_GRAY));

         txtSongDetailsPane = new JEditorPane();
        txtSongDetailsPane.setContentType("text/html");
        txtSongDetailsPane.setEditable(false);
        txtSongDetailsPane.setBackground(Color.WHITE); 
        txtSongDetailsPane.setBorder(null);
        JScrollPane scrollDetails = new JScrollPane(txtSongDetailsPane);
        detailsPanel.add(scrollDetails, BorderLayout.CENTER);
        detailsPanel.setBackground(Color.WHITE); 

        centerSplit.setLeftComponent(scrollTable);
        centerSplit.setRightComponent(detailsPanel);
        detailsPanel.setPreferredSize(new Dimension(250, 0)); 
        centerSplit.setDividerLocation(730);

        add(centerSplit, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        // Danh sách yêu thích
     JPanel favoritePanel = new JPanel(new BorderLayout());
        TitledBorder customBorder = BorderFactory.createTitledBorder(
        	    BorderFactory.createLineBorder(new Color(0x708090),5), 
        	    "Danh sách yêu thích",                                       
        	    TitledBorder.LEFT,                                          
        	    TitledBorder.TOP,                                            
        	    new Font("Segoe Script", Font.BOLD, 14),                         
        	    new Color(25, 25, 112)                                       
        	);

        	favoritePanel.setBorder(customBorder);
        	favoritePanel.setBackground(Color.WHITE);

        favoriteListModel = new DefaultListModel<>();
        favoriteList = new JList<>(favoriteListModel);
        JScrollPane scrollFavorite = new JScrollPane(favoriteList);
        favoritePanel.add(scrollFavorite, BorderLayout.CENTER);

        // Nút thêm/xóa yêu thích
        JPanel favBtnPanel = new JPanel(new FlowLayout());
        favBtnPanel.setBackground(Color.WHITE);
        ImageIcon btnAddFavoritehIcon = new ImageIcon("src/Images/like.png");
		Image scaledImg2 = btnAddFavoritehIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon2 = new ImageIcon(scaledImg2);
		JButton btnAddFavorite = new JButton("Thêm vào yêu thích",resizedIcon2);
		searchbtn.setIconTextGap(3);
		btnAddFavorite.setForeground(textColor);
		Color bgColor = Color.WHITE;
		btnAddFavorite.setBackground(bgColor);
		btnAddFavorite.setBorder(border);
		Dimension btnSDimension2 = new Dimension(165, 30);
		btnAddFavorite.setPreferredSize(btnSDimension2);
		ImageIcon btnRemoveFavoriteIcon = new ImageIcon("src/Images/notlike.png");
		Image scaledImg3 = btnRemoveFavoriteIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon3 = new ImageIcon(scaledImg3);
        JButton btnRemoveFavorite = new JButton("Xóa khỏi yêu thích",resizedIcon3);
        btnRemoveFavorite.setBackground(bgColor);
        favBtnPanel.add(btnAddFavorite);
        favBtnPanel.add(btnRemoveFavorite);
        btnRemoveFavorite.setPreferredSize(btnSDimension2);
        favoritePanel.add(favBtnPanel, BorderLayout.SOUTH);
        favoritePanel.setPreferredSize(new Dimension(720, 200)); 

        bottomPanel.add(favoritePanel, BorderLayout.CENTER);

        ImageIcon btnExitAppchIcon = new ImageIcon("src/Images/log-out.png");
		Image scaledImg5 = btnExitAppchIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon5 = new ImageIcon(scaledImg5);
		JButton btnExitApp = new JButton("ĐÓNG ỨNG DỤNG", resizedIcon5);
		btnExitApp.setForeground(Color.RED);
		btnExitApp.setIconTextGap(5);
        btnExitApp.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnExitApp.setBackground(bgColor);
        btnExitApp.setBorder(new LineBorder(new Color(33, 150, 243), 2, true));
        btnRemoveFavorite.setBorder(border);
        bottomPanel.add(btnExitApp);
        btnExitApp.setPreferredSize(new Dimension(200, 40));  

        btnExitApp.setFont(new Font("SansSerif", Font.BOLD, 18));
        btnExitApp.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn quay lại trang chính?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                dispose(); // Đóng UserForm
                new RoleSelectionFrame().setVisible(true); // Mở lại giao diện chọn vai trò
            }
        });

        bottomPanel.setBackground((new Color(102, 153, 204)));
       
        add(bottomPanel, BorderLayout.SOUTH);

        //======== SỰ KIỆN ==========
             controller2 = new BaiHatController();
            controller2.loadBaiHat();
            btnAddFavorite.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedRow = tblSongs.getSelectedRow();
                    if (selectedRow >= 0) {
                        int id = (int) tblSongs.getValueAt(selectedRow, 0);
                        BaiHat bh = controller.timBaiHatTheoId(id);
                        if (bh != null && !favoriteSongs.contains(bh)) {
                            favoriteSongs.add(bh);
                            favoriteListModel.addElement(bh.getTenBaiHat() + " - " + bh.getCaSi()); // Hiển thị
                        } else {
                            JOptionPane.showMessageDialog(UserForm.this,
                                    "Bài hát đã có trong danh sách yêu thích!",
                                    "Thông báo",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(UserForm.this,
                                "Vui lòng chọn một bài hát trong bảng!",
                                "Cảnh báo",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
            });

            btnRemoveFavorite.addActionListener(e -> {
                int selectedIndex = favoriteList.getSelectedIndex();

                if (selectedIndex == -1) {
                    // Chưa chọn bài hát
                    JOptionPane.showMessageDialog(UserForm.this, 
                        "Bạn chưa chọn bài hát để xóa khỏi danh sách yêu thích!", 
                        "Thông báo", 
                        JOptionPane.WARNING_MESSAGE);
                    return;  
                }

                int confirm = JOptionPane.showConfirmDialog(UserForm.this, 
                    "Bạn có chắc chắn muốn xóa bài hát này khỏi danh sách yêu thích?", 
                    "Xác nhận xóa", 
                    JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    // Xóa bài hát
                    favoriteSongs.remove(selectedIndex);
                    favoriteListModel.remove(selectedIndex);
                    JOptionPane.showMessageDialog(UserForm.this, 
                        "Xóa bài hát khỏi danh sách yêu thích thành công!", 
                        "Thông báo", 
                        JOptionPane.INFORMATION_MESSAGE);
                }
            });

            btnRefresh.addActionListener(e -> {
                loadTable(tableModel);
                txtSearch.setText(""); 
            });


    }



}
