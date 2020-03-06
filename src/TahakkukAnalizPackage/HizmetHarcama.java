package TahakkukAnalizPackage;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;


public class HizmetHarcama {
    JPanel jPanel_hizmetharcama;
    JTable jTable;


    HizmetHarcama() {

        setjPanel_hizmetharcama();
        setjTable();

    }

    public void setjPanel_hizmetharcama() {
        jPanel_hizmetharcama = new JPanel();
        jPanel_hizmetharcama.setBounds(0, 40, 1700, 500);
        jPanel_hizmetharcama.setLayout(null);
    }

    public void setjTable() {
        jTable = new JTable();
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        jTable.setModel(defaultTableModel);
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setBounds(0, 0, 1650, 300);
        jScrollPane.setViewportView(jTable);
        jPanel_hizmetharcama.add(jScrollPane);

        Object[] satır = new Object[18];
        String[] sütun = new String[18];

        JTextField jTextField_filtre = new JTextField();
        JTextField jTextField_toplam = new JTextField();
        jPanel_hizmetharcama.add(jTextField_filtre);
        jPanel_hizmetharcama.add(jTextField_toplam);
        jTextField_filtre.setBounds(50, 320, 120, 30);
        jTextField_toplam.setBounds(180, 320, 120, 30);
        TableRowSorter<DefaultTableModel> tableRowSorter = new TableRowSorter<>(defaultTableModel);
        jTable.setRowSorter(tableRowSorter);

        NumberFormat numberFormat = NumberFormat.getInstance(Locale.GERMANY);


        try {
            Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/bash?user=root&password=1234");
            Statement statement = connection.createStatement();
            String sorgu = "select * from hizmetler";
            ResultSet resultSet = statement.executeQuery(sorgu);
            for (int ix = 0; ix < resultSet.getMetaData().getColumnCount(); ix++) {
                sütun[ix] = resultSet.getMetaData().getColumnName(ix + 1);
                defaultTableModel.setColumnIdentifiers(sütun);
            }
            while (resultSet.next()) {
                satır[0] = resultSet.getString(1);
                satır[1] = resultSet.getString(2);
                satır[2] = resultSet.getString(3);
                satır[3] = resultSet.getString(4);
                satır[4] = resultSet.getString(5);
                satır[5] = resultSet.getString(6);
                satır[6] = resultSet.getString(7);
                satır[7] = resultSet.getString(8);
                satır[8] = resultSet.getString(9);
                satır[9] = resultSet.getString(10);
                satır[10] = resultSet.getString(11);
                satır[11] = resultSet.getString(12);
                satır[12] = resultSet.getDouble(13);
                satır[13] = resultSet.getString(14);
                satır[14] = resultSet.getString(15);
                satır[15] = resultSet.getString(16);
                satır[16] = resultSet.getString(17);
                satır[17] = resultSet.getString(18);
                defaultTableModel.addRow(satır);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        jTextField_filtre.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                RowFilter<DefaultTableModel, Object> rowFilter = RowFilter.regexFilter(jTextField_filtre.getText());
                tableRowSorter.setRowFilter(rowFilter);
                if (jTable.getRowCount() > -1) {
                    double ix = 0;
                    for (int i = 0; i < jTable.getRowCount(); i++) {
                        ix += Double.valueOf(jTable.getValueAt(i, 12).toString());
                        jTextField_toplam.setText(numberFormat.format(ix));

                    }

                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                RowFilter<DefaultTableModel, Object> rowFilter = RowFilter.regexFilter(jTextField_filtre.getText());
                tableRowSorter.setRowFilter(rowFilter);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                RowFilter<DefaultTableModel, Object> rowFilter = RowFilter.regexFilter(jTextField_filtre.getText());
                tableRowSorter.setRowFilter(rowFilter);
            }
        });


    }


}
