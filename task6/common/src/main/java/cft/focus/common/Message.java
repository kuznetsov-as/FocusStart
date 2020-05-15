package cft.focus.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * Класс, отвечающий за пересылаемые сообщения.
 */
@AllArgsConstructor
@Getter
public class Message implements Serializable {
    private final MessageType type;
    private final String data;

    public Message(MessageType type) {
        this.type = type;
        data = null;
    }
}