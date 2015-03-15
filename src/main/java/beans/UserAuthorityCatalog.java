package beans;

import javax.persistence.*;

/**
 * Created by vlasov-id-131216 on 07.03.15.
 */
@Entity
@Table(name="UserAuthorityCatalog")
@AttributeOverrides({
        @AttributeOverride(name="id",               column=@Column(name="id")),
        @AttributeOverride(name="name",             column=@Column(name="name")),
        @AttributeOverride(name="localizationCode", column=@Column(name="localizationCode")),
        @AttributeOverride(name="order",            column=@Column(name="_order"))
})
public class UserAuthorityCatalog extends GenericCatalog {
    public static String ROLE_USER = "ROLE_USER";
    public static String ROLE_ADMIN = "ROLE_ADMIN";
}
