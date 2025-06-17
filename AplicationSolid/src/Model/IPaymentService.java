
package Model;

public interface IPaymentService {

    boolean process(IPaymentMethod method, double amount);
}