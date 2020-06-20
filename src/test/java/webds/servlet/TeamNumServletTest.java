package webds.servlet;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static org.mockito.Mockito.*;

public class TeamNumServletTest {
    @Mock private HttpServletRequest request;
    @Mock private HttpServletResponse response;
    @Mock private RequestDispatcher requestDispatcher;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void doPostWithoutName() throws Exception {
        when(request.getRequestDispatcher("views/TeamNumView.jsp"))
            .thenReturn(requestDispatcher);

        new TeamNumServlet().doPost(request, response);

        verify(request).setAttribute("teamNum", "0000");
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void doPostWithName() throws Exception {
        when(request.getParameter("num")).thenReturn("862");
        when(request.getRequestDispatcher("views/TeamNumView.jsp"))
            .thenReturn(requestDispatcher);

        new TeamNumServlet().doPost(request, response);

        verify(request).setAttribute("teamNum", "862");
        verify(requestDispatcher).forward(request,response);
    }
}