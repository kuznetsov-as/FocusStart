package cft.focus.client.model;

import cft.focus.client.view.ClientView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

class ClientViewNotifier {
    private final List<ClientView> clientViews = new ArrayList<>();

    void attachView(ClientView clientView) {
        clientViews.add(clientView);
    }

    void notifyViewsAboutServerAddressRequest() {
        clientViews.forEach(ClientView::renderRequestServerAddress);
    }

    void notifyViewsAboutPortRequest() {
        clientViews.forEach(ClientView::renderRequestPort);
    }

    void notifyViewsAboutNameRequest() {
        clientViews.forEach(ClientView::renderRequestName);
    }

    void notifyViewsAboutUsersUpdate(Set<String> allUserNames) {
        clientViews.forEach(clientView -> clientView.renderRefreshUsers(allUserNames));
    }

    void notifyViewsAboutNewMessage(String message) {
        clientViews.forEach(clientView -> clientView.renderRefreshMessages(message));
    }
}
