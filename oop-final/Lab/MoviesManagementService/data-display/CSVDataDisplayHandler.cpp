//
// Created by Teodor Filp on 16/04/2021.
//
#include <fstream>
#include <sstream>

#include "CSVDataDisplayHandler.h"

using std::ofstream, std::stringstream;

CSVDataDisplayHandler::CSVDataDisplayHandler(string &fileName): DataDisplayHandler(fileName) {}

void CSVDataDisplayHandler::generatePreview(vector<Movie> &movies) {
    ofstream write("../" + fileName + getFileExtension());

    for (auto &movie: movies) {
        stringstream  ss;
        ss << movie.id << ","
            << movie.getTitle() << ","
            << movie.getGenre() << ","
            << movie.getTrailerLink() << ","
            << movie.getReleaseYear() << ","
            << movie.getLikes() << "\n";
        write << ss.str();
    }

    write.close();
}

void CSVDataDisplayHandler::preview() {
    string command = "qlmanage -p ../" + fileName + getFileExtension();
    try {
        system(command.c_str());
    } catch (...) {

    }
}

string CSVDataDisplayHandler::getFileExtension() {
    return ".csv";
}


CSVDataDisplayHandler::~CSVDataDisplayHandler() {}