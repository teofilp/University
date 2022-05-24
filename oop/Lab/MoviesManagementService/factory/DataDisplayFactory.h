//
// Created by Teodor Filp on 16/04/2021.
//

#pragma once
#ifndef A2_DATADISPLAYFACTORY_H
#define A2_DATADISPLAYFACTORY_H

#include "../data-display/DataDisplayHandler.h"

enum DataDisplayMode {
    HTML,
    CSV
};

class DataDisplayFactory {
    public:
        DataDisplayHandler* getDataDisplayHandler(enum DataDisplayMode, string&);
};

#endif //A2_DATADISPLAYFACTORY_H
