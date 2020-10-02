#include "SocketHandler.h"

SocketHandler::SocketHandler(const QUrl &url, QObject *parent) :
    QObject(parent),
    url(url)
{
    qDebug() << "Server: " << url;
    connect(&socket, &QWebSocket::connected, this, &SocketHandler::onConnected);
    connect(&socket, &QWebSocket::disconnected, this, &SocketHandler::closed);
    socket.open(QUrl(url));
}

void SocketHandler::onConnected()
{
    qDebug() << "Connected";
    connect(&socket, &QWebSocket::textMessageReceived, this, &SocketHandler::onTextMessageReceived);
    socket.sendTextMessage(QStringLiteral("IAMROBOT"));
}

void SocketHandler::connectTo(const QString &url)
{
    socket.sendTextMessage(QStringLiteral("ROBOTDIED"));
    qDebug() << "Attempting to Connect To " << url;
    socket.close();
    socket.open(QUrl(url));
}

void SocketHandler::onTextMessageReceived(QString message)
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

SocketHandler::~SocketHandler()
{
    socket.sendTextMessage(QStringLiteral("ROBOTDIED"));
    socket.close();
}
