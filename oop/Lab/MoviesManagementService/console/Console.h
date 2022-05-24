//
// Created by Teodor Filp on 20/03/2021.
//

#ifndef A2_CONSOLE_H
#define A2_CONSOLE_H

#include "../services/ManagerService.h"
#include "../services/UserService.h"

class Console {
    private:
        ManagerService *managerService;
        UserService *userService;

        void printManagerMenu();
        int getOption();
        int runManagerCommand(int);
        int handleNewMovie();
        int handleUpdateMovie();
        int handleDeleteMovie();
        int handleViewMovies();
        void runManager();
        void printModes();
        void runUser();
        void printUserMenu();
        int runUserCommand(int option);
        int handleSearchByGenre();
        int handleViewWatchlist();
        int handleDeleteFromWatchlist();
    public:
        Console(ManagerService&, UserService&);
        void run();


    int handlePreviewMovies();
};

#endif //A2_CONSOLE_H
