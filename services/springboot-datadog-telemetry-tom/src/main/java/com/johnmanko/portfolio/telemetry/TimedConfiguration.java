package com.johnmanko.portfolio.telemetry;

import io.micrometer.common.annotation.ValueExpressionResolver;
import io.micrometer.common.annotation.ValueResolver;
import io.micrometer.core.aop.MeterTagAnnotationHandler;
import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Arrays;

/**
 * Configuration class that creates a TimedAspect bean and sets a custom MeterTagAnnotationHandler on it.
 * @see <a href="https://docs.micrometer.io/micrometer/reference/concepts/timers.html#_the_timed_annotation">The @Timed Annotation</a>
 * @see <a href="https://docs.micrometer.io/micrometer/reference/concepts/timers.html#_metertag_on_method_parameters">@MeterTag on Method Parameters</a>
 */
@Configuration
public class TimedConfiguration {

    @Bean
    public TimedAspect timedAspect(MeterRegistry registry) {

        // https://docs.micrometer.io/micrometer/reference/concepts/timers.html#_pause_detection
        //Duration sleepInterval = Duration.ofMillis(150);
        //Duration pauseThreshold = Duration.ofMillis(150);
        //registry.config().pauseDetector(new ClockDriftPauseDetector(sleepInterval, pauseThreshold));
        //registry.config().pauseDetector(new NoPauseDetector());

        registry.config().commonTags(
                Arrays.asList(
                        Tag.of("stack", "dev"),
                        Tag.of("region", "us-east-1")
                )
        );

        return getTimedAspect(registry);
    }

    private static TimedAspect getTimedAspect(MeterRegistry registry) {
        TimedAspect timedAspect = new TimedAspect(registry);

        ValueResolver valueResolver = parameter -> "Value from my customer timed resolver [" + parameter + "]";

        // Example of a ValueExpressionResolver that uses Spring Expression Language
        ValueExpressionResolver valueExpressionResolver = new TimedValueExpressionResolver();

        // Setting the handler on the aspect
        timedAspect.setMeterTagAnnotationHandler(
                new MeterTagAnnotationHandler(
                        aClass -> valueResolver,
                        aClass -> valueExpressionResolver)
        );
        return timedAspect;
    }

}