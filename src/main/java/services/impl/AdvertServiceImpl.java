package services.impl;

import beans.Advert;
import dao.AdvertDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import services.AdvertService;
import utils.exception.BusinessException;

import java.util.List;

/**
 * Created by vlasov-id-131216 on 28.03.15.
 */
@Service
public class AdvertServiceImpl implements AdvertService {

    @Autowired
    private AdvertDao advertDao;

    @Override
    @Transactional
    public List<Advert> list() throws BusinessException {
        try {
            return advertDao.getList();
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }
}
