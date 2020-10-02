#ifndef _VIRTUAL_JOYSTICK_H
#define _VIRTUAL_JOYSTICK_H

#include <QKeyEvent>

class VirtualJoystick : public QObject
{
    Q_OBJECT

public:
    explicit VirtualJoystick();

private slots:
    void readAxes (int key, bool pressed);
    void readPOVs (int key, bool pressed);
    void readButtons (int key, bool pressed);
    void processKeyEvent (QKeyEvent* event, bool pressed);

protected:
    bool eventFilter (QObject* object, QEvent* event);
};

#endif
