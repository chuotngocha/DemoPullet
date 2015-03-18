package com.demopullet.main;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

	ParallaxBackground background;

	private MainThread thread;

	public static float mWidth;
	public static float mHeight;

	PanelSkill panelSkill;

	Element myelement;

	Bullet motviendan;

	ArrayList<Bullet> bullets = new ArrayList<Bullet>();

	int thoigiannapdan1 = 0; // thoi gian giua 2 lan ban

	int thoigiannapdan3 = 0; // thoi gian giua 2 lan ban

	Paint paint = new Paint();

	public Rect skill1;
	public Rect skill3;

	ArrayList<Enemies> enemies = new ArrayList<Enemies>();
	int thoigiankethu = 0;// thoigianrakethu, 10 sera
	Enemies motkethu;

	public GamePanel(Context context) {

		super(context);

		getHolder().addCallback(this);

		background = new ParallaxBackground(this.getResources());

		thread = new MainThread(getHolder(), this);

		setFocusable(true);

		panelSkill = new PanelSkill(this);
		motviendan = new Bullet(getResources(), 0, 0, R.drawable.lua);

		paint.setColor(Color.GREEN);
		paint.setStrokeWidth(10);

	}

	@Override
	protected void onDraw(Canvas canvas) {

		super.onDraw(canvas);

		canvas.drawColor(Color.WHITE);
		background.doDrawRunning(canvas);

		synchronized (this) {
			thoigiannapdan1++;
			thoigiannapdan3++;
			thoigiankethu++;
			if (myelement != null) {
				myelement.doDraw(canvas);
				this.doDrawBullet(canvas);
				this.doDrawEnemies(canvas);
				xetvacham(canvas);
			}
		}
		panelSkill.draw(canvas);

		// canvas.drawLine(canvas.getWidth() - panelSkill.getWidth(), 0,
		// canvas.getWidth() - panelSkill.getWidth(), canvas.getHeight(),
		// paint);

		// canvas.drawLine(canvas.getWidth() - panelSkill.getWidth() + 100,
		// 0,
		// canvas.getWidth() - panelSkill.getWidth() + 100,
		// canvas.getHeight(), paint);

		// canvas.drawLine(0, canvas.getHeight() - 130, canvas.getWidth(),
		// canvas.getHeight() - 130, paint);
		//
		// canvas.drawLine(0, canvas.getHeight() - 20, canvas.getWidth(),
		// canvas.getHeight() - 20, paint);

		// canvas.drawLine(0, canvas.getHeight() - panelSkill.getHeight()
		// + 100, canvas.getWidth(),
		// canvas.getHeight() - panelSkill.getHeight() + 100, paint);
		//
		// canvas.drawLine(0, canvas.getHeight() - panelSkill.getHeight(),
		// canvas.getWidth(),
		// canvas.getHeight() - panelSkill.getHeight(), paint);
		//
		// canvas.drawLine(canvas.getWidth() - 130, 0,
		// canvas.getWidth() - 130, canvas.getHeight(), paint);
		//
		// canvas.drawLine(canvas.getWidth() - 20, 0, canvas.getWidth() -
		// 20,
		// canvas.getHeight(), paint);

		if (skill1 == null) {
			skill1 = new Rect(canvas.getWidth() - panelSkill.getWidth() + 10,
					canvas.getHeight() - 90, canvas.getWidth()
							- panelSkill.getWidth() + 90,
					canvas.getHeight() - 35);

			skill3 = new Rect(canvas.getWidth() - 120, canvas.getHeight()
					- panelSkill.getHeight() + 20, canvas.getWidth() - 50,
					canvas.getHeight() - panelSkill.getHeight() + 80);
		}
	}

	public boolean vc_b_e(Bullet bullet, Enemies enemies) {

		float nuarong_b = (float) bullet.getWidth() / 2;
		int nuacao_b = bullet.getHeight() / 2;
		float nuarong_e = (float) enemies.getWidth() / 2;
		int nuacao_e = enemies.getHeight() / 2;
		// khoangcach 2 tamtheo x
		int kc_ht_x = Math.abs(bullet.gettamX() - enemies.gettamX());
		// khoangcach 2 tamtheo y
		int kc_ht_y = Math.abs(bullet.gettamY() - enemies.gettamY());
		if (kc_ht_x <= nuarong_b + nuarong_e && kc_ht_y <= nuacao_b + nuacao_e)
			return true;
		else
			return false;
	}

	public void xetvacham(Canvas canvas) {
		synchronized (this) {

			try {
				for (int i = 0; i < bullets.size(); i++)
					for (int j = 0; j < enemies.size(); j++) {
						if (vc_b_e(bullets.get(i), enemies.get(j)) == true) {
							bullets.remove(i);
							enemies.remove(j);
						}
					}
			} catch (Exception e) {
				Log.d("loi", e.toString());
			}
		}
	}

	public void doDrawBullet(Canvas canvas) {
		synchronized (this) {

			for (int i = 0; i < bullets.size(); i++) {
				bullets.get(i).doDraw(canvas);
			}

			for (int i = 0; i < bullets.size(); i++) {
				if (bullets.get(i).x > canvas.getWidth()) {
					bullets.remove(i);
				}
			}
		}

	}

	// ve tap hop kethu
	public void doDrawEnemies(Canvas canvas) {
		synchronized (this) {

			if (thoigiankethu >= 70) {
				thoigiankethu = 0;
				motkethu = new Enemies(getResources(), canvas.getWidth(),
						canvas.getHeight());
				enemies.add(motkethu);
			}
			for (int i = 0; i < enemies.size(); i++)
				enemies.get(i).doDraw(canvas);

			for (int i = 0; i < enemies.size(); i++)
				if (enemies.get(i).y < 0)
					enemies.remove(i);
		}
	}

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		synchronized (this) {

			if (myelement == null) {
				myelement = new Element(getResources(), (int) event.getX(),
						(int) event.getY());
				Log.d("abc", "khoi tao dau tien");
				return true;
			} else {
				myelement.mX = (int) event.getX() - myelement.bitmap.getWidth()
						/ 2;
				myelement.mY = (int) event.getY()
						- myelement.bitmap.getHeight() / 2;
			}

			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				myelement.mX = (int) event.getX() - myelement.bitmap.getWidth()
						/ 2;
				myelement.mY = (int) event.getY()
						- myelement.bitmap.getHeight() / 2;
				Log.d("abc", "ddddddddddddddddddddddddddddown");
			}
			if (event.getAction() == MotionEvent.ACTION_UP) {
				myelement.mX = (int) event.getX() - myelement.bitmap.getWidth()
						/ 2;
				myelement.mY = (int) event.getY()
						- myelement.bitmap.getHeight() / 2;
				Log.d("abc", "uuuuuuuuuuuuuuuuuuuuuuuuuuuup");
			}
			if (event.getAction() == MotionEvent.ACTION_MOVE) {
				myelement.mX = (int) event.getX() - myelement.bitmap.getWidth()
						/ 2;
				myelement.mY = (int) event.getY()
						- myelement.bitmap.getHeight() / 2;
				Log.d("abc", "mmmmmmmmmmmmmmmmmmmmmmmmmmove");
			}

			if (skill1.contains((int) event.getX(), (int) event.getY())) {

				if (thoigiannapdan1 >= 70) {

					thoigiannapdan1 = 0;

					motviendan = new Bullet(getResources(), 0, 500,
							R.drawable.lua);

					bullets.add(motviendan);
				}
			}

			if (skill3.contains((int) event.getX(), (int) event.getY())) {

				if (thoigiannapdan3 >= 50) {

					thoigiannapdan3 = 0;

					motviendan = new Bullet(getResources(), 0, 300,
							R.drawable.lua);

					bullets.add(motviendan);
				}
			}

			System.out.println("Touch X: " + myelement.mX);
			System.out.println("Touch Y:" + myelement.mY);
		}
		return true; // super.onTouchEvent(event);
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {

	}

	public void surfaceCreated(SurfaceHolder arg0) {
		thread.setRunning(true);
		thread.start();
	}

	public void surfaceDestroyed(SurfaceHolder arg0) {
		if (thread.isAlive()) {
			thread.setRunning(false);
		}
	}

}
