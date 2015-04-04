package web.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import services.NewsService;
import utils.json.AjaxResponse;
import utils.json.AjaxResponseStatus;
import web.controllers.vo.NewsVo;
import web.controllers.vo.UserVo;
import web.validators.NewsVoValidator;

/**
 * Created by vlasov-id-131216 on 28.03.15.
 */
@Controller
@RequestMapping(value = "/api/", produces="application/json; charset=utf-8")
public class NewsRestController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private NewsVoValidator newsVoValidator;

    @RequestMapping(value = "news/list.json", method = RequestMethod.GET)
    @ResponseBody
    public Object getList() {
        AjaxResponse response = new AjaxResponse();
        try {
            response.setData(newsService.list());
            response.setStatus(AjaxResponseStatus.OK);
        } catch (Throwable t) {
            response.setStatus(AjaxResponseStatus.ERROR);
            response.setMessage(t.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "admin/news.json", method = RequestMethod.POST)
    @ResponseBody
    public Object add(@RequestBody NewsVo news) {
        AjaxResponse response = new AjaxResponse();
        try {
            newsVoValidator.validate(news);
            if (newsVoValidator.hasErrors()) {
                response.setData(newsVoValidator.getErrors());
                response.setStatus(AjaxResponseStatus.ERROR);
            }
            news = newsService.save(news);
            response.setData(news);
            response.setStatus(AjaxResponseStatus.OK);
        } catch (Throwable t) {
            response.setStatus(AjaxResponseStatus.ERROR);
            response.setMessage(t.getMessage());
        }
        return response;
    }
}
