package com.mayi.douyu.ui.about;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.transition.TransitionInflater;

import com.mayi.douyu.R;
import com.mayi.douyu.base.BaseActivity;
import com.mayi.douyu.widget.ElasticDragDismissFrameLayout;
import com.mayi.douyu.widget.InkPageIndicator;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者 by yugai 时间 17/2/6.
 * ＊ 邮箱 784787081@qq.com
 */

public class AboutActivity extends BaseActivity {
    @Bind(R.id.pager)
    ViewPager mPager;
    @Bind(R.id.indicator)
    InkPageIndicator mIndicator;
    @Bind(R.id.draggable_frame)
    ElasticDragDismissFrameLayout mDraggableFrame;

    @Override
    protected int getLayout() {
        return R.layout.activity_about;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        mPager.setAdapter(new AboutAdapter(AboutActivity.this));
        mPager.setPageMargin(getResources().getDimensionPixelSize(R.dimen.spacing_normal));
        mIndicator.setViewPager(mPager);

        mDraggableFrame.addListener(
                new ElasticDragDismissFrameLayout.SystemChromeFader(this) {
                    @Override
                    public void onDragDismissed() {
                        // if we drag dismiss downward then the default reversal of the enter
                        // transition would slide content upward which looks weird. So reverse it.
                        if (mDraggableFrame.getTranslationY() > 0) {
                            getWindow().setReturnTransition(
                                    TransitionInflater.from(AboutActivity.this)
                                            .inflateTransition(R.transition.about_return_downward));
                        }
                        finishAfterTransition();
                    }
                });
    }
}
