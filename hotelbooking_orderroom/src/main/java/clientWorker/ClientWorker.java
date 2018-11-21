package clientWorker;

import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.client.ExternalTaskClientBuilder;
import org.camunda.bpm.client.impl.ExternalTaskClientBuilderImpl;
import org.camunda.bpm.client.impl.ExternalTaskClientImpl;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.ObjectValue;

import java.util.HashMap;
import java.util.Map;

public class ClientWorker {

    public void runClient() {
        ExternalTaskClient client = new ExternalTaskClientBuilderImpl().baseUrl("http://localhost:8080/engine-rest").build();

        // subscribe to an external task topic as specified in the process
        client.subscribe("get-guest-id")
            .lockDuration(1000) // the default lock duration is 20 seconds, but you can override this
            .handler((externalTask, externalTaskService) -> {
                // Put your business logic here

                // Get a process variable
                System.out.println("masuk get guest id");
                Boolean exist = true;
                ObjectValue isExist = Variables
                    .objectValue(exist)
                    .serializationDataFormat("application/xml")
                    .create();

                Map<String, Object> map = new HashMap<>();
                map.put("status", isExist);
                // Complete the task
                externalTaskService.complete(externalTask, map);
            })
            .open();
        client.subscribe("create-guest")
            .lockDuration(1000) // the default lock duration is 20 seconds, but you can override this
            .handler((externalTask, externalTaskService) -> {
                // Put your business logic here

                // Get a process variable
                System.out.println("masuk create guest");
                // Complete the task
                externalTaskService.complete(externalTask);
            })
            .open();
        client.subscribe("create-booking-data")
            .lockDuration(1000) // the default lock duration is 20 seconds, but you can override this
            .handler((externalTask, externalTaskService) -> {
                // Put your business logic here

                // Get a process variable
                System.out.println("masuk create booking data");
                // Complete the task
                externalTaskService.complete(externalTask);
            })
            .open();
    }
}

