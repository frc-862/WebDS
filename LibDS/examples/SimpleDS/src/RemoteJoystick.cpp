#include "RemoteJoystick.h"

#include <QApplication>
#include <DriverStation.h>

/**
 * Initialize the event filter and register a joystick
 */
RemoteJoystick::RemoteJoystick()
{
    /* Listen for events */
    qApp->installEventFilter(this);

    /* Register a joystick with 6 axis, 1 POV and 10 buttons */
    DriverStation::getInstance()->resetJoysticks();
    DriverStation::getInstance()->addJoystick(6, 1, 10);
}

void RemoteJoystick::updateAxes (float left, float right)
{

    /* Left Stick is Axis 1 */
    DriverStation::getInstance()->setJoystickAxis(0, 1, left);
    /* Right Stick is Axis 5 */
    DriverStation::getInstance()->setJoystickAxis(0, 5, right);

}

bool RemoteJoystick::eventFilter (QObject* object, QEvent* event)
{
    Q_UNUSED (object);
    switch (static_cast<int>(event->type())) {
    case 1023:
    {
        float leftInp = 0;
        float rightInp = 0;
        bool leftNext = true;
        SocketEvent* evt = reinterpret_cast<SocketEvent*>(event);
        QString cmd = evt->data;
        for(QString token : cmd.split(":")) {
            if(token == "CMD") continue;
            if(token == "L") {
                leftNext = true;
                continue;
            }
            if(token == "R") {
                leftNext = false;
                continue;
            }
            // Number
            if(leftNext) leftInp = token.toFloat();
            else rightInp = token.toFloat();
        }
        updateAxes(leftInp, rightInp);
    } break;
    default:
        break;
    }
    return false;
}
