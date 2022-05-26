//
// Created by Teodor Filp on 29/05/2021.
//

#include "UndoRedoService.h"

void UndoRedoService::undo() {
    if (!canUndo()) {
        return;
    }

    current--;
    repo.overwriteRepo(history[current]);
}

void UndoRedoService::redo() {
    if (!canRedo()) {
        return;
    }

    current++;
    repo.overwriteRepo(history[current]);
}

UndoRedoService::UndoRedoService(MoviesRepository &repo) : IUndoRedoService(repo) {
    init();
}

void UndoRedoService::init() {
    current = 0;
    history.push_back(repo.getAll());
}

bool UndoRedoService::canUndo() {
    return current > 0;
}

bool UndoRedoService::canRedo() {
    return current < history.size() - 1;
}

void UndoRedoService::markCheckpoint() {
    auto it = find_if(this->history.begin(), this->history.end(), [this] (const vector<Movie> movies) { return movies == this->history[this->current]; });
    history.erase(it+1, history.end());
    history.push_back(repo.getAll());
    current++;
}
