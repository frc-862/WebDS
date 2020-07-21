#include "Socket.h"

Socket::Socket(QObject *parent) : QObject(parent) {}

Socket::~Socket()
{
    CloseConnection();
}

void Socket::Connect()
{
    socket = new QTcpSocket(this);
    socket->connectToHost("google.com", 80); // "frc862.com"?
    if(socket->waitForConnected(3000))
    {
        qDebug() << "Connected!";
        establishedConnection = true;
        // send
        socket->write("hello server\r\n\r\n\r\n\r\n");
        socket->waitForBytesWritten(1000);
        socket->waitForReadyRead(3000);
        qDebug() << "Reading: " << socket->bytesAvailable();
        qDebug() << socket->readAll();
        //socket->close();
    }
    else
    {
        qDebug() << "Unable to connect!";
        establishedConnection = false;
    }
}

QString Socket::readJoystickData()
{
    // TODO - implement
}

void Socket::CloseConnection()
{
    qDebug() << "Closed Connection";
    socket->close();
}
