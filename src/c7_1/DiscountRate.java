package c7_1;

public class DiscountRate {
    private static final double serviceDiscountPremium = 0.2;
    private static final double serviceDiscountGold = 0.15;
    private static final double serviceDiscountSilver = 0.1;
    private static final double productDiscountAll = 0.1; 

    public static double getServiceDiscountRate(String type) {
        switch (type) {
            case "Premium": return serviceDiscountPremium;
            case "Gold": return serviceDiscountGold;
            case "Silver": return serviceDiscountSilver;
            default: return 0;
        }
    }

    public static double getProductDiscountRate() {
        return productDiscountAll;
    }
}
