package ParentModule.childModule.component;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.springframework.stereotype.Component;

@Component("apiDelegate")
public class ApiDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        try {
            // Your API call logic here
            callExternalApi();
        } catch (Exception e) {
            // Log the error if needed
            System.out.println("API call failed: " + e.getMessage());


            // Set error message as a process variable
            execution.setVariable("apiErrorMessage", e.getMessage());


            // Throw a BPMN error to trigger the boundary event
            throw new BpmnError("API_ERROR", "API call failed");
        }
    }

    private void callExternalApi() {
        // Simulate API call
        throw new RuntimeException("Simulated API failure");
    }
}

