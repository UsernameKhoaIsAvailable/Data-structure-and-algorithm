package Interface.bai2;

import java.util.Date;

public class Main {
    public static void runTest(String type, boolean isMember, double expectedTotalExpense ) {
        Customer customer = new Customer("Khoa");
        customer.setMember(isMember);
        customer.setMemberType(type);

        Date date = new Date();
        Visit todayVisit = new Visit(customer, date);
        todayVisit.setProductExpense(5000);
        todayVisit.setServiceExpense(5000);
        assert todayVisit.getTotalExpense() == expectedTotalExpense;
    }

    public static void testMemberPremium() {
        runTest("Premium", true, 8500);
    }

    public static void testMemberGold() {
        runTest("Gold", true, 8750);
    }

    public static void testMemberSilver() {
        runTest("Silver", true, 9000);
    }

    public static void testNoMember() {
        runTest(null,false, 10000);
    }
    public static void main(String[] args) {
        testMemberPremium();
        testMemberGold();
        testMemberSilver();
        testNoMember();
    }
}
