#ifndef SOCKET_H
#define SOCKET_H

#include <QObject>
#include <QtNetwork/QTcpSocket>
#include <QString>

class Socket : public QObject
{
    Q_OBJECT

public:
    explicit Socket(QObject *parent = 0);
    ~Socket();
    void Connect();
    QString readJoystickData();
    void CloseConnection();

private:
    QTcpSocket* socket;
    bool establishedConnection;

};

#endif // SOCKET_H
