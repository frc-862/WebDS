#ifndef SOCKETEVENT_H
#define SOCKETEVENT_H

#include <QEvent>
#include <QString>

class SocketEvent : QEvent
{
public:
    QString data;
    SocketEvent(QEvent::Type type) : QEvent(type) {}
};

#endif // SOCKETEVENT_H
