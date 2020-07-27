#ifndef SOCKET_H
#define SOCKET_H

#include <QObject>
#include <QtNetwork/QTcpSocket>
#include <QString>
#include <QIODevice>
#include <QEvent>
#include <QApplication>

#include "SocketEvent.h"

class Socket : public QObject
{
    Q_OBJECT

public:
    explicit Socket(QObject* parent = 0);
    ~Socket();
    void onDisconnect();

private slots:
    void readJoystickData();


private:
    QTcpSocket* socket;
    bool establishedConnection;

};

#endif // SOCKET_H
