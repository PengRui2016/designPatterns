package com.rainggo.simple.factory.simple.impl;

import java.util.Scanner;

/**
 * 计算器demo的简单实现
 *
 * @author rainggo
 * @date 2018/4/4
 * @since 1.0.0
 */
public class CalcSimpleImpl {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("请输入第一个数：");
        int firstInput = Integer.valueOf(scanner.nextLine());

        System.out.print("请输入操作符：");
        String operator = scanner.nextLine();

        System.out.print("请输入第二个数：");
        int secondInput = Integer.valueOf(scanner.nextLine());

        String result = "";
        switch (operator) {
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

        System.out.println("计算结果：" + result);
    }
}
