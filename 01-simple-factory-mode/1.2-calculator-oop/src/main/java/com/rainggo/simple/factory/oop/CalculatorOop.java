package com.rainggo.simple.factory.oop;

import java.util.Scanner;

/**
 * 以面向对象的形式编写计算器的例子
 *
 * @author rainggo
 * @date 2018/4/4
 * @since 1.0.0
 */
public class CalculatorOop {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("请输入第一个数：");
        int firstInput = Integer.valueOf(scanner.nextLine());

        System.out.print("请输入操作符：");
        String operator = scanner.nextLine();

        System.out.print("请输入第二个数：");
        int secondInput = Integer.valueOf(scanner.nextLine());

        Operation operation = new Operation(operator);
        System.out.println("计算结果：" + operation.getResult(firstInput, secondInput));
    }
}
