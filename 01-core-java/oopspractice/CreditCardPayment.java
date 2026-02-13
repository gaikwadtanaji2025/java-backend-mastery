public class CreditCardPayment extends PaymentProcessor { 
    private String cardNumber;
    public CreditCardPayment(double amount,String cardNumber){
        super(amount);
        this.cardNumber=cardNumber;
    }
    @Override
    public String processPayment() {
        if(this.cardNumber.length()==16){
            return "credit card payment of "+this.getAmount()+" processed";
    }else{
        return "Invalid card number";
    }
}
}
