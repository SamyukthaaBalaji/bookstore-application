package com.bookstore.api_gateway_book.filter;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class RateLimiterFilter
        extends AbstractGatewayFilterFactory<RateLimiterFilter.Config> {

    private final AtomicInteger requestCount = new AtomicInteger(0);
    private final AtomicLong windowStart =
            new AtomicLong(System.currentTimeMillis());

    public RateLimiterFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {

        return (exchange, chain) -> {

            long currentTime = System.currentTimeMillis();

            // Reset every 10 seconds
            if (currentTime - windowStart.get() >= 10000) {

                requestCount.set(0);
                windowStart.set(currentTime);
            }

            int count = requestCount.incrementAndGet();

            if (count > 10) {

                exchange.getResponse()
                        .setStatusCode(HttpStatus.TOO_MANY_REQUESTS);

                return exchange.getResponse().setComplete();
            }

            return chain.filter(exchange);
        };
    }

    public static class Config {
    }
}