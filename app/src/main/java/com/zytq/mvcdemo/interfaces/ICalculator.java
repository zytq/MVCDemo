package com.zytq.mvcdemo.interfaces;

/**
 * 这里定义计算器模型的接口约束
 * 面向接口编程的好处之一，就是即使以后换了另一种算法模型
 * 只需要它继续实现这个接口，程序中使用模型处的代码就不用改变
 */

public interface ICalculator {
    //接收操作数输入
    public void pushOperand(String operand);


    //接收运算符的操作，定义返回类型为double
    public double pushOperate(String operate);

    //重置清零
    public void reset();


}
