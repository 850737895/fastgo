package com.hnnd.fastgo.temp;

import lombok.Data;
import java.util.Map;

/**
 * 短信文本内容
 * Created by Administrator on 2018/12/19.
 */
@Data
public class Sms {

    //短信接受者
    private String receiver;
    //短信模版编码
    private Integer smsType;
    //填充模版数据
    private Map<String,Object> textMap;
}
