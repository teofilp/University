//
// Created by Teodor Filp on 16/04/2021.
//

#pragma once
#ifndef A2_HTMLDATADISPLAYHANDLER_H
#define A2_HTMLDATADISPLAYHANDLER_H

#include "DataDisplayHandler.h"

class HTMLDataDisplayHandler: public DataDisplayHandler {
    public:
        HTMLDataDisplayHandler(string&);
        void generatePreview(vector<Movie>&) override;
        void preview() override;
        string getFileExtension() override;
        ~HTMLDataDisplayHandler() override;
};

#endif //A2_HTMLDATADISPLAYHANDLER_H
