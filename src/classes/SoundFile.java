package classes;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class SoundFile {
	private String name;
	private File file;
	
	/* The name is simply the name of the sound file.
	 * The pathname is simply the path where the sound file is located.
	 */
	public SoundFile(String name, String pathname){
		this.name = name;
		this.file = new File(pathname);
	}
	
	public void play() throws Exception{
		//Loads the audio files and plays it continuously the user clicks the close button.
		Clip clip = AudioSystem.getClip();
		AudioInputStream ais = AudioSystem.getAudioInputStream(file);
		clip.open(ais);
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				//Shows a message dialog box and then closes everything when the user clicks the close button.
				JOptionPane.showMessageDialog(null, "Close to stop alarm.");
				System.exit(0);
			}
		});
	}
	
	public String getName(){
		return name;
	}
}
