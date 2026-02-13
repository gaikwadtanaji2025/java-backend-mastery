import java.util.regex.Pattern;

public class UpiPayment extends PaymentProcessor {
    private String upiId;
    private static final Pattern UPI_PATTERN =Pattern.compile("^[a-zA-Z0-9._-]+@[a-zA-Z]+$");

    public boolean isValid(){
        return this.upiId!=null&& UPI_PATTERN.matcher(this.upiId).matches();
       
    }
    public UpiPayment(double amount,String upiId){
        super(amount);
        this.upiId=upiId;
    }
    @Override
    public String processPayment() {
    if(this.isValid()){
        return "UPI Payment of "+this.getAmount()+" processed";
    }else{
        return "Invalid UPI ID";
    }
    }
}
