package web.controllers.vo;

import java.util.Date;

/**
 * Created by vlasov-id-131216 on 03.04.15.
 */
public class NewsVo extends BaseVo {
    private String id;
    private String guid;
    private Boolean active;
    private String next;
    private String previous;
    private Date changeDate;
    private String englishText;
    private String russianText;
    private String type;
    private UserVo author;
    private Date creationDate;
    private Integer likes;
    private Integer dislikes;
    private Boolean show;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewsVo newsVo = (NewsVo) o;

        if (active != null ? !active.equals(newsVo.active) : newsVo.active != null) return false;
        if (author != null ? !author.equals(newsVo.author) : newsVo.author != null) return false;
        if (changeDate != null ? !changeDate.equals(newsVo.changeDate) : newsVo.changeDate != null) return false;
        if (creationDate != null ? !creationDate.equals(newsVo.creationDate) : newsVo.creationDate != null)
            return false;
        if (dislikes != null ? !dislikes.equals(newsVo.dislikes) : newsVo.dislikes != null) return false;
        if (englishText != null ? !englishText.equals(newsVo.englishText) : newsVo.englishText != null) return false;
        if (guid != null ? !guid.equals(newsVo.guid) : newsVo.guid != null) return false;
        if (id != null ? !id.equals(newsVo.id) : newsVo.id != null) return false;
        if (likes != null ? !likes.equals(newsVo.likes) : newsVo.likes != null) return false;
        if (next != null ? !next.equals(newsVo.next) : newsVo.next != null) return false;
        if (previous != null ? !previous.equals(newsVo.previous) : newsVo.previous != null) return false;
        if (russianText != null ? !russianText.equals(newsVo.russianText) : newsVo.russianText != null) return false;
        if (show != null ? !show.equals(newsVo.show) : newsVo.show != null) return false;
        if (type != null ? !type.equals(newsVo.type) : newsVo.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (guid != null ? guid.hashCode() : 0);
        result = 31 * result + (active != null ? active.hashCode() : 0);
        result = 31 * result + (next != null ? next.hashCode() : 0);
        result = 31 * result + (previous != null ? previous.hashCode() : 0);
        result = 31 * result + (changeDate != null ? changeDate.hashCode() : 0);
        result = 31 * result + (englishText != null ? englishText.hashCode() : 0);
        result = 31 * result + (russianText != null ? russianText.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (likes != null ? likes.hashCode() : 0);
        result = 31 * result + (dislikes != null ? dislikes.hashCode() : 0);
        result = 31 * result + (show != null ? show.hashCode() : 0);
        return result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
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

    public UserVo getAuthor() {
        return author;
    }

    public void setAuthor(UserVo author) {
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

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }
}
