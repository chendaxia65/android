package com.cz.circleprogressbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Czhen on 2016/3/25 0025.
 */
public class ProgressView extends View {


    /**
     * View的间距
     */
    private final float MARGIN = dipToPx(5);

    /**
     * 外圆和内圆的距离
     */
    private final float SPACE = dipToPx(2);

    /**
     * 直径
     */
    private float diameter = dipToPx(100);

    /**
     * 外圆的宽度
     */
    private float outerCircleWidth = dipToPx(2);

    /**
     * 外圆的矩形
     */
    private RectF outerCirlceRectF;

    /**
     * 外圆的画笔
     */
    private Paint outerCirclePaint;

    /**
     * 内圆的矩形
     */
    private RectF innerCirlceRectF;

    /**
     * 内圆的画笔
     */
    private Paint innerCirclePaint;

    /**
     * 是否需要外圆
     */
    private boolean isNeedOuterCircle = true;

    /**
     * 外圆的颜色
     */
    private int outerCircleColor = Color.WHITE;

    /**
     * 内圆的颜色
     */
    private int innerCircleColor = Color.WHITE;

    private int startAngle = 270;

    private int sweepAngle = 0;

    public ProgressView(Context context) {
        super(context);
        initTool();
    }

    public ProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttr(context, attrs);
        initTool();
    }

    public ProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(context, attrs);
        initTool();
    }

    /**
     * 初始化参数值
     *
     * @param context
     * @param attrs
     */
    private void initAttr(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ProgressView);

        isNeedOuterCircle = array.getBoolean(R.styleable.ProgressView_need_ouerCircle, true);
        outerCircleColor = array.getColor(R.styleable.ProgressView_outerCircle_color, Color.WHITE);
        innerCircleColor = array.getColor(R.styleable.ProgressView_innerCircle_color, Color.WHITE);
        int diameter = array.getInt(R.styleable.ProgressView_diameter, 100);
        this.diameter = dipToPx(diameter);

        int outerCircle_width = array.getInt(R.styleable.ProgressView_outerCircle_width, 2);
        this.outerCircleWidth = dipToPx(outerCircle_width);

        array.recycle();
    }

    /**
     * 设置矩阵、画笔
     */
    private void initTool() {
        outerCirlceRectF = new RectF();
        outerCirlceRectF.top = MARGIN;
        outerCirlceRectF.left = MARGIN;
        outerCirlceRectF.right = MARGIN * 2 + diameter + outerCircleWidth;
        outerCirlceRectF.bottom = MARGIN * 2 + diameter + outerCircleWidth;

        innerCirlceRectF = new RectF();
        innerCirlceRectF.left = outerCirlceRectF.left + outerCircleWidth + SPACE;
        innerCirlceRectF.top = outerCirlceRectF.top + outerCircleWidth + SPACE;
        innerCirlceRectF.right = outerCirlceRectF.right - outerCircleWidth - SPACE;
        innerCirlceRectF.bottom = outerCirlceRectF.bottom - outerCircleWidth - SPACE;

        outerCirclePaint = new Paint();
        outerCirclePaint.setAntiAlias(true); //去锯齿
        outerCirclePaint.setStyle(Paint.Style.STROKE);
        outerCirclePaint.setStrokeWidth(outerCircleWidth);
        outerCirclePaint.setColor(outerCircleColor);

        innerCirclePaint = new Paint();
        innerCirclePaint.setAntiAlias(true);
        innerCirclePaint.setStyle(Paint.Style.FILL);
        innerCirclePaint.setColor(innerCircleColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {


//        canvas.drawRect(rectF,rectPaint);

        if (isNeedOuterCircle) {
//            canvas.drawArc(outerCirlceRectF, 1, 360, false, outerCirclePaint);
            float radius = diameter / 2 + SPACE * 2;
            canvas.drawCircle(outerCirlceRectF.centerX(), outerCirlceRectF.centerY(), radius, outerCirclePaint);
        }

        canvas.drawArc(innerCirlceRectF, startAngle, sweepAngle, true, innerCirclePaint);
    }

    /**
     * 设置当前进度
     *
     * @param progress 0~100
     */
    public void setCurrentProgress(int progress) {
        sweepAngle = 360 * progress / 100;
        invalidate();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = (int) (diameter + outerCircleWidth * 2 + MARGIN * 2);
        int height = (int) (diameter + outerCircleWidth * 2 + MARGIN * 2);

        setMeasuredDimension(width, height);//设置当前View的高宽
    }

    /**
     * dip 转换成px
     *
     * @param dip
     * @return
     */
    private int dipToPx(float dip) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * density + 0.5f);
    }
}
