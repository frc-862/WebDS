#ifndef OVERRIDELISTENER_H
#define OVERRIDELISTENER_H

#include <QObject>
#include <QKeyEvent>

class OverrideListener : public QObject
{
    Q_OBJECT

public:
    explicit OverrideListener(QObject *parent = nullptr);

private slots:
    void processKeyEvent (QKeyEvent* event);

protected:
    bool eventFilter (QObject* object, QEvent* event);
};

#endif // OVERRIDELISTENER_H
