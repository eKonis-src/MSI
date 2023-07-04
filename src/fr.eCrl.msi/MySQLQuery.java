/*KHN - 18/10/2022 - RIP - MySQLQuery*/

package fr.eCrl.msi;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class MySQLQuery {

    private final MySQLQueryType type;
    private MySQLTableName table;

    private MySQLQueryFunction filter;
    private final List<MySQLIndication> indications;
    private final StringJoiner condition;

    private final List<MySQLLeftJoin> leftJoins;

    public MySQLQuery(MySQLQueryType type){
        Objects.requireNonNull(type);
        condition = new StringJoiner(" AND ");
        this.type = type;
        indications = new ArrayList<>();
        leftJoins = new ArrayList<>();
        table = null;
    }

    public MySQLQuery setTable(String table){
        this.table = new MySQLTableName(table);
        return this;
    }

    public MySQLQuery addFilter(String function){
        this.filter = new MySQLQueryFunction(function);
        return this;
    }

    public MySQLQuery addIndication(MySQLIndication indication){
        indications.add(indication);
        return this;
    }

    public MySQLQuery addCondition(String condition){
        this.condition.add(condition);
        return this;
    }

    public MySQLQuery leftJoin(MySQLLeftJoin join){
        this.leftJoins.add(join);
        return this;
    }

    /**
     *
     * @return Returns a SQL Query composed with the object fields
     */
    public String compose()
    {
        Objects.requireNonNull(table);
        Objects.requireNonNull(condition);
        StringJoiner fields = new StringJoiner(", ");
        StringJoiner values = new StringJoiner(", ");
        StringJoiner rq = new StringJoiner(" ");

        for (MySQLIndication indic: indications)
        {
            fields.add(indic.getField());
            if (Objects.isNull(indic.getValue()))
                values.add("null");
            else
                values.add(indic.getValue());
        }

        if (fields.length() == 0)
            fields.add("*");
        if (type == MySQLQueryType.SELECT)
        {
            rq.add("SELECT");
            String filteredFields;
            if (!Objects.isNull(filter))
                filteredFields = filter.compose(fields.toString());
            else
                filteredFields = fields.toString();
            rq.add(filteredFields).add("FROM")
                    .add(table.compose());
            if (leftJoins.size() != 0){
                leftJoins.forEach(i -> rq.add(i.compose()));
            }
            if (condition.length() != 0) {
                rq.add("WHERE").add(condition.toString());
            }
        }
        else if (type == MySQLQueryType.INSERT)
        {
            rq.add("INSERT INTO").add(table.compose()).add("("+fields+")")
                    .add("VALUES ("+values+")");
        }
        else {
            throw new IllegalArgumentException("Invalid query type : " + type.name());
        }
        return rq.add(";").toString();
    }

}
