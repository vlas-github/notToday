package dao;

import beans.Task;
import beans.User;

import java.util.List;

/**
 * Created by vlasov-id-131216 on 14.02.15.
 */
public interface TaskDao extends SimpleDao<Task> {

    List<Task> getList(User user);
}
