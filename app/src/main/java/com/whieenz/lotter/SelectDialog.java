package com.whieenz.lotter;

/**
 * Created by heziwen on 2017/8/23.
 * 传递退单错误信息显示Dialog
 */

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.whieenz.lotter.base.HzwDialog;

import java.util.List;

public class SelectDialog extends HzwDialog {

    public SelectDialog(Context context) {
        super(context);
    }

    public static class Builder {
        List<SelectData> data;
        Context context;
        SelectDialog dialog;
        RecyclerView listView;
        LotterSelectAdapter adapter;
        OnLotterClickListener clickListener;
        View layout;
        private String title;

        public Builder(Context context) {
            this.context = context;
            dialog = new SelectDialog(context);
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = inflater.inflate(R.layout.select_list_layout, null);
            listView = layout.findViewById(R.id.list);
            dialog.addContentView(layout);
        }

        public Builder setData(List<SelectData> data) {
            if (data != null) {
                this.data = data;
            }
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setSelectListener(OnLotterClickListener listener) {
            this.clickListener = listener;
            return this;
        }

        /**
         * 单按钮对话框和双按钮对话框的公共部分在这里设置
         */
        private SelectDialog create() {
            dialog.tv_title.setVisibility(View.VISIBLE);
            dialog.tv_title.setText(title);
            dialog.imb_closeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            adapter = new LotterSelectAdapter(context, data);
            adapter.setClickListener(new OnLotterClickListener() {
                @Override
                public void onClick(int number) {
                    if (clickListener != null){
                        clickListener.onClick(number);
                        dialog.dismiss();
                    }
                }
            });
            listView.setAdapter(adapter);
            int px = DensityUtil.dip2px(context, 50);
            int padding = DensityUtil.dip2px(context, 30);
            int items = (dialog.width - padding) / px;
            GridLayoutManager layoutManage = new GridLayoutManager(context, items);
            listView.setLayoutManager(layoutManage);
            int paddingBottom = DensityUtil.dip2px(context, 10);
            SpaceItemDecoration decoration = new SpaceItemDecoration(paddingBottom);
            listView.addItemDecoration(decoration);
            //用户可以点击手机Back键取消对话框显示
            dialog.setCancelable(true);
            //用户不能通过点击对话框之外的地方取消对话框显示
            dialog.setCanceledOnTouchOutside(false);
            dialog.setSizeByScale(0.9, 1);
            return dialog;
        }

        public SelectDialog show() {
            create();
            dialog.show();
            return dialog;
        }

    }
}
