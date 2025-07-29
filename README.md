Implement a natural merge sort for linked lists. (This is the method of choice for sorting linked lists because it uses no extra space and is guaranteed to be linearithmic.) During each iteration, natural merge sort works by scanning the list from the left to right identifying naturally sorted sub-lists and merging the sub-lists, and continue scanning further identifying and merging the sub-lists until the end of the list. Repeats the process until the entire list is sorted.

Example: Unsorted list M -> E -> R -> G -> E -> S -> O -> R -> T -> E -> X -> A -> M -> P -> L -> E

After first iteration: E - > M -> R -> E -> G -> S -> E -> O -> R -> T -> X -> A -> L -> M -> P -> E

After second iteration: E -> E -> G -> M -> R -> S -> A -> E -> L -> M -> 0 -> P -> R -> T -> X -> E

After third iteration: A -> E -> E -> E -> G -> L -> M -> M -> O -> P -> R -> R -> S – T -> X -> E

After fourth iteration: A -> E -> E -> E -> E -> G -> L -> M -> M -> O -> P -> R -> R -> S – T -> X
