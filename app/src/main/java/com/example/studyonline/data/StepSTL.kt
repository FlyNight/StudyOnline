package com.example.studyonline.data

import android.R.attr
import android.graphics.BlurMaskFilter
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.text.TextUtils
import com.orient.me.utils.UIUtils
import com.orient.me.widget.rv.itemdocration.timeline.DoubleTimeLineDecoration


class StepSTL(config: Config?): DoubleTimeLineDecoration(config) {

    private val mRectPaint: Paint = Paint()
    init {
        mRectPaint.maskFilter = BlurMaskFilter(10f, BlurMaskFilter.Blur.SOLID)
        mDotPaint.maskFilter = BlurMaskFilter(6f, BlurMaskFilter.Blur.SOLID)
    }
    override fun onDrawTitleItem(
        canvas: Canvas?,
        left: Int,
        top: Int,
        right: Int,
        bottom: Int,
        centerX: Int,
        pos: Int,
        isLeft: Boolean
    ) {
        val item = timeItems[pos]

        val rectWidth = UIUtils.dip2px(120f)
        val height = attr.bottom - attr.top
        val paddingLeft = UIUtils.dip2px(10f)
        mRectPaint.color = item.color
        canvas!!.drawRoundRect(
            (attr.left + paddingLeft).toFloat(),
            attr.top.toFloat(),
            (attr.left + rectWidth).toFloat(),
            attr.bottom.toFloat(), UIUtils.dip2px(6f).toFloat(),
            UIUtils.dip2px(6f).toFloat(), mRectPaint
        )


        val title = item.title
        if (TextUtils.isEmpty(title)) return
        val mRect = Rect()

        mTextPaint.getTextBounds(title, 0, title.length, mRect)
        val x: Int = attr.left + (rectWidth - mRect.width()) / 2
        //int x = left + UIUtils.dip2px(20);
        //int x = left + UIUtils.dip2px(20);
        val y: Int = attr.bottom - (height - mRect.height()) / 2
        canvas.drawText(title, x.toFloat(), y.toFloat(), mTextPaint)
    }

    override fun onDrawDotItem(canvas: Canvas?, cx: Int, cy: Int, radius: Int, pos: Int) {
        val item = timeItems[pos]
        mDotPaint.color = item.color
        canvas!!.drawCircle(cx.toFloat(), cy.toFloat(), UIUtils.dip2px(6f).toFloat(), mDotPaint)
    }
}