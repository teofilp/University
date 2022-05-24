#include <QApplication>
#include <QPushButton>
#include <QGridLayout>
int main(int argc, char *argv[]) {
    QApplication a(argc, argv);
    QPushButton button("Hello world!", nullptr);
    button.resize(200, 100);
    QGridLayout *layout = new QGridLayout();
    QWidget *widget = new QWidget();
    layout->addWidget(&button, 1, 1);
    widget->setLayout(layout);
    widget->show();

    return QApplication::exec();
}
