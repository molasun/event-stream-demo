// camel-k: dependency=camel-jackson configmap=costcenter-config

import org.apache.camel.builder.RouteBuilder;
import java.util.Map;
import java.util.HashMap;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.Body;
//FILTER EIP


public class CostCenter extends RouteBuilder{

    @Override
    public void configure() throws Exception{

        from("amqp:topic:mytopic?subscriptionDurable=false")
        .unmarshal(new JacksonDataFormat(Map.class))
        .filter().method(MaskDetail.class, "mask")
        .log("${body}")
        .marshal().json()
        .to("kafka:costcenter?groupId=costsender")
        ;

        
    }

    static class MaskDetail {
        public static Map mask(@Body Map farmharvest) {
            farmharvest.remove("harvest");
            return farmharvest;
        }
    }
}

