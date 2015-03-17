/**
 * 
 */
package com.demopullet.main;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * @author ngocha
 *
 */
public class MainThread extends Thread {

	private SurfaceHolder surfaceholder;
	private GamePanel gamepanel;
	private boolean running;

	public MainThread(SurfaceHolder surfaceholder, GamePanel gamepanel) {
		this.surfaceholder = surfaceholder;
		this.gamepanel = gamepanel;
	}

	public void setRunning(boolean run) {
		running = run;
	}

	@SuppressLint("WrongCall")
	@Override
	public void run() {

		super.run();
		Canvas canvas = null;
		while (running) {
			canvas = surfaceholder.lockCanvas();
			if (canvas != null) {
				gamepanel.onDraw(canvas);
				surfaceholder.unlockCanvasAndPost(canvas);
			}
		}
	}
}
