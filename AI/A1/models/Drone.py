v = [[-1, 0], [0, 1], [1, 0], [0, -1]]

class Drone():
    def __init__(self, x, y):
        self.x = x
        self.y = y
        self.next_positions = []
        self.visited = []
        self.add_next(x, y, False)

    def add_next(self, x, y, backtrack):
        self.next_positions.insert(0, {
            "pos": (x, y),
            "backtrack": backtrack
        })
                  
    def moveDSF(self, detectedMap, e):
        
        if len(self.next_positions) == 0: return 0

        next = self.next_positions[0]
        
        (x, y) = next["pos"]

        self.visited.append((x, y))
        
        self.x = x
        self.y = y

        self.next_positions = self.next_positions[1:]

        if next["backtrack"] == True: return

        depth = e.readUDMSensors(self.x, self.y)

        for i in range(len(v)):
            next_x = self.x + v[i][0]
            next_y = self.y + v[i][1]

            if next_x <= 19 and next_x >= 0 and next_y >= 0 and next_y <= 19 and depth[i] > 0 and (next_x, next_y) not in self.visited:
                self.add_next(self.x, self.y, True)
                self.add_next(next_x, next_y, False)

        return len(self.next_positions)