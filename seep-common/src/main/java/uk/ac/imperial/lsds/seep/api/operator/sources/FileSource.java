package uk.ac.imperial.lsds.seep.api.operator.sources;

import java.util.Properties;

import uk.ac.imperial.lsds.seep.api.API;
import uk.ac.imperial.lsds.seep.api.ConnectionType;
import uk.ac.imperial.lsds.seep.api.DataStore;
import uk.ac.imperial.lsds.seep.api.DataStoreDescriptor;
import uk.ac.imperial.lsds.seep.api.DataStoreType;
import uk.ac.imperial.lsds.seep.api.QueryBuilder;
import uk.ac.imperial.lsds.seep.api.SeepTask;
import uk.ac.imperial.lsds.seep.api.data.ITuple;
import uk.ac.imperial.lsds.seep.api.data.Schema;
import uk.ac.imperial.lsds.seep.api.operator.Connectable;
import uk.ac.imperial.lsds.seep.api.operator.LogicalOperator;
import uk.ac.imperial.lsds.seep.api.operator.Operator;


public class FileSource implements Connectable, DataStoreDescriptor {

	private static LogicalOperator lo;
	private Properties config;
	
	private FileSource(int opId, Properties config){
		this.config = config;
		QueryBuilder qb = new QueryBuilder();
		lo = qb.newStatelessSource(new FileSourceImpl(), opId);
	}
	
	public static FileSource newSource(int opId, Properties config){
		return new FileSource(opId, config);
	}
	
	/** Implement DataOriginDescriptor **/
	
	@Override
	public DataStoreType type() {
		return DataStoreType.FILE;
	}
	
	@Override
	public Properties getConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Schema getSchema() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/** Implement Connetable **/
	
	@Override
	public void connectTo(Operator downstreamOperator, int streamId, DataStore dataStore) {
		// TODO: check dataStore is of the right type = FILE
		lo.connectTo(downstreamOperator, streamId, dataStore);
	}

	@Override
	public void connectTo(Operator downstreamOperator, int streamId, DataStore dataStore, ConnectionType connectionType) {
		// TODO: check dataStore is of the right type = FILE
		lo.connectTo(downstreamOperator, streamId, dataStore, connectionType);
	}
	
//	@Override
//	public void connectTo(Operator downstreamOperator, int streamId, Schema schema){
//		DataStore dO = new DataStore(DataStoreType.FILE, config);
//		lo.connectTo(downstreamOperator, streamId, schema, ConnectionType.ONE_AT_A_TIME, dO);
//	}
//
//	@Override
//	public void connectTo(Operator downstreamOperator, int streamId, Schema schema, DataStore dO){
//		throw new NotSupportedException("Cannot override DataOrigin");
//	}
//
//	@Override
//	public void connectTo(Operator downstreamOperator, int streamId, Schema schema, ConnectionType conType){
//		DataStore dO = new DataStore(DataStoreType.FILE, config);
//		lo.connectTo(downstreamOperator, streamId, schema, conType, dO);
//	}
//
//	@Override
//	public void connectTo(Operator downstreamOperator, int streamId, Schema schema, ConnectionType connectionType, DataStore dSrc) {
//		throw new NotSupportedException("Cannot override DataOrigin");
//	}
	
	private static class FileSourceImpl implements SeepTask, Source{
		@Override
		public void setUp() {		}
		@Override
		public void processData(ITuple data, API api) {		}
		@Override
		public void processDataGroup(ITuple dataBatch, API api) {		}
		@Override
		public void close() {		}
	}

}
