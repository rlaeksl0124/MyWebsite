
import org.junit.*;
import org.junit.internal.runners.statements.Fail;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.*;
import org.springframework.context.support.*;
import org.springframework.jdbc.datasource.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.*;
import java.util.Date;
import javax.sql.*;
import java.sql.*;

import static org.junit.Assert.*;

// select
// update
// insert o
// delete o

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class DBConnectionTestTest {
    @Autowired
    DataSource ds; // 컨테이너로부터 자동 주입받는다.
    final int FAIL = 0;



    @Test
    public void insertUserTest() throws Exception {
        deleteAll();
        User user = new User("qwer3", "1234", "dani", "rlaeksl0124", "kakao", "seoul", "010-7741-1550", new Date());
        int rowCnt = insertUser(user);
        assertTrue(rowCnt==1);
        System.out.println(rowCnt);
    }

    @Test
    public void deleteUserTest() throws Exception {
        deleteAll();

        // insertUser
        User user = new User("qwer3", "1234", "dani", "rlaeksl0124", "kakao", "seoul", "010-7741-1550", new Date());
        int rowCnt = insertUser(user);
        assertTrue(rowCnt==1);

        // deleteUser
        deleteUser(user);
        assertTrue(rowCnt==0);
    }

    @Test
    public void deleteAllTest() throws Exception{
        deleteAll();

        // insertUser
        User user = new User("qwer5", "1234", "dani", "rlaeksl0124", "kakao", "seoul", "010-7741-1550", new Date());
        int rowCnt = insertUser(user);
        System.out.println(rowCnt);
        assertTrue(rowCnt==1);

        // insertUser
        User user2 = new User("qwer5", "1234", "dani", "rlaeksl0124", "kakao", "seoul", "010-7741-1550", new Date());
        rowCnt = insertUser(user2);
        assertTrue(rowCnt==1);

        rowCnt = deleteAll();
        assertTrue(rowCnt==0);

    }
    public int deleteUser(User user) throws SQLException{
        int rowCnt=FAIL;
        String sql = "delete from user_info where userid= ?";

        Connection conn = ds.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, user.getUserId());

        rowCnt = pstmt.executeUpdate();
        return rowCnt;
    }


    public int deleteAll() throws SQLException{
        int rowCnt = FAIL;
        String sql = "delete from user_info";

        Connection conn = ds.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.executeUpdate();
        rowCnt = pstmt.executeUpdate();
        return rowCnt;
    }

    public int insertUser(User user) throws SQLException {
//        ApplicationContext ac = new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/**/root-context.xml");
//        DataSource ds = ac.getBean(DataSource.class);
        int rowCnt = FAIL;
        String sql = "insert into user_info values (?,?,?,?,?,?,?,now())";

        try {
            Connection conn = ds.getConnection(); // 데이터베이스의 연결을 얻는다.
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, user.getUserId());
            pstmt.setString(2,user.getPwd());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getEmail());
            pstmt.setString(5,user.getSns());
            pstmt.setString(6,user.getAddress());
            pstmt.setString(7, user.getPhone_num());

            rowCnt = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return FAIL;
        }
        return rowCnt;
    }
}