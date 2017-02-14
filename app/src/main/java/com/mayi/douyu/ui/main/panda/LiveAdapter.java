package com.mayi.douyu.ui.main.panda;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.mayi.douyu.R;
import com.mayi.douyu.base.BaseRecyclerAdapter;
import com.mayi.douyu.entity.PDRoom;
import com.mayi.douyu.widget.CustomSizeImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者 by yugai 时间 16/11/3.
 * ＊ 邮箱 784787081@qq.com
 */

public class LiveAdapter extends BaseRecyclerAdapter<PDRoom.Room> {
    Context mContext;
    OnItemClickListener mOnItemClickListener;

    public LiveAdapter(Context context,OnItemClickListener onItemClickListener) {
        this.mContext = context;
        this.mOnItemClickListener=onItemClickListener;
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, final PDRoom.Room data, final int position) {
        Holder roomHolder= (Holder) holder;
        Glide.with(mContext).load(data.getPictures().getImg()).into(roomHolder.mIvRoomSrc);
        roomHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onItemClick(data.getId());
            }
        });
    }

    @Override
    public RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(mContext).inflate(R.layout.item_live, parent, false));
    }

    static class Holder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_room_src)
        CustomSizeImageView mIvRoomSrc;
        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(String roomId);
    }
}
