//
// Created by Teodor Filp on 10/04/2021.
//
#include "MovieFileRW.h"

#include <fstream>
#include <vector>

using std::vector;
using std::ifstream;
using std::ofstream;

ifstream& operator>>(ifstream& is, Movie& movie) {
    string title, genre, trailer;
    int releaseYear, likes, id;
    is >> id >> title >> genre >> trailer >> releaseYear >> likes;
    movie = Movie(title, genre, trailer, releaseYear, likes);
    movie.id = id;
    return is;
}

ofstream& operator<<(ofstream& os, Movie& movie) {
    os << movie.id << " "
        << movie.getTitle() << " "
        << movie.getGenre() << " "
        << movie.getTrailerLink() << " "
        << movie.getReleaseYear() << " "
        << movie.getLikes() << "\n";

    return os;
}

MovieFileRW::MovieFileRW(string fileName) {
    this->fileName = fileName;
}

vector<Movie> MovieFileRW::loadMovies() {
    vector<Movie> list;
    ifstream read("../" + this->fileName);
    int count;
    read >> count;

    for (int i=0; i<count; i++) {
        Movie m;
        read >> m;
        list.push_back(m);
    }
    read.close();
    return list;
}

void MovieFileRW::saveMovies(vector<Movie> &movies) {
    ofstream write("../" + this->fileName);
    write << movies.size() << "\n";
    for (auto &movie: movies) {
        write << movie;
    }

    write.close();
}
