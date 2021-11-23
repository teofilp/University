% generate(N - number, R - number)
%    - R, R <= N,
%    - generate(N, R+1), R < N

generate(N, R, R):- R =< N.

generate(N, R, C):- 
    R < N,
    R1 is R + 1,
    generate(N, R1, C).

generateMain(N, C):- generate(N, 1, C).

% getNext(l1l2...ln - list)
%   - 1, n = 0
%   - l1 + 1, otherwise
getNext([], 1):- !.
getNext([E|_], R):- R is E + 1.

% N - number, L - list, R - remaining, L - collector

% execute(N - number, l1l2...ln - list, Rem - number)
%   - L, Rem = 0
%   - execute(N, generateMain(N) U [], Rem - generateMain(N)), if n = 0
%   - execute(N, getNext(l1l2..ln) U l1l2...ln, NewRem - getNext(l1l2...ln)), otherwise
execute(_, L, 0, L).

execute(N, [], Rem, R):- 
    generateMain(N, G),
    G =< Rem,
    NewRem is Rem - G,
    execute(N, [G], NewRem, R).

execute(N, [H|T], Rem, R):-
    getNext([H|T], Next),
    Next =< Rem,
    NewRem is Rem - Next,
    execute(N, [Next,H|T], NewRem, R).

executeMain(N, R):- findall(Rl, execute(N, [], N, Rl), R).