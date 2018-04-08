package com.rainggo.simple.factory;

/**
 * 减法操作
 *
 * @author rainggo
 * @date 2018/4/4
 * @since 1.0.0
 */
public class OperatorSub implements Operator {

    public String getResult(int firstInput, int secondInput) {
        return firstInput - secondInput + "";
    }

}
