package com.rainggo.simple.factory.oop;

/**
 * 运算符操作类
 *
 * @author pengruib
 * @date 2018/4/4
 * @since 1.0.0
 */
public class Operation {

    private String type;

    public Operation(String type) {
        this.type = type;
    }

    public String getResult(int firstInput, int secondInput) {
        String result = "";
        switch (this.type) {
            case "+":
                result = firstInput + secondInput + "";
                break;
            case "-":
                result = firstInput - secondInput + "";
                break;
            case "*":
                result = firstInput * secondInput + "";
                break;
            case "/":
                result = firstInput / secondInput + "";
                break;
            default:
                break;
        }
        return result;
    }
}
