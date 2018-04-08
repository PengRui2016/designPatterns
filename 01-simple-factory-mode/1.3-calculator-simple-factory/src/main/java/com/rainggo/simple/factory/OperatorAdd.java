package com.rainggo.simple.factory;

/**
 * 加法操作
 *
 * @author rainggo
 * @date 2018/4/4
 * @since 1.0.0
 */
public class OperatorAdd implements Operator {

    public String getResult(int firstInput, int secondInput) {
        return firstInput + secondInput + "";
    }
}
