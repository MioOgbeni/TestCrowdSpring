package cz.spitsoft.testcrowd.utils;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockHttpServletRequest;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class BaseUrlTest {

    @Autowired
    MockHttpServletRequest request;

    @Before
    public void setUp() throws Exception {
        request = new MockHttpServletRequest();
        request.addHeader(HttpHeaders.HOST, "testHost.com");
        request.setLocalPort(8080); // e.g. 8081
        request.setRemoteAddr("127.0.0.1"); // e.g. 127.0.0.1
    }

    @Test
    public void testGetBaseUrl() throws IOException, ServletException {
        String result = URLUtils.getBaseURl(request);

        assertEquals(result, request.getRequestURL().substring(0, request.getRequestURL().length() - request.getRequestURI().length()) + request.getContextPath());
    }
}
