curl -XPOST -u Administrator:password \
    localhost:8091/pools/default/buckets \
            -d bucketType=couchbase \
            -d name=test \
            -d authType=sasl \
            -d ramQuotaMB=200