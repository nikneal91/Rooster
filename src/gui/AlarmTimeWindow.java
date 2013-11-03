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

import org.joda.time.DateTime;
import org.joda.time.Seconds;

public class AlarmTimeWindow extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3535651838362608675L;
	public AlarmTimeWindow(){
		initUI();
	}
	public final void initUI() {

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JLabel alarmNameLabel = new JLabel("Alarm name:");
		final JTextField alarmNameTextField = new JTextField();
		panel.add(alarmNameLabel);
		panel.add(alarmNameTextField);
		
		
		JLabel alarmTimeLabel = new JLabel("When time should the alarm go off:");
		final String[] hours = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
		final String[] minutes = new String[60];
		for(int i = 0; i < minutes.length; i++){
			if(i < 10){
				minutes[i] = "0"+i;
			}else{
				minutes[i] = i+"";
			}
		}
		final String[] m = {"am", "pm"};
		/*
		 * This handles setting up the alarm portion.
		 */
		JPanel alarmTimePanel = new JPanel();
		alarmTimePanel.setLayout(new BoxLayout(alarmTimePanel, BoxLayout.X_AXIS));
		JLabel hourLabel = new JLabel("Hour:");
		final JComboBox<String> hourList = new JComboBox<String>(hours);
		JLabel minLabel = new JLabel("Minute(s):");
		final JComboBox<String> minList = new JComboBox<String>(minutes);
		JLabel mLabel = new JLabel("Day/Night:");
		final JComboBox<String> mList = new JComboBox<String>(m);
		
		alarmTimePanel.add(hourLabel);
		alarmTimePanel.add(hourList);
		alarmTimePanel.add(minLabel);
		alarmTimePanel.add(minList);
		alarmTimePanel.add(mLabel);
		alarmTimePanel.add(mList);
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
				String m = mList.getSelectedItem()+"";
				//Calculate the seconds for the alarm.
				DateTime now = DateTime.now();
				int targetHourOfDay = hour;
				//If pass noon add 12 to convert to military time. 
				if(m.equals("pm")){
					targetHourOfDay += 12;
				}
				//The target deadline for the alarm to go off.
				DateTime dateTime = new DateTime(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), targetHourOfDay, min);

				
				Seconds seconds = Seconds.secondsBetween(now, dateTime);
			
				AlarmWindow window = new AlarmWindow(alarmName, seconds.getSeconds());
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
