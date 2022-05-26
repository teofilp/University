//
// Created by Teodor Filp on 30/05/2021.
//

#ifndef TESTQT_MOVIEMODEL_H
#define TESTQT_MOVIEMODEL_H


#include <QAbstractTableModel>
#include "../../repositories/MoviesRepository.h"

class MovieModel: public QAbstractTableModel {
    Q_OBJECT
    private:
        vector<Movie> movies;
    public:
        MovieModel(vector<Movie>);
        int rowCount(const QModelIndex &parent = QModelIndex()) const override;
        int columnCount(const QModelIndex &parent = QModelIndex()) const override;
        QVariant data(const QModelIndex &idx, int role = Qt::DisplayRole) const override;
        void reset(vector<Movie>);
};


#endif //TESTQT_MOVIEMODEL_H
