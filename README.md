## Data Structures

---

### Arrays

- Contiguous area of memory consisting of equal size elements indexed by contiguous integers.
- Constant-time access to read and write.

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

#### Common operations performed on arrays

|         | Add  | Remove |
|---------|------|--------|
| Start   | O(n) | O(n)   |
| Middle  | O(n) | O(n)   |
| End     | O(1) | O(1)   |

#### Static and Dynamically allocated arrays

- Static arrays will need to determine a size at compile time, dynamically allocated arrays can partly solve this problem by allocating a size at runtime.

#### Dynamic arrays
- Dynamic arrays work by storing a pointer to a dynamically allocated array, replacing it with a newly allocated array as needed (copying over old data, and creating a new pointer).
- Dynamic arrays are an abstract data type with `Get(i)`, `Set(i, val)`, `PushBack(val)`, `Remove(i)`, `Size()` operations (as a minimum).
- They need to store the following:
  - A dynamically-allocated array.
  - A capacity (size of the current dynamically allocated array).
  - Size (number of elements currently stored).

#### Jagged arrays

An array of arrays where the elements of the arrays can be of various lengths.

