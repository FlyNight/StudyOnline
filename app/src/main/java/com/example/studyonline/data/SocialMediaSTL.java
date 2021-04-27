package com.example.studyonline.data;

import android.annotation.SuppressLint;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextUtils;

import com.example.studyonline.data.bean.Outline;
import com.orient.me.data.ITimeItem;
import com.orient.me.utils.UIUtils;
import com.orient.me.widget.rv.itemdocration.timeline.SingleTimeLineDecoration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SocialMediaSTL extends SingleTimeLineDecoration {

    private static final String[] MONTHS = new String[]{
            "/1月", "/2月", "/2月", "/4月", "/5月", "/6月", "/7月", "/8月", "/9月", "/10月", "/11月", "/12月"
    };

    private int r;
    private Paint monTextPaint;
    private Paint dayTextPaint;
    private int space;

    public SocialMediaSTL(Config config) {
        super(config);

        r = UIUtils.dip2px(24);
        monTextPaint = new Paint();
        monTextPaint.setTextSize(UIUtils.sp2px(mContext, 10));
        monTextPaint.setColor(Color.parseColor("#F5F5F5"));

        dayTextPaint = new Paint();
        dayTextPaint.setTextSize(UIUtils.sp2px(mContext, 18));
        dayTextPaint.setColor(Color.parseColor("#ffffff"));

        space = UIUtils.dip2px(2);

        mDotPaint.setMaskFilter(new BlurMaskFilter(6, BlurMaskFilter.Blur.SOLID));

        mTextPaint.setTypeface(Typeface.DEFAULT_BOLD);

    }

    @Override
    protected void onDrawTitleItem(Canvas canvas, int left, int top, int right, int bottom, int pos) {
        ITimeItem item = timeItems.get(pos);

        int rectWidth = UIUtils.dip2px(20);
        int mid = (bottom + top) / 2;

        String title = item.getTitle();
        if (TextUtils.isEmpty(title))
            return;
        Rect mRect = new Rect();

        mTextPaint.getTextBounds(title, 0, title.length(), mRect);
        int x = left + rectWidth;
        //int x = left + UIUtils.dip2px(20);
        int y = mid + mRect.height() / 2;
        canvas.drawText(title, x, y, mTextPaint);
    }


    @SuppressLint("SimpleDateFormat")
    @Override
    protected void onDrawDotItem(Canvas canvas, int cx, int cy, int radius, int pos) {
        super.onDrawDotItem(canvas, cx, cy, radius, pos);

        Outline timeItem = (Outline) timeItems.get(pos);
        if(timeItem.getColor() == 1) {
            mDotPaint.setColor(Color.parseColor("#624BB7"));
        } else {
            mDotPaint.setColor(Color.parseColor("#3700FF"));
        }
        canvas.drawRoundRect(cx - r, cy - r, cx + r, cy + r
                , UIUtils.dip2px(2), UIUtils.dip2px(2),mDotPaint);

    }


}
