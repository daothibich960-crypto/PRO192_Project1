package Enum;

/**
 * Unit of product
 */
public enum Unit {
    GRAM("Gam (g)"),
    KILOGRAM("Kilôgam (Kg)"),
    PIECE("Cái"),
    SET("Bộ"),
    BOX("Hộp"),
    BOTTLE("Chai"),
    CAKE("Bánh");
    private final String symbol;

    Unit(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }

    public static Unit fromString(String text) {
        for (Unit u : Unit.values()) {
            if (u.name().equalsIgnoreCase(text) || u.symbol.equalsIgnoreCase(text)) {
                return u;
            }
        }
        throw new IllegalArgumentException("Unknown unit: " + text);
    }
}
