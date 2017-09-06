package com.zytq.mvcdemo.view;

import android.app.Activity;
import android.content.res.Resources;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/5 0005.
 * 用户输入的显示的view
 */

public class CaInputView {
    //首先定义好"通信协议"，也就是接口

    public interface InputHappend {
        //当用户输入运算数时，通知它输入“operand”这个数字
        public void opearandIn(String operand);
        //当用户输入操作符，通知他输入“operate”这个运算符
        public void opearateIn(String operate);
    }
    private List<Button> operands;//存放操作数button
    private List<Button> operates;//存放运算符button

    //保存这个“联系方式”，不需要指明dlg的实质，只要定义它实现了CaInputView.InputHappend这个接口
    //这个就是面向接口编程的概念
    private CaInputView.InputHappend dlg;

    //在构造函数中定义CaInputView需要的两个参数
    //ac是Activity类，用来使用Activity提供的方法
    //delegate 是实现了CaInputView.InputHappend 的对象，我不知道它是谁，只要适当时刻通知它既可
    public CaInputView(Activity ac , CaInputView.InputHappend delegate) {
        dlg = delegate;
        operands = new ArrayList<>();
        operates = new ArrayList<>();

        Resources res = ac.getResources();
        for (int i = 0; i < 9;i++) {
            if (i<=5) {
                int id_operate = res.getIdentifier("operate"+i,"id",ac.getPackageName());
                Button btn_operate = (Button) ac.findViewById(id_operate);
                operates.add(btn_operate);
            }
            int id_operand = res.getIdentifier("Operand"+i,"id",ac.getPackageName());
            Button btn_operand = (Button) ac.findViewById(id_operand);
            operands.add(btn_operand);
        }
        //为操作数添加事件，当用户触发时，通知dlg,发生operandIn
        for (Button btn : operands) {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Button cli_btn = (Button) view;
                    String text = cli_btn.getText().toString();
                    dlg.opearandIn(text);
                }
            });
        }

        //为操作符添加事件，当用户触发时，通知dlg,发生operateIn
        for (Button btn : operates) {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Button cli_btn = (Button) view;
                    String text = cli_btn.getText().toString();
                    dlg.opearateIn(text);
                }
            });
        }

    }


}
