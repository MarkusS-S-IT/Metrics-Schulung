package dec.sitconsulting.metrics.filter;

import dec.sitconsulting.metrics.wrapper.CountingHttpServletResponseWrapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ResponseSizeFilter implements Filter {
    @Autowired
    public ResponseSizeFilter() {
        // Default Constructor
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        if (response instanceof HttpServletResponse httpServletResponse) {
            CountingHttpServletResponseWrapper wrappedResponse =
                    new CountingHttpServletResponseWrapper(httpServletResponse);

            chain.doFilter(request, wrappedResponse);

            int responseSize = wrappedResponse.getContentSize();
            System.out.println("Response Größe: " + responseSize);

            wrappedResponse.flushToResponse();
        } else {
            chain.doFilter(request, response);
        }
    }
}
