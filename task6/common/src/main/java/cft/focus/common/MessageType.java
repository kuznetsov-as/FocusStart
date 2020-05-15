package cft.focus.common;

/**
 * Типы сообщений пересылаемых между клиентом и сервером
 */
public enum MessageType {
    NAME_REQUEST,
    USER_NAME,
    NAME_ACCEPTED,
    NAME_USED,
    TEXT,
    USER_ADDED,
    USER_REMOVED
}
