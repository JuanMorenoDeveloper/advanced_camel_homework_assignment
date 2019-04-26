package com.redhat.customer.translate;

import com.customer.app.Person;
import com.sun.mdm.index.webservice.CallerInfo;
import com.sun.mdm.index.webservice.ExecuteMatchUpdate;
import com.sun.mdm.index.webservice.PersonBean;
import com.sun.mdm.index.webservice.SystemPerson;
import org.apache.camel.Converter;
import org.apache.camel.Exchange;
import org.apache.camel.TypeConversionException;
import org.apache.camel.support.TypeConverterSupport;

@Converter
public class TransformToExecuteMatch extends TypeConverterSupport {

  @Override
  public <T> T convertTo(Class<T> aClass, Exchange exchange, Object value)
      throws TypeConversionException {
    Person person = (Person) value;
    SystemPerson systemPerson = new SystemPerson();
    PersonBean personBean = new PersonBean();

    // we only set the Father's name and Gender
    // Any of the other person objects could be set here
    personBean.setFirstName(person.getLegalname().getGiven());
    personBean.setFatherName(person.getFathername());
    personBean.setGender(person.getGender().getCode());
    systemPerson.setPerson(personBean);

    // These only show up in the logs and can be anything
    // We set the user to Xlate here to know the ESB was used
    CallerInfo callerInfo = new CallerInfo();
    callerInfo.setApplication("App");
    callerInfo.setApplicationFunction("Function");
    callerInfo.setAuthUser("Xlate");

    ExecuteMatchUpdate executeMatchUpdate = new ExecuteMatchUpdate();
    executeMatchUpdate.setCallerInfo(callerInfo);
    executeMatchUpdate.setSysObjBean(systemPerson);

    if (exchange != null) {
      exchange.getOut().setBody(executeMatchUpdate);
    }

    return (T) executeMatchUpdate;
  }

}
