package com.example;

import java.text.DecimalFormat;

public class MathConverter {
    public static String mConveter(String currency, Double baseCurrency, Double secondCurrency ) {
        Double  sum = ((Double.parseDouble(currency) / baseCurrency) * secondCurrency);
        String sum2 = String.valueOf(sum);
        return formatResult(sum2);
    }
    public static String formatResult(String amount) {
        DecimalFormat formatter = new DecimalFormat("###,###,##0.0000");
        return formatter.format(Double.parseDouble(amount));
    }
}

