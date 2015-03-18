/**
 * 
 */
package com.demopullet.main;

import java.util.ArrayList;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

/**
 * @author ngocha
 *
 */
public class ParallaxBackground {

	private int toadonen1_X = 0;
	private Bitmap hinhnen1;

	private int toadonen2_X = 0;
	private Bitmap hinhnen2;

	private ArrayList<Bitmap> listTileGrassTop = new ArrayList<Bitmap>();

	public ParallaxBackground(Resources c) {
		hinhnen1 = BitmapFactory.decodeResource(c, R.drawable.background);

		hinhnen2 = BitmapFactory.decodeResource(c, R.drawable.tilegrasstop);
		int numberOfTile = hinhnen1.getWidth() / hinhnen2.getWidth();
		for (int i = 0; i < numberOfTile + 1; i++) {
			hinhnen2 = BitmapFactory.decodeResource(c, R.drawable.tilegrasstop);
			listTileGrassTop.add(hinhnen2);
		}

	}

	public synchronized void doDrawRunning(Canvas canvas) {

		// giam toa do de dich chuyen cho nen1
		toadonen1_X = toadonen1_X - 1;

		toadonen2_X = toadonen2_X - 1;

		// tinh do lech cho hinh 2 (xem hinh minh hoa)
		int toadonen1_phu_X = hinhnen1.getWidth() - (-toadonen1_X);

		// da di chuyen het thi quay lai tu dau
		if (toadonen1_phu_X <= 0) {
			toadonen1_X = 0;
			// chi can ve 1 tam
			canvas.drawBitmap(hinhnen1, 0, 0, null);

		} else {
			// ve 1 tam lech va tam 2 noi duoi theo
			canvas.drawBitmap(hinhnen1, toadonen1_X, 0, null);
			canvas.drawBitmap(hinhnen1, toadonen1_phu_X, 0, null);
		}

		if (toadonen1_phu_X <= 0) {
			toadonen1_X = 0;
			canvas.drawBitmap(hinhnen1,
					canvas.getWidth() - hinhnen1.getWidth(), canvas.getHeight()
							- hinhnen1.getHeight(), null);

		} else {
			canvas.drawBitmap(hinhnen1, toadonen1_X, canvas.getHeight()
					- hinhnen1.getHeight(), null);
			canvas.drawBitmap(hinhnen1, toadonen1_phu_X, canvas.getHeight()
					- hinhnen1.getHeight(), null);
		}

		for (int i = 0; i < listTileGrassTop.size(); i++) {

			Bitmap bitmap = listTileGrassTop.get(i);
			int toadonen2_phu_X = canvas.getWidth() - (-toadonen2_X);

			if (toadonen2_phu_X <= 0) {
				toadonen2_X = 0;
				canvas.drawBitmap(bitmap,
						canvas.getWidth() + ((i + 1) * bitmap.getWidth()),
						canvas.getHeight() - bitmap.getHeight(), null);

			} else {
				canvas.drawBitmap(bitmap,
						toadonen2_X + ((i + 1) * bitmap.getWidth()),
						canvas.getHeight() - bitmap.getHeight(), null);
				canvas.drawBitmap(bitmap,
						toadonen2_phu_X + ((i + 1) * bitmap.getWidth()),
						canvas.getHeight() - bitmap.getHeight(), null);
			}
		}

	}
}
