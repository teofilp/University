//
// Created by Teodor Filp on 20/03/2021.
//

#ifndef A2_ENTITY_H
#define A2_ENTITY_H

#define NULL_ENTITY Entity(-1)

class Entity {
    public:
        int id;

        Entity();
        Entity(int);
        bool operator==(const Entity&) const;
        bool operator!=(const Entity&) const;
};

#endif //A2_ENTITY_H
