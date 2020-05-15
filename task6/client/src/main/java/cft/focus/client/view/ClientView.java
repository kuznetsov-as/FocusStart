package cft.focus.client.view;

import java.util.Set;

public interface ClientView {
    void renderRequestServerAddress();

    void renderRequestPort();

    void renderRequestName();

    void renderRefreshUsers(Set<String> allUserNames);

    void renderRefreshMessages(String message);
}
