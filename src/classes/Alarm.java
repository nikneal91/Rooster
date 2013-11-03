package classes;


import java.util.Timer;
import java.util.TimerTask;

public class Alarm {
	private String name;
	//Time in seconds.
	private long time;
	private SoundFile soundFile;
	private Timer timer;
	private static final int SECONDS = 1000;
	/* Name is the name of the alarm.
	 * Time is the amount of time in seconds until the alarm is suppose to go off.
	 * SoundFile is the sound that is suppose to play when the alarm goes off.
	 */
	public Alarm(String name, long time, SoundFile soundFile){
		this.name = name;
		this.time = time;
		this.soundFile = soundFile;
		timer = new Timer();
	}
	
	public void start(){
		timer.schedule(new AlarmTask(), this.time * SECONDS);
	}
	class AlarmTask extends TimerTask{
		public void run(){
			//When time is up, Plays the chosen sound file and cancels the timer.
			try {
				soundFile.play();
			} catch (Exception e) {
				//Sound file has had a error occur. 
				e.printStackTrace();
			}
			timer.cancel();
		}
	}
	
	public String getName(){
		return name;
	}
}
