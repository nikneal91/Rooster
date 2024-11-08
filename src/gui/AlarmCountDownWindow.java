package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.Instant;
import java.time.LocalDate;

public class AlarmCountDownWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public AlarmCountDownWindow(){
		initUI();
	}
	public final void initUI() {

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JLabel alarmNameLabel = new JLabel("Alarm name:");
		final JTextField alarmNameTextField = new JTextField();
		panel.add(alarmNameLabel);
		panel.add(alarmNameTextField);
		
		
		JLabel alarmTimeLabel = new JLabel("When should the alarm go off from now:");
		final String[] hours = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"};
		final String[] minutes = new String[61];
		final String[] seconds = new String[61];
		for(int i = 0; i < minutes.length; i++){
			minutes[i] = i+"";
			seconds[i] = i+"";
		}
		
		/*
		 * This handles setting up the alarm portion.
		 */
		JPanel alarmTimePanel = new JPanel();
		alarmTimePanel.setLayout(new BoxLayout(alarmTimePanel, BoxLayout.X_AXIS));
		JLabel hourLabel = new JLabel("Hour(s):");
		final JComboBox<String> hourList = new JComboBox<String>(hours);
		JLabel minLabel = new JLabel("Minute(s):");
		final JComboBox<String> minList = new JComboBox<String>(minutes);
		JLabel secLabel = new JLabel("Second(s):");
		final JComboBox<String> secList = new JComboBox<String>(seconds);
		
		alarmTimePanel.add(hourLabel);
		alarmTimePanel.add(hourList);
		alarmTimePanel.add(minLabel);
		alarmTimePanel.add(minList);
		alarmTimePanel.add(secLabel);
		alarmTimePanel.add(secList);
		panel.add(alarmTimeLabel);
		panel.add(alarmTimePanel);
		
		//The okay Button.
		JButton okayButton = new JButton("Ok");
		okayButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				String alarmName = alarmNameTextField.getText();
				//Set the name to a default if the text box was left empty.
				if(alarmName.length() == 0){
					alarmName = "Alarm";
				}
				//Get the user settings for the hour, min, and sec.
				int hour = Integer.parseInt(hourList.getSelectedItem()+"");
				int min = Integer.parseInt(minList.getSelectedItem()+"");
				int sec = Integer.parseInt(secList.getSelectedItem()+"");
				
				//Calculate the seconds for the alarm.
				LocalTime now = LocalTime.now();
				LocalTime dateTime = now.plusHours(hour);
				dateTime = dateTime.plusMinutes(min);
				dateTime = dateTime.plusSeconds(sec);
				long seconds= now.until(dateTime,
                       ChronoUnit.SECONDS);
				AlarmWindow window = new AlarmWindow(alarmName, seconds);
				window.setVisible(true);
			}
		});
		panel.add(okayButton);
		//Adds the main panel.
		add(panel);

		pack();
		setTitle("Alarm Settings");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
}
