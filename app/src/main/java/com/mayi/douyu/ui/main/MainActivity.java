package com.mayi.douyu.ui.main;

import android.app.ActivityOptions;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;
import com.bumptech.glide.Glide;
import com.mayi.douyu.R;
import com.mayi.douyu.api.ApiManager;
import com.mayi.douyu.base.BaseActivity;
import com.mayi.douyu.entity.DYClassify;
import com.mayi.douyu.entity.HYClassify;
import com.mayi.douyu.entity.PDClassify;
import com.mayi.douyu.mvpbase.MVPBaseFragment;
import com.mayi.douyu.service.FloatingPlayService;
import com.mayi.douyu.ui.about.AboutActivity;
import com.mayi.douyu.ui.search.SearchActivity;
import com.mayi.douyu.ui.video.full.FullVideoActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func3;

public class MainActivity extends BaseActivity
        implements AHBottomNavigation.OnTabSelectedListener {
    public static int OVERLAY_PERMISSION_REQ_CODE = 1234;
    public static int OPEN_ROOM_REQ_CODE = 2345;
    private static final String TAG = "MainActivity";
    public static final int RC_SEARCH = 0;
    FloatingPlayService.MyBinder mBinder;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.view_pager)
    AHBottomNavigationViewPager mViewPager;
    @Bind(R.id.floating_action_button)
    FloatingActionButton mFloatingActionButton;
    @Bind(R.id.bottom_navigation)
    AHBottomNavigation mBottomNavigation;
    @Bind(R.id.rv_classify)
    RecyclerView mRvClassify;

    private int[] tabColors;
    private AHBottomNavigationAdapter navigationAdapter;

    private MenuAdapter slideAdapter;

    private MVPBaseFragment currentFragment;
    private MainFragmentAdapter mAdapter;
    private boolean windowPermission = false;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBinder = (FloatingPlayService.MyBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected");
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setToolbar();
        setBottomNavigation();
        setClassifyMenu();
        askForPermission();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initFloatService();

    }

    /**
     * 设置侧滑菜单
     */
    private void setClassifyMenu() {
        mRvClassify.setLayoutManager(new LinearLayoutManager(this));
        Observable.zip(
                ApiManager.getInstance().getDYClassify(), ApiManager.getInstance().getPDClassify(), ApiManager.getInstance().getHYClassify()
                , new Func3<List<DYClassify>, List<PDClassify>, List<HYClassify>, List<List>>() {
                    @Override
                    public List<List> call(List<DYClassify> dyClassifies, List<PDClassify> pdClassifies, List<HYClassify> hyClassifies) {
                        Log.d(TAG, "call() called with: dyClassifies = [" + dyClassifies.size() + "], pdClassifies = [" + pdClassifies.size() + "], hyClassifies = [" + hyClassifies.size() + "]");
                        List<List> datas = new ArrayList();
                        datas.add(dyClassifies);
                        datas.add(pdClassifies);
                        datas.add(hyClassifies);
                        return datas;
                    }
                }
        ).subscribe(new Action1<List>() {
            @Override
            public void call(List list) {
                List<DYClassify> dyClassifies = (List<DYClassify>) list.get(0);
                List<PDClassify> pdClassifies = (List<PDClassify>) list.get(1);
                List<HYClassify> hyClassifies = (List<HYClassify>) list.get(2);
                slideAdapter = new MenuAdapter(MainActivity.this, dyClassifies, pdClassifies, hyClassifies);
                mRvClassify.setAdapter(slideAdapter);
            }
        });
    }

    /**
     * 初始化悬浮框服务
     */
    private void initFloatService() {
        Intent serviceIntent = new Intent(this, FloatingPlayService.class);
        bindService(serviceIntent, mConnection, BIND_AUTO_CREATE);
    }

    /**
     * 底部菜单初始化
     */
    private void setBottomNavigation() {
        tabColors = getApplicationContext().getResources().getIntArray(R.array.tab_colors);
        navigationAdapter = new AHBottomNavigationAdapter(this, R.menu.activity_main_bottom);
        navigationAdapter.setupWithBottomNavigation(mBottomNavigation, tabColors);
        mBottomNavigation.setOnNavigationPositionListener(new AHBottomNavigation.OnNavigationPositionListener() {
            @Override
            public void onPositionChange(int y) {
                Log.d(TAG, "BottomNavigation Position: " + y);
            }
        });

        mViewPager.setOffscreenPageLimit(3);
        mAdapter = new MainFragmentAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        currentFragment = mAdapter.getCurrentFragment();

        mBottomNavigation.setOnTabSelectedListener(this);
    }

    private void setToolbar() {
        setSupportActionBar(mToolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

    }


    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_about:
                startActivity(new Intent(this, AboutActivity.class));
                break;
            case R.id.menu_search:
                View searchMenuView = mToolbar.findViewById(R.id.menu_search);
                Bundle options = ActivityOptions.makeSceneTransitionAnimation(this, searchMenuView,
                        getString(R.string.transition_search_back)).toBundle();
                startActivityForResult(new Intent(this, SearchActivity.class), RC_SEARCH, options);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult() called with: requestCode = [" + requestCode + "], resultCode = [" + resultCode + "], data = [" + data + "]");
        if (requestCode == OVERLAY_PERMISSION_REQ_CODE) {
            if (!Settings.canDrawOverlays(this)) {
                Toast.makeText(this, "权限授予失败，无法开启悬浮窗", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "权限授予成功！", Toast.LENGTH_SHORT).show();
                //记录权限
            }

        } else if (requestCode == OPEN_ROOM_REQ_CODE || requestCode == RC_SEARCH) {
            if (resultCode == FullVideoActivity.RC_MULTI) {
                if (data != null) {
                    mBinder.startPlay(data.getStringExtra(FullVideoActivity.ROOM_ID));
                }
            }
        }
    }

    @Override
    public boolean onTabSelected(int position, boolean wasSelected) {
        if (currentFragment == null) {
            currentFragment = mAdapter.getCurrentFragment();
        }

        if (wasSelected) {
            Log.i(TAG, "onTabSelected: 需要刷新页面");
            return true;
        }

        if (currentFragment != null) {
            currentFragment.willBeHidden();
        }

        if (slideAdapter != null) {
            Log.i(TAG, "onTabSelected: " + position);
            switch (position) {
                case 0:
                    slideAdapter.notifyPlatfrom(FullVideoActivity.DOUYU);
                    break;
                case 1:
                    slideAdapter.notifyPlatfrom(FullVideoActivity.PADAN);
                    break;
                case 2:
                    slideAdapter.notifyPlatfrom(FullVideoActivity.HUYA);
                    break;
            }
        }


        mViewPager.setCurrentItem(position, false);
        currentFragment = mAdapter.getCurrentFragment();
        currentFragment.willBeDisplayed();
        return true;
    }

    /**
     * 请求用户给予悬浮窗的权限
     */
    public void askForPermission() {
        if (!Settings.canDrawOverlays(this)) {
            Toast.makeText(this, "当前无权限，请授权！", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE);
        } else {
            //这里记录权限
            windowPermission = true;
        }
    }

    @OnClick(R.id.floating_action_button)
    public void onClick() {
        startActivity(new Intent(this, AboutActivity.class));
    }

    static class MenuAdapter extends RecyclerView.Adapter {
        Context context;
        List<DYClassify> mDYClassifies;
        List<PDClassify> mPDClassifies;
        List<HYClassify> mHYClassifies;
        @FullVideoActivity.Platfrom
        int platfrom = FullVideoActivity.DOUYU;

        public MenuAdapter(Context context, List<DYClassify> DYClassifies, List<PDClassify> PDClassifies, List<HYClassify> HYClassifies) {
            this.context = context;
            mDYClassifies = DYClassifies;
            mPDClassifies = PDClassifies;
            mHYClassifies = HYClassifies;
        }

        public void notifyPlatfrom(@FullVideoActivity.Platfrom int platfrom) {
            this.platfrom = platfrom;
            notifyDataSetChanged();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new Holder(LayoutInflater.from(context).inflate(R.layout.item_classify, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
            Holder holder = (Holder) viewHolder;
            switch (platfrom) {
                case FullVideoActivity.DOUYU:
                    DYClassify classify1 = mDYClassifies.get(position);
                    Glide.with(context).load(classify1.getIcon_url()).into(holder.mIvPic);
                    holder.mTvName.setText(classify1.getTag_name());
                    break;
                case FullVideoActivity.PADAN:
                    PDClassify classify2 = mPDClassifies.get(position);
                    Glide.with(context).load(classify2.getImg()).into(holder.mIvPic);
                    holder.mTvName.setText(classify2.getCname());
                    break;
                case FullVideoActivity.HUYA:
                    HYClassify classify3 = mHYClassifies.get(position);
                    Glide.with(context).load(classify3.getImgSmallUrl()).into(holder.mIvPic);
                    holder.mTvName.setText(classify3.getGameName());
                    break;
            }
        }

        @Override
        public int getItemCount() {
            switch (platfrom) {
                case FullVideoActivity.DOUYU:
                    return mDYClassifies.size();
                case FullVideoActivity.PADAN:
                    return mPDClassifies.size();
                case FullVideoActivity.HUYA:
                    return mHYClassifies.size();
            }
            return 0;
        }

        static class Holder extends RecyclerView.ViewHolder {
            @Bind(R.id.iv_pic)
            CircleImageView mIvPic;
            @Bind(R.id.tv_name)
            TextView mTvName;

            public Holder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }
}
