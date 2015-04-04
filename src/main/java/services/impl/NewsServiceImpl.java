package services.impl;

import beans.News;
import dao.NewsDao;
import dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import services.NewsService;
import services.UserService;
import utils.exception.BusinessException;
import web.controllers.utils.converter.Converter;
import web.controllers.vo.NewsVo;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by vlasov-id-131216 on 28.03.15.
 */
@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsDao newsDao;

    @Autowired
    private UserDao userDao;

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
            News persisted = converter.convert(news);
            persisted.setGuid(UUID.randomUUID().toString());
            persisted.setActive(true);
            persisted.setAuthor(userDao.getById(news.getAuthor().getId()));
            persisted.setCreationDate(new Date());
            persisted.setChangeDate(new Date());
            persisted.setLikes(0);
            persisted.setDislikes(0);
            return converter.convert(newsDao.save(persisted));
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    @Override
    @Transactional
    public NewsVo update(NewsVo news) throws BusinessException {
        try {
            News old = newsDao.getLast(news.getGuid());
            News persisted = converter.convert(news);
            persisted.setAuthor(userDao.getById(news.getAuthor().getId()));
            persisted.setChangeDate(new Date());
            persisted.setCreationDate(old.getCreationDate());
            persisted.setPrevious(old.getId());
            persisted.setActive(true);
            news = converter.convert(newsDao.save(persisted));
            old.setNext(news.getId());
            old.setActive(false);
            newsDao.update(old);
            return converter.convert(newsDao.save(persisted));
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }
}
