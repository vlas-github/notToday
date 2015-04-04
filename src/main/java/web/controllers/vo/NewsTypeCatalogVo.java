package web.controllers.vo;

/**
 * Created by vlasov-id-131216 on 03.04.15.
 */
public class NewsTypeCatalogVo extends BaseCatalogVo {
    private String id;
    private String localizationCode;
    private String name;
    private Integer order;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocalizationCode() {
        return localizationCode;
    }

    public void setLocalizationCode(String localizationCode) {
        this.localizationCode = localizationCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}
