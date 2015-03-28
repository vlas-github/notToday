package beans;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by vlasov-id-131216 on 07.03.15.
 */
@Entity
@Table(name = "GenericCatalog")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class GenericCatalog {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "uuid", strategy=GenerationType.AUTO)
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    protected String id;

    @Column(name = "name")
    protected String name;

    @Column(name = "localizationCode")
    protected String localizationCode;

    @Column(name = "_order")
    protected Integer order;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GenericCatalog that = (GenericCatalog) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (localizationCode != null ? !localizationCode.equals(that.localizationCode) : that.localizationCode != null)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (order != null ? !order.equals(that.order) : that.order != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (localizationCode != null ? localizationCode.hashCode() : 0);
        result = 31 * result + (order != null ? order.hashCode() : 0);
        return result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocalizationCode() {
        return localizationCode;
    }

    public void setLocalizationCode(String localizationCode) {
        this.localizationCode = localizationCode;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}
