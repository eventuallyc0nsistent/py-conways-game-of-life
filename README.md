Game of life in python and Java
===============================

__The rules of life say:__

+ A living cell with two or three neighboring living cells survives into the next generation. A living cell with fewer than two or more than three living neighbors will die.
+ Each empty/dead cell that has exactly three living neighbors--no more, no fewer--is a birth cell. It will be alive in the next generation.


+ ###Read the input file :
  Determine the input rows and columns from the __life.txt__ file in the source. From there for each line, find the number of alive cells into a list.

+ ###Get neighbor cells :
  For each alive cell determine the neighboring cell positions into a list.

+ ###Alive cells :
  For every alive cell find the alive cells in the neighbor into a list. Also determine the cells that will be alive in the next iteration.

+ ###Dead cells :
  For every alive cell determine the cells that will be dead in the next iteration.
