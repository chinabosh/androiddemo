package com.china.bosh.demo.ui.activity;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.UnderlineSpan;
import android.widget.TextView;

import com.china.bosh.demo.R;

import butterknife.BindView;

/**
 * 前景色 {@link #setForegroundColor()}
 * 背景色{@link #setBackGroundColor()}
 * @author chinabosh
 * @Description SpannableString demo
 */
public class SpannableActivity extends BaseActivity {

    @BindView(R.id.tv_foreground_color)
    TextView mTvForegroundColor;
    @BindView(R.id.tv_background_color)
    TextView mTvBackgroundColor;
    @BindView(R.id.tv_relative)
    TextView mTvRelative;
    @BindView(R.id.tv_strike_through)
    TextView mTvStrikeThrough;
    @BindView(R.id.tv_underline)
    TextView mTvUnderline;
    @BindView(R.id.tv_superscript)
    TextView mTvSuperscript;
    @BindView(R.id.tv_subscript)
    TextView mTvSubscript;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_spannable;
    }

    @Override
    protected void initView() {
        setForegroundColor();
        setBackGroundColor();
        setRelativeText();
        setStrikeThrough();
        setUnderline();
        setSuperscript();
        setSubscript();
    }

    @Override
    protected void initData() {

    }

    private void setForegroundColor(){
        SpannableString ss = new SpannableString("这是前景色");
        ForegroundColorSpan span = new ForegroundColorSpan(Color.BLUE);
        ss.setSpan(span, 2, 5, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        mTvForegroundColor.setText(ss);
    }

    private void setBackGroundColor(){
        SpannableString ss = new SpannableString("这是背景色");
        BackgroundColorSpan span = new BackgroundColorSpan(Color.GREEN);
        ss.setSpan(span, 2, 5, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        mTvBackgroundColor.setText(ss);
    }

    private void setRelativeText(){
        SpannableString ss = new SpannableString("2倍正常0.5倍");
        RelativeSizeSpan spanBig = new RelativeSizeSpan(2f);
        RelativeSizeSpan spanSmall = new RelativeSizeSpan(0.5f);
        ss.setSpan(spanBig, 0, 2, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        ss.setSpan(spanSmall, 4, 8, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        mTvRelative.setText(ss);
    }

    private void setStrikeThrough(){
        SpannableString ss = new SpannableString("这是中划线");
        StrikethroughSpan span = new StrikethroughSpan();
        ss.setSpan(span, 2, 5, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        mTvStrikeThrough.setText(ss);
//        mTvStrikeThrough.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);//设置中划线并加清晰
    }

    private void setUnderline(){
        SpannableString ss = new SpannableString("这是下划线");
        UnderlineSpan span = new UnderlineSpan();
        ss.setSpan(span, 2, 5, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        mTvUnderline.setText(ss);
//        mTvUnderline.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
    }

    private void setSuperscript(){
        SpannableString ss = new SpannableString("这是上标");
        SuperscriptSpan span = new SuperscriptSpan();
        ss.setSpan(span, 2, 4, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        mTvSuperscript.setText(ss);
    }

    private void setSubscript(){
        SpannableString ss = new SpannableString("这是下标");
        SubscriptSpan span = new SubscriptSpan();
        ss.setSpan(span, 2, 4, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        mTvSubscript.setText(ss);
    }
}
