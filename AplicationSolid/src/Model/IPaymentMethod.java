package Model;


public interface IPaymentMethod extends PaymentMethod {

    boolean processPayment(double amount);


    String getName();
}
