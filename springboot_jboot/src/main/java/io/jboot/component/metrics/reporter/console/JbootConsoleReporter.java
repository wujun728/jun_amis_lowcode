/**
 * Copyright (c) 2015-2017, Michael Yang 杨福海 (fuhai999@gmail.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.jboot.component.metrics.reporter.console;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import io.jboot.component.metrics.JbootMetricsReporter;

import java.util.concurrent.TimeUnit;

/**
 * @author Wujun
 * @version V1.0
 * @Package io.jboot.component.metrics.reporter.console
 */
public class JbootConsoleReporter implements JbootMetricsReporter {
    @Override
    public void report(MetricRegistry metricRegistry) {
        final ConsoleReporter reporter = ConsoleReporter.forRegistry(metricRegistry)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
        reporter.start(1, TimeUnit.MINUTES);
    }
}