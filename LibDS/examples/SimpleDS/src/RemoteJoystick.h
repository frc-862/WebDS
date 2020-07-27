#ifndef REMOTEJOYSTICK_H
#define REMOTEJOYSTICK_H


#include <QKeyEvent>
#include "SocketEvent.h"

class RemoteJoystick : public QObject
{
    Q_OBJECT

public:
    explicit RemoteJoystick();

private slots:
    void updateAxes (float left, float right);

protected:
    bool eventFilter (QObject* object, QEvent* event);
};

#endif // REMOTEJOYSTICK_H
