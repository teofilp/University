//
// Created by Teodor Filp on 21/06/2021.
//

#pragma once
#ifndef JIRACLONE_SUBSCRIBER_H
#define JIRACLONE_SUBSCRIBER_H

class Subscriber {
    public:
        virtual void update() = 0;
};

#endif //JIRACLONE_SUBSCRIBER_H
