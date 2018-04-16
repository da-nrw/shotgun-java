/**
 * 
 */
package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * @author z1334006
 *
 */
public class StartManipulate extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Create and set up the window.
        JFrame frame = new JFrame("Datei manipulieren");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel label = new JLabel("Datei manipulieren");
        frame.getContentPane().add(label);

        frame.add(new FrameContent());
        
        //Display the window.
        frame.pack();
        frame.setVisible(true);
	}

}
