//
// Created by Teodor Filp on 10/04/2021.
//

#pragma once
#ifndef A2_REPOSITORYEXCEPTION_H
#define A2_REPOSITORYEXCEPTION_H

using std::runtime_error;

class RepositoryException : public runtime_error {
    public:
        RepositoryException(string message): runtime_error(message) {}
};

#endif //A2_REPOSITORYEXCEPTION_H
