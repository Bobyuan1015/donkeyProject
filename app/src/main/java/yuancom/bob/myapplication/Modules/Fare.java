package yuancom.bob.myapplication.Modules;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * Created by bobyuan on 07/08/2017.
 */

public class Fare {

        /**
         * {@code currency} contains the currency indicating the currency that the amount is expressed in.
         */
        public String currency;

        /**
         * {@code value} contains the total fare amount, in the currency specified in {@link #currency}.
         */
        public String value;
        public String text;
        public Fare(String a, String b, String c)
        {
              currency = a;
                value = b;
                text = c;
        }



}
