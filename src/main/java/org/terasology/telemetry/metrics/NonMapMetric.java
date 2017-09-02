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
import org.terasology.config.facade.TelemetryConfiguration;
import org.terasology.context.Context;
import org.terasology.telemetry.TelemetryCategory;
import org.terasology.telemetry.TelemetryField;

import java.util.Map;
import java.util.Optional;

/**
 * This is the a metric to test the telemetry api used in a module.
 */
@TelemetryCategory(id = "nonMapMetric",
        displayName = "${TutorialTelemetry:menu#non-map-metric}",
        isOneMapMetric = false
)
public class NonMapMetric extends Metric {

    public static final String SCHEMA_NON_MAP = "iglu:org.terasology/nonMap/jsonschema/1-0-0";

    @TelemetryField
    private String testField;

    @TelemetryField
    private String sayHello;

    private Context context;

    public NonMapMetric(Context context) {
        this.context = context;
        testField = "This is a non map metric";
        sayHello = "Say hello";
    }

    public Optional<Unstructured> getUnstructuredMetric() {
        createTelemetryFieldToValue();
        TelemetryConfiguration telemetryConfiguration = context.get(TelemetryConfiguration.class);
        Map<String, Object> filteredMap = filterMetricMap(telemetryConfiguration);
        return getUnstructuredMetric(SCHEMA_NON_MAP, filteredMap);
    }
}
