package beans;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by vlasov-id-131216 on 28.03.15.
 */
@Entity
@Table(name="News")
@AttributeOverrides({
        @AttributeOverride(name="id",           column=@Column(name="_id")),
        @AttributeOverride(name="guid",         column=@Column(name="_guid")),
        @AttributeOverride(name="next",         column=@Column(name="_next")),
        @AttributeOverride(name="previous",     column=@Column(name="_previous")),
        @AttributeOverride(name="changeDate",   column=@Column(name="_change_date"))})
public final class News extends BaseVersionedEntity {

    @Column(name="_english_text")
    private String englishText;

    @Column(name="_russian_text")
    private String russianText;

    @Column(name="_type")
    private String type;

    @Column(name="_author")
    private User author;

    @Column(name="_creation_date")
    private Date creationDate;

    @Column(name="_likes")
    private Integer likes;

    @Column(name="_dislikes")
    private Integer dislikes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        News news = (News) o;

        if (author != null ? !author.equals(news.author) : news.author != null) return false;
        if (creationDate != null ? !creationDate.equals(news.creationDate) : news.creationDate != null) return false;
        if (dislikes != null ? !dislikes.equals(news.dislikes) : news.dislikes != null) return false;
        if (englishText != null ? !englishText.equals(news.englishText) : news.englishText != null) return false;
        if (likes != null ? !likes.equals(news.likes) : news.likes != null) return false;
        if (russianText != null ? !russianText.equals(news.russianText) : news.russianText != null) return false;
        if (type != null ? !type.equals(news.type) : news.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (englishText != null ? englishText.hashCode() : 0);
        result = 31 * result + (russianText != null ? russianText.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (likes != null ? likes.hashCode() : 0);
        result = 31 * result + (dislikes != null ? dislikes.hashCode() : 0);
        return result;
    }

    public String getEnglishText() {
        return englishText;
    }

    public void setEnglishText(String englishText) {
        this.englishText = englishText;
    }

    public String getRussianText() {
        return russianText;
    }

    public void setRussianText(String russianText) {
        this.russianText = russianText;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getDislikes() {
        return dislikes;
    }

    public void setDislikes(Integer dislikes) {
        this.dislikes = dislikes;
    }
}
