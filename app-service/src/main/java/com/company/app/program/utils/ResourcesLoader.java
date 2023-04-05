package com.company.app.program.utils;

import com.netfective.bluage.gapwalk.io.catalog.DatasetCatalog;
import com.netfective.bluage.gapwalk.rt.provider.LnkRegistry;
import com.netfective.bluage.gapwalk.rt.provider.ScriptRegistry;
import com.netfective.bluage.gapwalk.rt.provider.SqlRegistry;
import com.netfective.bluage.gapwalk.runtime.report.ReportRegistry;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/**
 * Load resources into the container resource registry.
 */
@Component
public class ResourcesLoader {

	/**
	 * The logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ResourcesLoader.class);

	/**
	 * Prefix of system property name for the folder containing resource.
	 */
	private static final String PREFIX_PATH_PROPERTY = "configuration.";

	@Autowired
	private ApplicationContext applicationContext;

	/**
	 * The Class ResourceRegistrable.
	 */
	abstract class ResourceRegistrable {
		
		String resourceType;
		String resourceDescription;
		String resourceFilesPathTemplate;
		boolean removeAllExtensions = true;
	    /**
		 * Loaded resources.
		 */		 
		private Map<String, Resource> registeredResources = new  HashMap<>();
		
		/**
		 * Register resource, given its identifier and the corresponding file.
		 * @param identifier the identifier
		 * @param file the file
		 */
		public abstract void registerResource(String identifier, File file);
		
		/**
		 * Unregister resource, given its identifier and the corresponding file.
		 * @param identifier the identifier
		 * @param file the file
		 */
		public abstract void unregisterResource(String identifier, File file);
		
		/**
		 * Instantiates a new resourceRegistrable.
		 * @param resourceType the resource type
		 * @param resourceDescription the resource description
		 * @param resourceFilesPathTemplate the resource files path template
		 */
		public ResourceRegistrable(String resourceType, String resourceDescription, String resourceFilesPathTemplate) {
			this.resourceType = resourceType;
			this.resourceDescription = resourceDescription;
			this.resourceFilesPathTemplate = resourceFilesPathTemplate;
		}
		
		/**
		 * Instantiates a new resourceRegistrable.
		 * @param resourceType the resource type
		 * @param resourceDescription the resource description
		 * @param resourceFilesPathTemplate the resource files path template
		 * @param removeAllExtensions remove all extensions or not for identifier
		 */
		public ResourceRegistrable(String resourceType, String resourceDescription, String resourceFilesPathTemplate, boolean removeAllExtensions) {
			this(resourceType, resourceDescription, resourceFilesPathTemplate);
			this.removeAllExtensions = removeAllExtensions;
		}
		
		/**
		 * Gets the resource type.
		 * @return the resource type
		 */
		public String getResourceType() {
			return resourceType;
		}
		
		/**
		 * Gets the resource description.
		 * @return the resource description
		 */
		public String getResourceDescription() {
			return resourceDescription;
		}
		
		/**
		 * Gets the resource file path template.
		 * @return the resource file path template
		 */
		public String getResourceFilesPathTemplate() {
			return resourceFilesPathTemplate;
		}
		
		/**
		 * Gets the registered resources.
		 * @return the registered resources
		 */
		public Map<String, Resource> getRegistredResources() {
			return registeredResources;
		}
		
		/**
		 * Register resources from configured path.
		 */
		public void register() {
			List<Resource> resources = findResources();
			if (resources == null || resources.isEmpty()) {
				LOGGER.info("No {} detected.", resourceDescription);
			} else {
				for (Resource resource : resources) {
					register(resource);
				}
			}
		}
		
		/**
		 * Register resource.
		 * @param resource the resource to be registered
		 */ 
		public void register(Resource resource) {
			File file;
			try {
				file = resource.getFile();
				String identifier = file.getName();
				while (!FilenameUtils.getExtension(identifier).isEmpty()) {
					identifier = FilenameUtils.removeExtension(identifier);
					if(!removeAllExtensions) {
						break;
					}
				}
				registerResource(identifier, file);
				registeredResources.put(identifier, resource);
			} catch (IOException e) {
				LOGGER.error("The file resource \"" + resource.getFilename() + "\"  cannot be resolved. \"" + resource.getFilename() + "\" registration aborted.");
				LOGGER.debug("IOException is ",e);
			}
		}
		
		/**
		 * Unregister registered resources.
		 */
		public void unregister() {
			for (Entry<String, Resource> entry : registeredResources.entrySet()) {
				try {
					unregisterResource(entry.getKey(), entry.getValue().getFile());
				} catch (IOException e) {
					String identifier = entry.getKey();
					LOGGER.warn("The file resource \"" + identifier + "\"  cannot be resolved. \"" + identifier + "\" unregistration aborted.");
					LOGGER.debug("IOException is ",e);
				}
			}
		}
		
		/**
		 * Find resources successively from <li>specified system property</li><li>working directory</li><li>classpath</li>
		 */ 
		private List<Resource> findResources() {
		
			List<Resource> resources;
			String specificResourceProperty = PREFIX_PATH_PROPERTY + resourceType;
			String resourcesPath = System.getProperty(specificResourceProperty);
			if (resourcesPath != null) {
				LOGGER.info("The parameter \"-D{}\" is set to {}. Looking for {} ...",specificResourceProperty, resourcesPath, resourceDescription);
				resources = getResources(resourcesPath);
				if (resources != null && !resources.isEmpty()) {
					return resources;
				}
			} else {
				LOGGER.info("The parameter \"-D{}\" is not set.", specificResourceProperty);
			}
			
			LOGGER.info("Looking for {} in the working directory...", specificResourceProperty);
			resources = getResources("file:." + resourceFilesPathTemplate);
			if (resources != null && !resources.isEmpty()) {
				return resources;
			}
			
			LOGGER.info("Looking for {} in the classpath...", specificResourceProperty);
			return getResources("classpath:"+ resourceFilesPathTemplate);
		}
		
		/**
	 * Gets the resources from the location pattern to resolve.
	 */
		private List<Resource> getResources(String locationPattern) {
		Resource [] resources = null;
		try {
			resources = applicationContext.getResources(locationPattern);
		} catch (IOException e) {
			LOGGER.debug("No resource found in " + locationPattern + ".", e);
		}
		return resources == null ? null : Arrays.asList(resources);
	}
	}
	
	ResourceRegistrable scriptsGroovyResourceRegistrable;
	ResourceRegistrable sqlResourceRegistrable;
	ResourceRegistrable scriptsDaemonsResourceRegistrable;
	ResourceRegistrable queriesDatabaseMappingResourceRegistrable;
	ResourceRegistrable jasperResourceRegistrable;
	ResourceRegistrable datasetCatalog;
	ResourceRegistrable lnkResourceRegistrable;
		
	@PostConstruct
	public void registerResources() {
		
	    /**
		 * Register all groovy script inside the groovy folder.
		 */	 		
		scriptsGroovyResourceRegistrable = new ResourceRegistrable("scripts", "groovy scripts", "/scripts/**/*.groovy") {
			
			@Override
			public void unregisterResource(String identifier, File file) {
				ScriptRegistry.unregisterScript(identifier, file);
			}
			
			@Override
			public void registerResource(String identifier, File file) {
				ScriptRegistry.registerScript(identifier, file);
			}
		};
		
	    /**
		 * Register all sql queries inside the sql folder.
		 */	 		
		sqlResourceRegistrable = new ResourceRegistrable("sql", "SQL Queries files", "/sql/**/*.sql") {
			
			@Override
			public void unregisterResource(String identifier, File file) {
				SqlRegistry.unregisterSQLQueryFile(identifier, file);
			}
			
			@Override
			public void registerResource(String identifier, File file) {
				SqlRegistry.registerSQLQuery(identifier, file);
			}
		};
		
	    /**
		 * Register all daemons scripts inside the daemons folder.
		 */	 		
		scriptsDaemonsResourceRegistrable = new ResourceRegistrable("daemons", "daemon scripts", "/daemons/**/*.groovy") {
			
			@Override
			public void unregisterResource(String identifier, File file) {
				ScriptRegistry.unregisterDaemon(identifier, file);
			}
			
			@Override
			public void registerResource(String identifier, File file) {
				ScriptRegistry.registerDaemon(identifier, file);
			}
		};
		
		/**
		 * Register queries database mapping file.
		 */	 		
		queriesDatabaseMappingResourceRegistrable = new ResourceRegistrable("databaseMapping", "Queries Database mapping file", "/sql/**/queries-database.mapping") {
			
			@Override
			public void unregisterResource(String identifier, File file) {
				SqlRegistry.unregisterQueriesDatabaseMappingFile(identifier, file);
			}
			
			@Override
			public void registerResource(String identifier, File file) {
				SqlRegistry.registerQueriesDatabaseMappingFile(identifier, file);
			}
		};
		
		/**
		 * Register all jasper resources inside the reports folder.
		 */	 		
		jasperResourceRegistrable = new ResourceRegistrable("templates", "Jasper resource files", "/templates/**/*.jrxml") {
			
			@Override
			public void unregisterResource(String identifier, File file) {
				ReportRegistry.unregisterJasperFile(identifier, file);
			}
			
			@Override
			public void registerResource(String identifier, File file) {
				ReportRegistry.registerJasperFile(identifier, file);
			}
		};
		
		/**
		 * Register all datasets inside the catalog folder.
		 */	 		
		datasetCatalog = new ResourceRegistrable("catalog", "Dataset Catalog", "/catalog/*.json", false) {
			
			@Override
			public void unregisterResource(String identifier, File file) {
				DatasetCatalog.unregisterDataset(identifier, file);
			}
			
			@Override
			public void registerResource(String identifier, File file) {
				DatasetCatalog.registerDataset(identifier, file);
			}
		};

		/**
		 * Register all lnk file inside the lnkJson folder.
		 */	 		
		lnkResourceRegistrable = new ResourceRegistrable("lnk", "lnk files", "/lnk/*.json") {
			
			@Override
			public void unregisterResource(String identifier, File file) {
				LnkRegistry.unregisterLnkFile(identifier, file);
			}
			
			@Override
			public void registerResource(String identifier, File file) {
				LnkRegistry.registerLnkFile(identifier, file);
			}
		};
		
		scriptsGroovyResourceRegistrable.register();
		sqlResourceRegistrable.register();
		scriptsDaemonsResourceRegistrable.register();
		queriesDatabaseMappingResourceRegistrable.register();
		jasperResourceRegistrable.register();
		datasetCatalog.register();
		lnkResourceRegistrable.register();
	}
	
	/**
	 * Unregister all resources previously added.
	 */	 
	@PreDestroy
	public void unregisterResources() {
		scriptsGroovyResourceRegistrable.unregister();
		sqlResourceRegistrable.unregister();
		scriptsDaemonsResourceRegistrable.unregister();
		queriesDatabaseMappingResourceRegistrable.unregister();
		jasperResourceRegistrable.unregister();
		datasetCatalog.unregister();
		lnkResourceRegistrable.unregister();
	}
}


