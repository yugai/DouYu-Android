package com.mayi.douyu.base;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mayi.douyu.R;

import java.util.ArrayList;
import java.util.List;


/**
 * 作者 by yugai 时间 16/11/25.
 * ＊ 邮箱 784787081@qq.com
 */

public abstract class BaseRecyclerAdapter<M> extends RecyclerView.Adapter{
    private boolean hasMore=true,isLoading=true,canLoad=true;
    public static final int ITEM_LOADING = Integer.MAX_VALUE;
    static final int ITEM_TYPE=0;
    private List<M> datas=new ArrayList<>();

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    public void setCanLoad(boolean canLoad) {
        this.canLoad=canLoad;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof LoadingHolder) {
            LoadingHolder loadingHolder= (LoadingHolder) holder;
            if (hasMore) {
                loadingHolder.progressBar.setVisibility(View.VISIBLE);
                loadingHolder.content.setText(R.string.rv_loading);
            } else {
                loadingHolder.progressBar.setVisibility(View.GONE);
                loadingHolder.content.setText(R.string.rv_loading_complete);
            }
        } else {
            onBindItemViewHolder(holder, datas.get(position), position);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_LOADING) {
            return new LoadingHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bottom_loading, parent, false));
        } else {
            return onCreateItemViewHolder(parent, viewType);
        }
    }

    public abstract void onBindItemViewHolder(RecyclerView.ViewHolder holder, M data, int position);
    public abstract RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType);

    @Override
    public int getItemViewType(int position) {
        if (canLoad&&position == datas.size()) {
            return ITEM_LOADING;
        } else {
            return ITEM_TYPE;
        }
    }

    @Override
    public int getItemCount() {
        return canLoad?datas.size() + 1:datas.size();
    }

    public void addLoadMoreData(List<M> data) {
        if (data == null) return;
        this.datas.addAll(data);
        this.isLoading = false;
        notifyDataSetChanged();
    }

    public void addRefreshData(List<M> data) {
        if (data == null) return;
        this.datas.clear();
        this.datas.addAll(data);
        this.isLoading = false;
        notifyDataSetChanged();
    }
    public boolean canLoadMore() {
        return hasMore && !isLoading;
    }

    static class LoadingHolder extends RecyclerView.ViewHolder{
        ProgressBar progressBar;
        TextView content;
        public LoadingHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progress_view);
            content = (TextView) itemView.findViewById(R.id.tv_content);
        }
    }
}
