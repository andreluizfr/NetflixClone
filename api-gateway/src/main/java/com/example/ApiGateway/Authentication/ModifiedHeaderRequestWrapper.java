package com.example.ApiGateway.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class ModifiedHeaderRequestWrapper extends HttpServletRequestWrapper{
    
    private final String modifiedHeader;

    public ModifiedHeaderRequestWrapper(HttpServletRequest request, String modifiedHeader) {
        super(request);
        
        this.modifiedHeader = modifiedHeader.toString();
    }

    @Override
    public String getHeader(String name) {

        if ("X-Logged-In-User".equalsIgnoreCase(name)) {
            return modifiedHeader;
        }
        // Se não for o cabeçalho desejado, usar o cabeçalho original
        return super.getHeader(name);
    }
}
