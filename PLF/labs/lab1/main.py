## 3/a

# Mathematical Model

# listContains(l1l2...ln, el):
#   - false, n = 0
#   - true, l1 = el
#   - listContains(l2...ln, el), otherwise

# checkSet(l1l2...ln):
#   - true, n = 0
#   - false, listContains(l2...ln, l1) = true
#   - checkSet(l2...ln), otherwise
def getLength(list, size = 0):
    if list == []: return size
    return getLength(list[1:], size + 1)

def listContains(list, el):
    if getLength(list) == 0:
        return False
    if list[0] == el:
        return True
    return listContains(list[1:], el)

def checkSet(list):
    if getLength(list) == 0:
        return True
    if listContains(list[1:], list[0]) == True:
        return False
    return checkSet(list[1:])

# getLength (l1l2...ln)
#   - 0, n = 0
#   - 1 + getLength(l2...ln), otherwise

# getLengthMain(l1l2...ln)
#   - getLength(l1l2...ln, 0)

# distinctElements(l1l2...ln, k1k2...kn, length):
#   - length - getLength(k1k2...kn), getLength(l1l2...ln) = 0
#   - distinctElements(l2...ln, l1 U k1k2...kn, length + 1), listContains(l2l3...ln, l1) or listContains(k1k2...kn, l1)
#   - distinctElements(l2...ln, k1k2...kn, length + 1), otherwise

# distinctElementsMain(l1l2...ln):
#   - distinctElements(l1l2...ln, [], 0)


def distinctElements(list, repeatedElements = [], length = 0):
    if getLength(list) == 0:
        return length - getLength(repeatedElements)
    if listContains(list[1:], list[0]) or listContains(repeatedElements, list[0]):
        return distinctElements(list[1:], repeatedElements + [list[0]], length + 1)
    else:
        return distinctElements(list[1:], repeatedElements, length + 1)

print(checkSet([]))
print(checkSet([1, 2, 3, 1]))
print(checkSet([1, 2, 3, 4]))

print(distinctElements([]))
print(distinctElements([1, 2, 3, 4]))
print(distinctElements([1, 2, 3, 4, 1]))
print(distinctElements([1, 2, 3, 1, 1, 2, 3, 5, 6, 5]))