% (i, i)
% listContains(el, l1l2...ln) = 
%   - true, el = l1
%   - false, n = 0
% 
listContains(E, [E|_]).
listContains(E, [_|L]):- listContains(E, L).

% Element, Source, dest
% (i, i, o)
% Element, list
% - [E], n = 0
% - l1 U AddToEnd(E, l2...ln), otherwise

addToEnd(E, [], [E]).
addToEnd(E, [H|T], [H|R]):- addToEnd(E, T, R).

% removeRepeated(l1l2...ln, k1k2...km) = 
%   - [], if n = 0
%   - removeRepeated(l2...ln, l1 U k1k2...km), listContains(l1, k1k2...km) = true
%   - removeRepeated(l2...ln, l1 U k1k2...km), listContains(l1, l2...ln) = true
%   - l1 U removeRepeated(l2...ln, k1k2...km), otherwise

% (i, i, i, o)
% removeRepeated - source, repeated, result, collector
removeRepeated([], _, C, C).

removeRepeated([H|T], Rep, R, C):-
    listContains(H, Rep),
    removeRepeated(T, [H|Rep], R, C).

removeRepeated([H|T], Rep, R, C):-
    listContains(H, T),
    removeRepeated(T, [H|Rep], R, C).

removeRepeated([H|T], Rep, R, C):-
    addToEnd(H, R, Res),
    removeRepeated(T, Rep, Res, C).

removeRepeatedMain(L, R):- removeRepeated(L, [], [], R).

% (i, i)
% isBiggest(el, l1l2...ln) = 
%   - true, n = 0
%   - isBiggest(el, l2...ln), el >= l1
%   - false, otherwise

isBiggest(_, []).
isBiggest(E, [H|T]):-
    E >= H,
    isBiggest(E, T).

% (i, i, i, o)
% removeBiggest(l1l2...ln, c1c2...cm) = 
%   - [], n = 0 
%   - removeBiggest(l2l3...ln, c1c2...cm), isBiggest(l1, c1c2...cm)
%   - l1 U removeBiggest(l2l3...ln, c1c2...cm), otherwise

removeBiggest([], _, C, C).

removeBiggest([H|T], CS, R, C):-
    isBiggest(H, CS),
    removeBiggest(T, CS, R, C).

removeBiggest([H|T], CS, R, C):- 
    addToEnd(H, R, Res),
    removeBiggest(T, CS, Res, C).

% (i, 0)
removeBiggestMain(L, R):- removeBiggest(L, L, [], R).