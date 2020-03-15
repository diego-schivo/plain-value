package org.gradle.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class HelloServletTest {

	@Mock
	private HttpServletRequest request;
	@Mock
	private HttpServletResponse response;
	@Mock
	private RequestDispatcher requestDispatcher;

	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void doGet() throws Exception {
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);

		when(response.getWriter()).thenReturn(printWriter);

		new HelloServlet().doGet(request, response);

		assertEquals("Hello, World!", stringWriter.toString());
	}

	@Test
	public void doPostWithoutName() throws Exception {
		when(request.getRequestDispatcher("response.jsp")).thenReturn(requestDispatcher);

		new HelloServlet().doPost(request, response);

		verify(request).setAttribute("user", "World");
		verify(requestDispatcher).forward(request, response);
	}

	@Test
	public void doPostWithName() throws Exception {
		when(request.getParameter("name")).thenReturn("Dolly");
		when(request.getRequestDispatcher("response.jsp")).thenReturn(requestDispatcher);

		new HelloServlet().doPost(request, response);

		verify(request).setAttribute("user", "Dolly");
		verify(requestDispatcher).forward(request, response);
	}
}
