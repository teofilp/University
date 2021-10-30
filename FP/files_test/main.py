import csv

class Person:
    def __init__(self, p_id, name, phone):
        self._id = p_id # {_id: p_id, _name: name, _phone: phone, _age = 10}
        self._name = name
        self._phone = phone
        self._age = 10
    
    def increment_age(self):
        self._age += 1

# person = Person(1, "teo", "954309543")
# person2 = Person(2, "ioa", "84938534")
people = []

class FileRepository:
    def __init__(self, file_name):
        self._file_name = file_name
        self._data = []
        self.load()

    def save(self):
        if len(self._data) == 0:
            return 
        with open(self._file_name, "w") as file:
            keys = self._data[0].__dict__.keys()
            writer = csv.DictWriter(file, keys)
            writer.writeheader()
            for person in self._data:
                writer.writerow(person.__dict__)

    def load(self):
        file = open(self._file_name, "r")
        reader = csv.DictReader(file)
        for row in reader:
            self._data.append(Person(row["_id"], row["_name"], row["_phone"]))
        file.close()

    def add(self, person):
        self._data.append(person)
        self.save()

    def delete(self, person_id):
        self._data = list(filter(lambda x: x._id != person_id, self._data))
        self.save()


fileRepo = FileRepository("test.txt")
print(fileRepo._data)
# fileRepo.add(Person(3, "random", "854958349"))
# fileRepo.delete()
