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
package org.terasology.telemetry;

import com.snowplowanalytics.snowplow.tracker.emitter.Emitter;
import com.snowplowanalytics.snowplow.tracker.events.Unstructured;
import org.terasology.config.Config;
import org.terasology.config.TelemetryConfig;
import org.terasology.context.Context;
import org.terasology.entitySystem.systems.BaseComponentSystem;
import org.terasology.entitySystem.systems.RegisterSystem;
import org.terasology.registry.In;
import org.terasology.telemetry.metrics.TelemetryApiTestMetric;

import java.util.Map;

/**
 * The a component system in module to test telemetry api.
 */
@RegisterSystem
public class TelemetryApiTestSystem extends BaseComponentSystem {

    private final String trackerNamespace = this.getClass().toString();

    @In
    private Emitter emitter;

    @In
    private Metrics metrics;

    @In
    private Context context;

    private TelemetryApiTestMetric telemetryApiTestMetric;

    @Override
    public void initialise() {
        telemetryApiTestMetric = new TelemetryApiTestMetric();
    }

    @Override
    public void postBegin() {
        sendTelemetryApiTestMetric();
    }

    private void sendTelemetryApiTestMetric() {
        TelemetryConfig telemetryConfig = context.get(Config.class).getTelemetryConfig();
        Map<String, Boolean> bindingMap = telemetryConfig.getMetricsUserPermissionConfig().getBindingMap();
        if (telemetryConfig.isTelemetryEnabled()) {
            Unstructured unstructured = telemetryApiTestMetric.getUnstructuredMetric();
            TelemetryUtils.trackMetric(emitter, trackerNamespace, unstructured, telemetryApiTestMetric, bindingMap);
        }
    }
}
