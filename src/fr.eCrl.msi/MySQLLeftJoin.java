/* KHN - 02/12/2022 - RIP - MySQLLeftJoin*/

package fr.eCrl.msi;

public class MySQLLeftJoin {
    private final String guest;
    private final String host;
    private final String name;
    private final String guestField;
    private final String hostField;


    public MySQLLeftJoin(String guest, String host, String name, String guestField, String hostField) {
        this.guest = guest;
        this.host = host;
        this.name = name;
        this.guestField = guestField;
        this.hostField = hostField;
    }

    /**
     * Returns a string equivalent of this MySQL query attribute
     * @return A string.
     */
    public String compose(){
        return String.format("LEFT JOIN %s AS %s ON %s.%s = %s.%s",guest,name,name,guestField,host,hostField);
    }
}
