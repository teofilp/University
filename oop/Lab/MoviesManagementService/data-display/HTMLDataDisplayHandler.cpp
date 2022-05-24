//
// Created by Teodor Filp on 16/04/2021.
//

#include <fstream>

#include "HTMLDataDisplayHandler.h"
#include "../builders/HTMLBuilder.h"

using std::ofstream, std::stringstream;

HTMLDataDisplayHandler::HTMLDataDisplayHandler(string &fileName): DataDisplayHandler(fileName) {}

void HTMLDataDisplayHandler::generatePreview(vector<Movie> &movies) {
    ofstream write("../" + fileName + getFileExtension());
    string html = HTMLBuilder::init().buildPage(movies);
    write << html;
    write.close();
}

void HTMLDataDisplayHandler::preview() {
    system(("open ../" + fileName + getFileExtension()).c_str());
}

string HTMLDataDisplayHandler::getFileExtension() {
    return ".html";
}

HTMLDataDisplayHandler::~HTMLDataDisplayHandler() {}