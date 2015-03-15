package utils.converter.vo;

import beans.Repeat;
import org.springframework.stereotype.Component;
import vo.RepeatVo;

/**
 * Created by vlasov-id-131216 on 15.02.15.
 */
@Component
public class RepeatVoConverter implements Converter<Repeat, RepeatVo> {
    @Override
    public RepeatVo convertSourceToTarget(Repeat from, RepeatVo to) {
        to.setName(from.getName());
        to.setId(from.getId());
        return to;
    }

    @Override
    public Repeat convertTargetToSource(RepeatVo from, Repeat to) {
        to.setName(from.getName());
        to.setId(from.getId());
        return to;
    }

    @Override
    public Class<Repeat> getSource() {
        return Repeat.class;
    }

    @Override
    public Class<RepeatVo> getTarget() {
        return RepeatVo.class;
    }
}
