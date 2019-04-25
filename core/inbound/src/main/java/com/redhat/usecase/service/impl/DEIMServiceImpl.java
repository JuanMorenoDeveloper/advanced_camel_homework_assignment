package com.redhat.usecase.service.impl;

import com.customer.app.Person;
import com.redhat.usecase.service.DEIMService;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Path("/cxf/demos")
public class DEIMServiceImpl implements DEIMService {


  @Autowired
  private CamelContext context;

  private ProducerTemplate template;

  @Override
  @POST
  @Path("/match")
  @Consumes(MediaType.APPLICATION_XML)
  public Response addPerson(Person person) {

    template = context.createProducerTemplate();

    String requestMsg = (String) template.requestBody("direct:start", person);

    return Response.ok(requestMsg).build();

  }

  @GET
  @Path("/test")
  @Produces(MediaType.TEXT_PLAIN)
  public String test() {
    return "OK";
  }

}
