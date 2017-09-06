package com.zytq.mvcdemo.model;

import com.zytq.mvcdemo.interfaces.ICalculator;

import java.util.Stack;

/**
 * Created by Administrator on 2017/9/5 0005.
 */

public class CalModel implements ICalculator{

    //计算器算法

    public static double popupOffStack(Stack<String> stack) {
        double result = 0;
        //从栈中获取运算数，由于运算数以字符串的形式存储
        //在做计算时需要转型为计算类型，这里用double
        double operand = Double.valueOf(stack.pop());
        //从栈中获取元素后，假如栈已经空了，直接返回operand
        if (stack.isEmpty()) {
            return operand;
        }
        //继续从栈中获取操作符，根据操作符继续递归调用
        String operate = stack.pop();
        if (operate.equals("+")) {
            result = CalModel.popupOffStack(stack) + operand;
        } else if (operate.equals("-")) {
            result = CalModel.popupOffStack(stack) - operand;
        } else if (operate.equals("*")) {
            result = CalModel.popupOffStack(stack) * operand;
        } else if (operate.equals("/")) {
            result = CalModel.popupOffStack(stack) / operand;
        }
        return result;
    }

    //记录输入的运算符和操作符的栈
    private Stack<String> dataStack = new Stack<String>();

    //是否在输入操作符，对连续输入操作符做替换
    private boolean isOperate = false;



    @Override
    public void pushOperand(String operand) {
        //当输入运算数直接压入栈不会触发计算
        isOperate = false;
        dataStack.add(operand);

    }

    @Override
    public double pushOperate(String operate) {
        //当操作符是“+ - * /”，输入会继续
        //所以copy一份当前栈的数据作为参数传入进行计算并返回
        //当操作符是=号，直接使用当前栈作为参数
        //因为=号意味着需要重新开始
        double result = 0;
        if (isOperate) {
            dataStack.pop();
        }//如果前一个是操作符,则将他替换
        if (operate.equals("=")) {
            result = CalModel.popupOffStack(dataStack);
        } else {
            Stack<String> tmpStack = (Stack<String>) dataStack.clone();
            result = CalModel.popupOffStack(tmpStack);
            //计算完后把输入的运算符压入栈
            dataStack.add(operate);
            isOperate = true;
        }
        return result;
    }

    @Override
    public void reset() {
        dataStack.removeAllElements();
        isOperate = false;
    }
}
