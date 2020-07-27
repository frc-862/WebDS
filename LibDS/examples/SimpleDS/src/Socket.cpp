#include "Socket.h"

Socket::Socket(QObject *parent) : QObject(parent) {
    socket = new QTcpSocket(this);
    socket->connectToHost("localhost", 8080); // "frc862.com"?
    if(socket->isOpen()) {
        qDebug() << "Connected";
        establishedConnection = true;
        socket->write("IAMROBOT");
    } else {
        qDebug() << "Unable to connect";
        establishedConnection = false;
    }
    connect(socket, &QIODevice::readyRead, this, &Socket::readJoystickData);
}

Socket::~Socket()
{
    onDisconnect();
}

void Socket::readJoystickData()
{
    QString msg = "";
    if(establishedConnection /*&& socket->waitForReadyRead(20)*/) {
        msg = QString(socket->readAll());
    } else {
        qDebug() << "Faulty Connection";
    }
    SocketEvent* event = new SocketEvent(QEvent::Type(1023));
    event->data = msg;
    QApplication::postEvent(this, reinterpret_cast<QEvent*>(event));
}

void Socket::onDisconnect()
{
    socket->write("ROBOTDIED");
    socket->waitForBytesWritten(1000);
    qDebug() << "Closed Connection";
    socket->close();
}
