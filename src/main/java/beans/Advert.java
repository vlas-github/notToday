package beans;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by vlasov-id-131216 on 28.03.15.
 */
@Entity
@Table(name = "Adverts")
public class Advert {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "uuid", strategy= GenerationType.AUTO)
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    protected String id;

    @Column(name = "titleRu")
    protected String titleRu;

    @Column(name = "titleEn")
    protected String titleEn;

    @Column(name = "creationDate")
    private Date creationDate;

    @Column(name = "type")
    private String type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Advert advert = (Advert) o;

        if (!creationDate.equals(advert.creationDate)) return false;
        if (!id.equals(advert.id)) return false;
        if (!titleEn.equals(advert.titleEn)) return false;
        if (!titleRu.equals(advert.titleRu)) return false;
        if (!type.equals(advert.type)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + titleRu.hashCode();
        result = 31 * result + titleEn.hashCode();
        result = 31 * result + creationDate.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitleRu() {
        return titleRu;
    }

    public void setTitleRu(String titleRu) {
        this.titleRu = titleRu;
    }

    public String getTitleEn() {
        return titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
