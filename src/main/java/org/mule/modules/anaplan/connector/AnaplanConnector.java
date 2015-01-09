/**
 * (c) 2003-2014 MuleSoft, Inc. The software in this package is published under the terms of the CPAL v1.0 license,
 * a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.anaplan.connector;

import java.util.ArrayList;
import java.util.List;

import org.mule.api.ConnectionException;
import org.mule.api.ConnectionExceptionCode;
import org.mule.api.annotations.Connect;
import org.mule.api.annotations.ConnectionIdentifier;
import org.mule.api.annotations.Connector;
import org.mule.api.annotations.Disconnect;
import org.mule.api.annotations.MetaDataKeyRetriever;
import org.mule.api.annotations.MetaDataRetriever;
import org.mule.api.annotations.Processor;
import org.mule.api.annotations.ValidateConnection;
import org.mule.api.annotations.display.Password;
import org.mule.api.annotations.param.ConnectionKey;
import org.mule.api.annotations.param.Default;
import org.mule.api.annotations.param.Optional;
import org.mule.common.metadata.DefaultMetaData;
import org.mule.common.metadata.DefaultMetaDataKey;
import org.mule.common.metadata.MetaData;
import org.mule.common.metadata.MetaDataKey;
import org.mule.common.metadata.MetaDataModel;
import org.mule.common.metadata.builder.DefaultMetaDataBuilder;
import org.mule.common.metadata.builder.DynamicObjectBuilder;
import org.mule.common.metadata.datatype.DataType;

import com.anaplan.connector.exceptions.AnaplanConnectionException;
import com.anaplan.connector.utils.AnaplanExportOperation;
import com.anaplan.connector.utils.LogUtil;
//import org.apache.logging.log4j.Logger;
//import org.apache.logging.log4j.LogManager;
//import org.mule.api.annotations.Configurable;

/**
 * Anaplan Connector built using Anypoint Studio.
 * 
 * @author MuleSoft, Inc.
 * @author Spondon Saha.
 */
@Connector(name = "anaplan", schemaVersion = "1.0", friendlyName = "Anaplan")
public class AnaplanConnector {

	// public static final Logger LOGGER =
	// LogManager.getLogger(AnaplanConnector.class);

	/**
	 * Configurable
	 */
	// @Configurable
	// @Default("value")
	// private String myProperty;

	/**
	 * Stores the connection to the Anaplan API
	 */
	private AnaplanConnection apiConn;

	private static AnaplanExportOperation exporter;

	/**
	 * Retrieves the list of keys
	 */
	@MetaDataKeyRetriever
	public List<MetaDataKey> getMetaDataKeys() throws Exception {
		List<MetaDataKey> keys = new ArrayList<MetaDataKey>();

		// Generate the keys
		keys.add(new DefaultMetaDataKey("id1", "User"));
		keys.add(new DefaultMetaDataKey("id2", "Book"));

		return keys;
	}

	/**
	 * Get MetaData given the Key the user selects
	 * 
	 * @param key
	 *            The key selected from the list of valid keys
	 * @return The MetaData model of that corresponds to the key
	 * @throws Exception
	 *             If anything fails
	 */
	@MetaDataRetriever
	public MetaData getMetaData(MetaDataKey key) throws Exception {
		DefaultMetaDataBuilder builder = new DefaultMetaDataBuilder();
		// If you have a Pojo class
		// PojoMetaDataBuilder<?> pojoObject=builder.createPojo(Pojo.class);

		// If you use maps as input of your processors that work with DataSense
		DynamicObjectBuilder<?> dynamicObject = builder.createDynamicObject(key
				.getId());

		if (key.getId().equals("id1")) {
			dynamicObject.addSimpleField("Username", DataType.STRING);
			dynamicObject.addSimpleField("age", DataType.INTEGER);
		} else {
			dynamicObject.addSimpleField("Author", DataType.STRING);
			dynamicObject.addSimpleField("Tittle", DataType.STRING);
		}
		MetaDataModel model = builder.build();
		MetaData metaData = new DefaultMetaData(model);

		return metaData;
	}

	/**
	 * Create a record
	 * 
	 * @return
	 */
	@Processor(friendlyName = "Import")
	public boolean importModel() {
		return false;
	}

	/**
	 * Run an export of an Anaplan Model specified bu model ID.
	 * 
	 * @return Serializable response object.
	 */
	@Processor(friendlyName = "Export")
	public AnaplanResponse exportModel(String anaplanModelId) {
		exporter = new AnaplanExportOperation(apiConn);
		return exporter.runExport(anaplanModelId);
	}

	/**
	 * Updates a record
	 * 
	 * @return
	 */
	@Processor(friendlyName = "Update")
	public boolean updateModel(String anaplanModelId) {
		return false;
	}

	/**
	 * Deletes a record
	 * 
	 * @return
	 */
	@Processor(friendlyName = "Delete")
	public boolean deleteModel(String anaplanModelId) {
		return false;
	}

	/**
	 * Connect to the Anaplan API.
	 * 
	 * @param username
	 *            A username
	 * @param password
	 *            A password
	 * @throws ConnectionException
	 */
	@Connect
	public synchronized void connect(@ConnectionKey String username,
			@Password String password,
			@Optional @Default("https://api.anaplan.com") String url,
			@Optional @Default("") String workspaceId,
			@Optional @Default("") String modelId,
			@Optional @Default("") String proxyHost,
			@Optional @Default("") String proxyUser,
			@Optional @Default("") String proxyPass)
			throws org.mule.api.ConnectionException {

		LogUtil.status(getClass().toString(), "Initiating connection...");

		if (apiConn == null) {
			apiConn = new AnaplanConnection(username, password, url,
					workspaceId, modelId, proxyHost, proxyUser, proxyPass);
			try {
				apiConn.openConnection();
			} catch (AnaplanConnectionException e) {
				throw new org.mule.api.ConnectionException(
						ConnectionExceptionCode.INCORRECT_CREDENTIALS, null,
						e.getMessage(), e);
			}

			LogUtil.status(getClass().toString(),
					"Successfully connected to Anaplan API!");
		}
	}

	/**
	 * Disconnect
	 */
	@Disconnect
	public void disconnect() {
		if (apiConn != null) {
			apiConn.closeConnection();
		} else {
			LogUtil.error(getClass().toString(), "No connection to disconnect!");
		}
	}

	/**
	 * Are we connected?
	 */
	@ValidateConnection
	public boolean isConnected() {
		if (apiConn != null) {
			return true;
		}
		return false;
	}

	/**
	 * Are we connected?
	 */
	@ConnectionIdentifier
	public String connectionId() {
		if (apiConn != null)
			return apiConn.getConnectionId();
		else
			return "Not connected!";
	}

	/**
	 * Custom processor
	 * 
	 * {@sample.xml ../../../doc/anaplan-connector.xml.sample
	 * anaplan:my-processor}
	 * 
	 * @param content
	 *            Content to be processed
	 * @return Some string
	 */
	@Processor
	public String myProcessor(String content) {
		/*
		 * MESSAGE PROCESSOR CODE GOES HERE
		 */
		return content;
	}
}
