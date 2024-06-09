import org.ginormous712.airline.dao.StewardessDao;
import org.ginormous712.airline.model.Stewardess;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/stewardesses")
public class StewardessServlet extends HttpServlet {

    private final StewardessDao stewardessDao = new StewardessDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Stewardess> stewardesses = stewardessDao.getStewardesses();
        req.setAttribute("stewardesses", stewardesses);
        req.getRequestDispatcher("/stewardesses.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        int number = Integer.parseInt(req.getParameter("number"));
        String email = req.getParameter("email");

        Stewardess stewardess = new Stewardess();
        stewardess.setFirstName(firstName);
        stewardess.setLastName(lastName);
        stewardess.setNumber(number);
        stewardess.setEmail(email);

        stewardessDao.addStewardess(stewardess);
        resp.sendRedirect(req.getContextPath() + "/stewardesses");
    }
}
