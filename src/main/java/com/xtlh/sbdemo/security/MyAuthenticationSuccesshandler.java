package com.xtlh.sbdemo.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @作者 陈坤
 * @创建日期 2018/5/14
 * @功能描述 保存登录前的请求资源，登录后重定向至该资源
 */
@Service
public class MyAuthenticationSuccesshandler extends SavedRequestAwareAuthenticationSuccessHandler {

    /*@Autowired
    private LogService logService;

    @Autowired
    private UserService userService;*/

//    private final static Logger logger = (Logger) LoggerFactory.getLogger(MyAuthenticationSuccesshandler.class);


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException
    {
        RequestCache requestCache = new HttpSessionRequestCache();
//        MyUserDetailService userDetails = (MyUserDetailService) authentication.getPrincipal();
       /* User user = null;
        try {

            user = userService.getUserByMail(userDetails.getUsername());
            request.getSession().setAttribute("username",user.getUsername());
            request.getSession().setAttribute("userId",user.getId());
            logService.addLog("myUserDetailsService.loadUserByUsername","认证模块","低",
                    "登录","成功","邮箱为" + user.getMail() + "的用户登录成功，登录IP为" + request.getRemoteAddr(),user.getId());
        }catch (Exception e){
            logService.addLog("MyAuthenticationSuccessHandler.onAuthenticationSuccess","认证模块","高","登录","失败","保存session失败,mail为" + user.getMail(),user.getId());
        }*/

        String url = null;
        SavedRequest savedRequest = requestCache.getRequest(request,response);
        if(savedRequest != null){
            url = savedRequest.getRedirectUrl();
        }
        if(url == null){
            getRedirectStrategy().sendRedirect(request,response,"/home");
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
