package services.impl;

import dao.NewsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import services.NewsService;
import utils.exception.BusinessException;
import web.controllers.utils.converter.Converter;
import web.controllers.vo.NewsVo;

import java.util.List;

/**
 * Created by vlasov-id-131216 on 28.03.15.
 */
@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsDao newsDao;

    @Autowired
    @Qualifier("voConverter")
    private Converter converter;

    @Override
    @Transactional
    public List<NewsVo> list() throws BusinessException {
        try {
            return converter.convert(newsDao.getList(), NewsVo.class);
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    @Override
    @Transactional
    public NewsVo save(NewsVo news) throws BusinessException {
        try {
            return null; // newsDao.save(news); // todo
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    @Override
    @Transactional
    public NewsVo update(NewsVo news) throws BusinessException {
        try {
            return null; // newsDao.update(news); // todo
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }
}
