package com.company.app.program.utils;

import com.netfective.bluage.gapwalk.rt.provider.MessageRegistry;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/** Load JSON representations of messages (as extracted by Analyzer) */
@Component
public class MessageLoader {

	/**
	 * The Constant logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(MessageLoader.class);

	/**
	 * The Constant MESSAGE_DIR_NAME.
	 */
	private static final String MESSAGE_DIR_NAME = "messages";
	
	@Autowired
	private ApplicationContext applicationContext;

	/** Search for /messages/** / *.properties files */
	@PostConstruct
	public void registerMessages() throws IOException {

		Resource[] resources;
		try {
			resources = applicationContext.getResources("classpath:/" + MESSAGE_DIR_NAME + "/**/*.properties");
		} catch (FileNotFoundException e) {
			LOGGER.info("No messages folder; nothing to load");
			return;
		}

		for (Resource resource : resources) {
			try (InputStream is = resource.getInputStream()) {

				// Read and parse resource
				Reader data = new InputStreamReader(is, Charset.forName("UTF-8"));
				Properties prop = new Properties();
				prop.load(data);

				// Add to the registry
				MessageRegistry.register(resource, MESSAGE_DIR_NAME, prop);

			} catch (Exception e) {
				// Do not halt loader
				LOGGER.error("Error while loading message file " + resource.getFilename(), e);
			}
		}
	}

	/**
	 * Unregister all messages previously added.
	 */
	@PreDestroy
	public void unregisterMessages() {
		// TODO
	}
}
