/**
 * 
 */
package gui.listener;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import gui.FrameContent;

/**
 * @author z1334006
 *
 */
public class SlideChangeListener implements ChangeListener {

	private FrameContent frameContent;
	public SlideChangeListener(FrameContent frameContent) {
		this.frameContent = frameContent;
	}

	/* (non-Javadoc)
	 * @see javax.swing.event.ChangeListener#stateChanged(javax.swing.event.ChangeEvent)
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		//System.out.println( ((JSlider) e.getSource()).getValue() );
		frameContent.setCorruptSize(((JSlider) e.getSource()).getValue());
	}

}
