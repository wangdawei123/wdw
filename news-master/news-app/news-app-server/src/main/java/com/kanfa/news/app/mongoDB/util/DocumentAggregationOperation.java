package com.kanfa.news.app.mongoDB.util;

/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/4/9 11:26
 */

import org.bson.Document;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperationContext;

public class DocumentAggregationOperation implements AggregationOperation {
    private Document operation;

    public DocumentAggregationOperation (Document operation) {
        this.operation = operation;
    }

    @Override
    public Document toDocument(AggregationOperationContext context) {
        return context.getMappedObject(operation);
    }
}