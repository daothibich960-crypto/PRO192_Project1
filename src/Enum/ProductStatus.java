
package Enum;



public enum ProductStatus {

    AVAILABLE("Còn hàng"),
    OUT_OF_STOCK("Hết hàng"),
    DISCONTINUED("Ngừng kinh doanh");

    private final String displayName;

    ProductStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }

    public static ProductStatus fromString(String text) {
        for (ProductStatus s : ProductStatus.values()) {
            if (s.name().equalsIgnoreCase(text) || s.displayName.equalsIgnoreCase(text)) {
                return s;
            }
        }
        throw new IllegalArgumentException("Unknown status: " + text);
    }
}
