//
// Created by Teodor Filp on 30/05/2021.
//

#include "MovieModel.h"

MovieModel::MovieModel(vector<Movie> movies) {
    this->movies = movies;
}

int MovieModel::rowCount(const QModelIndex &parent) const {
    return this->movies.size();
}

int MovieModel::columnCount(const QModelIndex &parent) const {
    return 6;
}

QVariant MovieModel::data(const QModelIndex &idx, int role) const {
    int row = idx.row();
    int column = idx.column();
    auto movie = movies[row];

    if (role == Qt::DisplayRole) {
        switch (column) {
            case 0: return QVariant(movie.id);
            case 1: return QVariant(movie.getTitle().c_str());
            case 2: return QVariant(movie.getGenre().c_str());
            case 3: return QVariant(movie.getTrailerLink().c_str());
            case 4: return QVariant(movie.getReleaseYear());
            case 5: return QVariant(movie.getLikes());
        }
    }

    return QVariant();
}

void MovieModel::reset(vector<Movie> movies) {
    this->beginResetModel();
    this->movies = movies;
    this->endResetModel();
}
