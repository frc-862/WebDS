#include "Socket.h"

Socket::Socket(const QUrl &url, QObject *parent) :
    QObject(parent),
    url(url)
{
    qDebug() << "Server: " << url;
    connect(&socket, &QWebSocket::connected, this, &Socket::onConnected);
    connect(&socket, &QWebSocket::disconnected, this, &Socket::closed);
    socket.open(QUrl(url));
}

void Socket::onConnected()
{
    qDebug() << "Connected";
    connect(&socket, &QWebSocket::textMessageReceived, this, &Socket::onTextMessageReceived);
    socket.sendTextMessage(QStringLiteral("IAMROBOT"));
}

void Socket::onTextMessageReceived(QString message)
{
    float leftInp = 0;
    float rightInp = 0;
    bool leftNext = true;
    for(QString token : message.split(":")) {
        if(token == "CMD") continue;
        if(token == "L") {
            leftNext = true;
            continue;
        }
        if(token == "R") {
            leftNext = false;
            continue;
        }
        if(leftNext) leftInp = token.toFloat();
        else rightInp = token.toFloat();
    }
    // Send
    RemoteJoystick::updateAxes(leftInp, rightInp);
}

Socket::~Socket()
{
    socket.sendTextMessage(QStringLiteral("ROBOTDIED"));
    socket.close();
}
