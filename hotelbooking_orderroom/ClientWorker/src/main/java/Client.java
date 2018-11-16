import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.client.ExternalTaskClientBuilder;
import org.camunda.bpm.client.impl.ExternalTaskClientBuilderImpl;

public class Client {

    public static void main(String[] args) {
        ExternalTaskClient client = ExternalTaskClient.create()
            .baseUrl("http://localhost:8080/engine-rest")
            .build();

        // subscribe to an external task topic as specified in the process
        client.subscribe("charge-card")
            .lockDuration(1000) // the default lock duration is 20 seconds, but you can override this
            .handler((externalTask, externalTaskService) -> {
                // Put your business logic here

                // Get a process variable

                // Complete the task
                externalTaskService.complete(externalTask);
            })
            .open();
    }
}
