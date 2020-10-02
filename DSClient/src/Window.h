#ifndef _WINDOW_H
#define _WINDOW_H

#include <QMainWindow>
#include <QApplication>

#include "ui_Window.h"
#include "SocketHandler.h"

namespace Ui {
class Window;
}

class DriverStation;

class Window : public QMainWindow
{
    Q_OBJECT

public:
    explicit Window (QWidget* parent = 0);
    ~Window();

private slots:
    void updateAddressPlaceholder();
    void updateEnabled (int unused);
    void syncButtons (bool enabled);
    void setProtocol (int protocol);
    void setTeamStation (int station);
    void updateControlMode (int unused);
    void setVoltage (const float voltage);

private:
    Ui::Window* ui;
    DriverStation* ds;
    SocketHandler* sh;
};

#endif
