package Interface.bai2;

import java.util.Date;

public class Visit {
    private Customer customer;
    private Date date;
    private double serviceExpense;
    private double productExpense;

    public Visit(Customer customer, Date date) {
        this.customer = customer;
        this.date = date;
    }

    public String getName() {
        return customer.getName();
    }

    public double getServiceExpense() {
        return serviceExpense;
    }

    public void setServiceExpense(double ex) {
        this.serviceExpense = ex;
    }

    public double getProductExpense() {
        return productExpense;
    }

    public void setProductExpense(double ex) {
        this.productExpense = ex;
    }

    public double getTotalExpense() {
        double discountAmount;
        if(!customer.isMember()) {
            discountAmount = 0;
        }
        else{
            discountAmount = DiscountRate.getProductDiscountRate(customer.getMemberType()) * productExpense + DiscountRate.getServiceDiscountRate(customer.getMemberType()) * serviceExpense;
        }
        return serviceExpense + productExpense - discountAmount;
    }

    @Override
    public String toString() {
        return customer + ", date: " + date + ", serviceExpense: " + serviceExpense + ", productExpense: " + productExpense;
    }
}
