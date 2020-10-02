#include "RemoteJoystick.h"

#include <QApplication>
#include <DriverStation.h>

/**
 * Initialize the event filter and register a joystick
 */
RemoteJoystick::RemoteJoystick()
{
    /* Listen for events */
    // qApp->installEventFilter(this);

    /* Register a joystick with 6 axis, 1 POV and 10 buttons */
    DriverStation::getInstance()->resetJoysticks();
    DriverStation::getInstance()->addJoystick(6, 1, 10);
    // jsConfigured = true;
}

void RemoteJoystick::updateAxes (float left, float right)
{
    // qDebug() << "Left: " << left << " Right: " << right;
    /* Left Stick is Axis 1 */
    DriverStation::getInstance()->setJoystickAxis(0, 1, left);
    /* Right Stick is Axis 5 */
    DriverStation::getInstance()->setJoystickAxis(0, 5, right);
    // QApplication::beep();
    QString msg = QString("Left: %1  |  Right: %2").arg(left).arg(right);
    emit DriverStation::getInstance()->newMessage(msg);
}
