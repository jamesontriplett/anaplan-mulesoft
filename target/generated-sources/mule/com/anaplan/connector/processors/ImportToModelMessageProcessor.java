
package com.anaplan.connector.processors;

import java.util.List;
import javax.annotation.Generated;
import com.anaplan.connector.AnaplanConnector;
import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.config.ConfigurationException;
import org.mule.api.devkit.ProcessAdapter;
import org.mule.api.devkit.ProcessTemplate;
import org.mule.api.lifecycle.InitialisationException;
import org.mule.api.processor.MessageProcessor;
import org.mule.api.registry.RegistrationException;
import org.mule.common.DefaultResult;
import org.mule.common.FailureType;
import org.mule.common.Result;
import org.mule.common.metadata.ConnectorMetaDataEnabled;
import org.mule.common.metadata.DefaultMetaData;
import org.mule.common.metadata.DefaultPojoMetaDataModel;
import org.mule.common.metadata.DefaultSimpleMetaDataModel;
import org.mule.common.metadata.MetaData;
import org.mule.common.metadata.MetaDataKey;
import org.mule.common.metadata.MetaDataModel;
import org.mule.common.metadata.OperationMetaDataEnabled;
import org.mule.common.metadata.datatype.DataType;
import org.mule.common.metadata.datatype.DataTypeFactory;
import org.mule.devkit.processor.DevkitBasedMessageProcessor;
import org.mule.security.oauth.callback.ProcessCallback;


/**
 * ImportToModelMessageProcessor invokes the {@link com.anaplan.connector.AnaplanConnector#importToModel(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)} method in {@link AnaplanConnector }. For each argument there is a field in this processor to match it.  Before invoking the actual method the processor will evaluate and transform where possible to the expected argument type.
 * 
 */
@Generated(value = "Mule DevKit Version 3.6.0", date = "2015-04-20T09:44:38-07:00", comments = "Build UNNAMED.2363.ef5c8a7")
public class ImportToModelMessageProcessor
    extends DevkitBasedMessageProcessor
    implements MessageProcessor, OperationMetaDataEnabled
{

    protected Object data;
    protected String _dataType;
    protected Object workspaceId;
    protected String _workspaceIdType;
    protected Object modelId;
    protected String _modelIdType;
    protected Object importId;
    protected String _importIdType;
    protected Object columnSeparator;
    protected String _columnSeparatorType;
    protected Object delimiter;
    protected String _delimiterType;

    public ImportToModelMessageProcessor(String operationName) {
        super(operationName);
    }

    /**
     * Obtains the expression manager from the Mule context and initialises the connector. If a target object  has not been set already it will search the Mule registry for a default one.
     * 
     * @throws InitialisationException
     */
    public void initialise()
        throws InitialisationException
    {
    }

    @Override
    public void start()
        throws MuleException
    {
        super.start();
    }

    @Override
    public void stop()
        throws MuleException
    {
        super.stop();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    /**
     * Sets delimiter
     * 
     * @param value Value to set
     */
    public void setDelimiter(Object value) {
        this.delimiter = value;
    }

    /**
     * Sets modelId
     * 
     * @param value Value to set
     */
    public void setModelId(Object value) {
        this.modelId = value;
    }

    /**
     * Sets workspaceId
     * 
     * @param value Value to set
     */
    public void setWorkspaceId(Object value) {
        this.workspaceId = value;
    }

    /**
     * Sets columnSeparator
     * 
     * @param value Value to set
     */
    public void setColumnSeparator(Object value) {
        this.columnSeparator = value;
    }

    /**
     * Sets data
     * 
     * @param value Value to set
     */
    public void setData(Object value) {
        this.data = value;
    }

    /**
     * Sets importId
     * 
     * @param value Value to set
     */
    public void setImportId(Object value) {
        this.importId = value;
    }

    /**
     * Invokes the MessageProcessor.
     * 
     * @param event MuleEvent to be processed
     * @throws Exception
     */
    public MuleEvent doProcess(final MuleEvent event)
        throws Exception
    {
        Object moduleObject = null;
        try {
            moduleObject = findOrCreate(null, false, event);
            final String _transformedData = ((String) evaluateAndTransform(getMuleContext(), event, ImportToModelMessageProcessor.class.getDeclaredField("_dataType").getGenericType(), null, "#[payload]"));
            final String _transformedWorkspaceId = ((String) evaluateAndTransform(getMuleContext(), event, ImportToModelMessageProcessor.class.getDeclaredField("_workspaceIdType").getGenericType(), null, workspaceId));
            final String _transformedModelId = ((String) evaluateAndTransform(getMuleContext(), event, ImportToModelMessageProcessor.class.getDeclaredField("_modelIdType").getGenericType(), null, modelId));
            final String _transformedImportId = ((String) evaluateAndTransform(getMuleContext(), event, ImportToModelMessageProcessor.class.getDeclaredField("_importIdType").getGenericType(), null, importId));
            final String _transformedColumnSeparator = ((String) evaluateAndTransform(getMuleContext(), event, ImportToModelMessageProcessor.class.getDeclaredField("_columnSeparatorType").getGenericType(), null, columnSeparator));
            final String _transformedDelimiter = ((String) evaluateAndTransform(getMuleContext(), event, ImportToModelMessageProcessor.class.getDeclaredField("_delimiterType").getGenericType(), null, delimiter));
            Object resultPayload;
            final ProcessTemplate<Object, Object> processTemplate = ((ProcessAdapter<Object> ) moduleObject).getProcessTemplate();
            resultPayload = processTemplate.execute(new ProcessCallback<Object,Object>() {


                public List<Class<? extends Exception>> getManagedExceptions() {
                    return null;
                }

                public boolean isProtected() {
                    return false;
                }

                public Object process(Object object)
                    throws Exception
                {
                    return ((AnaplanConnector) object).importToModel(_transformedData, _transformedWorkspaceId, _transformedModelId, _transformedImportId, _transformedColumnSeparator, _transformedDelimiter);
                }

            }
            , this, event);
            event.getMessage().setPayload(resultPayload);
            return event;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Result<MetaData> getInputMetaData() {
        return new DefaultResult<MetaData>(null, (Result.Status.SUCCESS));
    }

    @Override
    public Result<MetaData> getOutputMetaData(MetaData inputMetadata) {
        return new DefaultResult<MetaData>(new DefaultMetaData(getPojoOrSimpleModel(String.class)));
    }

    private MetaDataModel getPojoOrSimpleModel(Class clazz) {
        DataType dataType = DataTypeFactory.getInstance().getDataType(clazz);
        if (DataType.POJO.equals(dataType)) {
            return new DefaultPojoMetaDataModel(clazz);
        } else {
            return new DefaultSimpleMetaDataModel(dataType);
        }
    }

    public Result<MetaData> getGenericMetaData(MetaDataKey metaDataKey) {
        ConnectorMetaDataEnabled connector;
        try {
            connector = ((ConnectorMetaDataEnabled) findOrCreate(null, false, null));
            try {
                Result<MetaData> metadata = connector.getMetaData(metaDataKey);
                if ((Result.Status.FAILURE).equals(metadata.getStatus())) {
                    return metadata;
                }
                if (metadata.get() == null) {
                    return new DefaultResult<MetaData>(null, (Result.Status.FAILURE), "There was an error processing metadata at AnaplanConnector at importToModel retrieving was successful but result is null");
                }
                return metadata;
            } catch (Exception e) {
                return new DefaultResult<MetaData>(null, (Result.Status.FAILURE), e.getMessage(), FailureType.UNSPECIFIED, e);
            }
        } catch (ClassCastException cast) {
            return new DefaultResult<MetaData>(null, (Result.Status.FAILURE), "There was an error getting metadata, there was no connection manager available. Maybe you're trying to use metadata from an Oauth connector");
        } catch (ConfigurationException e) {
            return new DefaultResult<MetaData>(null, (Result.Status.FAILURE), e.getMessage(), FailureType.UNSPECIFIED, e);
        } catch (RegistrationException e) {
            return new DefaultResult<MetaData>(null, (Result.Status.FAILURE), e.getMessage(), FailureType.UNSPECIFIED, e);
        } catch (IllegalAccessException e) {
            return new DefaultResult<MetaData>(null, (Result.Status.FAILURE), e.getMessage(), FailureType.UNSPECIFIED, e);
        } catch (InstantiationException e) {
            return new DefaultResult<MetaData>(null, (Result.Status.FAILURE), e.getMessage(), FailureType.UNSPECIFIED, e);
        } catch (Exception e) {
            return new DefaultResult<MetaData>(null, (Result.Status.FAILURE), e.getMessage(), FailureType.UNSPECIFIED, e);
        }
    }

}
