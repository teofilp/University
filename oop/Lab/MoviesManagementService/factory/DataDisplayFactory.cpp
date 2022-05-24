//
// Created by Teodor Filp on 16/04/2021.
//

#include "../data-display/HTMLDataDisplayHandler.h"
#include "../data-display/CSVDataDisplayHandler.h"
#include "DataDisplayFactory.h"

DataDisplayHandler* DataDisplayFactory::getDataDisplayHandler(enum DataDisplayMode mode, string &fileName) {
    switch (mode) {
        case HTML: return new HTMLDataDisplayHandler(fileName);
        case CSV: return new CSVDataDisplayHandler(fileName);
        default: return nullptr;
    }
}
