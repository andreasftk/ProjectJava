public class FalseAmountException extends ProjectExceptions{
    public void getExceptionInfo(){System.out.println("The amount you asked for exceeds the maximum already available\n\t\t\tor\nThe amount asked for has a negative value.");}
}