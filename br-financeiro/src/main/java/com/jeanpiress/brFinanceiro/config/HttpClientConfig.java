package com.jeanpiress.brFinanceiro.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Bean;

public class HttpClientConfig {
	private int connectTimeout;
	private int readTimeout;

	public int getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public int getReadTimeout() {
		return readTimeout;
	}

	public void setReadTimeout(int readTimeout) {
		this.readTimeout = readTimeout;
	}

	@PostConstruct
	public void init() {
		Properties props = new Properties();
		try (InputStream input = new FileInputStream("config.properties")) {
			props.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}

		connectTimeout = Integer.parseInt(props.getProperty("connectTimeout"));
		readTimeout = Integer.parseInt(props.getProperty("readTimeout"));
	}

	@Bean
	public CloseableHttpClient httpClient() {
		RequestConfig config = RequestConfig.custom().setConnectTimeout(connectTimeout).setSocketTimeout(readTimeout)
				.build();

		return HttpClients.custom().setDefaultRequestConfig(config).build();
	}
}
