/**
 * 
 */
package com.demopullet.main;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

/**
 * @author ngocha
 *
 */
public class PanelSkill {

	private Bitmap bmpSkill;

	private GamePanel gameView;

	public PanelSkill(GamePanel gameView) {

		this.gameView = gameView;

		bmpSkill = BitmapFactory.decodeResource(this.gameView.getResources(),
				R.drawable.rsz_image_skill_1_red_3);

	}

	public void draw(Canvas canvas) {

		canvas.drawBitmap(bmpSkill,
				this.gameView.getWidth() - bmpSkill.getWidth(),
				this.gameView.getHeight() - bmpSkill.getHeight(), null);
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return this.bmpSkill.getWidth();
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return this.bmpSkill.getHeight();
	}

}
