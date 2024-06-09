package org.ginormous712.airline.servlet;

import org.ginormous712.airline.dao.DispatcherDao;
import org.ginormous712.airline.model.Dispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/dispatchers")
public class DispatcherServlet extends HttpServlet {

    private final DispatcherDao dispatcherDao = new DispatcherDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Dispatcher> dispatchers = dispatcherDao.getDispatchers();
        req.setAttribute("dispatchers", dispatchers);
        req.getRequestDispatcher("/dispatchers.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        int number = Integer.parseInt(req.getParameter("number"));
        String email = req.getParameter("email");

        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setFirstName(firstName);
        dispatcher.setLastName(lastName);
        dispatcher.setNumber(number);
        dispatcher.setEmail(email);

        dispatcherDao.addDispatcher(dispatcher);
        resp.sendRedirect(req.getContextPath() + "/dispatchers");
    }
}
