import com.zgy.javawebclass.bean.Manager;
import com.zgy.javawebclass.utils.DBUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author zgy
 * @create 2022-12-13 16:01
 */
public class test {
    @Test
    public void test(){
        DBUtil.init();
        Connection connection = DBUtil.getConn();
        Manager byId = DBUtil.getById(connection, 1, Manager.class);
        System.out.println(byId);
        DBUtil.close(connection);
    }
}
