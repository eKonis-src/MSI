/* KHN - 03/11/2022 - RIP - MySQLTableName*/

package fr.eCrl.msi;

import java.util.Objects;

public class MySQLTableName{
    private final String name;

    public MySQLTableName(String name)
    {
        Objects.requireNonNull(name);
        this.name = name;
    }

    /**
     * Returns a string equivalent of this MySQL query attribute
     * @return A string.
     */
    public String compose() {
        return name;
    }
}
