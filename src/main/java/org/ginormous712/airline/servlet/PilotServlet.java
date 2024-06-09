import org.ginormous712.airline.dao.PilotDao;
import org.ginormous712.airline.model.Pilot;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/pilots")
public class PilotServlet extends HttpServlet {

    private final PilotDao pilotDao = new PilotDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Pilot> pilots = pilotDao.getPilots();
        req.setAttribute("pilots", pilots);
        req.getRequestDispatcher("/pilots.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Логіка додавання нового пілота
    }
}
