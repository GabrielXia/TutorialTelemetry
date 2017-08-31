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

import org.terasology.config.facade.TelemetryConfiguration;
import org.terasology.context.Context;
import org.terasology.entitySystem.systems.BaseComponentSystem;
import org.terasology.entitySystem.systems.RegisterSystem;
import org.terasology.registry.In;
import org.terasology.telemetry.metrics.ApiTestMapMetric;
import org.terasology.telemetry.metrics.TelemetryApiTestMetric;

/**
 * The a component system in module to test telemetry api.
 */
@RegisterSystem
public class TelemetryApiTestSystem extends BaseComponentSystem {

    private final String trackerNamespace = this.getClass().toString();

    @In
    private TelemetryConfiguration telemetryConfiguration;

    @In
    private Context context;


    @Override
    public void postBegin() {
        if (telemetryConfiguration.isTelemetryEnabled()) {
            TelemetryUtils.fetchMetricAndSend(context, TelemetryApiTestMetric.class, trackerNamespace);
            TelemetryUtils.fetchMetricAndSend(context, ApiTestMapMetric.class, trackerNamespace);
        }
    }
}
