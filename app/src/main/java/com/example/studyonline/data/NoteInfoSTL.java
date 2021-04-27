package com.example.studyonline.data;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;

import com.example.studyonline.data.bean.Task;
import com.orient.me.utils.UIUtils;
import com.orient.me.widget.rv.itemdocration.timeline.SingleTimeLineDecoration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NoteInfoSTL extends SingleTimeLineDecoration {

    private Paint strTextPaint;
    private Paint dayTextPaint;
    private int space;

    public NoteInfoSTL(Config config) {
        super(config);

        strTextPaint = new Paint();
        strTextPaint.setTextSize(UIUtils.sp2px(mContext,12));
        strTextPaint.setColor(Color.parseColor("#8d6e63"));

        dayTextPaint = new Paint();
        dayTextPaint.setTextSize(UIUtils.sp2px(mContext,20));
        dayTextPaint.setColor(Color.parseColor("#8d6e63"));
        dayTextPaint.setTypeface(Typeface.DEFAULT_BOLD);

        space = UIUtils.dip2px(8);
    }

    @Override
    protected void onDrawTitleItem(Canvas canvas, int left, int top, int right, int bottom, int pos) {
        Task timeItem = (Task) timeItems.get(pos);
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(timeItem.getCommitTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(date != null){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            String year = Integer.toString(calendar.get(Calendar.YEAR));
            String mon = calendar.get(Calendar.MONTH)<9?"0"+(calendar.get(Calendar.MONTH)+1)
                    : Integer.toString(calendar.get(Calendar.MONTH+1));
            String str = year + "-" +mon;
            int n = calendar.get(Calendar.DAY_OF_MONTH);
            String day = n<10?"0"+n: Integer.toString(n);

            Rect monRect = new Rect();
            strTextPaint.getTextBounds(str,0,str.length(),monRect);
            Rect dayRect = new Rect();
            dayTextPaint.getTextBounds(day,0,day.length(),dayRect);

            int monWidth = monRect.width();
            int monHeight = monRect.height();
            int dayWidth = dayRect.width();
            int dayHeight = dayRect.height();

            int cy = (top + bottom)/2;
            int cx = (left + right)/2;

            int beginY = cy - space/2;
            canvas.drawText(day,cx-dayWidth/2,beginY,dayTextPaint);
            beginY = cy + space/2 + monHeight;
            canvas.drawText(str,cx-monWidth/2,beginY,strTextPaint);
        }
    }

    @Override
    protected void onDrawDotItem(Canvas canvas, int cx, int cy, int radius, int pos) {
        // 不绘制
    }
}
