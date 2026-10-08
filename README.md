# VatSimulation

Simulates the sale of 10,000 units (net cost 9.99, VAT rate 5 %) and prints the VAT per unit
plus the total net, VAT and gross values.

Money is kept as scaled `long` integers (1 unit = 0.0001), so the totals are exact and the
loop allocates nothing, which keeps it suitable for millions of transactions.

## Run in IntelliJ IDEA

1. **File > Open...** and select this folder.
2. If IntelliJ says "Project JDK is not defined", click **Setup SDK** and pick any JDK 17 or newer.
3. Press the green run button next to `main` in `src/VatSimulation.java`
   (or use the ready-made `VatSimulation` run configuration).

Without an IDE (JDK 11+): `java src/VatSimulation.java`

## Output

```
VAT per unit      : 0.4995
Total net sales   : 99900.0000
Total VAT         : 4995.0000
Total gross value : 104895.0000
```

## Tweaks

- `UNITS_SOLD`: number of transactions (for example `10_000_000`).
- `DECIMALS = 2`: round VAT to the cent per transaction. VAT per unit becomes 0.50 and the totals
  become 99900.00 / 5000.00 / 104900.00.
