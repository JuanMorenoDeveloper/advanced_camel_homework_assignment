package com.redhat.usecase.service;

import javax.ws.rs.core.Response;
import com.customer.app.Person;

public interface DEIMService {
  Response addPerson(Person person);
  String test();
}
