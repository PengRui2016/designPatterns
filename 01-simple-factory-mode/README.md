# 计算器的例子

简单工厂模式，又称为静态工厂方法模式，它属于类创建型模式（同属于创建型模式的还有工厂方法模式，抽象工厂模式，单例模式，建造者模式）。

在简单工厂模式中，可以根据参数的不同返回不同类的实例。

简单工厂模式专门定义一个类来负责创建其他类的实例，被创建的实例通常都具有共同的父类。

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
在上述代码中，很容易看出一些问题。

1. 所有的输入没有做相关校验，这里我们不多说，本文的重点不在这里。

2. 做除法的时候没有做被除数为0 的判断

3. 代码完全面向过程操作，可维护性极低，如果说以后加需求，需要加上一个算平方跟的计算方式，那么就需要添加switch里面的case，需要定位到具体算法的位置才可以实现。

---
## 以面向对象的方式修改实现
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
经过上面的修改之后，我们的代码具备了面向对象的部分特性。
但是仍然不能算是一个优秀的代码，当我们需要新增某种操作符的运算时，我们需要对```Operation```类中的switch进行修改，存在更改到其他运算符的实现的风险。

---
## 使用简单工厂模式进行修改

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
## 小结

好，至此一个简单工厂模式的计算器实现方法就编写完成了。

当我们需要新增一个新的操作符算法时，只需要新增加一个类，并在操作符工厂中，添加上对应的返回类型即可，将具体算法对客户端隐藏，也对工厂类隐藏，具体的运算算法只对各个操作符开放。

## 模式优点

* 工厂类含有必要的判断逻辑，可以决定在什么时候创建哪一个产品类的实例，客户端可以免除直接创建产品对象的责任，而仅仅“消费”产品；简单工厂模式通过这种做法实现了对责任的分割，它提供了专门的工厂类用于创建对象。

* 客户端无须知道所创建的具体产品类的类名，只需要知道具体产品类所对应的参数即可，对于一些复杂的类名，通过简单工厂模式可以减少使用者的记忆量。

* 通过引入配置文件，可以在不修改任何客户端代码的情况下更换和增加新的具体产品类，在一定程度上提高了系统的灵活性。

* 当需要引入新的产品是不需要修改客户端的代码，只需要添加相应的产品类并修改工厂类就可以了，所以说从产品的角度上简单工厂模式是符合“开-闭”原则的。

## 模式缺点

* 由于工厂类集中了所有产品创建逻辑，工厂类一般被我们称作“全能类”或者“上帝类”，因为所有的产品创建他都能完成，这看似是好事，但仔细想想是有问题的。比如全国上下所有的事情都有国家主义一个人干会不会有问题，当然有！一旦不能正常工作，整个系统都要受到影响。

* 使用简单工厂模式将会增加系统中类的个数，在一定程序上增加了系统的复杂度和理解难度。

* 系统扩展困难，一旦添加新产品就不得不修改工厂逻辑，在产品类型较多时，有可能造成工厂逻辑过于复杂，不利于系统的扩展和维护。所以说从工厂的角度来说简单工厂模式是不符合“开-闭”原则的。

* 简单工厂模式由于使用了静态工厂方法，造成工厂角色无法形成基于继承的等级结构。

## 适用场景

* 工厂类负责创建的对象比较少：由于创建的对象较少，不会造成工厂方法中的业务逻辑太过复杂。

* 客户端只知道传入工厂类的参数，对于如何创建对象不关心：客户端既不需要关心创建细节，甚至连类名都不需要记住，只需要知道类型所对应的参数。