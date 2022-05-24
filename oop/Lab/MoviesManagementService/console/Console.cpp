//
// Created by Teodor Filp on 20/03/2021.
//
#include <iostream>
#include <string>

#include "Console.h"

using std::string;
using std::cin;
using std::cout;
using std::ostream;

Console::Console(ManagerService &managerService, UserService &userService) {
    this->managerService = &managerService;
    this->userService = &userService;
}

void Console::printManagerMenu() {
    string menu = "\n\t(Manager)\n"
                  "1. Add a new movie \n"
                  "2. Update a movie \n"
                  "3. Delete a movie \n"
                  "4. View movies \n"
                  "5. Exit \n";
    cout << menu;
}

Movie readMovie() {
    string title, genre, trailerLink;
    string year, lk;
    int likes, releaseYear;

    cout << "Enter the title: ";
    cin >> title;

    cout << "Enter the genre: ";
    cin >> genre;

    cout << "Enter trailer link: ";
    cin >> trailerLink;

    cout << "Enter release year: ";
    cin >> year;

    cout << "Enter likes: ";
    cin >> lk;

    likes = atoi(lk.c_str());
    releaseYear = atoi(year.c_str());

    return Movie(title, genre, trailerLink, releaseYear, likes);
}

int Console::handleNewMovie() {
    Movie newMovie = readMovie();
    this->managerService->add(newMovie);

    return 0;
}

int Console::handleDeleteMovie() {
    string id_str;
    int id;
    cout << "Enter the id: ";
    cin >> id_str;

    id = atoi(id_str.c_str());

    this->managerService->remove(id);

    return 0;
}

int Console::handleUpdateMovie() {
    int id;
    cout << "Enter the id: ";
    cin >> id;
    Movie updated = readMovie();
    updated.id = id;
    this->managerService->update(updated);

    return 0;
}

int Console::handleViewMovies() {
    vector<Movie> movies = this->managerService->getAll();

    for (auto &movie: movies) {
        cout << movie;
    }
    return 0;
}

int Console::getOption() {
    string optionString;
    cout << "Enter your option:";
    cin >> optionString;
    return atoi(optionString.c_str());
}

int Console::runManagerCommand(int option) {
    switch (option) {
        case 1:
            return handleNewMovie();
        case 2:
            return handleUpdateMovie();
        case 3:
            return handleDeleteMovie();
        case 4:
            return handleViewMovies();
        case 5:
            return 1;
        default:
            throw std::runtime_error("\n\tInvalid option!\n\n");
    }
}

void Console::runManager() {
    bool isDone = false;
    while (!isDone) {
        printManagerMenu();
        try {
            int shouldExit = runManagerCommand(getOption());
            if (shouldExit) {
                isDone = true;
            }
        } catch (std::exception &ex) {
            cout << "\n\n\t" << ex.what() << "\n\n\t";
        }
    }
}

void Console::run() {
    bool isDone = false;
    while (!isDone) {
        printModes();
        int option = getOption();
        if (option == 1) {
            runManager();
        } else if (option == 2) {
            runUser();
        } else {
            isDone = true;
        }
    }
}

void Console::printModes() {
    string message = ""
                     "1. Manager mode \n"
                     "2. User mode \n"
                     "3. Exit \n";
    cout << message;
}

void Console::runUser() {
    bool isDone = false;
    while (!isDone) {
        printUserMenu();
        try {
            int shouldExit = runUserCommand(getOption());
            if (shouldExit) {
                isDone = true;
            }
        } catch (std::exception &ex) {
            cout << "\n\n\t" << ex.what() << "\n\n\t";
        }
    }
}

void Console::printUserMenu() {
    string message = "\n\t(User)\n"
                     "1. SearchTab movies by genre \n"
                     "2. Delete from watchlist \n"
                     "3. View watchlist \n"
                     "4. Preview watchlist \n"
                     "5. Exit \n";
    cout << message;
}

int Console::runUserCommand(int option) {
    switch (option) {
        case 1:
            return handleSearchByGenre();
        case 2:
            return handleDeleteFromWatchlist();
        case 3:
            return handleViewWatchlist();
        case 4:
            return handlePreviewMovies();
        case 5:
            return 1;
        default:
            throw std::runtime_error("\n\tInvalid command\n\n");
    }
}

int Console::handleSearchByGenre() {
    string genre;
    cout << "Enter desired genre: ";
    getchar();
    getline(cin, genre);
    vector<Movie> movies = userService->getByGenre(genre);

    if (movies.empty()) {
        cout << "We couldn't find any movie having the genre " + genre;
        return 0;
    }

    int showNext = true;
    int i = 0;
    while (showNext) {
        Movie current = movies[i];
        bool shouldAdd;
        try {
            cout << current;
            std::system(("open " + current.getTrailerLink()).c_str());
            cout << "Want to add to watchlist? (1/0)\n";
            cin >> shouldAdd;
            if (shouldAdd) {
                this->userService->addToWatchlist(current);
            }
        } catch (std::exception &ex) {
            cout << "\n\n\t" << ex.what() << "\n\n";
        }
        cout << "Go to next movie? (1/0)\n";
        cin >> showNext;

        if (showNext) {
            i++;
            i %= movies.size();
        }
    }

    return 0;
}

int Console::handleViewWatchlist() {
    vector<Movie> watchlist = this->userService->getWatchlist();

    if (watchlist.size() == 0) {
        cout << "Your watchlist is empty, try adding some movies :)";
    }

    for (auto &movie: watchlist) {
        cout << movie;
    }

    return 0;
}

int Console::handleDeleteFromWatchlist() {
    int id;
    bool shouldRate;
    cout << "Enter the id:";
    cin >> id;
    try {
        this->userService->removeFromWatchlist(id);
    } catch (std::exception &ex) {
        cout << ex.what();
        return 0;
    }

    cout << "Do you wish to rate this movie? (1/0)";
    cin >> shouldRate;

    if (shouldRate) {
        try {
            this->managerService->increaseLikes(id);
        } catch (std::exception &ex) {
            cout << ex.what();
        }
    }

    return 0;
}

int Console::handlePreviewMovies() {
    this->userService->previewWatchlist();
    return 0;
}
