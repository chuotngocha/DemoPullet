/**
 * 
 */
package com.demopullet.main;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * @author ngocha
 *
 */
public class ParallaxBackground {

	private int toadonen1_X = 0;
	private Bitmap hinhnen1;

	private int toadonen2_X = 0;
	private Bitmap hinhnen2;

	Paint paint = new Paint();

	public ParallaxBackground(Resources c) {
		hinhnen1 = BitmapFactory.decodeResource(c, R.drawable.background);

		hinhnen2 = BitmapFactory.decodeResource(c, R.drawable.tilegrasstop);

		paint.setAntiAlias(false);
		paint.setFilterBitmap(false);
		paint.setDither(true);

	}

	public synchronized void doDrawRunning(Canvas canvas) {

		// giam toa do de dich chuyen cho nen1
		toadonen1_X = toadonen1_X - 1;

		toadonen2_X = toadonen2_X - 5; // width cua tile phai them 5 px vi toc
										// do =5

		// tinh do lech cho hinh 2 (xem hinh minh hoa)
		int toadonen1_phu_X = canvas.getWidth() - (-toadonen1_X);

		// da di chuyen het thi quay lai tu dau
		if (toadonen1_phu_X <= 0) {
			toadonen1_X = 0;
			// chi can ve 1 tam
			canvas.drawBitmap(hinhnen1, 0, 0, paint);

		} else {
			// ve 1 tam lech va tam 2 noi duoi theo
			canvas.drawBitmap(hinhnen1, toadonen1_X, 0, paint);
			canvas.drawBitmap(hinhnen1, toadonen1_phu_X, 0, paint);
		}

		int toadonen2_phu_X = canvas.getWidth() - (-toadonen2_X);

		if (toadonen2_phu_X <= 0) {
			toadonen2_X = 0;
			canvas.drawBitmap(hinhnen2, hinhnen2.getWidth() + 5, 0, paint);

		} else {
			canvas.drawBitmap(hinhnen2, toadonen2_X, canvas.getHeight()
					- hinhnen2.getHeight(), paint);
			canvas.drawBitmap(hinhnen2, toadonen2_phu_X, canvas.getHeight()
					- hinhnen2.getHeight(), paint);
		}

		System.out.println("Canvas Height: " + canvas.getHeight()
				+ " Canvas Width: " + canvas.getWidth());

		System.out.println("hinhnen1 Height: " + hinhnen1.getHeight()
				+ " hinhnen1 Width: " + hinhnen1.getWidth());

		System.out.println("hinhnen2 Height: " + hinhnen2.getHeight()
				+ " hinhnen2 Width: " + hinhnen2.getWidth());

	}
}
