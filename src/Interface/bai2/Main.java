package Interface.bai2;

import java.util.Date;

public class Main {
    public static void test() {
        Customer customer = new Customer("Khoa");
        customer.setMember(true);
        customer.setMemberType("Premium");
        System.out.println(customer);
        Date date = new Date();
        Visit today = new Visit(customer, date);
        System.out.println(today.getName());
        System.out.println(today);
    }

    public static void main(String[] args) {
        test();
    }
}
