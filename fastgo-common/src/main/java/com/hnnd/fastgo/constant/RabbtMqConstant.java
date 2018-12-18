package com.hnnd.fastgo.constant;

/**
 * 消息中心常量类
 * Created by 85073 on 2018/11/26.
 */
public class RabbtMqConstant {

    /**
     * 极速购所有的业务的交换机(topic交换机)
     */
    public static final String FASTGO_BIZ_EXCHANGE = "fastgo.biz.exchange";

    /**
     * 商家账户审核发送短信消息
     */
    public static final String FASTGO_SMS_QUEUE = "fastgo.sms.queue";

    /**
     * 商品上下架后的商品导入到solr库中
     */
    public static final String FASTGO_SOLR_QUEUE = "fastgo.addsolr.queue";

    /**
     * 导入到索引库的key
     */
    public static final String FASTGO_SOLR_KEY = "fastgo.addsolr.key";

    /**
     * 审核发送短信的key
     */
    public static final String FASTGO_SMS_KEY = "fastgo.sms.key";

    /**
     * 防止短信消息重复消费的前缀
     */

    public static final String FASTOG_SMS_LOCK_KEY="fastgo:sms:lockey";

    /**
     * 初始化消息重试次数
     */
    public static final Integer INIT_RETRY_COUNT=0;

    /**
     * 最大的消息重试次数
     */
    public static final Integer MAX_RETRY_COUNT=5;
}
