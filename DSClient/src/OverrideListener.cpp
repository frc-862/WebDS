#include <QApplication>

#include <DriverStation.h>

#include "OverrideListener.h"

OverrideListener::OverrideListener(QObject *parent) : QObject(parent)
{
    qApp->installEventFilter (this);
}

void OverrideListener::processKeyEvent (QKeyEvent* event)
{
    int key = event->key();
    if (key == Qt::Key_Enter)
    {
        DriverStation::getInstance()->setEnabled(false);
    }
    else if (key == Qt::Key_Space)
    {
        DriverStation::getInstance()->setEmergencyStopped(true);
    }
}

bool OverrideListener::eventFilter (QObject* object, QEvent* event)
{
    Q_UNUSED (object);

    switch (event->type()) {
    case QEvent::KeyPress:
        processKeyEvent (static_cast <QKeyEvent*> (event));
        break;
    default:
        break;
    }

    return false;
}
