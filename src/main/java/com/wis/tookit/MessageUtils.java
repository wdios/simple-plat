package com.wis.tookit;

import org.springframework.context.MessageSource;

/**
 * 消息内容，提示语等
 */
public class MessageUtils {

    private static MessageSource messageSource;

    /**
     * 根据消息键和参数 获取消息
     * 委托给spring messageSource
     *
     * @param code 消息键
     * @param args 参数
     * @return
     */
    public static String message(String code, Object... args) {
        if (messageSource == null) {
            messageSource = SpringUtils.getBean(MessageSource.class);
        }
        return messageSource.getMessage(code, args, null);
    }

}
