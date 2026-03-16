package dev.sitconsulting.metrics.wrapper;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.WriteListener;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class CountingHttpServletResponseWrapper extends HttpServletResponseWrapper {
    private final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    private ServletOutputStream outputStream;
    private PrintWriter writer;

    public CountingHttpServletResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    @Override
    public ServletOutputStream getOutputStream() {
        if (this.writer != null) {
            throw new IllegalStateException("getWriter() has already been called for this response");
        }

        if (this.outputStream == null) {
            this.outputStream = new CountingServletOutputStream(buffer);
        }
        return this.outputStream;
    }

    @Override
    public PrintWriter getWriter() {
        if (this.outputStream != null) {
            throw new IllegalStateException("getOutputStream() has already been called for this response");
        }

        if (this.writer == null) {
            this.writer = new PrintWriter(buffer, true, StandardCharsets.UTF_8);
        }
        return this.writer;
    }

    public int getContentSize() {
        try {
            if (writer != null) writer.flush();
            return buffer.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public void flushToResponse() throws IOException {
        if (writer != null) writer.flush();
        getResponse().getOutputStream().write(buffer.toByteArray());
        getResponse().getOutputStream().flush();
    }

    private static class CountingServletOutputStream extends ServletOutputStream {
        private final ByteArrayOutputStream buffer;

        public CountingServletOutputStream(ByteArrayOutputStream buffer) {
            this.buffer = buffer;
        }

        @Override
        public void write(int b) {
            buffer.write(b);
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setWriteListener(WriteListener listener) {
        }
    }
}
