import org.ginormous712.airline.dao.RadioOperatorDao;
import org.ginormous712.airline.model.RadioOperator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/radioOperators")
public class RadioOperatorServlet extends HttpServlet {

    private final RadioOperatorDao radioOperatorDao = new RadioOperatorDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<RadioOperator> radioOperators = radioOperatorDao.getRadioOperators();
        req.setAttribute("radioOperators", radioOperators);
        req.getRequestDispatcher("/radioOperators.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        int number = Integer.parseInt(req.getParameter("number"));
        String email = req.getParameter("email");

        RadioOperator radioOperator = new RadioOperator();
        radioOperator.setFirstName(firstName);
        radioOperator.setLastName(lastName);
        radioOperator.setNumber(number);
        radioOperator.setEmail(email);

        radioOperatorDao.addRadioOperator(radioOperator);
        resp.sendRedirect(req.getContextPath() + "/radioOperators");
    }
}
