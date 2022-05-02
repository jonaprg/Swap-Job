package tk.swapjob.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import tk.swapjob.security.jwt.JwtUtils;

import javax.servlet.http.HttpServletRequest;

public class Utils {
    public static String REGEX_EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static String getUserFromToken(JwtUtils jwtUtils) {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                        .getRequest();
        String token = JwtUtils.parseJwt(request);
        return jwtUtils.getUserNameFromJwtToken(token);
    }
}
