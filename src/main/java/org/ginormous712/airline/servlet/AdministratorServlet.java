package org.ginormous712.airline.servlet;
import org.ginormous712.airline.dao.AdministratorDao;
import org.ginormous712.airline.model.Administrator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/administrators")
public class AdministratorServlet extends HttpServlet {

    private final AdministratorDao administratorDao = new AdministratorDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Administrator> administrators = administratorDao.getAdministrators();
        req.setAttribute("administrators", administrators);
        req.getRequestDispatcher("/administrators.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        int number = Integer.parseInt(req.getParameter("number"));
        String email = req.getParameter("email");

        Administrator administrator = new Administrator();
        administrator.setFirstName(firstName);
        administrator.setLastName(lastName);
        administrator.setNumber(number);
        administrator.setEmail(email);

        administratorDao.addAdministrator(administrator);
        resp.sendRedirect(req.getContextPath() + "/administrators");
    }
}
