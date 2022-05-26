//
// Created by Teodor Filp on 29/05/2021.
//

#ifndef TESTQT_IUNDOREDOSERVICE_H
#define TESTQT_IUNDOREDOSERVICE_H

#include "../repositories/MoviesRepository.h"

class IUndoRedoService {
    protected:
        MoviesRepository &repo;

    public:
        virtual void undo() = 0;
        virtual void redo() = 0;
        virtual void init() = 0;
        virtual void markCheckpoint() = 0;
        IUndoRedoService(MoviesRepository &repo): repo(repo){};
};


#endif //TESTQT_IUNDOREDOSERVICE_H
