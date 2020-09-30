import com.joe.domian.dto.JwtUser;
import com.joe.domian.pojo.User;
import com.joe.utils.JwtTokenUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @program: springboot-security
 * @description:
 * @author: xiaoqiaohui
 * @create: 2020-09-29 15:49
 **/
@SpringBootTest
public class jwtTokenTest {

    @Test
    public void creatJwtTest(){
        User user = new User();
        user.setUsername("zhangsan");
        user.setPassword("123");

//        JwtUser jwtUser = new JwtUser(user);
//        String jwt = JwtTokenUtils.createToken(jwtUser,false);
//        Claims claims = Jwts.parser().setSigningKey("secret").parseClaimsJws(jwt).getBody();
//        String username = JwtTokenUtils.getUsername(jwt);
//        System.out.println(username);
    }

}
