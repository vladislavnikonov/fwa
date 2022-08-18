package edu.school21.cinema.filters;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AuthFilterImpl implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession();
        String uri = httpServletRequest.getRequestURI();

        if (isAuthPages(uri) && isAuthorized(session)) {
            httpServletResponse.sendRedirect("/fwa/profile");
            return;
        } else if (!isAuthPages(uri) && !isAuthorized(session)) {
            httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        chain.doFilter(request, response);
    }

    private boolean isAuthorized(HttpSession session) {
        return session.getAttribute("user") != null;
    }

    private boolean isAuthPages(String uri) {
        return uri.startsWith("/fwa/signIn") || uri.startsWith("/fwa/signUp");
    }

}
