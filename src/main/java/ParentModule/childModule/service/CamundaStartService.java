package ParentModule.childModule.service;

import ParentModule.childModule.model.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.ObjectValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CamundaStartService {

    @Autowired
    RuntimeService runtimeService;

    public void startProcessByMessage(Person person)
    {
        runtimeService.createMessageCorrelation("msg-s-1")
                .setVariable("springname",person.getName())
                .setVariable("gender",person.getGender())
                .correlate();
    }

    public void startProcessByMessage2(Person person) throws JsonProcessingException {
        String name = person.getName();
        String gender = person.getGender();
        ObjectMapper obj = new ObjectMapper();
        String json = obj.writeValueAsString(person);

        ObjectValue typedPersonValue = Variables.objectValue(person).serializationDataFormat("application/json").create();

        runtimeService.createMessageCorrelation("msg-s-2")
                .setVariable("name",name)
                .setVariable("customerSerialized",typedPersonValue)
                .setVariable("customerJson",json)
                .correlate();
    }
}
