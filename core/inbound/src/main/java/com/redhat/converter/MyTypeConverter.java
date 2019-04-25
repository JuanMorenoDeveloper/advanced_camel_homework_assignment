package com.redhat.converter;

import com.customer.app.Person;
import com.sun.mdm.index.webservice.CallerInfo;
import com.sun.mdm.index.webservice.ExecuteMatchUpdate;
import com.sun.mdm.index.webservice.PersonBean;
import com.sun.mdm.index.webservice.SystemPerson;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.apache.camel.Exchange;
import org.apache.camel.TypeConversionException;
import org.apache.camel.support.TypeConverterSupport;

public class MyTypeConverter extends TypeConverterSupport {


    //Convert Person Object into SOAP
    @Override
    public <T> T convertTo(Class<T> aClass, Exchange exchange, Object o) throws TypeConversionException {


        ExecuteMatchUpdate matchUpdate = new ExecuteMatchUpdate();
        Person person = (Person) o;


        CallerInfo callerInfo = new CallerInfo();
        callerInfo.setApplication("stuff");
        callerInfo.setApplicationFunction("stuff2");
        callerInfo.setAuthPassword("stuff3");
        callerInfo.setAuthUser("stuff4");
        callerInfo.setExecutionCycleId("stuff5");
        callerInfo.setSystem("stuff6");
        callerInfo.setUser("stuff7");

        SystemPerson sysObjBean = new SystemPerson();

        XMLGregorianCalendar date = null;
        try {
            date = DatatypeFactory.newInstance().newXMLGregorianCalendar(2015,12,17,11,10,30,10,5);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        sysObjBean.setCreateDateTime(date);
        sysObjBean.setCreateFunction("thisFunkyFunction");
        sysObjBean.setCreateUser("thisGuy");
        sysObjBean.setLocalId(person.getLocalid());
        sysObjBean.setPerson(new PersonBean());
        sysObjBean.setStatus("myStatus");
        sysObjBean.setSystemCode(person.getSystemcode());
        sysObjBean.setSystemCodeDescription("descriptor");
        sysObjBean.setUpdateDateTime(date);
        sysObjBean.setUpdateFunction("thisUpdate");
        sysObjBean.setUpdateUser("thisUser");


        matchUpdate.setCallerInfo(callerInfo);
        matchUpdate.setSysObjBean(sysObjBean);


        return (T) matchUpdate;

    }
}
