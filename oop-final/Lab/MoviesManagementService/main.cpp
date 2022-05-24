#include <QApplication>
#include <QPushButton>
#include <iostream>
#include <QGridLayout>

#include "./repositories/MoviesRepository.h"
#include "services/ManagerService.h"
#include "console/Console.h"
#include "tests/domain_tests.h"
#include "tests/repository_tests.h"
#include "tests/service_tests.h"
#include "tests/validators_tests.h"
#include "data-display/CSVDataDisplayHandler.h"
#include "factory/DataDisplayFactory.h"
#include "builders/HTMLBuilder.h"
#include "console/GuiApp.h"
#include "services/UndoRedoService.h"

void runAllTests() {
    runDomainTests();
    runRepositoryTests();
    runServiceTests();
    runValidatorsTests();
}

DataDisplayHandler* getAppropriateHandler() {
    return nullptr;
    DataDisplayFactory factory;
    std::cout << "CSV / HTML (1/0): ";
    bool isCSV;
    string fileName;
    std::cin >> isCSV;
    std::cout << "Insert desired file name:";
    std::cin >> fileName;
    DataDisplayHandler *handler = factory.getDataDisplayHandler(isCSV ? CSV : HTML, fileName);
    return handler;
}

int main(int argc, char *argv[]) {
//    bool runTests;
//    std::cout << "Run tests? (YES / NO): ";
//    std::cin >> runTests;
//    if (runTests) {
//        runAllTests();
//        return 0;
//    }
//
    auto *handler = getAppropriateHandler();
    MovieFileRW managerHandler("movies.txt"), userHandler("watchlist.txt");
    MoviesRepository repo(managerHandler), watchlist(userHandler);
    UndoRedoService *undoService = new UndoRedoService(repo);
    ManagerService managerService(repo);
    UserService userService(repo, watchlist, *handler);
    GuiApp app(argc, argv, &managerService, &userService, undoService);

    return app.run();
//    Console console(managerService, userService);
//
//    console.run();
//    delete handler;

}
