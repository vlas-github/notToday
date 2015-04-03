package beans;

import javax.persistence.*;

/**
 * Created by vlasov-id-131216 on 03.04.15.
 */
@Entity
@Table(name="NewsTypeCatalog")
@AttributeOverrides({
        @AttributeOverride(name="id",               column=@Column(name="_id")),
        @AttributeOverride(name="name",             column=@Column(name="_name")),
        @AttributeOverride(name="localizationCode", column=@Column(name="_localization_code")),
        @AttributeOverride(name="order",            column=@Column(name="_order"))})
public final class NewsTypeCatalog extends BaseCatalog {
    public static String SUCCESS = "success";
    public static String INFO = "info";
    public static String WARNING = "warning";
    public static String DANGER = "danger";
}
