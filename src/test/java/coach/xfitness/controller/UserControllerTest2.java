package coach.xfitness.controller;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class UserControllerTest2 extends Mockito {
    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    HttpSession session;

    @Mock
    RequestDispatcher requestDispatcher;

    @Before
    protected void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void doTest() throws Exception {
        when(request.getParameter("email")).thenReturn("jsmith@email.com");
        when(request.getParameter("password")).thenReturn("password123A");
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("/signin")).thenReturn(requestDispatcher);

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);

        when(response.getWriter()).thenReturn(printWriter);

        new UserController().doPost(request, response);
    }

}
