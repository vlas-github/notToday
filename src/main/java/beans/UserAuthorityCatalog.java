package beans;

import javax.persistence.*;

/**
 * Created by vlasov-id-131216 on 07.03.15.
 */
@Entity
@Table(name="UserAuthorityCatalog")
@AttributeOverrides({
        @AttributeOverride(name="id",               column=@Column(name="_id")),
        @AttributeOverride(name="name",             column=@Column(name="_name")),
        @AttributeOverride(name="localizationCode", column=@Column(name="_localization_code")),
        @AttributeOverride(name="order",            column=@Column(name="_order"))})
public final class UserAuthorityCatalog extends BaseCatalog {
    public static String ROLE_USER = "ROLE_USER";
    public static String ROLE_ADMIN = "ROLE_ADMIN";
}
