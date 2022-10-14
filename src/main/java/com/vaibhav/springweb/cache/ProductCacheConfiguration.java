package com.vaibhav.springweb.cache;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;

@Configuration
public class ProductCacheConfiguration {

	@Bean
	public Config cacheConfig() {
		return new Config()
				.setInstanceName("Hazel-instance")
				.addMapConfig( new MapConfig()
						.setName("Product-cache")
						.setTimeToLiveSeconds(3000));
	}
}
