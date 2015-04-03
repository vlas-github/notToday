package dao;

import beans.News;

import java.util.List;

/**
 * Created by vlasov-id-131216 on 28.03.15.
 */
public interface NewsDao extends SimpleDao<News> {
    News getLast(String guid);
}
