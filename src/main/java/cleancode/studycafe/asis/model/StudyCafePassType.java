package cleancode.studycafe.asis.model;

public enum StudyCafePassType implements StudyCafePassTypeShowable {

    HOURLY("시간 단위 이용권") {
        @Override
        public String showDisplayMessage(int duration, int price) {
            return String.format("%s시간권 - %d원", duration, price);
        }
    },

    WEEKLY("주 단위 이용권") {
        @Override
        public String showDisplayMessage(int duration, int price) {
            return String.format("%s주권 - %d원", duration, price);
        }
    },

    FIXED("1인 고정석") {
        @Override
        public String showDisplayMessage(int duration, int price) {
            return String.format("%s주권 - %d원", duration, price);
        }
    };

    private final String description;

    StudyCafePassType(String description) {
        this.description = description;
    }


}
