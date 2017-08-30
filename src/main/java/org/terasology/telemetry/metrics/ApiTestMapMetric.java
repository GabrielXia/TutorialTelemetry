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
import org.terasology.entitySystem.entity.EntityRef;
import org.terasology.logic.players.LocalPlayer;
import org.terasology.registry.CoreRegistry;
import org.terasology.telemetry.GamePlayStatsComponent;
import org.terasology.telemetry.TelemetryCategory;
import org.terasology.telemetry.TelemetryField;

import java.util.Map;
import java.util.Optional;

@TelemetryCategory(id = "apiTestMapMetric",
        displayName = "${TelemetryApiTest:menu#api-test-map}",
        isOneMapMetric = true
)
public class ApiTestMapMetric extends Metric {

    public static final String SCHEMA_API_TEST_MAP = "iglu:org.terasology/telemetryApiTest/jsonschema/1-0-0";

    @TelemetryField
    private Map<String, String> testMap;

    @Override
    public Optional<Unstructured> getUnstructuredMetric() {
        createTelemetryFieldToValue();
        return getUnstructuredMetric(SCHEMA_API_TEST_MAP, telemetryFieldToValue);
    }

    @Override
    public Map<String, ?> createTelemetryFieldToValue() {
        telemetryFieldToValue.put("the first say", "hello");
        telemetryFieldToValue.put("the second say", "hi");
        return telemetryFieldToValue;
    }
}
