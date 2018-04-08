# 计算器的例子

## 以面向过程的方式做一个简单实现
在本例子中，将通过一个简单的实现方式，做一个简单的加减乘除的计算器。

CalcSimpleImpl类
```java
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
```
---
在上述代码中，很容易看出一些问题。

1. 所有的输入没有做相关校验，这里我们不多说，本文的重点不在这里。

2. 做除法的时候没有做被除数为0 的判断

3. 代码完全面向过程操作，可维护性极低，如果说以后加需求，需要加上一个算平方跟的计算方式，那么就需要添加switch里面的case，需要定位到具体算法的位置才可以实现。

下面开始针对上述问题进行修改（第1条暂不修改，本文针对第2、3条进行修改）

首先，我们需要一个面向对象的思维进行修改，将具体的计算方法进行封装，对调用方（main方法）进行隐藏，仅仅暴露出一个```getResult```方法。

Operation类
```java
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
```
CalculatorOop类
```java
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
```
---
经过上面的修改之后，我们的代码具备了面向对象的部分特性。
但是仍然不能算是一个优秀的代码，当我们需要新增某种操作符的运算时，我们需要对```Operation```类中的switch进行修改，存在更改到其他运算符的实现的风险。


针对简单实现中的第3条问题，在本例中，各个操作符算法之间是相互独立的。
而具体的运算算法的使用，由使用方的传入值所决定，我们可以进行如下的修改。

定义一个```Operator```操作接口，接口中有一个```getResult```方法，每一种运算操作都各自实现它。
而用户在使用时，只需要通过该接口进行操作，无需关心内部如何实现。
再定义一个```OperatorFactory```类，根据不同的输入值，获取不同的```Operator```操作实例。

OperatorFactory类
```java
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
```
Operator接口
```java
public interface Operator {

    String getResult(int firstInput, int secondInput);
}
```
四种运算操作类
```java
public class OperatorAdd implements Operator {
    public String getResult(int firstInput, int secondInput) {
        return firstInput + secondInput + "";
    }
}
public class OperatorSub implements Operator {
    public String getResult(int firstInput, int secondInput) {
        return firstInput - secondInput + "";
    }

}
public class OperatorMulti implements Operator {
    public String getResult(int firstInput, int secondInput) {
        return firstInput * secondInput + "";
    }
}
public class OperatorMulti implements Operator {
    public String getResult(int firstInput, int secondInput) {
        return firstInput * secondInput + "";
    }
}
```
CalculatorMain类
```java
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
```
---

好，至此一个简单工厂模式的计算器实现方法就编写完成了。
当我们需要新增一个新的操作符算法时，只需要新增加一个类，并在操作符工厂中，添加上对应的返回类型即可，将具体算法对客户端隐藏，也对工厂类隐藏，具体的运算算法只对各个操作符开放。