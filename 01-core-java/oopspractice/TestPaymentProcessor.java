public class TestPaymentProcessor {
    public static void main(String[] args) {
        PaymentProcessor [] payments;
        PaymentProcessor validCreditCardPayment=new CreditCardPayment(1500.0,"9837865576656234");
        PaymentProcessor invalidCreditCardPayment=new CreditCardPayment(2000.0,"98378655");
        PaymentProcessor validUpiPayment=new UpiPayment(500.0,"123@oksbi");
        PaymentProcessor invalidUpiPayment=new UpiPayment(800.0,"23@76575");
        payments=new PaymentProcessor[]{validCreditCardPayment,invalidCreditCardPayment,validUpiPayment,invalidUpiPayment};
        for(PaymentProcessor payment:payments){
            System.out.println(payment.processPayment());
        }
    }
}
