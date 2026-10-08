import java.math.BigDecimal;

/**
 * Simulates UNITS_SOLD sales and totals net value, VAT and gross value.
 *
 * Money is held as scaled long integers (1 unit = 0.0001), so every addition is
 * exact, there is no per-transaction object allocation, and nothing drifts the
 * way repeated `double +=` does over millions of iterations.
 */
public class VatSimulation {

    private static final int DECIMALS = 4;                         // 1 stored unit = 0.0001
    private static final long SCALE = (long) Math.pow(10, DECIMALS);
    private static final long BASIS_POINTS = 10_000L;              // 100 % = 10_000 bp, so 5 % = 500 bp
    private static final int UNITS_SOLD = 10_000;

    public static void main(String[] args) {
        double productCost = 9.99;
        double vatRate = 0.05;

        // Convert once, outside the loop. From here on it is exact long arithmetic.
        long netPerUnit = Math.round(productCost * SCALE);                          // 99_900 -> 9.9900
        long rateBp = Math.round(vatRate * BASIS_POINTS);                           // 500    -> 5.00 %
        long vatPerUnit = (netPerUnit * rateBp + BASIS_POINTS / 2) / BASIS_POINTS;  // 4_995  -> 0.4995 (half-up)

        long totalNet = 0;
        long totalVat = 0;
        for (int i = 0; i < UNITS_SOLD; i++) {   // one iteration = one transaction
            totalNet += netPerUnit;
            totalVat += vatPerUnit;
        }
        long totalGross = totalNet + totalVat;   // exact, so no need to accumulate it separately

        System.out.println("VAT per unit      : " + money(vatPerUnit));
        System.out.println("Total net sales   : " + money(totalNet));
        System.out.println("Total VAT         : " + money(totalVat));
        System.out.println("Total gross value : " + money(totalGross));
    }

    private static BigDecimal money(long scaled) {
        return BigDecimal.valueOf(scaled, DECIMALS);   // used once per printed value, not in the loop
    }
}
