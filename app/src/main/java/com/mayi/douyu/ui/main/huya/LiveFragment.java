package com.mayi.douyu.ui.main.huya;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lianjiatech.infrastructure.ProgressLayout;
import com.mayi.douyu.R;
import com.mayi.douyu.entity.HYRoom;
import com.mayi.douyu.mvpbase.MVPBaseFragment;
import com.mayi.douyu.ui.video.full.FullVideoActivity;
import com.mayi.douyu.widget.help.BaseOnScrollListener;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者 by yugai 时间 17/1/18.
 * ＊ 邮箱 784787081@qq.com
 */

public class LiveFragment extends MVPBaseFragment<LiveContract.View, LivePresenter> implements LiveContract.View {

    @Bind(R.id.rv)
    RecyclerView mRv;
    @Bind(R.id.srl)
    SwipeRefreshLayout mSrl;
    @Bind(R.id.progress)
    ProgressLayout mProgress;

    private LiveAdapter mAdapter;

    public static LiveFragment newInstance() {
        Bundle args = new Bundle();
        LiveFragment fragment = new LiveFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    public void onRequestData(List<HYRoom> rooms) {
        mAdapter.addLoadMoreData(rooms);
    }

    @Override
    public void onRefreshData(List<HYRoom> rooms) {
        mAdapter.addRefreshData(rooms);
    }

    @Override
    public void onRequestError(String msg) {
        mProgress.showNetError(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.getListData(true);
            }
        });
    }

    @Override
    public void onRequestEnd() {
        mProgress.showContent();
        hideRefreshLayout();
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_live;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRv.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter=new LiveAdapter(getActivity(), new LiveAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int sid, long subsid) {

            }
        });
        mRv.setAdapter(mAdapter);
        mRv.addOnScrollListener(new BaseOnScrollListener() {
            @Override
            public void onLoadMore() {
                mPresenter.getListData(false);
                mAdapter.setLoading(true);
            }
        });
        mSrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getListData(true);
            }
        });
        mProgress.showLoading();
        mPresenter.getListData(true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void hideRefreshLayout() {
        if (mSrl.isRefreshing()) {
            mSrl.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mSrl.setRefreshing(false);
                }
            },500);
        }
    }
}
