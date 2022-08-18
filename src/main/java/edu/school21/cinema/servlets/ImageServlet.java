package edu.school21.cinema.servlets;

import edu.school21.cinema.models.User;
import edu.school21.cinema.services.ImageService;
import org.springframework.context.ApplicationContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.FileNotFoundException;
import java.io.IOException;

@WebServlet(name = "ImageServlet", value = "/images/*")
@MultipartConfig
public class ImageServlet extends HttpServlet {
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
            String encode = imageService.getHashPhoto(user.getId(), request.getPathInfo());
            request.setAttribute("image", encode);
        } catch (FileNotFoundException e) {
            request.setAttribute("error", e.getMessage());
        } finally {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/image.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Part part = request.getPart("image");
        imageService.saveImage(part, user.getId());
        response.sendRedirect("/fwa/profile");
    }
}
