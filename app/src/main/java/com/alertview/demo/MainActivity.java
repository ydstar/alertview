package com.alertview.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.alertview.lib.AlertView;
import com.alertview.lib.OnDismissListener;
import com.alertview.lib.OnItemClickListener;

public class MainActivity extends AppCompatActivity
        implements OnItemClickListener, OnDismissListener {

    private AlertView mAlertView;//避免创建重复View，先创建View，然后需要的时候show出来，推荐这个做法
    private AlertView mAlertViewExt;//窗口拓展例子
    private EditText etName;//拓展View内容
    private InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        //拓展窗口
        mAlertViewExt = new AlertView("提示",
                "请完善你的个人资料！",
                "取消",
                null,
                new String[]{"完成"},
                this,
                AlertView.Style.Alert,
                this);
        ViewGroup extView = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.alertext_form, null);
        etName =  extView.findViewById(R.id.etName);
        etName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focus) {
                //输入框出来则往上移动
                boolean isOpen = imm.isActive();
                mAlertViewExt.setMarginBottom(isOpen && focus ? 120 : 0);
                System.out.println(isOpen);
            }
        });
        mAlertViewExt.addExtView(extView);
    }

    /**
     * 普通的取消确定弹框
     */
    public void alertShow1(View view) {
        //避免创建重复View，先创建View，然后需要的时候show出来，推荐这个做法
        if (mAlertView == null) {
            mAlertView = new AlertView("标题",
                    "内容",
                    "取消",
                    new String[]{"确定"},
                    null,
                    this,
                    AlertView.Style.Alert,
                    this)
                    .setCancelable(true)
                    .setOnDismissListener(this);
        }

        mAlertView.show();
    }

    /**
     * 标题内容框
     */
    public void alertShow2(View view) {
        new AlertView("标题",
                "内容",
                null,
                new String[]{"确定"},
                null,
                this,
                AlertView.Style.Alert,
                this)
                .show();
    }

    /**
     * sheet列表框
     */
    public void alertShow3(View view) {
        new AlertView(null,
                null,
                null,
                new String[]{"高亮按钮1", "高亮按钮2", "高亮按钮3"},
                new String[]{"其他按钮1", "其他按钮2", "其他按钮3",
                        "其他按钮4", "其他按钮5", "其他按钮6",
                        "其他按钮7", "其他按钮8", "其他按钮9",
                        "其他按钮10", "其他按钮11", "其他按钮12"},
                this,
                AlertView.Style.Alert,
                this).show();
    }

    /**
     * 从下面弹出的sheet列表框
     */
    public void alertShow4(View view) {
        new AlertView("标题",
                null,
                "取消",
                new String[]{"高亮按钮1"},
                new String[]{"其他按钮1", "其他按钮2", "其他按钮3"},
                this,
                AlertView.Style.ActionSheet,
                this).show();
    }

    /**
     * 从下面弹出的sheet标题内容框
     */
    public void alertShow5(View view) {
        new AlertView("标题",
                "内容",
                "取消",
                null,
                null,
                this,
                AlertView.Style.ActionSheet,
                this)
                .setCancelable(true)
                .show();
    }

    /**
     * 高仿ios图片选择框
     */
    public void alertShow6(View view) {
        new AlertView("上传头像",
                null,
                "取消",
                null,
                new String[]{"拍照", "从相册中选择"},
                this,
                AlertView.Style.ActionSheet,
                this)
                .show();
    }

    /**
     * 窗口拓展点这里
     */
    public void alertShowExt(View view) {
        mAlertViewExt.show();
    }

    private void closeKeyboard() {
        //关闭软键盘
        imm.hideSoftInputFromWindow(etName.getWindowToken(), 0);
        //恢复位置
        mAlertViewExt.setMarginBottom(0);
    }

    @Override
    public void onItemClick(Object o, int position) {
        closeKeyboard();
        //判断是否是拓展窗口View，而且点击的是非取消按钮
        if (o == mAlertViewExt && position != AlertView.CANCELPOSITION) {
            String name = etName.getText().toString();
            if (name.isEmpty()) {
                Toast.makeText(this, "啥都没填呢", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "hello," + name, Toast.LENGTH_SHORT).show();
            }

            return;
        }
        Toast.makeText(this, "点击了第" + position + "个", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDismiss(Object o) {
        closeKeyboard();
        Toast.makeText(this, "消失了", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if (mAlertView != null && mAlertView.isShowing()) {
                mAlertView.dismiss();
                return false;
            }
        }

        return super.onKeyDown(keyCode, event);

    }
}
