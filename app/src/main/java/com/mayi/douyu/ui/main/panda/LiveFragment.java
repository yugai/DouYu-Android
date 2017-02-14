package com.mayi.douyu.ui.main.panda;

import android.content.Intent;
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
import com.mayi.douyu.entity.PDRoom;
import com.mayi.douyu.mvpbase.MVPBaseFragment;
import com.mayi.douyu.ui.video.full.FullVideoActivity;
import com.mayi.douyu.widget.help.BaseOnScrollListener;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者 by yugai 时间 16/11/3.
 * ＊ 邮箱 784787081@qq.com
 */

public class LiveFragment extends MVPBaseFragment<LiveContract.View, LivePresenter> implements LiveContract.View {

    @Bind(R.id.rv)
    RecyclerView mRv;
    @Bind(R.id.srl)
    SwipeRefreshLayout mSrl;
    @Bind(R.id.progress)
    ProgressLayout mProgress;

    private LiveAdapter mLiveAdapter;
    public static LiveFragment newInstance() {
        Bundle args = new Bundle();
        LiveFragment fragment = new LiveFragment();
        fragment.setArguments(args);
        return fragment;
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
        mLiveAdapter=new LiveAdapter(getActivity(), new LiveAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String roomId) {
                Intent intent=new Intent(getActivity(),FullVideoActivity.class);
                intent.putExtra(FullVideoActivity.ROOM_ID,roomId);
                intent.putExtra(FullVideoActivity.ROOM_PLATFORM,FullVideoActivity.PADAN);
                startActivity(intent);
            }
        });
        mRv.setAdapter(mLiveAdapter);
        mRv.addOnScrollListener(new BaseOnScrollListener() {
            @Override
            public void onLoadMore() {
                mPresenter.getListData(false);
                mLiveAdapter.setLoading(true);
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

    @Override
    public void onRequestData(List<PDRoom.Room> pdRooms) {
        mLiveAdapter.addLoadMoreData(pdRooms);
    }

    @Override
    public void onRefreshData(List<PDRoom.Room> pdRooms) {
        mLiveAdapter.addRefreshData(pdRooms);
    }
}
