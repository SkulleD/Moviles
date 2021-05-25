package com.example.repasojuego;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.WindowManager;

import java.io.IOException;
import java.io.InputStream;

public class InicioView extends View {
    int anchoPantalla, altoPantalla, cont = 1;
    String texto;
    Paint paint;
    TextPaint tpaint;
    Rect cuadrado, cuadradoBorde, cuadradoConTexto;
    RectF cuadrado2;
    StaticLayout textLayout;
    Bitmap imagen;
    Bitmap frame, btnAvanza, btnRetrocede;
    int posX = 0;
    boolean avanza = true;
    Context context;

    public InicioView(Context context) {
        super(context);
        this.context = context;

        paint = new Paint();
        paint.setAlpha(240);
        paint.setTextSize(110);
        paint.setAntiAlias(true);

        tpaint = new TextPaint();
        tpaint.setTextSize(80);
        tpaint.setTextAlign(Paint.Align.CENTER);
        tpaint.setColor(Color.WHITE);
        tpaint.setShadowLayer(4f, 4f, 4f, Color.RED);

        cuadrado = new Rect(getPixels(6.66f), getPixels(70), getPixels(115), getPixels(136.6f));
        cuadradoBorde = new Rect(620, 210, 920, 410);
        cuadrado2 = new RectF(320, 240, 640, 440);
        cuadradoConTexto = new Rect();

        //imagen = BitmapFactory.decodeResource(getResources(), R.drawable.bocatagarto);

        this.frame = getBitmapFromAssets("run_00.png");
        this.btnAvanza = BitmapFactory.decodeResource(getResources(), R.drawable.avanza);
        this.btnRetrocede = BitmapFactory.decodeResource(getResources(), R.drawable.retrocede);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLUE);
        paint.setColor(Color.WHITE);
        canvas.drawText(cont + " Texto de ejemplo", 20, 100, paint);

        paint.setColor(Color.GREEN);
        canvas.drawRect(cuadrado, paint);

        paint.setAlpha(30);
        paint.setColor(Color.RED);
        canvas.drawRoundRect(cuadrado2, 40, 30, paint);

        paint.setAlpha(240);
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        canvas.drawRect(cuadradoBorde, paint);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        canvas.drawCircle(anchoPantalla / 2, altoPantalla / 2, 200, paint);
        paint.setColor(Color.WHITE);
        canvas.drawCircle(anchoPantalla / 2, altoPantalla / 2, 150, paint);
        paint.setColor(Color.RED);
        canvas.drawCircle(anchoPantalla / 2, altoPantalla / 2, 100, paint);
        paint.setColor(Color.WHITE);
        canvas.drawCircle(anchoPantalla / 2, altoPantalla / 2, 50, paint);
        paint.setColor(Color.RED);
        canvas.drawCircle(anchoPantalla / 2, altoPantalla / 2, 25, paint);

        //canvas.drawBitmap(imagen, 0, 0, null);

        textLayout = new StaticLayout(texto, tpaint, anchoPantalla / 2, Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
        int textHeight = textLayout.getHeight();
        canvas.save();
        canvas.translate(anchoPantalla / 2, 1100);
        textLayout.draw(canvas);
        canvas.restore();

        canvas.drawBitmap(frame, posX, 0, null);

        if (avanza) {
            canvas.drawBitmap(btnAvanza, anchoPantalla / 2 - btnAvanza.getWidth() / 2, altoPantalla - btnAvanza.getHeight(), null);
        } else {
            canvas.drawBitmap(btnRetrocede, anchoPantalla / 2 - btnRetrocede.getWidth() / 2 , altoPantalla - btnRetrocede.getHeight(), null);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int accion = event.getAction();
        float x = event.getX(), y = event.getY();

        switch (accion) {
            case MotionEvent.ACTION_DOWN:
                if (x > (anchoPantalla / 2 - btnRetrocede.getWidth() / 2) && x < (anchoPantalla / 2 + btnRetrocede.getWidth() / 2) && y > altoPantalla - btnRetrocede.getHeight()) {
                    avanza =! avanza;
                } else {
                    cont++;
                    this.frame = getBitmapFromAssets("run_0" + (cont%10) + ".png");
                    if (avanza) {
                        posX += getPixels(3f);
                    } else {
                        posX -= getPixels(3f);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        invalidate();
        return true;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.anchoPantalla = w;
        this.altoPantalla = h;
        this.texto = "El ancho de la pantalla es " + w + " y el alto " + h;
    }

    int getPixels(float dp) {
        DisplayMetrics metrics = new DisplayMetrics();
        ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().
                getMetrics(metrics);
        return (int) (dp * metrics.density);
    }

    public Bitmap getBitmapFromAssets(String fichero) {
        try {
            InputStream is = context.getAssets().open(fichero);
            return BitmapFactory.decodeStream(is);
        } catch (IOException e) {
            return null;
        }
    }
}