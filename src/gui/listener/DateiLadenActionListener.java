package gui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.jgoodies.forms.layout.CellConstraints;

import gui.FrameContent;

public class DateiLadenActionListener implements ActionListener {
	
	private File selectedFile;
	private FrameContent frameContent = null;
    
	public DateiLadenActionListener(FrameContent frameContent) {
		 this.frameContent = frameContent;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
			
		if (e.getSource() == frameContent.getButtDateiHoch()) {
			selectedFile = oeffnenDialog();
			if (selectedFile != null) { 
	        	frameContent.getTxtDateiHoch().setText(selectedFile.getPath());
	        }
	        frameContent.setSelectedFile(selectedFile);
		}
	}
				
	private File oeffnenDialog() {
		JFileChooser chooserLaden = new JFileChooser();
		chooserLaden.setDialogType(JFileChooser.OPEN_DIALOG);
		chooserLaden.setDialogTitle("Datei laden");

		// Dialog zum Oeffnen von Dateien anzeigen
        if(chooserLaden.showOpenDialog(frameContent) == JFileChooser.APPROVE_OPTION) {
        	selectedFile = chooserLaden.getSelectedFile();
        }
        return selectedFile;
	}

}
