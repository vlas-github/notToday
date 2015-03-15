package beans;

import javax.persistence.*;

/**
 * Created by vlasov-id-131216 on 07.03.15.
 */
@Entity
@Table(name="RepeatCatalog")
@AttributeOverrides({
        @AttributeOverride(name="id",               column=@Column(name="id")),
        @AttributeOverride(name="name",             column=@Column(name="name")),
        @AttributeOverride(name="localizationCode", column=@Column(name="localizationCode")),
        @AttributeOverride(name="order",            column=@Column(name="_order"))
})
public class RepeatCatalog extends GenericCatalog {
    public static String EACH_DAY = "EACH_DAY";
    public static String EACH_TWO_DAYS = "EACH_TWO_DAYS";
    public static String EACH_THREE_DAYS = "EACH_THREE_DAYS";
    public static String EACH_WEEKDAY = "EACH_WEEKDAY";
    public static String EACH_WEEK = "EACH_WEEK";
    public static String EACH_MONTH = "EACH_MONTH";
    public static String EACH_YEAR = "EACH_YEAR";
}
