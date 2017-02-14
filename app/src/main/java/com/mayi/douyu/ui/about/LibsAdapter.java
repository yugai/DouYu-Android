package com.mayi.douyu.ui.about;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mayi.douyu.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 作者 by yugai 时间 17/2/6.
 * ＊ 邮箱 784787081@qq.com
 */

public class LibsAdapter extends RecyclerView.Adapter<LibsAdapter.Holder> {
    private static final Library[] libs = {
            new Library("Bilibili",
                    "ijkplayer",
                    "https://github.com/Bilibili/ijkplayer",
                    "https://avatars1.githubusercontent.com/u/12002442?v=3&s=200"),
            new Library("Bilibili",
                    "DanmakuFlameMaster",
                    "https://github.com/Bilibili/DanmakuFlameMaster",
                    "https://avatars1.githubusercontent.com/u/12002442?v=3&s=200"),
            new Library("ReactiveX",
                    "RxJava",
                    "https://github.com/ReactiveX/RxJava",
                    "https://avatars1.githubusercontent.com/u/6407041?v=3&s=200"),
            new Library("square",
                    "retrofit",
                    "https://github.com/square/retrofit",
                    "https://avatars2.githubusercontent.com/u/82592?v=3&s=200"),
            new Library("bumptech",
                    "Glide",
                    "https://github.com/bumptech/glide",
                    "https://avatars.githubusercontent.com/u/423539")};

    Context mContext;


    public LibsAdapter(Context context) {
        mContext = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(mContext).inflate(R.layout.item_library, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        final Library library = libs[position];
        Glide.with(mContext).load(library.imageUrl).into(holder.mLibraryImage);
        holder.mLibraryName.setText(library.name);
        holder.mLibraryDescription.setText(library.description);
        holder.mLibraryLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(library.link));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return libs.length;
    }

    static class Holder extends RecyclerView.ViewHolder {
        @Bind(R.id.library_image)
        CircleImageView mLibraryImage;
        @Bind(R.id.library_name)
        TextView mLibraryName;
        @Bind(R.id.library_description)
        TextView mLibraryDescription;
        @Bind(R.id.library_link)
        Button mLibraryLink;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private static class Library {
        final String name;
        final String link;
        final String description;
        final String imageUrl;

        Library(String name, String description, String link, String imageUrl) {
            this.name = name;
            this.description = description;
            this.link = link;
            this.imageUrl = imageUrl;
        }
    }
}
