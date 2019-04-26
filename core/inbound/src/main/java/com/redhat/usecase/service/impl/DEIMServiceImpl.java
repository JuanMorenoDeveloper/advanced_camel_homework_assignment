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
import org.springframework.stereotype.Service;

@Service
@Path("/deim")
public class DEIMServiceImpl implements DEIMService {

  private CamelContext context;

  public DEIMServiceImpl() {
  }

  public DEIMServiceImpl(CamelContext context) {
    this.context = context;
  }

  @Override
  @POST
  @Path("/add")
  @Consumes(MediaType.APPLICATION_XML)
  @Produces(MediaType.TEXT_PLAIN)
  public Response addPerson(Person person) {

    ProducerTemplate template = context.createProducerTemplate();

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
