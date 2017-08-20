/*
 * Copyright 2017 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.terasology.telemetry.metrics;

import com.snowplowanalytics.snowplow.tracker.events.Unstructured;
import com.snowplowanalytics.snowplow.tracker.payload.SelfDescribingJson;
import org.terasology.context.Context;
import org.terasology.telemetry.TelemetryCategory;
import org.terasology.telemetry.TelemetryField;

/**
 * This is the a metric to test the telemetry api used in a module.
 */
@TelemetryCategory(id = "telemetryApiTestMetric",
        displayName = "telemetryApiTestMetric",
        isOneMapMetric = false
)
public class TelemetryApiTestMetric extends Metric {

    public static final String SCHEMA_TELEMETRY_API = "iglu:org.terasology/telemetryApiTest/jsonschema/1-0-0";

    @TelemetryField
    private String testField;

    public TelemetryApiTestMetric(Context context) {
        super(context);
        testField = "for test";
    }

    public Unstructured getUnstructuredMetric() {
        getFieldValueMap();
        SelfDescribingJson systemContextData = new SelfDescribingJson(SCHEMA_TELEMETRY_API, metricMap);

        return Unstructured.builder()
                .eventData(systemContextData)
                .build();
    }
}
