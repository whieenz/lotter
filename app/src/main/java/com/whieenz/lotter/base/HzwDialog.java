package com.whieenz.lotter.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.whieenz.lotter.R;


/**
 * Created by heziwen on 2017/8/8.
 */

public class HzwDialog extends Dialog {

    protected TextView tv_title;

    protected LinearLayout close_layout;
    protected ImageButton imb_closeBtn;
    protected LinearLayout contentLayout;
    protected String title;
    protected int width;


    public HzwDialog(Context context) {
        super(context, R.style.hzwDialog);
        init(context);
        setLayoutParams();
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_base, null);
        tv_title = (TextView) view.findViewById(R.id.tv_dialog_title);
        imb_closeBtn = (ImageButton) view.findViewById(R.id.imb_base_close);
        close_layout = (LinearLayout) view.findViewById(R.id.id_hzwdialog_close_layout);
        contentLayout = (LinearLayout) view.findViewById(R.id.dialog_content);
        super.setContentView(view);
    }

    /**
     * 默认的对话框尺寸
     * height wrap_content
     * width  占手机屏幕的90%
     * gravity 居中显示
     *
     * @return
     */
    private void setLayoutParams() {
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        WindowManager manager = getWindow().getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        int width = outMetrics.widthPixels;
        lp.gravity = Gravity.CENTER;
        lp.width = (int) (width * 0.9);
        this.width = lp.width;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) close_layout.getLayoutParams();
        layoutParams.topMargin = (int) (width * 0.13);
        close_layout.setLayoutParams(layoutParams);
        this.getWindow().setAttributes(lp);
    }

    /**
     * 设置Dialog对话框尺寸大小
     *
     * @param X
     * @param Y
     */
    public void setSizeByScale(double X, double Y) {
        if (X < 0 || X > 1 || Y < 0 || Y > 1) {
            return;
        }
        Window window = this.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        WindowManager manager = getWindow().getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        int width = outMetrics.widthPixels;
        int height = outMetrics.heightPixels;
        lp.gravity = Gravity.CENTER;
        if (X == 1) {
            lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        } else {
            lp.width = (int) (width * X);
        }
        if (Y == 1) {
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        } else {
            lp.height = (int) (height * Y);
        }
        this.getWindow().setAttributes(lp);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    protected void addContentView(View child) {
        contentLayout.addView(child);
    }


    public void setTitle(String title) {
        this.title = title;
        tv_title.setText(title);
    }

}
