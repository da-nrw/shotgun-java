/**
 * 
 */
package gui;

import java.io.File;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import gui.listener.DateiAendernActionListener;
import gui.listener.DateiLadenActionListener;
import gui.listener.SlideChangeListener;

/**
 * @author z1334006
 *
 */
public class FrameContent extends JPanel  {
	
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextField txtDateiHoch = new JTextField();
	private JButton buttDateiHoch = new JButton("Datei suchen");
	private JSlider jSliderSize = new JSlider(JSlider.HORIZONTAL, 1, 100, 1);
	private JLabel labelSlider = new JLabel("zu manipulierende Größe (1-100)");
	private JButton buttDateiManipulieren = new JButton("Datei manipulieren");
	
	private CellConstraints cc = new CellConstraints();
    
    private JPanel panel = new JPanel();
    private File selectedFile = null;
    private int corruptSize = 0;

    public FrameContent() {
    	 
        panel.setLayout(new FormLayout("200dlu, 15dlu, 105dlu", "20dlu, 10dlu, 20dlu, 10dlu, 20dlu"));
        
        panel.add(txtDateiHoch, cc.xy(1, 1));
        panel.add(buttDateiHoch, cc.xy(3, 1));
        buttDateiHoch.addActionListener(new DateiLadenActionListener(this));
        
        panel.add(jSliderSize, cc.xy(1, 3));
        jSliderSize.setPaintTicks(true);
        jSliderSize.setMajorTickSpacing(20);
        jSliderSize.setMinorTickSpacing(2);
        jSliderSize.addChangeListener(new SlideChangeListener(this));
        panel.add(labelSlider, cc.xy(3, 3));
        
        panel.add(buttDateiManipulieren, cc.xy(1, 5));
        buttDateiManipulieren.addActionListener(new DateiAendernActionListener(this));
        
        add(panel);       
	}

	public File getSelectedFile() {
		return selectedFile;
	}

	public void setSelectedFile(File selectedFile) {
		this.selectedFile = selectedFile;
	}

	public JTextField getTxtDateiHoch() {
		return txtDateiHoch;
	}


	public void setTxtDateiHoch(JTextField txtDateiHoch) {
		this.txtDateiHoch = txtDateiHoch;
	}


	public JButton getButtDateiHoch() {
		return buttDateiHoch;
	}


	public void setButtDateiHoch(JButton buttDateiHoch) {
		this.buttDateiHoch = buttDateiHoch;
	}
	public int getCorruptSize() {
		return corruptSize;
	}


	public void setCorruptSize(int corruptSize) {
		this.corruptSize = corruptSize;
	}
}
