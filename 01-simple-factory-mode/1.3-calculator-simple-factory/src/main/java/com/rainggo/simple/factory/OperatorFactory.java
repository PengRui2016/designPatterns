package com.rainggo.simple.factory;

/**
 * 操作符工厂类
 *
 * @author rainggo
 * @date 2018/4/4
 * @since 1.0.0
 */
public class OperatorFactory {

    public static Operator getOperator(String type) {
        Operator operator = null;
        switch (type) {
            case "+":
                operator = new OperatorAdd();
                break;
            case "-":
                operator = new OperatorSub();
                break;
            case "*":
                operator = new OperatorMulti();
                break;
            case "/":
                operator = new OperatorDiv();
                break;
            default:
                break;
        }

        // 可能会存在为没有定义的操作符，使得operator为null，但是不在本次文章的重点中

        return operator;
    }
}
