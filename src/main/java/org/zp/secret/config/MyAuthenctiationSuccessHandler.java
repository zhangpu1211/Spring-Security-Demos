package org.zp.secret.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.zp.secret.utils.RespBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class MyAuthenctiationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication auth) throws IOException, ServletException {
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        RespBean ok = RespBean.ok("登录成功!",auth.getPrincipal());
        String s = new ObjectMapper().writeValueAsString(ok);
        out.write(s);
        out.flush();
        out.close();
    }
}
