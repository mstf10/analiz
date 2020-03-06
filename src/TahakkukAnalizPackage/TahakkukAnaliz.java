package TahakkukAnalizPackage;

import javax.swing.*;
import java.awt.*;

public class TahakkukAnaliz {
    private JFrame jFrame_TahakkukAnaliz;
    private JTabbedPane jTabbedPane;
    private JPanel jPanel_anapanel;


    TahakkukAnaliz() {
        setjFrame_TahakkukAnaliz();
        setjPanel_anapanel();
        setjTabbedPane();

    }

    private void setjFrame_TahakkukAnaliz() {
        jFrame_TahakkukAnaliz = new JFrame("Tahakkuk Analiz");
        jFrame_TahakkukAnaliz.setVisible(true);
        jFrame_TahakkukAnaliz.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame_TahakkukAnaliz.setSize(1700, 800);
        jFrame_TahakkukAnaliz.setLayout(null);
    }

    private void setjTabbedPane() {
        jTabbedPane = new JTabbedPane();
        jTabbedPane.setBounds(0, 0, 1700, 800);
        jPanel_anapanel.add(jTabbedPane);
        jTabbedPane.addTab("ÖDENEK ve FATURA", new GelenÖdenek().jPanel_gelenödenek);
        jTabbedPane.addTab("TDMS - BL1 (2020)", new TdmsBL1_2020().jPanel_TdmsBL1_2020);
        jTabbedPane.addTab("TDMS - BL1 (2019)", new TdmsBL1_2019().jPanel_TdmsBL1_2019);
        jTabbedPane.addTab("BORÇ DAĞILIMI",new BorçDağılım().jPanel_borçdağılım);

    }

    private void setjPanel_anapanel() {
        jPanel_anapanel = new JPanel();
        jPanel_anapanel.setLayout(null);
        jPanel_anapanel.setBounds(0, 0, 1700, 800);
        jPanel_anapanel.setBackground(Color.YELLOW);
        jFrame_TahakkukAnaliz.getContentPane().add(jPanel_anapanel);
    }


    public static void main(String[] args) {
        TahakkukAnaliz tahakkukAnaliz = new TahakkukAnaliz();

    }
}
