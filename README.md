## Data Structures

### Arrays

- Contiguous area of memory consisting of equal size elements indexed by contiguous integers.
- **Time** - O(1) to add/remove at end, O(n) to add/remove at start or middle
- **Space** - O(n) Contiguous in memory

> Single dimensional
> 
> ```
> arrayAddress + elementSize *
> (index - firstIndex)
> ```

> Multi dimensional 
> 
> ```
> arrayAddress + elementSize *
> (rowIndex - firstRowIndex) * elementsPerRow + (columnIndex - firstColumnIndex)
> ```

**Row-major ordering (above)** - Consecutive elements of a row reside next to one another in memory. Column index changes most rapidly for successive elements.

**Column-major ordering** - Consecutive elements of a column reside next to one another in memory. Row index changes most rapidly for successive elements.

#### Common operations performed on Arrays

|         | Add  | Remove |
|---------|------|--------|
| Start   | O(n) | O(n)   |
| Middle  | O(n) | O(n)   |
| End     | O(1) | O(1)   |

#### Static and Dynamically allocated Arrays

- Static arrays will need to determine a size at compile time, dynamically allocated arrays can partly solve this problem by allocating a size at runtime.

#### Dynamic Arrays
- Dynamic arrays work by storing a pointer to a dynamically allocated array, replacing it with a newly allocated array as needed (copying over old data, and creating a new pointer).
- Dynamic arrays are an abstract data type with `Get(i)`, `Set(i, val)`, `PushBack(val)`, `Remove(i)`, `Size()` operations (as a minimum).
- They need to store the following:
  - A dynamically-allocated array.
  - A capacity (size of the current dynamically allocated array).
  - Size (number of elements currently stored).

#### Jagged Arrays

An array of arrays where the elements of the arrays can be of various lengths.

---

### Singly-Linked Lists

- A linear data structure comprised of a series of connected nodes
- Each node contains a key value and a pointer to the next node in the list.
- The first node is the HEAD node, and the last node is NULL.
- **Space** - O(n) Linear

#### Common operations performed on Linked Lists

- `PushFront(Key)` - Add item to the front
- `Key TopFront()` - Return item from front
- `PopFront()` - Remove item from the front
- `PushBack(Key)` or `Append(Key)` - Add item to the back
- `Key TopBack()` - Return item from back
- `PopBack()` - Remove item from the front
- `Boolean Find(Key)` - Find item in list
- `Erase(Key)` - Remove item from list
- `Boolean Empty()` - Check if the list is empty
- `AddBefore(Node, Key)` - Add item before node
- `AddAfter(Node, Key)` - Add item after node

|                                                                      | Time | Detail                                                                                                    |
|----------------------------------------------------------------------|------|-----------------------------------------------------------------------------------------------------------|
| PushFront(Key)                                                       | O(1) | Update HEAD pointer & update new node pointer                                                             |
| TopFront()                                                           | O(1) | Return first node                                                                                         |
| PopFront()                                                           | O(1) | Return first node, update HEAD pointer                                                                    |
| PushBack(Key) (no TAIL pointer)                                      | O(n) | Follow pointers to end of list, insert new node                                                           |
| TopBack() (no TAIL pointer)                                          | O(n) | Follow pointers to end of list, return last node                                                          |
| PopBack() (no TAIL pointer)                                          | O(n) | Follow pointers to end of list, return and remove last node                                               |
| PushBack(Key) (with TAIL pointer)                                    | O(1) | Insert node directly at end of list, update old last node pointer, set new TAIL pointer                   |
| TopBack() (with TAIL pointer)                                        | O(1) | Return last node at TAIL pointer                                                                          |
| PopBack() (with TAIL pointer)                                        | O(n) | Return and remove last node at TAIL pointer, follow pointers to new end of list to set TAIL pointer       |
| Find(Key)                                                            | O(n) | Follow pointers until node key matches                                                                    |
| Erase(Key)                                                           | O(n) | Follow pointers until node key matches, remove node, update previous node pointer                         |
| Empty()                                                              | O(1) | Check HEAD pointer value                                                                                  |
| AddBefore(Node, Key) (excluding list traversal, with node reference) | O(n) | Insert node updating pointer to next node, follow pointers to previous node to update pointer to new node |
| AddAfter(Node, Key) (excluding list traversal, with node reference)  | O(1) | Insert node updating pointer to next node, and previous node pointer to new node                          |

### Doubly-Linked Lists

- Similar to a singly-linked list, but each node also contains a reference to its previous node.

This makes some operations related to inserting and deleting much quicker. This includes operations that require setting the previous node's pointer.
It is no longer required to reiterate through the list to find the previous node as there is now a variable in each node that references its previous
node. Because this is the case, some O(n) operations become constant time O(1) operations.

We can also have circularly linked doubly-linked list with a sentinel node. A **sentinel** node is a special node that does not represent an item.
Rather, it contains 2 pointers to both the front and the end of the list. The previous field of the first node and next field of the last node will both
point to this new sentinel node. An enclosing list implementation would then no longer store a head and tail, but just a reference to the sentinel.

#### Comparing Linked-Lists and Arrays

**Advantages**
- Constant time inserts into start or middle of list *if* you have a reference to the previous node.
- Don't need to allocate memory at the start, or create new memory allocations like dynamic arrays. Linked lists can keep growing because each new node is a reference to a new object in memory.

**Disadvantages**
- Finding the nth item of a list takes time proportional to the length of the list, because each node pointer must be followed until the value is found.


A very general rule of thumb is that arrays are faster to read, and linked lists are faster to write, so which is most appropriate will depend upon the algorithm it's being used with.
However, in practise on a larger scope, linked lists must employ costly linear search traversal to locate the appropriate nodes, which outweigh the benefits of constant time insertion/deletion.
This primarily comes down to how data is structured in memory. Linked lists will store items within disjointed separate areas of memory, unable to take advantage of cache line utilization.
Arrays store data contiguously in memory favouring memory locality and keeping data compact. Cache utilization is maximised in this case, reducing cache misses, and even though insertion/deletion 
requires the array to be reallocated, it is a cheap operation in practise. It is a complicated topic, more related to lower level architecture. Here is an interesting article/study on the matter, 
with performance graphs for list and array implementations on various architecture:

https://kjellkod.wordpress.com/2012/02/25/why-you-should-never-ever-ever-use-linked-list-in-your-code-again/

---

### Stacks

- A stack is an abstract data type with a LIFO (last in, first out) structure. This means the last item of data added is the first to leave.
- A stack pointer is used to keep track of the top of the stack.
- The most important operations performed on a stack are:
  - `Push(Key)` - Add a new data item to the top of the stack
  - `Key Top()` - Return the data item at the top of the stack (also known as peak or peaking)
  - `Key Pop()` - Remove and return the data item at the top of the stack
  - `Boolean Empty()` - Return true if the stack contains any items

Stacks can be used to store information about a running program. A **stack frame** is a collection of data that gets pushed onto a stack. In a **call stack** (a special type of stack used to store information about active subroutines and functions in a program), this would represent a subroutine or function call along with its argument data.
The function's return address is pushed onto the stack first, so that when the function finishes executing, it will resume execution at the address.
Stacks can also be useful in many other cases. For example:
- During recursion or when using nested program constructs.
- Reversing the contents of a list
- Syntax parsing

Stacks can be implemented with either an array or a linked list, with each operation being O(1).

---