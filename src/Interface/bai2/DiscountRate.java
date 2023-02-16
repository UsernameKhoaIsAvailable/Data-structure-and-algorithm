package Interface.bai2;

public class DiscountRate {
    private static double serviceDiscountPremium = 0.2;
    private static double serviceDiscountGold = 0.15;
    private static double serviceDiscountSilver = 0.1;
    private static double productDiscountPremium = 0.1;
    private static double productDiscountGold = 0.1;
    private static double productDiscountSilver = 0.1;

    public static double getServiceDiscountRate(String type) {
        if(type.equalsIgnoreCase("premium")) {
            return serviceDiscountPremium;
            }
        else if(type.equalsIgnoreCase("gold")) {
            return serviceDiscountGold;
        }
        else if (type.equalsIgnoreCase("silver")) {
            return serviceDiscountSilver;
        }
        return 0;
    }

    public static double getProductDiscountRate(String type) {
        if(type.equalsIgnoreCase("premium")) {
            return productDiscountPremium;
        }
        else if(type.equalsIgnoreCase("gold")) {
            return productDiscountGold;
        }
        else if (type.equalsIgnoreCase("silver")) {
            return productDiscountSilver;
        }
        return 0;
    }
}
