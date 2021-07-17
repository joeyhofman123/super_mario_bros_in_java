package Game;

import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;

import org.newdawn.slick.Music;
import org.newdawn.slick.Sound;

public class AudioPlayer {
	
	public static AudioInputStream audioinputstream;

	public static Map<String, Sound> soundMap = new HashMap<String, Sound>();
	public static Map<String, Music> musicMap = new HashMap<String, Music>();

	
	public static void load() {
		System.out.println("loading audio...");
		try {
			musicMap.put("background_overworld", new Music("sound/background_overworld.ogg"));
			
			soundMap.put("jump_small", new Sound("sound/jump_small.ogg"));
			soundMap.put("coin", new Sound("sound/coin.ogg"));
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public static Music getMusic(String MusicKey) {
		return musicMap.get(MusicKey);
	}
	
	public static Sound getSound(String soundKey) {
		return soundMap.get(soundKey);
	}
}
