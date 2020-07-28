#ifndef SOCKET_H
#define SOCKET_H

#include <QObject>
#include <QtNetwork/QTcpSocket>
#include <QString>
#include <QIODevice>
#include <QEvent>
#include <QApplication>
#include <QtWebSockets/QWebSocket>

#include "RemoteJoystick.h"

class Socket : public QObject
{
    Q_OBJECT

public:
    explicit Socket(const QUrl &url, QObject *parent = nullptr);
    ~Socket();
    // void onDisconnect();

Q_SIGNALS:
    void closed();

private Q_SLOTS:
    void onConnected();
    void onTextMessageReceived(QString message);
    // void readJoystickData();

private:
    QWebSocket socket;
    QUrl url;
    // QTcpSocket* socket;
    // bool establishedConnection;

};

#endif // SOCKET_H
