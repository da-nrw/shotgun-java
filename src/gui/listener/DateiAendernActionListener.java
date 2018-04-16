/**
 * 
 */
package gui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JSlider;

import gui.FrameContent;

/**
 * @author z1334006
 *
 */
public class DateiAendernActionListener implements ActionListener {

	FrameContent frameContent;

	public DateiAendernActionListener(FrameContent frameContent) {
		this.frameContent = frameContent;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		if (frameContent.getTxtDateiHoch().getText().isEmpty()) {
			JOptionPane.showMessageDialog(frameContent, "Bitte die zu ändernde Datei auswählen!");
			return;
		}
		RandomAccessFile inFile;
		RandomAccessFile outFile;

		String inName = frameContent.getTxtDateiHoch().getText();
		// Dateinamen der ausgehenden Datei manipulieren
		int index = inName.lastIndexOf(".");
		String outName =  inName.substring(0, index) + "-broken" + inName.substring(index); 
		try {
			inFile = new RandomAccessFile(inName, "r");
			inFile.read();
			if (inFile.length() == 0) {
				JOptionPane.showMessageDialog(frameContent,
						"Die gewählte Datei ist 0 Byte groß! Bitte eine andere Datei auswählen!");
			} else {
				new File(outName).delete();
				Files.copy(Paths.get(inName), Paths.get(outName));
				outFile = new RandomAccessFile(outName, "rw");

				corruptFile(inFile, outFile);

				outFile.close();
				inFile.close();
				
				JOptionPane.showMessageDialog(frameContent, "Ergebnis: " + frameContent.getCorruptSize() +
                        " bytes wurden verändert.");
			}
		} catch (FileNotFoundException e1) {
			JOptionPane.showMessageDialog(frameContent,
					"Die gewählte Datei konnte nicht gefunden werden. Bitte Pfad und Dateinamen prüfen!");
			//e1.printStackTrace();
		}

		catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	/**
	 * und nun wird das File zerstört
	 * @param inFile
	 * @param outFile
	 * @throws IOException
	 */
	private void corruptFile(RandomAccessFile inFile, RandomAccessFile outFile) throws IOException {
		byte[] bites = new byte[] { (byte) 0200, 0100, 040, 020, 010, 04, 02, 01 };
		long filesize = inFile.length();
		int corruptSize = frameContent.getCorruptSize();
		int corruptCount = (int) (corruptSize*0.3); //30
		long step = 0L;
		int shot = 0;
		int errorCount = 30;
		for (int i = 0; i < corruptCount; i++) {
			Random rn = new Random(System.currentTimeMillis());
			step = Math.abs(rn.nextLong()) % (filesize - corruptSize);
			int errorCountCounter = errorCount;
			long bytei = (step / 8);
			int bit = (int) (step % 8);
			byte[] bytes = new byte[1];
			for (shot = corruptSize; 0 < shot; shot--) {
				inFile.seek(bytei);
				bytes[0] = inFile.readByte();
				if ((bytes[0] & bites[bit]) > 0) {
					errorCountCounter--;
					if(errorCountCounter==0)
						break;
				}
				if (++bit == 8) {
					bit = 0;
					bytei++;
				}
			}

			if (shot > 0) {
				i--;
				continue;
			}
			
			bytei = (int) step / 8;
			bit = (int) step % 8;
			for (shot = corruptSize; 0 < shot; shot--) {
				inFile.seek(bytei);
				bytes[0] = inFile.readByte();
				bytes[0] |= bites[bit];
				if (++bit == 8) {
					bit = 0;
					bytei++;
				}

				outFile.seek(bytei);
				outFile.write(bytes[0]);
			}

		}

	}
}
