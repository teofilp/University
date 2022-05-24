//
// Created by Teodor Filp on 16/04/2021.
//

#pragma once
#ifndef A2_CSVDATADISPLAYHANDLER_H
#define A2_CSVDATADISPLAYHANDLER_H

#include <vector>

#include "DataDisplayHandler.h"
#include "../domain/Movie.h"

using std::vector;

class CSVDataDisplayHandler: public DataDisplayHandler {
    public:
        CSVDataDisplayHandler(string&);
        void generatePreview(vector<Movie>&) override;
        void preview() override;
        string getFileExtension() override;
        ~CSVDataDisplayHandler() override;
};

#endif //A2_CSVDATADISPLAYHANDLER_H
