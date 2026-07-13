
package Enum;



/**
 * Unit of product
 */
public enum Unit {

    GRAM("g"),
    KILOGRAM("kg"),
    PIECE("piece"),
    SET("set"),
    BOX("box"),
    BOTTLE("bottle"),
    CAKE("cake-357g");

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
