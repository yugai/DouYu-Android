package com.mayi.douyu.widget.help;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * 作者 by yugai 时间 16/11/25.
 * ＊ 邮箱 784787081@qq.com
 */

public abstract class BaseOnScrollListener extends RecyclerView.OnScrollListener {
    protected int lastVisibleItem;

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        lastVisibleItem = ((LinearLayoutManager)recyclerView.getLayoutManager()).findLastVisibleItemPosition();

    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        if (newState == RecyclerView.SCROLL_STATE_IDLE
                && lastVisibleItem + 1 == recyclerView.getAdapter().getItemCount()) {
            onLoadMore();
        }
    }

    public abstract void onLoadMore();
}
