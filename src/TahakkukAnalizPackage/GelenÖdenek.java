package TahakkukAnalizPackage;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.text.NumberFormat;
import java.util.Locale;

public class GelenÖdenek {
    JPanel jPanel_gelenödenek;
    JTable jTable;
    JTable jTable2;
    JTable jTable3;
    DbConnect dbConnect = new DbConnect();

    GelenÖdenek() {
        setjPanel_gelenödenek();
        setjTable();
        setjTable2();
        setjTable3();
    }

    public void setjPanel_gelenödenek() {
        jPanel_gelenödenek = new JPanel();
        jPanel_gelenödenek.setBounds(0, 40, 1700, 800);
        jPanel_gelenödenek.setLayout(null);
    }


    public void setjTable() {
        jTable = new JTable();
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        jTable.setModel(defaultTableModel);
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setBounds(0, 0, 800, 300);
        jScrollPane.setViewportView(jTable);

        jPanel_gelenödenek.add(jScrollPane);
        jScrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "ÖDENEK DAĞILIMI - GÜNLÜK", TitledBorder.CENTER, TitledBorder.TOP));
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.GERMANY);

        String satır[] = new String[5];
        String sütun[] = new String[5];

        try {
            String sorgu = "select `Fis Tarihi`,`Hesap Kodu`,`Hesap Adi`,`Alacak`,`Fis Açiklamasi` from hesap where `Hesap Kodu`='349.02' and `Alacak`>0";
            Statement statement = dbConnect.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sorgu);
            while (resultSet.next()) {
                sütun[0] = resultSet.getMetaData().getColumnName(1);
                sütun[1] = resultSet.getMetaData().getColumnName(2);
                sütun[2] = resultSet.getMetaData().getColumnName(3);
                sütun[3] = resultSet.getMetaData().getColumnName(4);
                sütun[4] = resultSet.getMetaData().getColumnName(5);
                defaultTableModel.setColumnIdentifiers(sütun);

                satır[0] = resultSet.getString(1);
                satır[1] = resultSet.getString(2);
                satır[2] = resultSet.getString(3);
                satır[3] = numberFormat.format(resultSet.getDouble(4));
                satır[4] = resultSet.getString(5);

                defaultTableModel.addRow(satır);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void setjTable2() {
        jTable2 = new JTable();
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        jTable2.setModel(defaultTableModel);

        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setBounds(850, 0, 800, 300);
        jScrollPane.setViewportView(jTable2);
        jPanel_gelenödenek.add(jScrollPane);
        jScrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "AYLIK GELİR - GİDER TOPLAMI", TitledBorder.CENTER, TitledBorder.TOP));
        String[] sütun = new String[4];
        Object[] satır = new Object[4];

        NumberFormat numberFormat = NumberFormat.getInstance(Locale.GERMANY);


        try {
            Statement statement = dbConnect.connection.createStatement();
            String sorgu = "SELECT " +
                    "SUM(CASE WHEN `Hesap Kodu`='349.02' AND `Fis Tarihi`LIKE '%.01.2020' THEN `Alacak` END)," + //1
                    "SUM(CASE WHEN `Hesap Kodu`='349.02' AND `Fis Tarihi`LIKE '%.02.2020' THEN `Alacak` END)," + //2
                    "SUM(CASE WHEN `Hesap Kodu`='349.02' AND `Fis Tarihi`LIKE '%.03.2020' THEN `Alacak` END)," + //3
                    "SUM(CASE WHEN `Hesap Kodu`='349.02' THEN `Alacak` END)," + //4

                    "SUM(CASE WHEN `Hesap Kodu`='102.01.02' AND `Fis Tarihi` LIKE '%.01.2020' THEN `Borç` END)-" +
                    "SUM(CASE WHEN `Hesap Kodu`='102.02.02' AND `Fis Tarihi` LIKE '%.01.2020' THEN `Borç` END)," + //5
                    "SUM(CASE WHEN `Hesap Kodu`='102.01.02' AND `Fis Tarihi` LIKE '%.02.2020' THEN `Borç` END)-" +
                    "SUM(CASE WHEN `Hesap Kodu`='102.02.02' AND `Fis Tarihi` LIKE '%.02.2020' THEN `Borç` END)," + //6
                    "SUM(CASE WHEN `Hesap Kodu`='102.01.02' AND `Fis Tarihi` LIKE '%.03.2020' THEN `Borç` END)-" +
                    "SUM(CASE WHEN `Hesap Kodu`='102.02.02' AND `Fis Tarihi` LIKE '%.03.2020' THEN `Borç` END)," + //7
                    "SUM(CASE WHEN `Hesap Kodu`='102.01.02' THEN `Borç` END) -" +
                    "SUM(CASE WHEN `Hesap Kodu`='102.02.02' THEN `Borç` END), " + //8

                    "SUM(CASE WHEN `Hesap Kodu`='103.02' AND `Fis Tarihi` LIKE '%.01.2020' THEN `Alacak` END)," + //9
                    "SUM(CASE WHEN `Hesap Kodu`='103.02' AND `Fis Tarihi` LIKE '%.02.2020' THEN `Alacak` END)," + //10
                    "SUM(CASE WHEN `Hesap Kodu`='103.02' AND `Fis Tarihi` LIKE '%.03.2020' THEN `Alacak` END)," + //11
                    "SUM(CASE WHEN `Hesap Kodu`='103.02' THEN `Alacak` END) " + //12

                    "FROM hesap";
            ResultSet resultSet = statement.executeQuery(sorgu);
            while (resultSet.next()) {
                sütun[0] = "Ay";
                sütun[1] = "GELEN ÖDENEK - 349.02A";
                sütun[2] = "Toplam Gelir (102.01.02-102.02.02)";
                sütun[3] = "Toplam Harcama (103.02A)";
                defaultTableModel.setColumnIdentifiers(sütun);

                satır[0] = "Ocak";
                satır[1] = numberFormat.format(resultSet.getDouble(1));
                satır[2] = numberFormat.format(resultSet.getDouble(5));
                satır[3] = numberFormat.format(resultSet.getDouble(9));
                defaultTableModel.addRow(satır);

                satır[0] = "Şubat";
                satır[1] = numberFormat.format(resultSet.getDouble(2));
                satır[2] = numberFormat.format(resultSet.getDouble(6));
                satır[3] = numberFormat.format(resultSet.getDouble(10));
                defaultTableModel.addRow(satır);

                satır[0] = "Mart";
                if (resultSet.getDouble(3) <= 0) {
                    satır[1] = null;
                } else {
                    satır[1] = resultSet.getDouble(3);
                }
                satır[2] = numberFormat.format(resultSet.getDouble(7));
                satır[3] = numberFormat.format(resultSet.getDouble(11));
                defaultTableModel.addRow(satır);


                satır[0] = "Toplam";
                satır[1] = numberFormat.format(resultSet.getDouble(4));
                satır[2] = numberFormat.format(resultSet.getDouble(8));
                satır[3] = numberFormat.format(resultSet.getDouble(12));
                defaultTableModel.addRow(satır);


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setjTable3() {
        jTable3 = new JTable();
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        jTable3.setModel(defaultTableModel);

        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setBounds(0, 310, 800, 300);
        jScrollPane.setViewportView(jTable3);
        jPanel_gelenödenek.add(jScrollPane);
        jScrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "AYLIK KESİLEN FATURA", TitledBorder.TOP, TitledBorder.CENTER));
        String[] sütun = new String[4];
        Object[] satır = new Object[4];

        NumberFormat numberFormat = NumberFormat.getInstance(Locale.GERMANY);

        try {
//            Connection connection = DriverManager.getConnection("jdbc:mariadb://10.10.10.28:3306/bash?user=root&password=1234");
            Statement statement = dbConnect.connection.createStatement();
            String sorgu = "SELECT SUM(case when `Fis Tarihi` LIKE '%.01.2020' AND `Hesap Kodu` LIKE '120.05%' then `Borç` END)," +
                    "SUM(case when `Fis Tarihi` LIKE '%.02.2020' AND `Hesap Kodu` LIKE '120.05%' then `Borç` END)" +
                    "FROM hesap";

            ResultSet resultSet = statement.executeQuery(sorgu);

            while (resultSet.next()) {

                sütun[0] = "Dönem";
                sütun[1] = "Tutar";
                defaultTableModel.setColumnIdentifiers(sütun);

                satır[0] = "Ocak Fatura";
                satır[1] = numberFormat.format(resultSet.getDouble(1));
                defaultTableModel.addRow(satır);

                satır[0] = "Şubat Fatura";
                satır[1] = numberFormat.format(resultSet.getDouble(2));
                defaultTableModel.addRow(satır);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
