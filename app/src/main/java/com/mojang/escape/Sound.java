package com.mojang.escape;

import javax.sound.sampled.*;

public class Sound {
	public static Sound altar = loadSound("/snd/altar.wav");
	public static Sound bosskill = loadSound("/snd/bosskill.wav");
	public static Sound click1 = loadSound("/snd/click.wav");
	public static Sound click2 = loadSound("/snd/click2.wav");
	public static Sound hit = loadSound("/snd/hit.wav");
	public static Sound hurt = loadSound("/snd/hurt.wav");
	public static Sound hurt2 = loadSound("/snd/hurt2.wav");
	public static Sound kill = loadSound("/snd/kill.wav");
	public static Sound death = loadSound("/snd/death.wav");
	public static Sound splash = loadSound("/snd/splash.wav");
	public static Sound key = loadSound("/snd/key.wav");
	public static Sound pickup = loadSound("/snd/pickup.wav");
	public static Sound roll = loadSound("/snd/roll.wav");
	public static Sound shoot = loadSound("/snd/shoot.wav");
	public static Sound treasure = loadSound("/snd/treasure.wav");
	public static Sound crumble = loadSound("/snd/crumble.wav");
	public static Sound slide = loadSound("/snd/slide.wav");
	public static Sound cut = loadSound("/snd/cut.wav");
	public static Sound thud = loadSound("/snd/thud.wav");
	public static Sound ladder = loadSound("/snd/ladder.wav");
	public static Sound potion = loadSound("/snd/potion.wav");

	public static Sound loadSound(String fileName) {
		Sound sound = new Sound();
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(Sound.class.getResource(fileName));
			Clip clip = AudioSystem.getClip();
			clip.open(ais);

			float volume = .2f;
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(20f * (float) Math.log10(volume));

			sound.clip = clip;
		} catch (Exception e) {
			System.out.println(e);
		}
		return sound;
	}

	private Clip clip;

	public void play() {
		try {
			if (clip != null) {
				if (EscapeComponent.getVolume() > 1.0f && EscapeComponent.getVolume() < 0.0f) {
					System.out.println("ERROR: invalid volume: " + EscapeComponent.getVolume());
				} else {
					FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
					gainControl.setValue(20f * (float) Math.log10(EscapeComponent.getVolume()));

					System.out.println("volume level = " + clip.getLevel());
					System.out.println("volume = " + (float) Math.pow(10f, gainControl.getValue() / 20f));

				}
				new Thread() {
					public void run() {
						synchronized (clip) {
							clip.stop();
							clip.setFramePosition(0);
							clip.start();
						}
					}
				}.start();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}