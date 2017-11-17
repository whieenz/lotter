package com.whieenz.lotter;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @InjectView(R.id.tv_red_fifth)
    TextView tv_red_fifth;
    @InjectView(R.id.tv_red_sixth)
    TextView tv_red_sixth;
    @InjectView(R.id.tv_red_fourth)
    TextView tv_red_fourth;
    @InjectView(R.id.tv_red_third)
    TextView tv_red_third;
    @InjectView(R.id.tv_red_second)
    TextView tv_red_second;
    @InjectView(R.id.tv_red_first)
    TextView tv_red_first;
    @InjectView(R.id.tv_blue)
    TextView tv_blue;
    @InjectView(R.id.tv_black)
    TextView tv_black;
    @InjectView(R.id.btn_start)
    Button btn_start;
    @InjectView(R.id.btn_get)
    Button btn_get;
    List<Integer> redSet;
    List<Integer> blueSet;
    List<Integer> resultSet;
    private boolean suspended;
    private int period = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        //去掉Activity上面的状态栏
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
//                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "早日中大奖！", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        ButterKnife.inject(this);
        btn_start.setOnClickListener(this);
        btn_get.setOnClickListener(this);
        initView();
        init();
    }

    private void initView() {
        tv_red_first.setOnClickListener(this);
        tv_red_second.setOnClickListener(this);
        tv_red_third.setOnClickListener(this);
        tv_red_fourth.setOnClickListener(this);
        tv_red_fifth.setOnClickListener(this);
        tv_red_sixth.setOnClickListener(this);
        tv_blue.setOnClickListener(this);
    }

    private void settext() {
        createAllBall();
        tv_red_first.setText("" + resultSet.get(0));
        tv_red_second.setText("" + resultSet.get(1));
        tv_red_third.setText("" + resultSet.get(2));
        tv_red_fourth.setText("" + resultSet.get(3));
        tv_red_fifth.setText("" + resultSet.get(4));
        tv_red_sixth.setText("" + resultSet.get(5));
        tv_blue.setText("" + resultSet.get(6));
    }

    private void createAllBall() {
        reSet();
        for (int i = 0; i < 7; i++) {
            if (i < 6) {
                resultSet.add(createOneRedBall());
            }
            if (i == 6) {
                resultSet.add(createBlueBall());
            }
        }
    }

    public void init() {
        redSet = new ArrayList<>();
        blueSet = new ArrayList<>();
        resultSet = new ArrayList<>();
        for (int i = 1; i < 34; i++) {
            redSet.add(i);
            if (i < 17) {
                blueSet.add(i);
            }
        }
    }

    public void reSet() {
        redSet.clear();
        blueSet.clear();
        resultSet.clear();
        for (int i = 1; i < 34; i++) {
            redSet.add(i);
            if (i < 17) {
                blueSet.add(i);
            }
        }
    }

    public int createOneRedBall() {
        int index = (int) (Math.random() * redSet.size());
        return redSet.remove(index);
    }

    public int createBlueBall() {
        int index = (int) (Math.random() * blueSet.size());
        return blueSet.remove(index);
    }


    private String getCreatedBall() {
        Integer blue = resultSet.remove(6);
        Collections.sort(resultSet);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < resultSet.size(); i++) {
            stringBuilder.append(resultSet.get(i));
            stringBuilder.append(" ");
        }
        stringBuilder.append("+");
        stringBuilder.append(blue);
        return stringBuilder.toString();
    }

    private String getSelectedBall() {
        StringBuilder stringBuilder = new StringBuilder();
        List<Integer> selectedRed = getSelected();
        Collections.sort(selectedRed);
        for (int i = 0; i < selectedRed.size(); i++) {
            stringBuilder.append(selectedRed.get(i));
            stringBuilder.append(" ");
        }
        stringBuilder.append("+");
        stringBuilder.append(Integer.valueOf(tv_blue.getText().toString()));
        return stringBuilder.toString();
    }

    private boolean isSame(String first, String second) {
        return first.equals(second);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_red_first:
                showSelectDialog(tv_red_first, true);
                break;
            case R.id.tv_red_second:
                showSelectDialog(tv_red_second, true);
                break;
            case R.id.tv_red_third:
                showSelectDialog(tv_red_third, true);
                break;
            case R.id.tv_red_fourth:
                showSelectDialog(tv_red_fourth, true);
                break;
            case R.id.tv_red_fifth:
                showSelectDialog(tv_red_fifth, true);
                break;
            case R.id.tv_red_sixth:
                showSelectDialog(tv_red_sixth, true);
                break;
            case R.id.tv_blue:
                showSelectDialog(tv_blue, false);
                break;
            case R.id.btn_get:
                if (!suspended) {
                    btn_get.setText("开始匹配");
                    stop();
                } else {
                    getSameBall();
                    btn_get.setText("暂停匹配");
                }
                break;
            case R.id.btn_start:
                startCreat();
                break;

        }
    }

    private void startCreat() {
        stop();
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(2000);//休眠3秒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        settext();
    }

    private void start() {
        if (suspended) {
            suspended = false;
        }
    }

    private void stop() {
        if (!suspended) {
            suspended = true;
        }
    }

    private void showSelectDialog(final TextView view, boolean b) {
        List<SelectData> datas = new ArrayList<>();
        List<Integer> selected = getSelected();
        String title;
        if (b) {
            title = "请选择红球";
            for (int i = 0; i < 33; i++) {
                SelectData data = new SelectData();
                data.setInfo(i + 1);
                if (selected.contains(i + 1)) {
                    data.setState(true);
                } else {
                    data.setState(false);
                }
                datas.add(data);
            }
        } else {
            title = "请选择蓝球";
            for (int i = 0; i < 16; i++) {
                SelectData data = new SelectData();
                data.setInfo(i + 1);
                Integer selectBlue = 0;
                if (!tv_blue.getText().toString().isEmpty())
                    selectBlue = Integer.valueOf(tv_blue.getText().toString());
                if (selectBlue == data.getInfo()) {
                    data.setState(true);
                } else {
                    data.setState(false);
                }
                datas.add(data);
            }
        }
        SelectDialog.Builder builder = new SelectDialog.Builder(this);
        builder.setTitle(title).setData(datas).setSelectListener(new OnLotterClickListener() {
            @Override
            public void onClick(int number) {
                view.setText(String.valueOf(number));
            }
        }).show();
    }

    public List<Integer> getSelected() {
        List<Integer> selected = new ArrayList<>();
        if (!tv_red_first.getText().toString().isEmpty()) {
            selected.add(Integer.valueOf(tv_red_first.getText().toString()));
        }
        if (!tv_red_second.getText().toString().isEmpty()) {
            selected.add(Integer.valueOf(tv_red_second.getText().toString()));
        }
        if (!tv_red_third.getText().toString().isEmpty()) {
            selected.add(Integer.valueOf(tv_red_third.getText().toString()));
        }
        if (!tv_red_fourth.getText().toString().isEmpty()) {
            selected.add(Integer.valueOf(tv_red_fourth.getText().toString()));
        }
        if (!tv_red_fifth.getText().toString().isEmpty()) {
            selected.add(Integer.valueOf(tv_red_fifth.getText().toString()));
        }
        if (!tv_red_sixth.getText().toString().isEmpty()) {
            selected.add(Integer.valueOf(tv_red_sixth.getText().toString()));
        }
        return selected;
    }

    private Handler handler = new Handler() {

        public void handleMessage(Message msg) {
            if (!Thread.currentThread().isInterrupted()) {
                switch (msg.what) {
                    case 0:
                        updateBlackText(msg.getData().getString("msg"), msg.getData().getInt("count"));
                        break;
                    default:
                        break;
                }
            }
        }
    };

    private void updateBlackText(String msg, int count) {
        StringBuilder builder = new StringBuilder();
        builder.append(StringUtil.getTime());
        builder.append(":");
        builder.append(count);
        builder.append(":");
        builder.append(msg);
        builder.append("\n");
        if (tv_black.getLineCount() > 1000) {
            tv_black.setText("");
        }
        tv_black.append(builder);
        int offset = tv_black.getLineCount() * tv_black.getLineHeight();
        if (offset > tv_black.getHeight()) {
            tv_black.scrollTo(0, offset - tv_black.getHeight());
        }
    }

    int count;

    private void getSameBall() {
        this.start();
        tv_black.setText("");
        count = 0;
        Thread thread = new Thread() {
            public void run() {
                String selected = getSelectedBall();
                String created;
                do {
                    synchronized (this) {
                        while (suspended) {
                            try {
                                wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    count++;
                    createAllBall();
                    created = getCreatedBall();
                    if (count % period == 0) {
                        setMessget(created, count);
                    }
                } while (!isSame(selected, created));
                setMessget(created, count);
            }
        };
        thread.start();
    }

    private void setMessget(String created, int count) {
        Message msg = new Message();
        msg.what = 0;
        Bundle bundle = new Bundle();
        bundle.putString("msg", created);
        bundle.putInt("count", count);
        msg.setData(bundle);
        handler.sendMessage(msg);
    }
}
