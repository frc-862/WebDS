#ifndef SOCKET_H
#define SOCKET_H

#include <QObject>
#include <QtNetwork/QTcpSocket>
#include <QString>
#include <QIODevice>
#include <QEvent>
#include <QApplication>
#include <QtWebSockets/QWebSocket>
#include <DriverStation.h>

#include "RemoteJoystick.h"

class SocketHandler : public QObject
{
    Q_OBJECT

public:
    explicit SocketHandler(const QUrl &url, QObject *parent = nullptr);
    ~SocketHandler();

Q_SIGNALS:
    void closed();

private Q_SLOTS:
    void onConnected();
    void onTextMessageReceived(QString message);
    void connectTo(const QString &url);

private:
    QWebSocket socket;
    QUrl url;

};

#endif // SOCKET_H
