package KSQL;

import io.confluent.common.Configurable;
import io.confluent.ksql.function.udf.UdfDescription;

import java.util.Map;

@UdfDescription(name = "Timestamp",
        author = "ChiQuang",
        version = "1.0.2",
        description = "A custom UDF for compare timestamp")
public class TimeStampUDF implements Configurable {
    private int baseValue;
    @Override
    public void configure(Map<String, ?> map) {

    }
}
