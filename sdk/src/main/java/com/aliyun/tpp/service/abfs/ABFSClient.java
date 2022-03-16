/**
 * author: oe
 * date:   2022/2/23
 * comment:
 */
package com.aliyun.tpp.service.abfs;

import com.aliyun.tpp.service.inner.Service;
import com.taobao.abfs.client.core.PgSessionCtx;
import com.taobao.abfs.client.core.UpdateSessionCtx;
import com.taobao.abfs.client.model.PGQuery;
import com.taobao.abfs.client.model.QueryResult;
import com.taobao.abfs.client.model.UpdateQuery;
import lombok.NonNull;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.CompletableFuture;

public interface ABFSClient extends Service<ABFSConfig> {

    @Override
    default void setConfig(ABFSConfig abfsConfig) {
        setABFSConfig(abfsConfig);
    }

    /**
     * 在init之前设置config
     */
    void setABFSConfig(ABFSConfig abfsConfig);

    /**
     * 根据config初始化client
     */
    @PostConstruct
    void init();

    /**
     * 同步查询
     *
     * @param pgSessionCtx
     * @param queries
     */
    QueryResult searchSync(@NonNull PgSessionCtx pgSessionCtx, @NonNull PGQuery... queries) throws Exception;

    /**
     * 异步查询
     *
     * @param pgSessionCtx
     * @param queries
     */
    CompletableFuture<QueryResult> searchAsync(@NonNull PgSessionCtx pgSessionCtx, @NonNull PGQuery... queries) throws Exception;

    /**
     * 同步更新
     *
     * @param updateSessionCtx
     * @param updateQuery
     */
    void updateSync(@NonNull UpdateSessionCtx updateSessionCtx, @NonNull UpdateQuery updateQuery) throws Exception;

    /**
     * 异步更新
     *
     * @param updateSessionCtx
     * @param updateQuery
     */
    CompletableFuture updateAsync(@NonNull UpdateSessionCtx updateSessionCtx, @NonNull UpdateQuery updateQuery) throws Exception;

    /**
     * 同步删除
     *
     * @param updateSessionCtx
     * @param updateQuery
     */
    void deleteSync(@NonNull UpdateSessionCtx updateSessionCtx, @NonNull UpdateQuery updateQuery) throws Exception;

    /**
     * 异步删除
     *
     * @param updateSessionCtx
     * @param updateQuery
     */
    CompletableFuture deleteAsync(@NonNull UpdateSessionCtx updateSessionCtx, @NonNull UpdateQuery updateQuery) throws Exception;

    /**
     * 关闭
     */
    @PreDestroy
    void close();


}
