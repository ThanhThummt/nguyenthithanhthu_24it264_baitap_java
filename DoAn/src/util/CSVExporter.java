package util;

import javax.swing.*;
import javax.swing.table.TableModel;

import java.awt.Component;
import java.io.*;

public class CSVExporter {

    public static boolean exportTableToCSV(TableModel tableModel, File file, Component parentComponent) {
        try {
            // Đảm bảo phần mở rộng là .csv
            if (!file.getAbsolutePath().endsWith(".csv")) {
                file = new File(file.getAbsolutePath() + ".csv");
            }

            // Ghi file với encoding UTF-8
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");

            // Thêm BOM để Excel hiểu UTF-8
            writer.write('\ufeff');

            // Tiêu đề cột
            for (int i = 0; i < tableModel.getColumnCount(); i++) {
                writer.append(tableModel.getColumnName(i));
                if (i < tableModel.getColumnCount() - 1) writer.append(",");
            }
            writer.append("\n");

            // Dữ liệu hàng
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                for (int j = 0; j < tableModel.getColumnCount(); j++) {
                    writer.append(tableModel.getValueAt(i, j).toString());
                    if (j < tableModel.getColumnCount() - 1) writer.append(",");
                }
                writer.append("\n");
            }

            writer.flush();
            writer.close();

            JOptionPane.showMessageDialog(parentComponent, "Xuất file thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(parentComponent, "Lỗi khi xuất file!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
