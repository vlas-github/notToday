package web.controllers.utils.converter;

import beans.News;
import beans.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import web.controllers.vo.NewsVo;
import web.controllers.vo.UserVo;

/**
 * Created by vlasov-id-131216 on 03.04.15.
 */
@Component
public class NewsVoConverterProvider implements ConverterProvider<News, NewsVo> {

    @Autowired
    @Qualifier("voConverter")
    private Converter converter;

    @Override
    public NewsVo convertSourceToTarget(News from, NewsVo to) {
        to.setId(from.getId());
        to.setGuid(from.getGuid());
        to.setActive(from.isActive());
        to.setNext(from.getNext());
        to.setPrevious(from.getPrevious());
        to.setChangeDate(from.getChangeDate());
        to.setEnglishText(from.getEnglishText());
        to.setRussianText(from.getRussianText());
        to.setAuthor(converter.convert(from.getAuthor(), UserVo.class));
        to.setCreationDate(from.getCreationDate());
        to.setType(from.getType());
        to.setLikes(from.getLikes());
        to.setDislikes(from.getDislikes());
        return to;
    }

    @Override
    public News convertTargetToSource(NewsVo from, News to) {
        to.setId(from.getId());
        to.setGuid(from.getGuid());
        to.setActive(from.isActive());
        to.setNext(from.getNext());
        to.setPrevious(from.getPrevious());
        to.setChangeDate(from.getChangeDate());
        to.setEnglishText(from.getEnglishText());
        to.setRussianText(from.getRussianText());
        to.setAuthor(converter.convert(from.getAuthor(), User.class));
        to.setCreationDate(from.getCreationDate());
        to.setType(from.getType());
        to.setLikes(from.getLikes());
        to.setDislikes(from.getDislikes());
        return to;
    }

    @Override
    public Class<News> getSource() {
        return News.class;
    }

    @Override
    public Class<NewsVo> getTarget() {
        return NewsVo.class;
    }
}
