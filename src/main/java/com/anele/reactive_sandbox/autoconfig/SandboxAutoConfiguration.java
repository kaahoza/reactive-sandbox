package com.anele.reactive_sandbox.autoconfig;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.config.EnableWebFlux;

import java.beans.BeanProperty;

@AutoConfiguration
@ConditionalOnClass(EnableWebFlux.class)
public class SandboxAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public MetricsBannerPrinter bannerPrinter() {
        MetricsBannerPrinter printer = new MetricsBannerPrinter();
        printer.printStartupBanner();
        return printer;

    }
}
