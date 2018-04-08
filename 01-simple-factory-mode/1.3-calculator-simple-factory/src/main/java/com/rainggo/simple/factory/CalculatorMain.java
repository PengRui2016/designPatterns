package com.rainggo.simple.factory;

import java.util.Scanner;

/**
 * 计算器主程序类
 *
 * @author rainggo
 * @date 2018/4/4
 * @since 1.0.0
 */
public class CalculatorMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("请输入第一个数：");
        int firstInput = Integer.valueOf(scanner.nextLine());

        System.out.print("请输入操作符：");
        String operatorInput = scanner.nextLine();

        System.out.print("请输入第二个数：");
        int secondInput = Integer.valueOf(scanner.nextLine());

        Operator operator = OperatorFactory.getOperator(operatorInput);
        System.out.println("计算结果：" + operator.getResult(firstInput, secondInput));
    }
}
