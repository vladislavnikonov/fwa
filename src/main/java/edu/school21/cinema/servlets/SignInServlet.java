package edu.school21.cinema.servlets;

import edu.school21.cinema.models.Authentication;
import edu.school21.cinema.models.User;
import edu.school21.cinema.services.AuthenticationService;
import edu.school21.cinema.services.UserService;
import org.springframework.context.ApplicationContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SignInServlet", value = "/signIn")
public class SignInServlet extends HttpServlet {
    private UserService userService;
    private AuthenticationService authenticationService;

    @Override
    public void init(ServletConfig servletConfig) {
        ServletContext servletContext = servletConfig.getServletContext();
        ApplicationContext applicationContext = (ApplicationContext) servletContext.getAttribute("springContext");
        userService = applicationContext.getBean(UserService.class);
        this.authenticationService = applicationContext.getBean(AuthenticationService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/signIn.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        try {
            User user = userService.getUserByEmail(email, password);
            authenticationService.save(request.getRemoteAddr(), user.getId());
            session.setAttribute("user", user);
            List<Authentication> authentications = authenticationService.getAuthenticationByUserId(user.getId());
            session.setAttribute("authentications", authentications);
            response.sendRedirect("/fwa/profile");
        } catch (Exception e) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/signIn.jsp");
            request.setAttribute("error", e.getMessage());
            dispatcher.forward(request, response);
        }
    }
}
