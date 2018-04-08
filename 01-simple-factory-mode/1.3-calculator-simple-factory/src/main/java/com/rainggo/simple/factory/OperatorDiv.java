package com.rainggo.simple.factory;

/**
 * 除法操作
 *
 * @author rainggo
 * @date 2018/4/4
 * @since 1.0.0
 */
public class OperatorDiv implements Operator {

    public String getResult(int firstInput, int secondInput) {
        return firstInput / secondInput + "";
    }
}
