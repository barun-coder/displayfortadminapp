package com.displayfort.app.widgets;

/**
 * Created by pc on 12/01/2019 14:00.
 * DisplayFortSoftware
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.Region;
import android.graphics.Shader;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

public class HexagonMaskView extends AppCompatImageView {
    private Path hexagonPath;
    private Path hexagonBorderPath;
    private Paint mBorderPaint;
    private int backGroundColor = 0;

    public HexagonMaskView(Context context) {
        super(context);
        init();
    }

    public HexagonMaskView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HexagonMaskView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        this.hexagonPath = new Path();
        this.hexagonBorderPath = new Path();

        this.mBorderPaint = new Paint();
        this.mBorderPaint.setShader(new LinearGradient(0, 0, 0, getHeight(), Color.RED, Color.YELLOW, Shader.TileMode.MIRROR));
//        Color(Color.WHITE);
        this.mBorderPaint.setStrokeCap(Paint.Cap.ROUND);
        this.mBorderPaint.setStrokeWidth(8f);
        this.mBorderPaint.setStyle(Paint.Style.STROKE);
    }

    public void setRadius(float radius) {
        calculatePath(radius);
    }

    public void setBorderColor(int color) {
        this.mBorderPaint.setColor(color);
        invalidate();
    }

    public void setBackgroundColor(int color) {
        this.backGroundColor = (color);
        invalidate();
    }

    private void calculatePath(float radius) {
        float halfRadius = radius / 2f;
        float triangleHeight = (float) (Math.sqrt(3.0) * halfRadius);
        float centerX = getMeasuredWidth() / 2f;
        float centerY = getMeasuredHeight() / 2f;

        this.hexagonPath.reset();
//        this.hexagonPath.moveTo(centerX, centerY + radius);
//        this.hexagonPath.lineTo(centerX - triangleHeight, centerY + halfRadius);
//        this.hexagonPath.lineTo(centerX - triangleHeight, centerY - halfRadius);
//        this.hexagonPath.lineTo(centerX, centerY - radius);
//        this.hexagonPath.lineTo(centerX + triangleHeight, centerY - halfRadius);
//        this.hexagonPath.lineTo(centerX + triangleHeight, centerY + halfRadius);

        this.hexagonPath.moveTo(centerY + radius, centerX);
        this.hexagonPath.lineTo(centerY + halfRadius, centerX - triangleHeight);
        this.hexagonPath.lineTo(centerY - halfRadius, centerX - triangleHeight);
        this.hexagonPath.lineTo(centerY - radius, centerX);
        this.hexagonPath.lineTo(centerY - halfRadius, centerX + triangleHeight);
        this.hexagonPath.lineTo(centerY + halfRadius, centerX + triangleHeight);
        this.hexagonPath.close();

        float radiusBorder = radius - 5f;
        float halfRadiusBorder = radiusBorder / 2f;
        float triangleBorderHeight = (float) (Math.sqrt(3.0) * halfRadiusBorder);

        this.hexagonBorderPath.reset();
        this.hexagonBorderPath.moveTo(centerX, centerY + radiusBorder);
        this.hexagonBorderPath.lineTo(centerX - triangleBorderHeight, centerY + halfRadiusBorder);
        this.hexagonBorderPath.lineTo(centerX - triangleBorderHeight, centerY - halfRadiusBorder);
        this.hexagonBorderPath.lineTo(centerX, centerY - radiusBorder);
        this.hexagonBorderPath.lineTo(centerX + triangleBorderHeight, centerY - halfRadiusBorder);
        this.hexagonBorderPath.lineTo(centerX + triangleBorderHeight, centerY + halfRadiusBorder);
        this.hexagonBorderPath.close();
        invalidate();
    }

    @Override
    public void onDraw(Canvas c) {
//        c.drawPath(hexagonBorderPath, mBorderPaint);
        c.clipPath(hexagonPath, Region.Op.INTERSECT);
        if (backGroundColor == 0) {
            c.drawColor(Color.argb(0, 255, 249, 191), PorterDuff.Mode.CLEAR);
        } else {
            c.drawColor(Color.argb(0, 255, 255, 255), PorterDuff.Mode.CLEAR);
        }
        super.onDraw(c);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width, height);
        calculatePath(Math.min(width / 2f, height / 2f) - 10f);
    }
}
