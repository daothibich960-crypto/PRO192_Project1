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

}
