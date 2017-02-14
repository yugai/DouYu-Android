package com.mayi.douyu.ui.about;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AlignmentSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mayi.douyu.R;

import java.security.InvalidParameterException;

import butterknife.ButterKnife;

/**
 * 作者 by yugai 时间 17/2/6.
 * ＊ 邮箱 784787081@qq.com
 */

public class AboutAdapter extends PagerAdapter{
    Context mContext;
    View info,libs;

    private LayoutInflater layoutInflater;
    public AboutAdapter(Context context) {
        mContext = context;
        layoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        View layout = getPage(position, collection);
        collection.addView(layout);
        return layout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    private View getPage(int position, ViewGroup parent) {
        switch (position) {
            case 0:
                if (info==null){
                   info=layoutInflater.inflate(R.layout.layout_about_info,parent,false);
                }
                return info;
            case 1:
                if (libs==null){
                    libs=layoutInflater.inflate(R.layout.layout_about_support,parent,false);
                    RecyclerView mRvLibs= (RecyclerView) libs.findViewById(R.id.rv_libs);
                    mRvLibs.setLayoutManager(new LinearLayoutManager(mContext));
                    mRvLibs.setAdapter(new LibsAdapter(mContext));
                }
                return libs;
        }
        throw new InvalidParameterException();
    }


}
