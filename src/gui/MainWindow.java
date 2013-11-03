package gui;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainWindow extends JFrame{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6493517684652815624L;

	public MainWindow(){
		initUI();
	}
	
	public final void initUI() {

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		panel.setBorder(new EmptyBorder(new Insets(40, 60, 40, 60)));

		JButton createCountdownAlarm = new JButton("Create new countdown alarm"), createTimeAlarm = new JButton("Create new time based alarm");
		
		/*
		 * Sets the action listeners for all of the buttons.
		 */
		createCountdownAlarm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				AlarmCountDownWindow window = new AlarmCountDownWindow();
				window.setVisible(true);
			}
		});
		
		createTimeAlarm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				AlarmTimeWindow window = new AlarmTimeWindow();
				window.setVisible(true);
			}
		});
		
		
		panel.add(createCountdownAlarm);
		panel.add(Box.createRigidArea(new Dimension(0, 5)));
		panel.add(createTimeAlarm);

		add(panel);

		pack();
		
		setTitle("Rooster Main Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

}
