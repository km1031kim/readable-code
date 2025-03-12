package cleancode.studycafe.asis.discount;

import cleancode.studycafe.asis.model.StudyCafeLockerPass;
import cleancode.studycafe.asis.model.StudyCafePass;

public class StudyCafePricingService {

    private StudyCafePricingService() {
    }

    public static int getDiscountPriceFromPass(StudyCafePass studyCafePass) {
        double discountRate = studyCafePass.getDiscountRate();
        return (int) (studyCafePass.getPrice() * discountRate);
    }

    public static int getTotalPrice(StudyCafePass selectedPass, StudyCafeLockerPass lockerPass) {
        return selectedPass.getPrice() - getDiscountPriceFromPass(selectedPass) + (lockerPass != null ? lockerPass.getPrice() : 0);
    }
}
