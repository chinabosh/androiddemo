package com.bosh.module_demo.ui.activity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.china.bosh.mylibrary.ui.activity.BaseActivity;

import butterknife.BindView;
import com.bosh.module_demo.R;
import com.bosh.module_demo.R2;

/**
 * 前景色 {@link #setForegroundColor()}
 * 背景色{@link #setBackGroundColor()}
 * 大小比例文字 {@link #setRelativeText()}
 * 中划线 {@link #setStrikeThrough()}
 * 下划线 {@link #setUnderline()}
 * 上标 {@link #setSuperscript()} 若显示不全，设置padding可解决
 * 下标 {@link #setSubscript()} 若显示不全，设置padding可解决
 * 点击 {@link #setClickable()}
 * 图片 {@link #setImageText()}
 * 风格 {@link #setStyle()} 若显示不全，设置padding可解决
 * 超链接 {@link #setUrl()}
 * @author chinabosh
 * @Description SpannableString demo
 */
@Route(path = "/demo/spannableActivity")
public class SpannableActivity extends BaseActivity {

    @BindView(R2.id.tv_foreground_color)
    TextView mTvForegroundColor;
    @BindView(R2.id.tv_background_color)
    TextView mTvBackgroundColor;
    @BindView(R2.id.tv_relative)
    TextView mTvRelative;
    @BindView(R2.id.tv_strike_through)
    TextView mTvStrikeThrough;
    @BindView(R2.id.tv_underline)
    TextView mTvUnderline;
    @BindView(R2.id.tv_superscript)
    TextView mTvSuperscript;
    @BindView(R2.id.tv_subscript)
    TextView mTvSubscript;
    @BindView(R2.id.tv_click)
    TextView mTvClick;
    @BindView(R2.id.tv_image)
    TextView mTvImage;
    @BindView(R2.id.tv_style)
    TextView mTvStyle;
    @BindView(R2.id.tv_url)
    TextView mTvUrl;

    @Override
    protected int attachLayoutRes() {
        return R.layout.demo_activity_spannable;
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
        setClickable();
        setImageText();
        setStyle();
        setUrl();
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

    private void setClickable(){
        SpannableString ss = new SpannableString("点击这里试试");
        ClickableSpan span = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                toast("当然只有个toast啦");
            }
        };
        ss.setSpan(span, 2, 4, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        mTvClick.setMovementMethod(LinkMovementMethod.getInstance());
        mTvClick.setText(ss);
    }

    private void setImageText(){
        mTvImage.setText(getHuaji());
    }

    private SpannableString getHuaji(){
        SpannableString ss = new SpannableString("手动滑稽xx");
        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.demo_huaji, null);
        int drawableHeight = drawable.getMinimumHeight();
        drawable.setBounds(0, 0, drawableHeight, drawableHeight);
        ImageSpan span = new ImageSpan(drawable);
        ss.setSpan(span,4, 6, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        return ss;
    }

    private void setStyle(){
        SpannableString ss = new SpannableString("这是粗体、斜体、粗斜");
        StyleSpan spanBold = new StyleSpan(Typeface.BOLD);
        StyleSpan spanItalic = new StyleSpan(Typeface.ITALIC);
        StyleSpan spanBi = new StyleSpan(Typeface.BOLD_ITALIC);
        ss.setSpan(spanBold, 2, 4, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        ss.setSpan(spanItalic, 5, 7, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        ss.setSpan(spanBi, 8, 10, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        mTvStyle.setText(ss);
    }

    private void setUrl(){
        SpannableString ss = new SpannableString("这是百度网址");
        URLSpan span= new URLSpan("https://www.baidu.com");
        ss.setSpan(span, 2, 6, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        mTvUrl.setMovementMethod(LinkMovementMethod.getInstance());
        mTvUrl.setText(ss);
    }
}
