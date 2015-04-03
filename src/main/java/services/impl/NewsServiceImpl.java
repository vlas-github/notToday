package services.impl;

import beans.News;
import dao.NewsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import services.NewsService;
import utils.exception.BusinessException;

import java.util.List;

/**
 * Created by vlasov-id-131216 on 28.03.15.
 */
@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsDao newsDao;

    @Override
    @Transactional
    public List<News> list() throws BusinessException {
        try {
            return newsDao.getList();
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public News save(News news) throws BusinessException {
        try {
            return newsDao.save(news);
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public News update(News news) throws BusinessException {
        try {
            return newsDao.update(news); // todo
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }
}
