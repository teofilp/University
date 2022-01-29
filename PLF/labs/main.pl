insert(L, E, [E|L]).
insert([H|T], E, [H|R]):- insert(T, E, R).

subs([], []).
subs([H|T], Rez):-
    subs(T, R),
    insert(R, H, Rez).

subs([_|T], R):- subs(T, R).

list_length([], 0).
list_length([_|T], R):-
    list_length(T, R1),
    R is 1 + R1.

prod([], 1).
prod([H|T], R):-
    prod(T, R1),
    R is R1 * H.

oneSol(L, K, P, R):-
    subs(L, R),
    list_length(R, Le),
    Le =:= K,
    prod(R, Pr),
    Pr =:= P.

allSol(L, K, P, R):- findall(X, oneSol(L, K, P, X), R).










% f(T, S1), H > 0
f([H|T], S): H > 0, f(T, S1), S1 < H, !, S is H.
f([_|T], S): f(T, S1), S is S1.

f([H|T], S): f(T, S1), aux(H, S1, S).

aux(H, S1, S):- H > 0, S1 < H, !, S is H.
aux(_, S1, S1).
