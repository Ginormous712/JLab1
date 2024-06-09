package org.ginormous712.airline.servlet;

import org.ginormous712.airline.dao.NavigatorDao;
import org.ginormous712.airline.model.Navigator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/navigators")
public class NavigatorServlet extends HttpServlet {

    private final NavigatorDao navigatorDao = new NavigatorDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Navigator> navigators = navigatorDao.getNavigators();
        req.setAttribute("navigators", navigators);
        req.getRequestDispatcher("/navigators.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        int number = Integer.parseInt(req.getParameter("number"));
        String email = req.getParameter("email");

        Navigator navigator = new Navigator();
        navigator.setFirstName(firstName);
        navigator.setLastName(lastName);
        navigator.setNumber(number);
        navigator.setEmail(email);

        navigatorDao.addNavigator(navigator);
        resp.sendRedirect(req.getContextPath() + "/navigators");
    }
}
