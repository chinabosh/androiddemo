package com.china.bosh.mylibrary.ui.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author lzq
 * @date 2018/11/26
 * 作者：看书的小蜗牛
 * 链接：https://www.jianshu.com/p/4f554aba2bec
 * 來源：简书
 * 简书著作权归作者所有，任何形式的转载都请联系作者获得授权并注明出处。
 */

public class ExpandedLinearLayoutManager extends LinearLayoutManager {

    /**
     * 最大展示的数目，如果设置了，就会按照最大数目显示，不过注意在代码中 设置 RecyclerView.setNestedScrollingEnabled(true);
     */
    private int mMaxItemCount = -1;

    private int[] mMeasuredDimension = new int[2];
    private int measureWidth;
    private int measureHeight;
    private int count;

    public ExpandedLinearLayoutManager(Context context) {
        super(context);
    }

    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state,
                          int widthSpec, int heightSpec) {
        final int widthMode = View.MeasureSpec.getMode(widthSpec);
        final int heightMode = View.MeasureSpec.getMode(heightSpec);
        final int widthSize = View.MeasureSpec.getSize(widthSpec);
        final int heightSize = View.MeasureSpec.getSize(heightSpec);
        measureWidth = 0;
        measureHeight = 0;
        if (mMaxItemCount < 0 || getItemCount() < mMaxItemCount) {
            count = getItemCount();
        } else {
            count = mMaxItemCount;
        }
        for (int i = 0; i < count; i++) {
//            measureScrapChild(recycler, i,
//                    View.MeasureSpec.makeMeasureSpec(i, View.MeasureSpec.UNSPECIFIED),
//                    View.MeasureSpec.makeMeasureSpec(i, View.MeasureSpec.UNSPECIFIED),
//                    mMeasuredDimension);
            int[] measuredDimension = getChildDimension(recycler, i, widthSpec, heightSpec);
            if (measuredDimension == null || measuredDimension.length != 2)
                return;
            if (getOrientation() == HORIZONTAL) {
                measureWidth = measureWidth + measuredDimension[0];
                //获取最大高度
                measureHeight = Math.max(measureHeight, measuredDimension[1]);
//                measureWidth = measureWidth + mMeasuredDimension[0];
//                measureHeight = Math.max(measureHeight, mMeasuredDimension[1]);
            } else {
                measureHeight = measureHeight + measuredDimension[1];
                //获取最大宽度
                measureWidth = Math.max(measureWidth, measuredDimension[0]);
//                measureHeight = measureHeight + mMeasuredDimension[1];
//                measureWidth = Math.max(measureWidth, mMeasuredDimension[1]);
            }
            Log.e("test", "measureHeight:" + measureHeight);
        }

        measureHeight = measureHeight + getPaddingBottom() + getPaddingTop();
        measureWidth = measureWidth + getPaddingLeft() + getPaddingRight();
        Log.e("height", ":" + measureHeight);
//        measureHeight = heightMode == View.MeasureSpec.EXACTLY ? heightSize : measureHeight;
//        measureWidth = widthMode == View.MeasureSpec.EXACTLY ? widthSize : measureWidth;
        if (getOrientation() == HORIZONTAL && measureWidth > widthSize) {
            if (widthMode == View.MeasureSpec.UNSPECIFIED) {
                setMeasuredDimension(measureWidth, measureHeight);
            } else {
                super.onMeasure(recycler, state, widthSize, heightSpec);
            }
        } else if (getOrientation() == VERTICAL && measureHeight > heightSize) {
            if (heightMode == View.MeasureSpec.UNSPECIFIED) {
                setMeasuredDimension(measureWidth, measureHeight);
            } else {
                setMeasuredDimension(measureWidth, heightSize);
            }
        } else {
            setMeasuredDimension(measureWidth, measureHeight);
        }
    }

    private void measureScrapChild(RecyclerView.Recycler recycler, int position, int widthSpec,
                                   int heightSpec, int[] measuredDimension) {
        try {
            View view = recycler.getViewForPosition(position);//fix 动态添加时报IndexOutOfBoundsException

            if (view != null) {
                RecyclerView.LayoutParams p = (RecyclerView.LayoutParams) view.getLayoutParams();

                int childWidthSpec = ViewGroup.getChildMeasureSpec(widthSpec,
                        getPaddingLeft() + getPaddingRight(), p.width);

                int childHeightSpec = ViewGroup.getChildMeasureSpec(heightSpec,
                        getPaddingTop() + getPaddingBottom(), p.height);

                view.measure(childWidthSpec, childHeightSpec);
                measuredDimension[0] = view.getMeasuredWidth() + p.leftMargin + p.rightMargin;
                measuredDimension[1] = view.getMeasuredHeight() + p.bottomMargin + p.topMargin;
                recycler.recycleView(view);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }


    private int[] getChildDimension(RecyclerView.Recycler recycler, int position, int widthSpec, int heightSpec) {
        try {
            int[] measuredDimension = new int[2];
            View view = recycler.getViewForPosition(position);
            //获取childView，以便获得宽高（包括ItemDecoration的限制），以及边距
            RecyclerView.LayoutParams p = (RecyclerView.LayoutParams) view.getLayoutParams();
            Rect outRect = new Rect();
            // Android7.0不知道你做了什么，Textview的处理不太兼容，否则super.measureChild(view,0,0,);就够了
            // super.measureChild(view,0,0,); 其实用自定的可能还是有问题，比如边距之类的，所以，如果不是全展开还是用默认的
            measureChildInSelf(view, widthSpec, heightSpec);
            calculateItemDecorationsForChild(view, outRect);
            measuredDimension[0] = view.getMeasuredWidth() + outRect.left + outRect.right + p.leftMargin + p.rightMargin;
            measuredDimension[1] = view.getMeasuredHeight() + outRect.top + outRect.bottom + p.bottomMargin + p.topMargin;
            return measuredDimension;
        } catch (Exception e) {
            Log.d("LayoutManager", e.toString());
        }
        return null;
    }

    //自行测量，不用LayoutManager自带的，自带的时机有时候不好把控，
    public void measureChildInSelf(View child, int widthSpec, int heightSpec) {
        int widthSize = View.MeasureSpec.getSize(widthSpec);
        int widthMode = View.MeasureSpec.getMode(widthSpec);
        int heightSize = View.MeasureSpec.getSize(heightSpec);
        int heightMode = View.MeasureSpec.getMode(heightSpec);
        final RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) child.getLayoutParams();
        int newWidthSpec = getChildMeasureSpec(widthSize, widthMode,
                getPaddingLeft() + getPaddingRight(), lp.width, getOrientation() == HORIZONTAL);
        int newHeightSpec = getChildMeasureSpec(heightSize, heightMode,
                getPaddingBottom() + getPaddingTop(), lp.width, getOrientation() == VERTICAL);
        if (getOrientation() == VERTICAL) {
            child.measure(newWidthSpec, 0);
        } else {
            child.measure(0, newHeightSpec);
        }
    }
}
