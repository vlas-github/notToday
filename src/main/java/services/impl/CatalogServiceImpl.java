package services.impl;

import beans.GenericCatalog;
import dao.CatalogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import services.CatalogService;
import utils.comparator.GenericCatalogComparator;

import java.util.Collections;
import java.util.List;

/**
 * Created by vlasov-id-131216 on 07.03.15.
 */
@Service
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    private CatalogDao catalogDao;

    @Override
    @Transactional
    public <T extends GenericCatalog> List<T> getList(Class<T> clazz) {
        List list = catalogDao.getList(clazz);
        Collections.sort(list, new GenericCatalogComparator());
        return list;
    }

    @Override
    @Transactional
    public <T extends GenericCatalog> T get(String id, Class<T> clazz) {
        return catalogDao.get(id, clazz);
    }
}
