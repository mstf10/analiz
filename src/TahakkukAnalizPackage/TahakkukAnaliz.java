package TahakkukAnalizPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
        jFrame_TahakkukAnaliz.setSize(1650, 800);
        jFrame_TahakkukAnaliz.setLayout(null);
    }

    private void setjTabbedPane(){
        jTabbedPane = new JTabbedPane();
        jTabbedPane.setBounds(0,0,1650,600);
        jPanel_anapanel.add(jTabbedPane);
       jTabbedPane.addTab("Gelen Ödenek", new GelenÖdenek().jPanel_gelenödenek);
       jTabbedPane.addTab("Hizmet Harcama", new HizmetHarcama().jPanel_hizmetharcama);

    }

    private void setjPanel_anapanel(){
        jPanel_anapanel = new JPanel();
        jPanel_anapanel.setLayout(null);
        jPanel_anapanel.setBounds(0,0,1650,800);
        jPanel_anapanel.setBackground(Color.yellow);
        jFrame_TahakkukAnaliz.getContentPane().add(jPanel_anapanel);


    }


    public static void main(String[] args) {
        TahakkukAnaliz tahakkukAnaliz = new TahakkukAnaliz();
    }
}
