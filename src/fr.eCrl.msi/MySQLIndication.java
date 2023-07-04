/* KHN - 02/11/2022 - RIP - MySQLIndication*/

package fr.eCrl.msi;

import java.util.Objects;

public class MySQLIndication  {
    private final String field;
    private final String value;
    private final boolean isString;

    public MySQLIndication(String field, String value, boolean isString){
        Objects.requireNonNull(field);
        this.field = field;
        this.value = value;
        this.isString = isString;
    }

    public String getField() {
        return field;
    }

    public String getValue() {
        if (value == null)
            return "null";
        if (isString)
            return '"'+value+'"';
        return value;
    }
}
