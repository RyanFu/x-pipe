package com.ctrip.xpipe.redis.keeper.monitor;

/**
 * @author wenchao.meng
 *
 * Mar 10, 2017
 */
public interface ReplicationStoreStats {
	
	void increateReplicationStoreCreateCount();
	
	long getReplicationStoreCreateCount();

}
