package com.mayi.douyu.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.TransitionRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.util.Log;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mayi.douyu.R;
import com.mayi.douyu.entity.DYSearchDetails;
import com.mayi.douyu.mvpbase.MVPBaseActivity;
import com.mayi.douyu.ui.main.MainActivity;
import com.mayi.douyu.ui.main.douyu.LiveAdapter;
import com.mayi.douyu.ui.video.full.FullVideoActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者 by yugai 时间 17/1/19.
 * ＊ 邮箱 784787081@qq.com
 */

public class SearchActivity extends MVPBaseActivity<SearchContract.View, SearchPresenter> implements SearchContract.View {
    private static final String TAG = "SearchActivity";
    @Bind(R.id.search_background)
    View mSearchBackground;
    @Bind(R.id.search_view)
    EditText mSearchView;
    @Bind(R.id.searchback)
    ImageButton mSearchback;
    @Bind(R.id.searchback_container)
    FrameLayout mSearchbackContainer;
    @Bind(R.id.search_toolbar)
    FrameLayout mSearchToolbar;
    @Bind(R.id.rv_search)
    RecyclerView mRvSearch;
    @Bind(R.id.container)
    LinearLayout mContainer;

    private SparseArray<Transition> transitions = new SparseArray<>();
    SearchAdapter mSearchAdapter;
    @Override
    protected int getLayout() {
        return R.layout.activity_search;
    }

    @Override
    public void onRequestError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestEnd() {
        TransitionManager.beginDelayedTransition(mContainer, getTransition(R.transition.auto));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        mRvSearch.setLayoutManager(new LinearLayoutManager(this));
        mSearchAdapter=new SearchAdapter(this, new LiveAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String roomId) {
                Intent intent=new Intent(SearchActivity.this,FullVideoActivity.class);
                intent.putExtra(FullVideoActivity.ROOM_ID,roomId);
                intent.putExtra(FullVideoActivity.ROOM_PLATFORM,FullVideoActivity.DOUYU);
                startActivityForResult(intent,MainActivity.RC_SEARCH);
            }
        });
        mSearchAdapter.setCanLoad(false);
        mRvSearch.setAdapter(mSearchAdapter);
        mSearchView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == R.id.search || i == EditorInfo.IME_ACTION_SEARCH) {
                    mPresenter.search(mSearchView.getText().toString());
                }
                return false;
            }
        });
    }


    @OnClick(R.id.searchback)
    public void onClick() {
        finishAfterTransition();
    }

    @Override
    public void onSearchRequest(List<DYSearchDetails.RoomEntity> roomEntities) {
        mSearchAdapter.addRefreshData(roomEntities);
    }

    private Transition getTransition(@TransitionRes int transitionId) {
        Transition transition = transitions.get(transitionId);
        if (transition == null) {
            transition = TransitionInflater.from(this).inflateTransition(transitionId);
            transitions.put(transitionId, transition);
        }
        return transition;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult() called with: requestCode = [" + requestCode + "], resultCode = [" + resultCode + "], data = [" + data + "]");
        if (requestCode==MainActivity.RC_SEARCH&&resultCode==FullVideoActivity.RC_MULTI){
            setResult(resultCode);
            finishAfterTransition();
        }
    }
}
