//
// Created by Teodor Filp on 10/04/2021.
//

#pragma once
#ifndef A2_MOVIEVALIDATOREXCEPTION_H
#define A2_MOVIEVALIDATOREXCEPTION_H
using std::runtime_error;

class MovieValidatorException : public runtime_error {
public:
    MovieValidatorException(string message): runtime_error(message) {}
};
#endif //A2_MOVIEVALIDATOREXCEPTION_H
