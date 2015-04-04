package services;

import utils.exception.BusinessException;
import web.controllers.vo.NewsVo;

import java.util.List;

/**
 * Created by vlasov-id-131216 on 28.03.15.
 */
public interface NewsService {

    List<NewsVo> list() throws BusinessException;
    NewsVo save(NewsVo news) throws BusinessException;
    NewsVo update(NewsVo news) throws BusinessException;
}
