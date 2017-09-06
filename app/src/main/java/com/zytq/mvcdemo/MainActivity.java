package com.zytq.mvcdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zytq.mvcdemo.interfaces.ICalculator;
import com.zytq.mvcdemo.model.CalModel;
import com.zytq.mvcdemo.view.CaInputView;
import com.zytq.mvcdemo.view.CaOutputView;

//activity有生命周期,每个生命周期对应一个函数
//如：onCreate(),onStart(),onResume(),onPause(),onStop()等
//可以在对应的函数里编写代码
//一般会在Activity创建时开始编写代码
public class MainActivity extends AppCompatActivity implements CaInputView.InputHappend {

    private CaInputView civ;
    private CaOutputView cov;
    private ICalculator calModel;
    private String number = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //super 调用父类的构造方法
        //传入Bundle对象 saveInstanceState 用于信息的记录
        super.onCreate(savedInstanceState);
        //activity通过setContentView()方法加载视图
        //这里相当于this.setContentView(),this是指MainAcitivity实例本身
        //可以说Context使Activity具有"超级功能"
        //另外，java中的this始终指向实例本身，不会像js中的this那样作用域发生改变
        //所以，我们在构造其他类的时候，在其内部就不能使用一些方法了
        setContentView(R.layout.activity_main);

        civ = new CaInputView(this,this);
        cov = new CaOutputView(this);
        calModel = new CalModel();

    }


    @Override
    public void opearandIn(String operand) {
        //对首位为“0”作处理
        number = number.equals("0")?operand:number+operand;
        cov.outputData(number);//输入运算数时只需要显示

    }

    @Override
    public void opearateIn(String operate) {
        if (operate.equalsIgnoreCase("c")) {
            calModel.reset();
            number = "0";
            cov.outputData(number);
            return;
        }
        //输入符先将累计number压入栈，再讲自己压入栈
        calModel.pushOperand(number);
        double result = calModel.pushOperate(operate);
        if (result % 1d == 0d) {
            int tmp = Double.valueOf(result).intValue();
            cov.outputData(String.valueOf(tmp));
        } else {
            cov.outputData(String.valueOf(result));
        }
        number = "0";
    }
}
