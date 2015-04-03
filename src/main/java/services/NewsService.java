package services;

import beans.News;
import org.springframework.transaction.annotation.Transactional;
import utils.exception.BusinessException;

import java.util.List;

/**
 * Created by vlasov-id-131216 on 28.03.15.
 */
public interface NewsService {

    List<News> list() throws BusinessException;
    News save(News news) throws BusinessException;
    News update(News news) throws BusinessException;
}
