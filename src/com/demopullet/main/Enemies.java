/**
 * 
 */
package com.demopullet.main;

import java.util.Random;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

/**
 * @author ngocha
 *
 */
public class Enemies {

	int x;
	int y;
	Bitmap bitmap;
	int[] tocdo = { 2, 3, 4 };
	int[] manghinh = { R.drawable.monster1, R.drawable.monster2,
			R.drawable.monster3 };
	int e_ngnhien;

	public Enemies(Resources res, int rong_cv, int cao_cv) {
		Random rand = new Random();
		e_ngnhien = rand.nextInt(3);
		this.x = rong_cv;// x tuphai
		int a = 0 + (int) (Math.random() * ((cao_cv - 0) + 1));
		this.y = a;
		bitmap = BitmapFactory.decodeResource(res, manghinh[e_ngnhien]);
	}

	public Enemies(Resources res, int x, int y, int hinh) {
		this.x = x;
		this.y = y;
		bitmap = BitmapFactory.decodeResource(res, hinh);
	}

	public void doDraw(Canvas canvas) {
		synchronized (this) {

			canvas.drawBitmap(bitmap, x, y, null);
			x -= tocdo[e_ngnhien]; // tru vi chay phai sang trai

		}
	}

	public void setXY(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getWidth() {
		return bitmap.getWidth();
	}

	public int getHeight() {
		return bitmap.getHeight();
	}

	public int gettamX() {
		// tam x=toa do x congvoinuarong
		return x + (bitmap.getWidth() / 2);
	}

	public int gettamY() {
		// tam y=toa do y congnuacao
		return y + (bitmap.getHeight() / 2);
	}
}
