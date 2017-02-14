package com.mayi.douyu.ui.main.huya;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.mayi.douyu.R;
import com.mayi.douyu.base.BaseRecyclerAdapter;
import com.mayi.douyu.entity.HYRoom;
import com.mayi.douyu.widget.CustomSizeImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者 by yugai 时间 17/1/18.
 * ＊ 邮箱 784787081@qq.com
 */

public class LiveAdapter extends BaseRecyclerAdapter<HYRoom>{
    private static final String TAG = "LiveAdapter";
    Context mContext;
    OnItemClickListener mOnItemClickListener;

    public LiveAdapter(Context context, OnItemClickListener onItemClickListener) {
        mContext = context;
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, final HYRoom data, int position) {
        Holder roomHolder= (Holder) holder;
        Log.i(TAG, "onBindItemViewHolder: "+data);
        Glide.with(mContext).load(data.getSnapshot()).into(roomHolder.mIvRoomSrc);
        roomHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener!=null)mOnItemClickListener.onItemClick(data.getSid(),data.getSubSid());
            }
        });
    }

    @Override
    public RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(mContext).inflate(R.layout.item_live, parent, false));
    }

    static class Holder extends RecyclerView.ViewHolder{
        @Bind(R.id.iv_room_src)
        CustomSizeImageView mIvRoomSrc;
        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    public interface OnItemClickListener {
        void onItemClick(int sid,long subsid);
    }
}
