package beans;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by vlasov-id-131216 on 03.04.15.
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BaseEntity {

    @Id
    @Column(name = "_id")
    @GeneratedValue(generator = "uuid", strategy= GenerationType.AUTO)
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseEntity that = (BaseEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
