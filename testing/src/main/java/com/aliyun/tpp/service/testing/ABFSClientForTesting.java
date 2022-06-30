/**
 * author: oe
 * date:   2022/2/23
 * comment:
 */
package com.aliyun.tpp.service.testing;

import com.aliyun.tpp.service.abfs.ABFSClient;
import com.aliyun.tpp.service.abfs.ABFSConfig;
import com.taobao.abfs.client.core.ABFSClientBuilder;
import com.taobao.abfs.client.core.PgSessionCtx;
import com.taobao.abfs.client.core.UpdateSessionCtx;
import com.taobao.abfs.client.model.PGQuery;
import com.taobao.abfs.client.model.QueryResult;
import com.taobao.abfs.client.model.UpdateQuery;
import lombok.NonNull;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;

public class ABFSClientForTesting implements ABFSClient {

    //abfs各项配置
    private ABFSConfig abfsConfig;

    private com.taobao.abfs.client.core.ABFSClient abfsClient;

    //初始化标识
    private transient AtomicBoolean initial = new AtomicBoolean(false);

    public ABFSClientForTesting() {
    }

    @Override
    public void setABFSConfig(ABFSConfig abfsConfig) {
        if (abfsConfig.isValid()) {
            this.abfsConfig = abfsConfig;
        } else {
            throw new IllegalArgumentException("abfsConfig is invalid");
        }
    }

    @Override
    public void init() {
        if (initial.compareAndSet(false, true)) {
            this.abfsClient = ABFSClientBuilder.create().build(
                    abfsConfig.getSrc(),
                    abfsConfig.getEndpoint(),
                    abfsConfig.getUserName(),
                    abfsConfig.getUserPasswd());
        }
    }

    @Override
    public QueryResult searchSync(@NonNull PgSessionCtx pgSessionCtx, @NonNull PGQuery... queries) throws Exception {
        return this.abfsClient.searchSync(pgSessionCtx, queries);
    }

    @Override
    public CompletableFuture<QueryResult> searchAsync(@NonNull PgSessionCtx pgSessionCtx, @NonNull PGQuery... queries) throws Exception {
        return this.abfsClient.searchAsync(pgSessionCtx, queries);
    }

    @Override
    public void updateSync(@NonNull UpdateSessionCtx updateSessionCtx, @NonNull UpdateQuery updateQuery) throws Exception {
        this.abfsClient.updateSync(updateSessionCtx, updateQuery);
    }

    @Override
    public CompletableFuture updateAsync(@NonNull UpdateSessionCtx updateSessionCtx, @NonNull UpdateQuery updateQuery) throws Exception {
        return this.abfsClient.updateAsync(updateSessionCtx, updateQuery);
    }

    @Override
    public void deleteSync(@NonNull UpdateSessionCtx updateSessionCtx, @NonNull UpdateQuery updateQuery) throws Exception {
        this.abfsClient.deleteSync(updateSessionCtx, updateQuery);
    }

    @Override
    public CompletableFuture deleteAsync(@NonNull UpdateSessionCtx updateSessionCtx, @NonNull UpdateQuery updateQuery) throws Exception {
        return this.abfsClient.deleteAsync(updateSessionCtx, updateQuery);
    }

    @Override
    public void close() {
        if (this.abfsClient != null) {
            if (this.initial.compareAndSet(true, false)) {
                if (this.abfsClient != null) {
                    this.abfsClient.close();
                }
            }
        }
    }
}
