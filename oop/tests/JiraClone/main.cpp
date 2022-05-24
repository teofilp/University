#include <QApplication>
#include <QPushButton>
#include "repositories/Repository.h"
#include "widgets/MainWindow.h"

int main(int argc, char *argv[]) {
    QApplication a(argc, argv);

    Repository rep;
    MainWindow window(&rep);

    return QApplication::exec();
}
