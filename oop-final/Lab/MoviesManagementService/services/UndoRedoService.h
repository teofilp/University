//
// Created by Teodor Filp on 29/05/2021.
//

#ifndef TESTQT_UNDOREDOSERVICE_H
#define TESTQT_UNDOREDOSERVICE_H


#include "IUndoRedoService.h"
#include "../repositories/MoviesRepository.h"

class UndoRedoService: public IUndoRedoService {
    private:
        int current;
        vector<vector<Movie>> history;
    public:
        void undo() override;
        void redo() override;
        UndoRedoService(MoviesRepository &repo);
        void init() override;
        void markCheckpoint() override;

    bool canUndo();

    bool canRedo();
};


#endif //TESTQT_UNDOREDOSERVICE_H
