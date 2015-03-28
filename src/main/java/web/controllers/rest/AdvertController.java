package web.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import services.AdvertService;
import utils.json.AjaxResponse;
import utils.json.AjaxResponseStatus;

/**
 * Created by vlasov-id-131216 on 28.03.15.
 */
@Controller
@RequestMapping(value = "/api/", produces="application/json; charset=utf-8")
public class AdvertController {

    @Autowired
    private AdvertService advertService;

    @RequestMapping(value = "news/list.json", method = RequestMethod.GET)
    @ResponseBody
    public Object getList() {
        AjaxResponse response = new AjaxResponse();
        try {
            response.setData(advertService.list());
            response.setStatus(AjaxResponseStatus.OK);
        } catch (Throwable t) {
            response.setStatus(AjaxResponseStatus.ERROR);
            response.setMessage(t.getMessage());
        }
        return response;
    }
}
