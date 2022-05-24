//
// Created by Teodor Filp on 16/04/2021.
//

#pragma once
#ifndef A2_DATADISPLAY_H
#define A2_DATADISPLAY_H

#include <string>
#include <vector>

#include "../domain/Movie.h"

using std::string;
using std::vector;

class DataDisplayHandler {
    protected:
        string fileName;
    public:
        DataDisplayHandler(string &fileName) {
            this->fileName = fileName;
        };
        virtual void generatePreview(vector<Movie>&) = 0;
        virtual string getFileExtension() = 0;
        virtual void preview() = 0;
        virtual ~DataDisplayHandler() = default;
};


#endif //A2_DATADISPLAY_H
