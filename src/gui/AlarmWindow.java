package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import classes.Alarm;
import classes.SoundFile;

public class AlarmWindow extends JFrame {
	private String alarmName;
	/**
	 * 
	 */
	private static final long serialVersionUID = 7317979646518789994L;
	public AlarmWindow(String alarmName, int seconds){
		this.alarmName = alarmName;
		SoundFile soundFile = new SoundFile("Beep", "src/SoundFiles/Beep.wav");
		Alarm alarm = new Alarm(alarmName, seconds, soundFile);
		alarm.start();
		initUI();
	}
	public final void initUI() {
	
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JLabel alarmNameLabel = new JLabel("Alarm name: "+alarmName);
		panel.add(alarmNameLabel);
		
		JLabel notificationLabel = new JLabel("Alarm is set and active");
		panel.add(notificationLabel);
		//The cancel button. 
		JButton cancelButton = new JButton("Cancel/Turn Off Alarm");

		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});

		panel.add(cancelButton);
		//Adds the main panel.
		add(panel);

		pack();
		setTitle("Alarm");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

}
