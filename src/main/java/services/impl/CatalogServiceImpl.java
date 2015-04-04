package services.impl;

import beans.BaseCatalog;
import dao.CatalogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import services.CatalogService;
import utils.comparator.GenericCatalogComparator;
import web.controllers.utils.converter.Converter;
import web.controllers.vo.BaseCatalogVo;
import web.controllers.vo.BaseVo;

import java.util.Collections;
import java.util.List;

/**
 * Created by vlasov-id-131216 on 07.03.15.
 */
@Service
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    private CatalogDao catalogDao;

    @Autowired
    @Qualifier("voConverter")
    private Converter converter;

    @Override
    @Transactional
    public <T extends BaseCatalogVo> List<T> getList(Class<T> clazz) {
        List list = catalogDao.getList(converter.getOppositeClass(clazz));
        Collections.sort(list, new GenericCatalogComparator());
        return converter.convert(list);
    }

    @Override
    @Transactional
    public <T extends BaseCatalogVo> T get(String id, Class<T> clazz) {
        BaseCatalog catalog = catalogDao.get(id, converter.getOppositeClass(clazz));
        return converter.convert(catalog, clazz);
    }
}
