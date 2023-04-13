package com.yuge.ing.jackson.xml.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

/**
 * @author: yuge
 * @date: 2023/4/3
 **/
@Data
@JacksonXmlRootElement(localName = "xml")
public class MpFollowEventDTO {

    /**
     * 开发者微信号
     */
    @JacksonXmlProperty(localName = "ToUserName")
    private String toUserName;

    /**
     * 发送方帐号（一个OpenID）
     */
    @JacksonXmlProperty(localName = "FromUserName")
    private String fromUserName;

    /**
     * 消息创建时间 （整型）
     */
    @JacksonXmlProperty(localName = "CreateTime")
    private Long createTime;

    /**
     * 消息类型，event
     */
    @JacksonXmlProperty(localName = "MsgType")
    private String msgType;

    /**
     * 事件类型，subscribe(订阅)、unsubscribe(取消订阅)
     */
    @JacksonXmlProperty(localName = "Event")
    private String event;


}
