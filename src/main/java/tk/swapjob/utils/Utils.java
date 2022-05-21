package tk.swapjob.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import tk.swapjob.security.jwt.JwtUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

public class Utils {
    public static String REGEX_EMAIL_PATTERN = "^[_A-Za-z\\d-+]+(\\.[_A-Za-z\\d-]+)*@"
            + "[A-Za-z\\d-]+(\\.[A-Za-z\\d]+)*(\\.[A-Za-z]{2,})$";

    public static String getUserFromToken(JwtUtils jwtUtils) {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                        .getRequest();
        String token = JwtUtils.parseJwt(request);
        return jwtUtils.getUserNameFromJwtToken(token);
    }

    public static boolean isEmailValid(String email) {
        return Pattern.compile(REGEX_EMAIL_PATTERN)
                .matcher(email)
                .matches();
    }
}
