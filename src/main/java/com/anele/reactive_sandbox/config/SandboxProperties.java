package com.anele.reactive_sandbox.config;


import jakarta.validation.constraints.Min;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Configuration
@ConfigurationProperties(prefix = "app.sandbox")
@Validated
public class SandboxProperties {

    @Min(10) // Fails application startup if the delay parameter is configured under 10ms
    Long streamDelay;
    int maxBufferSize;

    public Long getStreamDelay() {
        return streamDelay;
    }
    public void setStreamDelay(Long streamDelay) {
        this.streamDelay = streamDelay;
    }
    public int getMaxBufferSize() {
        return maxBufferSize;
    }
    public void setMaxBufferSize(int maxBufferSize) {
        this.maxBufferSize = maxBufferSize;
    }
}
