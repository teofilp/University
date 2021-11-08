% a

% listContains(el, l1l2..ln):
%    - false, n = 0
%    - true, el = l1
%    - listContains(el, l2...ln), otherwise

% listContains(el - number, l - list);
listContains(E, [E|_]).
listContains(E, [_|T]):- listContains(E, T).

% addToList(el, l1l2...ln):
%   - [el], n = 0
%   - el U l1l2...ln, el < l1
%   - l1 U addToList(el, l2...ln), el >= l1

% addToList(el - number, rez - list, col - list)
addToList(E, [], [E]).

addToList(E, [H|T], [H|R]):-
    E >= H,
    addToList(E, T, R).

addToList(E, [H|T], [E,H|T]):-
    E < H.

% sortList(l1l2..ln, k1k2...km):
%   - k1k2...km, n = 0
%   - sortList(l2...ln, addToList(l1, k1k2...km)), otherwise

% sortList(l - list, rez - list, col - list)

sortList([], C, C).

sortList([H|T], R, C):-
    addToList(H, R, Rez),
    sortList(T, Rez, C).

% keepOnce(l1l2...ln, k1k2...km):
%   - keepOnce(l2...ln, addToEnd(l1, k1k2...km)), listContains(l1, k1k2...km) = false
%   - keepOnce(l2...ln, k1k2...km), otherwise

% keepOnce(l - list, rez - list, col - list)
% removes duplicates
keepOnce([], C, C).

keepOnce([H|T], R, C):-
    listContains(H, R), !,
    keepOnce(T, R, C).

keepOnce([H|T], R, C):-
    addToEnd(H, R, Rez),
    keepOnce(T, Rez, C).

keepOnceMain(L, R):-
    keepOnce(L, [], R).

% sorts and for each sublist keeps only the first occurence of an element

% sortWithKeepOnceMain(l1l2...ln):
%   - sortList(keepOnce(l1...ln, []), [])

sortWithKeepOnceMain(L, R):-
    keepOnceMain(L, Rez),
    sortList(Rez, [], R).

% 
% addToEnd(el, l1l2...ln):
% - [el], n = 0
% - l1 U AddToEnd(el, l2...ln), otherwise

addToEnd(E, [], [E]).
addToEnd(E, [H|T], [H|R]):- addToEnd(E, T, R).

% b

% sortH(l1l2...ln, k1k2...km):
%   - k1k2...km, n = 0
%   - sortH(l2...ln, addToEnd(sortWithKeepOnceMain(l1), k1k2...km)), is_list(l1)
%   - sortH(l2...ln, addToEnd(l1, k1k2...km)), otherwise

sortH([], C, C).

sortH([H|T], R, C):-
    is_list(H), !,
    sortWithKeepOnceMain(H, Rez),
    addToEnd(Rez, R, NewRez),
    sortH(T, NewRez, C).

sortH([H|T], R, C):-
    addToEnd(H, R, Rez),
    sortH(T, Rez, C).

sortHMain(L, R):- sortH(L, [], R).