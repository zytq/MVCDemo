package com.zytq.mvcdemo.view;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.zytq.mvcdemo.R;

/**
 * Created by Administrator on 2017/9/5 0005.
 */

public class CaOutputView {
    private TextView tv;

    //在CaOutPutView中，不需要通知Activity发生了什么
    //只需要显示结果即可
    public CaOutputView(Activity ac) {
        tv = (TextView) ac.findViewById(R.id.OutputText);
    }

    //定义CaOutputView拥有的方法
    //将输入的结果显示出来
    public  void outputData(String str) {
        tv.setText(str);
    }

}
