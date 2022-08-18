package edu.school21.cinema.servlets;

import edu.school21.cinema.models.Image;
import edu.school21.cinema.models.User;
import edu.school21.cinema.services.ImageService;
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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProfileServlet", value = "/profile")
public class ProfileServlet extends HttpServlet {
    private ImageService imageService;

    @Override
    public void init(ServletConfig servletConfig) {
        ServletContext servletContext = servletConfig.getServletContext();
        ApplicationContext applicationContext = (ApplicationContext) servletContext.getAttribute("springContext");
        this.imageService = applicationContext.getBean(ImageService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        try {
            List<Image> images = imageService.getImages(user.getId());
            session.setAttribute("images", images);
            String lastPhoto = File.separator + imageService.getNameLastPhoto(images);
            session.setAttribute("avatar", imageService.getHashPhoto(user.getId(), lastPhoto));
        } catch (FileNotFoundException e) {
            request.setAttribute("error", e.getMessage());
        } finally {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/profile.jsp");
            dispatcher.forward(request, response);
        }
    }
}
