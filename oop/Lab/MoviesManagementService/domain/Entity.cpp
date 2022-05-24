//
// Created by Teodor Filp on 20/03/2021.
//
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#include "./Entity.h"

int getRandomId();

Entity::Entity() {
    this->id = getRandomId();
}

Entity::Entity(int id) {
    this->id = id;
}

bool Entity::operator==(const Entity &rhs) const {
    return this->id == rhs.id;
}

bool Entity::operator!=(const Entity &rhs) const {
    return this->id != rhs.id;
}

int getRandomId() {
    srand(time(NULL));
    return -(rand() % 1000000);
}