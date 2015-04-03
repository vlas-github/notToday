package dao;

import beans.User;

/**
 * Created by vlasov-id-131216 on 14.02.15.
 */
public interface UserDao extends SimpleDao<User> {

    User getByEmail(String email);
}
