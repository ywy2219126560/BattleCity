package com.project.util;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.concurrent.ExecutorService;


import static java.util.concurrent.Executors.*;

public class AudioUtils {

    private static ExecutorService executorService = newFixedThreadPool(3);


  

    public static void startAudio() {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                	FileInputStream startInput = new FileInputStream(new File("src/audio/start.mp3"));
                    Player player = new Player(startInput);
                    player.play();   
                    startInput.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public static void fireAudio() {
    	executorService.execute(new Runnable() {		
			@Override
			public void run() {
				 try {
					 FileInputStream fireInput = new FileInputStream(new File("src/audio/fire.mp3"));
					 Player player = new Player(fireInput);
					 player.play(3);	
					 fireInput.close();
				} catch (Exception e) {
					e.printStackTrace();
				}              
			}
		});
    }

    public static void failAudio() {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    FileInputStream failInput = new FileInputStream(new File("src/audio/fail.mp3"));
                    Player player = new Player(failInput);
                    player.play();
                    failInput.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public static void bangAudio(){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    FileInputStream bangInput = new FileInputStream(new File("src/audio/bang.mp3"));
                    Player player = new Player(bangInput);
                    player.play();
                    bangInput.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


}
