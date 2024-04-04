
import com.fastcampus.ch3.domain.UserDto;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.*;
import java.util.Date;
import javax.sql.*;
import java.sql.*;

import static org.junit.Assert.*;

// select o
// update o
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
        UserDto userDto = new UserDto("qwer3", "1234", "dani", "rlaeksl0124", "kakao", "seoul", "010-7741-1550", new Date());
        assertTrue(insertUser(userDto)==1);
        assertTrue(count()==1);

    }

    @Test
    public void deleteUserTest() throws Exception {
        deleteAll();

        // insertUser
        UserDto userDto = new UserDto("qwer3", "1234", "dani", "rlaeksl0124", "kakao", "seoul", "010-7741-1550", new Date());
        assertTrue(insertUser(userDto)==1);
        assertTrue(count()==1);

        deleteUser(userDto);
        assertTrue(deleteUser(userDto)==0);
    }

    @Test
    public void deleteAllTest() throws Exception{
        deleteAll();
        assertTrue(deleteAll()==0);

        // insertUser
        UserDto userDto = new UserDto("qwer5", "1234", "dani", "rlaeksl0124", "kakao", "seoul", "010-7741-1550", new Date());
        assertTrue(insertUser(userDto)==1);
        assertTrue(count()==1);

        // insertUser
        UserDto userDto2 = new UserDto("qwer6", "1234", "dani", "rlaeksl0124", "kakao", "seoul", "010-7741-1550", new Date());
        assertTrue(insertUser(userDto2)==1);
        assertTrue(count()==2);

        //deleteAll
        deleteAll();
        assertTrue(deleteAll()==0);

    }

    @Test
    public void selectUserTest() throws Exception {
        deleteAll();
        // insertUser
        UserDto userDto = new UserDto("qwer5", "1234", "dani", "rlaeksl0124", "kakao", "seoul", "010-7741-1550", new Date());
        assertTrue(insertUser(userDto)==1);
        assertTrue(count()==1);

        // selectUser
        UserDto userDto2 = selectUser(userDto);
        assertEquals(userDto, userDto2);
    }

    @Test
    public void updateUserTest() throws Exception {
        deleteAll();
        UserDto userDto = new UserDto("qwer5", "1234", "dani", "rlaeksl0124", "kakao", "seoul", "010-7741-1550", new Date());
        assertTrue(insertUser(userDto)==1);

        userDto.setPwd("5678");
        userDto.setName("update name");
        assertTrue(updateUser(userDto)==1);
        assertTrue(count()==1);

        UserDto userDto2 = selectUser(userDto);
        assertEquals(userDto, userDto2);
    }

    public int updateUser(UserDto userDto) throws SQLException {
        int rowCnt=0;
        String sql="update user_info set pwd=?, name=?, email=?, sns=?, address=?, phone_num=? where userid=?";
        Connection conn = ds.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, userDto.getPwd());
        pstmt.setString(2, userDto.getName());
        pstmt.setString(3, userDto.getEmail());
        pstmt.setString(4, userDto.getSns());
        pstmt.setString(5, userDto.getAddress());
        pstmt.setString(6, userDto.getPhone_num());
        pstmt.setString(7, userDto.getUserId());

        rowCnt = pstmt.executeUpdate();

        return rowCnt;
    }

    public UserDto selectUser(UserDto userDto) throws SQLException {
        int rowCnt=FAIL;
        String sql = "select * from user_info where userid=?";

        Connection conn = ds.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, userDto.getUserId());

        ResultSet rs = pstmt.executeQuery();

        if(rs.next()){
            userDto = new UserDto();
            userDto.setUserId(rs.getString(1));
            userDto.setPwd(rs.getString(2));
            userDto.setName(rs.getString(3));
            userDto.setEmail(rs.getString(4));
            userDto.setSns(rs.getString(5));
            userDto.setAddress(rs.getString(6));
            userDto.setPhone_num(rs.getString(7));
            userDto.setCreate_date(rs.getDate(8));
        }
        return userDto;
    }

    public int deleteUser(UserDto userDto) throws SQLException{
        int rowCnt;
        String sql = "delete from user_info where userid= ?";

        Connection conn = ds.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, userDto.getUserId());
        rowCnt = pstmt.executeUpdate();
        return rowCnt;
    }


    public int deleteAll() throws SQLException{
        int rowCnt;
        String sql = "delete from user_info";

        Connection conn = ds.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        rowCnt = pstmt.executeUpdate();
        return rowCnt;
    }

    public int insertUser(UserDto userDto) throws SQLException {
        int rowCnt = FAIL;
        String sql = "insert into user_info values (?,?,?,?,?,?,?,now())";

        try {
            Connection conn = ds.getConnection(); // 데이터베이스의 연결을 얻는다.
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, userDto.getUserId());
            pstmt.setString(2, userDto.getPwd());
            pstmt.setString(3, userDto.getName());
            pstmt.setString(4, userDto.getEmail());
            pstmt.setString(5, userDto.getSns());
            pstmt.setString(6, userDto.getAddress());
            pstmt.setString(7, userDto.getPhone_num());

            rowCnt = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return FAIL;
        }
        return rowCnt;
    }

    public int count() throws SQLException {
        int result=0;
        String sql = "select count(*) from user_info";
        Connection conn = ds.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

        if(rs.next()){
            result = rs.getInt(1);
        }
        return result;
    }
}