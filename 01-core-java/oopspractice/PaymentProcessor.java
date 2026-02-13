public abstract class PaymentProcessor {
    public abstract String processPayment();
    private double amount;
    public PaymentProcessor(double amount){
        this.amount = amount;
    }
    public double getAmount(){
        return amount;
    }

}
