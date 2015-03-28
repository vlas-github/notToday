package beans;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by vlasov-id-131216 on 14.02.15.
 */
@Entity
@Table(name = "Repeats")
public class Repeat {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "uuid", strategy=GenerationType.AUTO)
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "name")
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Repeat repeat = (Repeat) o;

        if (id != null ? !id.equals(repeat.id) : repeat.id != null) return false;
        if (name != null ? !name.equals(repeat.name) : repeat.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
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

    public Repeat clone() {
        Repeat repeat = new Repeat();
        repeat.setId(getId());
        repeat.setName(getName());
        return repeat;
    }
}
