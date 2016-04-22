package com.netflix.eureka.resources;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

public abstract class AbstractCORSResource {
    protected String _corsHeaders;

    protected Response makeCORS(ResponseBuilder req, String returnMethod) {
       ResponseBuilder rb = req.header("Access-Control-Allow-Origin", "*")
          .header("Access-Control-Allow-Methods", "GET, POST, OPTIONS, DELETE, PUT");

       if (!"".equals(returnMethod)) {
          rb.header("Access-Control-Allow-Headers", returnMethod);
       }

       return rb.build();
    }

    protected Response makeCORS(ResponseBuilder req) {
       return makeCORS(req, _corsHeaders);
    }

    @OPTIONS
    public Response preflight(@HeaderParam("Access-Control-Request-Headers") String requestH) {
        _corsHeaders = requestH;
        return makeCORS(Response.ok(), requestH);
    }
}
