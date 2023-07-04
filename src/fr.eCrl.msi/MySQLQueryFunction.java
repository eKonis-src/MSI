/* KHN - 03/11/2022 - RIP - MySQLQueryFunction*/

package fr.eCrl.msi;

import java.util.Objects;

public class MySQLQueryFunction {

    private final String function;

    public MySQLQueryFunction(String function) {
        Objects.requireNonNull(function);
        this.function = function;
    }

    /**
     * Returns a string equivalent of this MySQL query attribute
     * @param target Target of the function
     * @return A string.
     */
    public String compose(String target) {
        Objects.requireNonNull(target);
        return function.toUpperCase() + "(" + target + ")";
    }
}
