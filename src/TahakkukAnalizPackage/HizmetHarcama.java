package TahakkukAnalizPackage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class HizmetHarcama {
    JPanel jPanel_hizmetharcama;
    JTable jTable;

    HizmetHarcama() {

        setjPanel_hizmetharcama();
        setjTable();
    }

    public void setjPanel_hizmetharcama() {
        jPanel_hizmetharcama = new JPanel();
        jPanel_hizmetharcama.setBackground(Color.YELLOW);
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
                for (int i = 0; i < resultSet.getMetaData().getColumnCount(); i++) {
                    satır[i] = resultSet.getString(i + 1);
                }
                defaultTableModel.addRow(satır);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
