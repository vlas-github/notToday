package services;

import beans.Advert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import utils.exception.BusinessException;

import java.util.List;

/**
 * Created by vlasov-id-131216 on 28.03.15.
 */
public interface AdvertService {

    @Transactional
    List<Advert> list() throws BusinessException;
}
