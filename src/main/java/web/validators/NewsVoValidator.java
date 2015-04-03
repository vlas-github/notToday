package web.validators;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import web.controllers.vo.NewsVo;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vlasov-id-131216 on 03.04.15.
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class NewsVoValidator implements Validator<NewsVo>  {

    private Map<String, Object> errors = new HashMap<String, Object>();

    @Override
    public Object validate(NewsVo newsVo) {
        if (StringUtils.isEmpty(newsVo.getEnglishText())) {
            errors.put("English text is empty", true);
        }

        if (StringUtils.isEmpty(newsVo.getRussianText())) {
            errors.put("Russian text is empty", true);
        }
        return errors;
    }

    @Override
    public Object validate(Collection<NewsVo> list) {
        list.stream().forEach(x -> validate(x));
        return errors;
    }

    @Override
    public Map<String, Object> getErrors() {
        return errors;
    }

    @Override
    public boolean hasErrors() {
        return !errors.isEmpty();
    }
}
