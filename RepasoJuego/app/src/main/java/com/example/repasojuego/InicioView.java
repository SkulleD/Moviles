package com.example.repasojuego;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.MotionEvent;
import android.view.View;

public class InicioView extends View {
    int anchoPantalla, altoPantalla, cont = 1;
    String texto;
    Paint paint;
    TextPaint tpaint;
    Rect cuadrado, cuadradoBorde, cuadradoConTexto;
    RectF cuadrado2;
    StaticLayout textLayout;

    public InicioView(Context context) {
        super(context);
        paint = new Paint();
        paint.setAlpha(240);
        paint.setTextSize(110);
        paint.setAntiAlias(true);

        tpaint = new TextPaint();
        tpaint.setTextSize(80);
        tpaint.setTextAlign(Paint.Align.CENTER);
        tpaint.setColor(Color.WHITE);
        tpaint.setShadowLayer(4f, 4f, 4f, Color.RED);

        cuadrado = new Rect(20, 210, 350, 410);
        cuadradoBorde = new Rect(620, 210, 920, 410);
        cuadrado2 = new RectF(320, 240, 640, 440);
        cuadradoConTexto = new Rect();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLUE);
        paint.setColor(Color.WHITE);
        canvas.drawText(cont + " Texto de ejemplo", 20, 100, paint);

        paint.setColor(Color.GREEN);
        canvas.drawRect(cuadrado, paint);

        paint.setAlpha(170);
        paint.setColor(Color.RED);
        canvas.drawRoundRect(cuadrado2, 40, 30, paint);

        paint.setAlpha(240);
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        canvas.drawRect(cuadradoBorde, paint);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.CYAN);
        canvas.drawCircle(anchoPantalla / 2, altoPantalla / 2, 200, paint);
        paint.setColor(Color.WHITE);
        canvas.drawCircle(anchoPantalla / 2, altoPantalla / 2, 100, paint);

        textLayout = new StaticLayout(texto, tpaint, anchoPantalla / 2, Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
        int textHeight = textLayout.getHeight();
        canvas.save();
        canvas.translate(anchoPantalla / 2, 500);
        textLayout.draw(canvas);
        canvas.restore();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.anchoPantalla = w;
        this.altoPantalla = h;
        this.texto = "El ancho de la pantalla es " + w + " y el alto " + h;
    }
}
