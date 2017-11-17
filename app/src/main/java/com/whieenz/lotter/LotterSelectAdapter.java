package com.whieenz.lotter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.whieenz.lotter.base.BaseRecyclerAdapter;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by heziwen on 2017/11/10.
 * 作用：
 */

public class LotterSelectAdapter extends BaseRecyclerAdapter<SelectData, LotterSelectAdapter.ViewHolder> {

    public LotterSelectAdapter(Context context, List<SelectData> datas) {
        super(context, datas);
    }

    OnLotterClickListener clickListener;

    public void setClickListener(OnLotterClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_cell_select, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickListener != null && !mDatas.get((Integer) view.getTag()).isState()) {
                    clickListener.onClick((Integer) view.getTag() + 1);
                }
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_text.setText(String.valueOf(mDatas.get(position).getInfo()));
        if (mDatas.get(position).isState()) {
            holder.tv_text.setBackgroundResource(R.drawable.gray_bg);
        } else {
            if (mDatas.size() > 17) {
                holder.tv_text.setBackgroundResource(R.drawable.red_bg);
            } else {
                holder.tv_text.setBackgroundResource(R.drawable.blue_bg);

            }
        }
        holder.itemView.setTag(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.tv_info)
        SquareTextView tv_text;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}
