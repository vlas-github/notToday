package web.controllers.rest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import services.CatalogService;
import utils.json.AjaxResponse;
import utils.json.AjaxResponseStatus;
import web.controllers.vo.NewsTypeCatalogVo;
import web.controllers.vo.RepeatCatalogVo;
import web.controllers.vo.UserAuthorityCatalogVo;

/**
 * Created by vlasov-id-131216 on 07.03.15.
 */
@Controller
@RequestMapping(value = "/api/", produces="application/json; charset=utf-8")
public class CatalogRestController {

    @Autowired
    private CatalogService catalogService;

    @RequestMapping(value = "catalog/{name}/list.json", method = RequestMethod.GET)
    @ResponseBody
    public Object getList(@PathVariable("name") String name) {
        AjaxResponse response = new AjaxResponse();
        try {
            if (StringUtils.isEmpty(name)) {
                response.setStatus(AjaxResponseStatus.ERROR);
                response.setMessage("Name catalog is null");
                return response;
            }
            if ("userAuthority".equals(name)) {
                response.setData(catalogService.getList(UserAuthorityCatalogVo.class));
            } else if ("repeat".equals(name)) {
                response.setData(catalogService.getList(RepeatCatalogVo.class));
            } else if ("newsType".equals(name)) {
                response.setData(catalogService.getList(NewsTypeCatalogVo.class));
            }
            response.setStatus(AjaxResponseStatus.OK);
        } catch (Throwable t) {
            response.setStatus(AjaxResponseStatus.ERROR);
            response.setMessage(t.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "catalog/{name}/{id}.json", method = RequestMethod.GET)
    @ResponseBody
    public Object get(@PathVariable("name") String name, @PathVariable("id") String id) {
        AjaxResponse response = new AjaxResponse();
        try {
            if (StringUtils.isEmpty(name)) {
                response.setStatus(AjaxResponseStatus.ERROR);
                response.setMessage("Name catalog is null");
                return response;
            } else if (StringUtils.isEmpty(id)) {
                response.setStatus(AjaxResponseStatus.ERROR);
                response.setMessage("Id is null");
                return response;
            }
            if ("userAuthority".equals(name)) {
                response.setData(catalogService.get(id, UserAuthorityCatalogVo.class));
            } else if ("repeat".equals(name)) {
                response.setData(catalogService.get(id, RepeatCatalogVo.class));
            }
            response.setStatus(AjaxResponseStatus.OK);
        } catch (Throwable t) {
            response.setStatus(AjaxResponseStatus.ERROR);
            response.setMessage(t.getMessage());
        }
        return response;
    }
}
