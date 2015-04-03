package beans;

import javax.persistence.*;

/**
 * Created by vlasov-id-131216 on 07.03.15.
 */
@Entity
public abstract class BaseCatalog extends BaseEntity {

    @Column(name="_localization_code")
    private String localizationCode;

    @Column(name="_name")
    private String name;

    @Column(name="_order")
    private Integer order;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        BaseCatalog that = (BaseCatalog) o;

        if (localizationCode != null ? !localizationCode.equals(that.localizationCode) : that.localizationCode != null)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (order != null ? !order.equals(that.order) : that.order != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (localizationCode != null ? localizationCode.hashCode() : 0);
        result = 31 * result + (order != null ? order.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
