//
// Created by Teodor Filp on 21/06/2021.
//

#pragma once
#ifndef JIRACLONE_OBSERVABLE_H
#define JIRACLONE_OBSERVABLE_H

#include <vector>
#include "Subscriber.h"

using std::vector;

class Observable {
private:
    vector<Subscriber*> subscribers;
public:
    void subscribe(Subscriber* subs) {
        subscribers.push_back(subs);
    }
    void notify() {
        for (auto &sub: subscribers) {
            sub->update();
        }
    }
};

#endif //JIRACLONE_OBSERVABLE_H
