#include <iostream>
#include <string>
#include <vector>
#include <sstream>

using namespace std;

class AmbientCondition {
    public:
        virtual int adjust() = 0;
};

class Temperature: public AmbientCondition {
    private:
        double value;

    public:
        Temperature(double val): value(val){}

        int adjust() override { return value; }
};

class Light: public AmbientCondition {
    private:
        bool value;
    public:
        Light(bool val): value(val){}

        int adjust() override { return value; }
};

class BuildingComponent {
    protected:
        AmbientCondition *condition;
        
    public:
        virtual void setAmbientCondition(AmbientCondition *cond) = 0;
        virtual void addBuildingComponent(BuildingComponent *component) = 0;
        virtual ~BuildingComponent() {}
};

class Room: public BuildingComponent {
    private:
        int number;
    public:
        Room(int num): number(num) {}

        void setAmbientCondition(AmbientCondition *cond) override {
            condition = cond;
        }

        void addBuildingComponent(BuildingComponent *component) override {}
};

class Floor: public BuildingComponent {
    private:
        int number;
        vector<BuildingComponent *> components;
    public:
        Floor(int num) {
            number = num;
        }

        void setAmbientCondition(AmbientCondition *cond) override {
            condition = cond;
            for (auto &comp: components) {
                comp->setAmbientCondition(cond);
            }
        }

        void addBuildingComponent(BuildingComponent *component) override {
            components.push_back(component);
        }

        ~Floor() {
            for (auto &comp: components) {
                delete comp;
            }
        }
};

class Building: public BuildingComponent {
    private:
        string name;
        vector<BuildingComponent *> components;
    public:
        Building(string n) {
            name = n;
        }

        void setAmbientCondition(AmbientCondition *cond) override {
            condition = cond;
            for (auto &comp: components) {
                comp->setAmbientCondition(cond);
            }
        }

        void addBuildingComponent(BuildingComponent *component) override {
            components.push_back(component);
        }

        ~Building() {
            for (auto &comp: components) {
                delete comp;
            }
        }
};

int
main()
{
    Room *r0 = new Room(0);
    Floor *f0 = new Floor(0);

    f0->addBuildingComponent(r0);

    Room *r1 = new Room(1);
    Room *r2 = new Room(2);

    Floor *f1 = new Floor(1);
    
    f1->addBuildingComponent(r1);
    f1->addBuildingComponent(r2);

    AmbientCondition *temp = new Temperature(23);

    f0->setAmbientCondition(temp);
    f1->setAmbientCondition(temp);


    Room *r3 = new Room(3);
    Floor *f2 = new Floor(2);
    f2->addBuildingComponent(r3);


    Room *r4 = new Room(4);
    Floor *f3 = new Floor(3);
    f3->addBuildingComponent(r4);

    Floor *attic = new Floor(4);
    attic->addBuildingComponent(f2);
    attic->addBuildingComponent(f3);

    AmbientCondition *condition = new Temperature(18);
    attic->setAmbientCondition(condition);

    BuildingComponent *building = new Building("FSEGA");

    building->addBuildingComponent(f0);
    building->addBuildingComponent(f1);
    building->addBuildingComponent(attic);

    AmbientCondition *lights = new Light(true);

    building->setAmbientCondition(lights);

    delete building;
}